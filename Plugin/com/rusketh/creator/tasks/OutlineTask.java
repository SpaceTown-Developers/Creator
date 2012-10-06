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
			
				queBlock(getWorld().getBlockAt(indexX, min.getBlockY(), indexZ), blocks.next(), true);
				
				if ( indexX > max.getBlockX() ) {
					indexX = min.getBlockX();
					
					if ( indexZ > max.getBlockY() ) {
						indexZ = min.getBlockY();
						doneBottom = true;
						
					} else indexZ++;
				} else indexX++;
			
			} else {
				
				queBlock(getWorld().getBlockAt(indexX, max.getBlockY(), indexZ), blocks.next(), true);
				
				if ( indexX > max.getBlockX() ) {
					indexX = min.getBlockX();
					
					if ( indexZ > max.getBlockY() ) {
						indexZ = min.getBlockY();
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
