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

package com.rusketh.creator.Extensions;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rusketh.creator.commands.Command;
import com.rusketh.creator.commands.CommandInput;
import com.rusketh.creator.commands.CreateCommand;
import com.rusketh.creator.exceptions.CmdException;

public class CreatorExtension extends Extension {
	
	public String name() {
		return "core.creator";
	}
	
	/*========================================================================================================*/
	
	@CreateCommand( names = { "help" }, example = "/cr help [cmd]", desc = "Get help with a command.", least = 0, most = 1, console = true )
	public boolean helpCommand( CommandSender sender, CommandInput input ) {
		
		if ( input.size( ) == 1 ) {
			Command helpWith = plugin.getCommandManager( ).getCommand( input.arg( 0 ) );
			
			if ( helpWith != null ) {
				sender.sendMessage( helpWith.getHelp( ) );
			} else {
				throw new CmdException( "%rThat command does not exist." );
			}
			
		} else {
			for ( Command helpWith : plugin.getCommandManager( ).getCommands( ) ) {
				if ( !( sender instanceof Player ) || helpWith.hasPermission( (Player) sender ) ) {
					sender.sendMessage( helpWith.getHelp( ) );
				}
			}
		}
		
		return true;
	}
	
	/*========================================================================================================*/
	
	@CreateCommand( names = { "reload" }, example = "/cr reload", desc = "Reload Creator.", least = 0, most = 0, console = true, perms = {"creator.reload"} )
	public boolean reloadCommand( CommandSender sender, CommandInput input ) {
		
		sender.sendMessage("Reloading Creator - this might lag.");
		
		plugin.reload();
		
		sender.sendMessage("Creator has been reloaded.");
		
		return true;
	}
}
