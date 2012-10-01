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

package com.rusketh.creator.exceptions;

import org.bukkit.entity.Player;

import com.rusketh.creator.blocks.CreatorItemStack;
import com.rusketh.util.CreatorString;


@SuppressWarnings( "serial" )
public class CmdException extends RuntimeException {
	
	public CmdException(String message) {
		builder.append(message);
	}
	
	public CmdException(String... message) {
		builder.append(message);
	}
	
	/*========================================================================================================*/
	
	public CmdException append(String message) {
		builder.append(message);
		return this;
	}
	
	public CmdException append(String... message) {
		builder.append(message);
		return this;
	}
	
	/*========================================================================================================*/
	
	public CmdException append(int number) {
		builder.append(number);
		return this;
	}
	
	public CmdException append(Player player) {
		builder.append(player);
		return this;
	}
	
	public CmdException append(CreatorItemStack item) {
		builder.append(item);
		return this;
	}
	
	/*========================================================================================================*/
	
	public String getMessage() {
		return builder.toString( );
	}
	
	/*========================================================================================================*/
	
	CreatorString builder = new CreatorString();
}