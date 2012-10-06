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

import org.bukkit.World;
import org.bukkit.util.Vector;

import com.rusketh.creator.blocks.RandomBlockArray;
import com.rusketh.creator.exceptions.CmdException;
import com.rusketh.creator.tasks.selection.BoxSelection;
import com.rusketh.creator.tasks.selection.Selection;

public class OutlineTask extends Task {

	String taskName() {
		return wallsOnly ? "Wall Task" : "Outline Task";
	}

	/*========================================================================================================*/
	
	public OutlineTask(TaskSession session, World world, int rate) {
		super(session, world, rate);
		setSelection((BoxSelection) session.getSelection());
	}
	
	/*========================================================================================================*/
	
	public void setSelection(Selection selection) {
		if ( !(selection instanceof BoxSelection) ) throw new CmdException("%Selection uncompatabe.\nCuboid selection required.");
		
		this.selection = selection;
		
		Vector min = selection.getMin();
		indexX = min.getBlockX();
		indexY = min.getBlockY();
		indexZ = min.getBlockZ();
	}
	
	public Selection getSelection() {
		return selection;
	}
	
	public void setBlocks(RandomBlockArray blocks) {
		this.blocks = blocks;
	}
	
	public void setWallsOnly() {
		wallsOnly = true;
	}
	
	/*========================================================================================================*/
	
	public boolean runTask() {
		int count = getCount();
		for (int i = count; i < count + getRate(); i++) {
			if ( build() ) return true;
		}
		
		return false;
	}
	
	/*========================================================================================================*/
	
	private boolean build() {
		Vector min = selection.getMin();
		Vector max = selection.getMax();
		
		if ( wallsOnly || doneTop ) {
			if ( !doneX ) {
				//Build Front & Back Wall
				
				queBlock(getWorld().getBlockAt(min.getBlockX(), indexY, indexZ), blocks.next(), true);
				queBlock(getWorld().getBlockAt(max.getBlockX(), indexY, indexZ), blocks.next(), true);
				
				if ( indexX > max.getBlockX() ) {
					indexX = min.getBlockX();
					
					if ( indexY > max.getBlockY() ) {
						indexY = min.getBlockY();
						doneX = true;
						
					} else indexY++;
				} else indexX++;
				
			} else {
				//Build Left & Right Wall
				
				queBlock(getWorld().getBlockAt(indexX, indexY, min.getBlockZ()), blocks.next(), true);
				queBlock(getWorld().getBlockAt(indexX, indexY, max.getBlockZ()), blocks.next(), true);
				
				if ( indexZ > max.getBlockZ() ) {
					indexZ = min.getBlockZ();
					
					if ( indexY > max.getBlockY() ) {
						indexY = min.getBlockY();
						return true;
						
					} else indexY++;
				} else indexZ++;

			}
			
		} else {
			
			if ( !doneBottom ) { 
				//Build Floor.
			
				queBlock(getWorld().getBlockAt(indexX, min.getBlockY(), indexZ), blocks.next(), true);
				
				if ( indexX > max.getBlockX() ) {
					indexX = min.getBlockX();
					
					if ( indexZ > max.getBlockZ() ) {
						indexZ = min.getBlockZ();
						doneBottom = true;
						
					} else indexZ++;
				} else indexX++;
			
			} else {
				//Build Roof
				
				queBlock(getWorld().getBlockAt(indexX, max.getBlockY(), indexZ), blocks.next(), true);
				
				if ( indexX > max.getBlockX() ) {
					indexX = min.getBlockX();
					
					if ( indexZ > max.getBlockZ() ) {
						indexZ = min.getBlockZ();
						doneTop = true;
						
					} else indexZ++;
				} else indexX++;
			}
		}
		
		return false;
	}
		
		
	/*========================================================================================================*/
	
	Selection selection;
	private RandomBlockArray blocks;
	
	private boolean doneX = false;
	private boolean doneBottom = false;
	private boolean doneTop = false;
	
	private boolean wallsOnly = false;
	private int indexX, indexY, indexZ;
}
