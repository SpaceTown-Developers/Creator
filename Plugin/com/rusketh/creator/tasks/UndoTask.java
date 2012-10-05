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

package com.rusketh.creator.tasks;

import org.bukkit.entity.Player;

import com.rusketh.creator.blocks.StoredBlock;
import com.rusketh.util.CreatorString;


public class UndoTask extends Task {
	
	public UndoTask(Task task) {
		super( task.getSession( ), task.getWorld( ), task.getRate( ) );
		this.task = task;
	}
	
	/*========================================================================================================*/
	
	public boolean runTask( ) {
		for (int i = 1; i < getRate(); i++) {
			StoredBlock block = task.getUndoArray( ).get( pos );
			
			if (block == null) {
				return true;
				
			} else if ( block.isSpecial( ) ) {
				block.pushState( block.getBlock( ) );
				counter++;
				
			} else {
				queBlock(block.getBlock( ), block.getTypeId( ), block.getDataByte( ), true);
			}
		}
		
		return false;
	}
	
	/*========================================================================================================*/
	
	public boolean finish() {
		Player player = getSession().getPlayer();
		if ( player != null ) player.sendMessage( new CreatorString("%gSuccessfully undone '%b").append(getCount()).append("'%g changes.").toString() );
			
		return true;
	}
	
	/*========================================================================================================*/
	
	public int undoCount() {
		return task.getUndoArray( ).size();
	}
	
	/*========================================================================================================*/
	
	private Task task;
	
	private int pos = 0;
}