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


package com.rusketh.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.rusketh.creator.blocks.CreatorItemStack;


public class CreatorString {
	
	public CreatorString(String message) {
		append(message);
	}
	
	public CreatorString(String... message) {
		for (String string : message) append(string);
	}
	
	/*========================================================================================================*/
	
	public CreatorString append(String message) {
		message = message.replace("%r", ChatColor.RED.toString());
		message = message.replace("%R", ChatColor.DARK_RED.toString());
		
		message = message.replace("%y", ChatColor.YELLOW.toString());
		message = message.replace("%Y", ChatColor.GOLD.toString());
		
		message = message.replace("%g", ChatColor.GREEN.toString());
		message = message.replace("%G", ChatColor.DARK_GREEN.toString());
		
		message = message.replace("%c", ChatColor.AQUA.toString());
		message = message.replace("%C", ChatColor.DARK_AQUA.toString());
		
		message = message.replace("%b", ChatColor.BLUE.toString());
		message = message.replace("%B", ChatColor.DARK_BLUE.toString());
		
		message = message.replace("%p", ChatColor.LIGHT_PURPLE.toString());
		message = message.replace("%P", ChatColor.DARK_PURPLE.toString());
		
		message = message.replace("%a", ChatColor.GRAY.toString());
		message = message.replace("%A", ChatColor.DARK_GRAY.toString());
		
		message = message.replace("%w", ChatColor.WHITE.toString());
		message = message.replace("%W", ChatColor.BLACK.toString());
		
		message = message.replace("%m", ChatColor.MAGIC.toString());
		
		message = message.replace("%B", ChatColor.BOLD.toString());
		message = message.replace("%s", ChatColor.STRIKETHROUGH.toString());
		message = message.replace("%u", ChatColor.UNDERLINE.toString());
		message = message.replace("%i", ChatColor.ITALIC.toString());
		
		message = message.replace("%x", ChatColor.RESET.toString());
		
		builder.append(message);
		
		return this;
	}
	
	public CreatorString append(String... message) {
		for (String string : message) append(string);
		return this;
	}
	
	/*========================================================================================================*/
	
	public CreatorString append(int number) {
		builder.append(number);
		return this;
	}
	
	public CreatorString append(Player player) {
		builder.append(player.getDisplayName( ));
		return this;
	}
	
	public CreatorString append(CreatorItemStack item) {
		builder.append(item.niceName( ));
		return this;
	}
	
	/*========================================================================================================*/
	
	public String toString() {
		return builder.toString( );
	}
	
	/*========================================================================================================*/
	
	StringBuilder builder = new StringBuilder();
}
