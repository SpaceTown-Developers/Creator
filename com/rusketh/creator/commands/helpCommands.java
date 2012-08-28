package com.rusketh.creator.commands;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rusketh.creator.creatorPlugin;
import com.rusketh.creator.commands.manager.Perameters;
import com.rusketh.creator.commands.manager.command;
import com.rusketh.creator.commands.manager.commandAnote;

public class helpCommands {
	
	public helpCommands( creatorPlugin plugin ) {
		this.plugin = plugin;
	}
	
	/*========================================================================================================*/
	
	@commandAnote( names = { "help" }, example = "/cr help [cmd]", desc = "Get help with a command.", least = 0, most = 1, console = true )
	public boolean helpCommand( Perameters perams ) {
		
		if ( perams.perams( ) == 1 ) {
			command helpWith = plugin.getCommandManager( ).getCommand( perams.getPeram( 0 ) );
			
			if ( helpWith != null ) {
				perams.getSender( ).sendMessage( helpWith.getHelp( ) );
			} else {
				throw new CommandException( "That command does not exist." );
			}
			
		} else {
			for ( command helpWith : plugin.getCommandManager( ).getCommands( ) ) {
				CommandSender sender = perams.getSender( );
						
				if ( !( sender instanceof Player ) || helpWith.hasPermission( (Player) sender ) ) {
					sender.sendMessage( helpWith.getHelp( ) );
				}
			}
		}
		
		return true;
	}
	
	/*========================================================================================================*/
	
	private creatorPlugin	plugin;
	
}
