package com.rusketh.creator.commands.manager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.server.CommandException;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.rusketh.creator.creatorPlugin;

public class commandManager {
	
	public commandManager( creatorPlugin plugin ) {
		this.plugin = plugin;
		
		commands = new HashMap< String, command >( );
		leastCommands = new ArrayList< command >( );
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
	
	public boolean onCommand( CommandSender sender, String commandName, String[] perams ) {
		boolean sucess = false;
		
		if ( commandName.equalsIgnoreCase( "creator" ) || commandName.equalsIgnoreCase( "cr" ) ) {
			if ( perams.length > 0 ) {
				command command = commands.get( perams[0] );
				
				if ( command != null ) {
					try {
						sucess = command.execute( plugin, sender, perams );
					} catch ( CommandException e ) {
						throw new CommandException( new StringBuilder( ChatColor.RED.toString( ) ).append( e.getMessage( ) ).toString( ) );
					}
				}
			}
		}
		
		return sucess;
	}
	
	/*========================================================================================================*/
	
	creatorPlugin						plugin;
	
	private HashMap< String, command >	commands;
	private ArrayList< command >		leastCommands;
}
