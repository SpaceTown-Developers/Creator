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

import java.util.HashSet;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import com.rusketh.creator.blocks.CreatorItem;
import com.rusketh.creator.commands.CommandInput;
import com.rusketh.creator.commands.CreateCommand;
import com.rusketh.creator.exceptions.CmdException;
import com.rusketh.creator.tasks.TaskSession;
import com.rusketh.creator.tasks.selection.BoxSelection;
import com.rusketh.creator.tasks.selection.Selection;
import com.rusketh.util.CreatorString;
import com.rusketh.util.Direction;

public class SelectionExtension extends Extension {
	
	public String name() {
		return "core.selection";
	}
	
	/*========================================================================================================*/
	
	@EventHandler
	public void wandEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if ( !wandUsers.contains(player.getName()) || event.getItem() == null || event.getItem().getTypeId() != plugin.WandID ) return;
		
		plugin.getTaskManager().getSession(player).getSelection().wandEvent(player, event.getClickedBlock(), event.getAction());	
	}
	
	/*========================================================================================================*/
	
	@CreateCommand( names = { "wand" }, example = "wand [<T/F> -p:<player>]", desc = "Enable Wand useage.", least = 0, most = 1, console = false, flags = { "p*" }, perms = { "creator.selection.wand" } )
	public boolean wandCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		
		if ( input.flag('p') ) {
			if ( !player.hasPermission("creator.wand.other") ) throw new CmdException( "%rYou are not allowed to control another players wand." );
			player = plugin.getServer( ).getPlayer( input.flagString( 'p' ) );
			if ( player == null ) throw new CmdException( "%rPlayer was not found." );
		}
		
		if ( input.argBool( 0, !wandUsers.contains(player.getName()) ) ) {
			wandUsers.add(player.getName());
			player.sendMessage("Wand mode activated (?).".replace("?", CreatorItem.get(plugin.WandID).getName()) );
		} else {
			wandUsers.remove(player.getName());
			player.sendMessage("Wand mode deactivated.");
		}
		
		return true;
	}
	
/*========================================================================================================*/
	
	@CreateCommand( names = { "expand", "ex" }, example = "expand <Lengh> [<Lengh>/<dir>]", desc = "Expands your region.", least = 1, most = 2, console = false, perms = { "creator.selection.expand" } )
	public boolean expandCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		Selection selection = plugin.getTaskManager().getSession(player).getSelection();
		
		if ( !selection.isValid() ) throw new CmdException("%rMake a valid selection first.");
		
		int count;
		
		if ( input.size() == 1 ) {
			count = selection.expand(Direction.getDirection(player), input.argInt(0) );
		} else {
			Direction dir = Direction.getDirection(player, input.arg(1));
			if (dir == null) {
				count = selection.expand(Direction.getDirection(player, 180), input.argInt(1) );
			} else {
				count = selection.expand(dir, input.argInt(0) );
			}
		}
		
		player.sendMessage(new CreatorString("%gSelection expanded %b").append(count).append(" %g blocks.").toString() );
		return true;
	}
	
	/*========================================================================================================*/
	
	@CreateCommand( names = { "shift" }, example = "shift <Lengh> [dir]", desc = "Shifts your region.", least = 1, most = 2, console = false, perms = { "creator.selection.shift" } )
	public boolean shiftCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		Selection selection = plugin.getTaskManager().getSession(player).getSelection();
		
		if ( !selection.isValid() ) throw new CmdException("%rMake a valid selection first.");
		
		if ( input.size() == 1 ) {
			selection.shift(Direction.getDirection(player), input.argInt(0) );
		} else {
			Direction dir = Direction.getDirection(player, input.arg(1));
			if (dir == null) {
				selection.shift(Direction.getDirection(player, 180), input.argInt(1) );
			} else {
				selection.shift(dir, input.argInt(0) );
			}
		}
		
		player.sendMessage(new CreatorString("%gSelection shifted %b").append(input.argInt(0)).append(" %g blocks.").toString() );
		return true;
	}
	
	/*========================================================================================================*/
	
	@CreateCommand( names = { "selection", "sel" }, example = "selection <clear>", desc = "Sets your region type.", least = 1, most = 1, console = false, perms = { "creator.selection" } )
	public boolean selectionCommand( CommandSender sender, CommandInput input ) {
		Player player = (Player) sender;
		TaskSession session = plugin.getTaskManager().getSession(player);
		
		switch( input.arg(0) ) {
			case "clear":
				Selection selection = session.getSelection();
				if ( selection.isValid() ) selection.reset();
				player.sendMessage("%gYour selection has been cleared.");
			
			case "cube": case "cuboid": case "box":
				session.setSelection(new BoxSelection( player.getWorld( ) ) );
				player.sendMessage("%gCuboid selection mode selected.");
				
			//TODO: More selection types.
		}
		
		return true;
	}
	
	/*========================================================================================================*/
	
	
	/*========================================================================================================*/
	
	HashSet<String> wandUsers = new HashSet<String> ();
}
