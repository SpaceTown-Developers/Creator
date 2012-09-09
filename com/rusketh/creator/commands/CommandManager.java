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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.server.CommandException;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.rusketh.creator.CreatorPlugin;

public class CommandManager implements Listener {
	
	public CommandManager( CreatorPlugin plugin ) {
		this.plugin = plugin;
		
		initializeConfig( );
		
		commands = new HashMap< String, Command >( );
		leastCommands = new ArrayList< Command >( );
		
		plugin.getServer( ).getPluginManager( ).registerEvents( this, plugin );
	}
	
	/*========================================================================================================*/
	
	public void registerCommands( Object register ) {
		for ( Method method : register.getClass( ).getMethods( ) ) {
			CreateCommand anote = method.getAnnotation( CreateCommand.class );
			
			if ( anote != null ) {
				
				if ( anote.names( ).length == 0 ) {
					plugin.logger.info( "Creator has found an unnamed command" );
					continue;
				}
				
				Command command = new Command( this.plugin, anote, register, method );
				
				leastCommands.add( command );
				
				for ( String name : anote.names( ) ) {
					commands.put( name, command );
				}
			}
		}
	}
	
	/*========================================================================================================*/
	
	public ArrayList< Command > getCommands( ) {
		return leastCommands;
	}
	
	public Command getCommand( String name ) {
		return commands.get( name );
	}
	
	/*========================================================================================================*/
	
	public boolean run( CommandSender sender, String[] args ) throws CommandException {
		if ( args.length > 0 ) {
			Command command = commands.get( args[0] );
			
			if ( command != null ) { return command.execute( plugin, sender, args ); }
		}
		
		return false;
	}
	
	/*========================================================================================================*/
	
	@EventHandler( priority = EventPriority.NORMAL )
	public void PlayerChat( AsyncPlayerChatEvent event ) {
		if ( plugin.cmdUse && event.getMessage( ).toLowerCase( ).startsWith( plugin.cmdPrefix ) ) {
			boolean result = run( event.getPlayer( ), event.getMessage( ).substring( plugin.cmdPrefix.length( ) ).split( " " ) );
			if ( result ) event.setCancelled( true );
		}
	}
	
	/*========================================================================================================*/
	
	private void initializeConfig( ) {
		configFile = new File( plugin.getDataFolder( ), "commands.yml" );
		if ( !configFile.exists( ) ) {
			try {
				configFile.getParentFile( ).mkdirs( );
				configFile.createNewFile( );
			} catch ( IOException e ) {
				plugin.getLogger( ).severe( "Unable to create commands.yml: " + e.getMessage( ) );
			}
		}
		
		reloadConfig( );
	}
	
	public FileConfiguration getConfig( ) {
		return YamlConfig;
	}
	
	public void saveConfig( ) {
		try {
			YamlConfig.save( configFile );
		} catch ( Exception e ) {
			plugin.getLogger( ).info( "Failed to save commands.yml: " + e.getMessage( ) );
		}
	}
	
	public void reloadConfig( ) {
		YamlConfig = new YamlConfiguration( );
		YamlConfig.options( ).pathSeparator( '.' );
		try {
			YamlConfig.load( configFile );
			
		} catch ( Exception e ) {
			plugin.getLogger( ).warning( "Unable to load commands.yml: " + e.getMessage( ) );
		}
	}
	
	protected void refreshConfig( ) {
		try {
			YamlConfig.save( configFile );
			reloadConfig( );
		} catch ( IOException e ) {
			plugin.getLogger( ).warning( "Failed to write changed commands.yml: " + e.getMessage( ) );
		}
	}
	
	/*========================================================================================================*/
	
	CreatorPlugin						plugin;
	
	private HashMap< String, Command >	commands;
	private ArrayList< Command >		leastCommands;
	
	private File						configFile;
	private YamlConfiguration			YamlConfig;
}
