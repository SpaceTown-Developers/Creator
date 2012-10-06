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

import java.util.ArrayList;

import com.rusketh.creator.blocks.StoredBlock;


public class UndoTask extends Task {
	
	public String taskName() {
		return "Undo Task";
	}
	
	/*========================================================================================================*/
	
	public UndoTask(Task task) {
		super( task.getSession( ), task.getWorld( ), task.getRate( ) );
		queue = task.getUndoArray();
		setBlockPrice(-task.getBlockPrice());
	}
	
	/*========================================================================================================*/
	
	public boolean runTask( ) {
		for (int i = 1; i < getRate(); i++) {
			if ( pos >= queue.size() ) return true;
			StoredBlock block = queue.get( pos++ );
			
			if ( block.isSpecial( ) ) {
				if ( block.pushState(block.getBlock()) )counter++;
			} else {
				queBlock(block.getBlock( ), block.getTypeId( ), block.getDataByte( ), true);
			}
		}
		
		return false;
	}
	
	/*========================================================================================================*/
	
	public int undoCount() {
		return queue.size();
	}
	
	/*========================================================================================================*/
	
	private ArrayList<StoredBlock> queue;
	private int pos = 0;
}