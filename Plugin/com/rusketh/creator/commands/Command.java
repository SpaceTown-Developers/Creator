/*
 * Creator - Bukkit Plugin
 * Copyright (C) 2012 Rusketh & Oskar94 <www.Rusketh.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.rusketh.creator.commands;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.rusketh.creator.CreatorPlugin;
import com.rusketh.creator.exceptions.CmdException;
import com.rusketh.util.CreatorString;

public class Command {
	
	public Command( CreatorPlugin plugin, CreateCommand anote, Object baseClass, Method method ) {
		this.plugin = plugin;
		this.baseClass = baseClass;
		this.method = method;
		
		this.name = anote.names( )[0];
		this.example = anote.example( );
		this.desc = anote.desc( );
		this.least = anote.least( );
		this.most = anote.most( );
		this.player = anote.player( );
		this.console = anote.console( );
		this.perms = anote.perms( );
		
		this.flags = new HashMap< Character, Boolean >( );
		
		for ( String flag : anote.flags( ) ) {
			if ( flag.length( ) == 1 ) {
				this.flags.put( flag.charAt( 0 ), false );
			} else if ( flag.length( ) == 2 && flag.charAt( 1 ) == '*' ) {
				this.flags.put( flag.charAt( 0 ), true );
			} else {
				plugin.logger.warning( new StringBuilder( "[Creator] invalid flag '" ).append( flag ).append( "' for command " ).append( this.name ).toString( ) );
			}
		}
		
		YamlConfiguration config = (YamlConfiguration) plugin.getCommandManager( ).getConfig( );
		
		if ( !config.isConfigurationSection( this.name ) ) {
			config.set( this.name + ".enabled", true );
			
			if ( anote.usePrice( ) != -1 ) {
				config.set( this.name + ".price", anote.usePrice( ) );
			}
			
			if ( anote.blockPrice( ) != -1 ) {
				config.set( this.name + ".blockprice", anote.blockPrice( ) );
			}
			
			plugin.getCommandManager( ).saveConfig( );
		}
		
		this.enabled = config.getBoolean( this.name + ".enabled" );
		this.usePrice = config.getInt( this.name + ".price" );
		this.blockPrice = config.getInt( this.name + ".blockprice" );
		
	}
	
	/*========================================================================================================*/
	
	public ConfigurationSection getConfig( ) {
		return plugin.getCommandManager( ).getConfig( ).getConfigurationSection( this.name );
	}
	
	/*========================================================================================================*/
	
	public boolean execute( CreatorPlugin plugin, CommandSender sender, String[] args ) {
		if ( !this.enabled ) {
			sender.sendMessage( "This command has been disabled." );
			return false;
		}
		
		CommandInput input = new CommandInput( args, flags );
		
		int size = input.size( );
		
		// Note: Going to use string builders here, Java is shit at combining strings with out them!
		if ( size < this.least && this.least != -1 ) {
			sender.sendMessage( new CreatorString( "%rNot enogh perameters. [%R" ).append( size ).append( "/" ).append( this.least ).append( "%r]\n", this.example ).toString( ) );
			return true;
		} else if ( size > this.most && this.most != -1 ) {
			sender.sendMessage( new CreatorString( "%rToo meany perameters. [%R" ).append( size ).append( "/" ).append( this.most ).append( "%r]\n", this.example ).toString( ) );
			return true;
		}
		
		boolean isPlayer = ( sender instanceof Player );
		
		if ( isPlayer && !this.player ) {
			sender.sendMessage( new CreatorString("%rThis command can not be called by players.").toString() );
			return true;
		} else if ( !isPlayer && !this.console ) {
			sender.sendMessage( "This command can not be called from console.");
			return true;
		}
		
		if ( isPlayer ) { // Note: Check permissions?
			Player player = (Player) sender;
			
			if ( !hasPermission( player ) ) { // Note: Player is not allowed.
				sender.sendMessage( "Sorry but you do not have permission to do that." );
				return true;
			}
			
			// Note: Check vault is enabled and that player could be charged for this.
			if ( plugin.Vault && !player.hasPermission( "creator.commands.free" ) && !player.hasPermission( new StringBuilder( "creator.commands." ).append( this.name ).append( ".free" ).toString( ) ) ) {
				
				// Note: Check vault prices.
				if ( this.usePrice > 0 && plugin.getEconomy( ).getBalance( player.getName( ) ) < this.usePrice ) {
					sender.sendMessage( "Sorry but you can not afford to do that." );
					return true;
				}
			}
		}
		
		input.setCommand( this );
		
		return invoke( sender, input );
	}
	
	/*========================================================================================================*/
	
	public boolean invoke( CommandSender sender, CommandInput input ) {
		try {
			return (Boolean) this.method.invoke( this.baseClass, sender, input );
			
		} catch ( Throwable e ) {
			Throwable cause = e.getCause();
			
			if ( cause != null ) {
				
				if ( cause.getClass( ).equals( CmdException.class ) || cause.getClass( ).equals( CommandException.class )) {
					if ( e.getMessage( ) != null ) sender.sendMessage(e.getMessage( ));
					return true;
				}
				plugin.logger.info( "Creator failed to invoke command ?".replace( "?", this.name ) );
				plugin.debug(cause);
				
			} else {
				plugin.logger.info( "Creator failed to invoke command ?".replace( "?", this.name ) );
				plugin.debug(e);
			}
			
			sender.sendMessage("Oooops, somthing just failed epicly." );
			return true;
		}
	}
	
	/*========================================================================================================*/
	
	public boolean hasPermission( Player player ) {
		if ( this.perms.length == 0 ) { return true; // No permission nodes.
		}
		
		boolean allowed = false;
		
		for ( String perm : this.perms ) {
			if ( player.hasPermission( perm ) ) {
				allowed = true;
				break;
			}
		}
		
		return allowed;
	}
	
	/*========================================================================================================*/
	
	public String getHelp( ) {
		
		CreatorString msg = new CreatorString( "%y", this.example, " " );
		
		if ( this.usePrice > 0 ) { // Add price data
			msg.append( "(%r" ).append( this.usePrice ).append( plugin.getEconomy( ).currencyNameSingular( ) , "%y)" );
		}
		
		return msg.append( "\n %b", this.desc ).toString( );
	}
	
	/*========================================================================================================*/
	
	private CreatorPlugin				plugin;
	private Object						baseClass;
	private Method						method;
	
	private String						name;
	private String						example;
	private String						desc;
	
	private int							least;
	private int							most;
	public int							usePrice;
	public int							blockPrice;
	
	private boolean						player;
	private boolean						console;
	private boolean						enabled;
	
	private Map< Character, Boolean >	flags;
	private String[]					perms;
	
}
