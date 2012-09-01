/*
 * Creator - Bukkit Plugin
 * Copyright (C) 2012 Rusketh <www.Rusketh.com>
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

package com.rusketh.creator.commands.manager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.server.CommandException;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.rusketh.creator.creatorPlugin;

public class commandManager implements Listener {
	
	public commandManager( creatorPlugin plugin ) {
		this.plugin = plugin;
		
		commands = new HashMap< String, command >( );
		leastCommands = new ArrayList< command >( );
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/*========================================================================================================*/
	
	public void registerCommands( Object register ) {
		for ( Method method : register.getClass( ).getMethods( ) ) {
			commandAnote anote = method.getAnnotation( commandAnote.class );
			
			if ( anote != null ) {
				
				if ( anote.names( ).length == 0 ) {
					plugin.logger.info( "Creator has found an unnamed command" );
					continue;
				}
				
				command command = new command( this.plugin, anote, register, method );
				
				leastCommands.add( command );
				
				for ( String name : anote.names( ) ) {
					commands.put( name, command );
				}
			}
		}
	}
	
	/*========================================================================================================*/
	
	public ArrayList< command > getCommands( ) {
		return leastCommands;
	}
	
	public command getCommand( String name ) {
		return commands.get( name );
	}
	
	/*========================================================================================================*/
	
	public boolean run( CommandSender sender, String[] args ) throws CommandException {
		if ( args.length > 0 ) {
			command command = commands.get( args[0] );
			
			if ( command != null ) {
				return command.execute( plugin, sender, args );
			}
		}
		
		return false;
	}
	
	/*========================================================================================================*/
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void PlayerChat(AsyncPlayerChatEvent event) {
		if (plugin.cmdUse && event.getMessage( ).toLowerCase( ).startsWith( plugin.cmdPrefix )) {
			boolean result = run( event.getPlayer( ), event.getMessage( ).substring( plugin.cmdPrefix.length( ) ).split( " " ) );
			if (result) event.setCancelled( true );
		}
	}
	
	/*========================================================================================================*/
	
	creatorPlugin						plugin;
	
	private HashMap< String, command >	commands;
	private ArrayList< command >		leastCommands;
}
