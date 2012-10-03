package com.rusketh.creator.tasks;

import org.bukkit.World;
import org.bukkit.block.Block;
import com.rusketh.creator.blocks.RandomBlockArray;
import com.rusketh.creator.tasks.selection.Selection;

public class SetTask extends Task {
	
	public SetTask( TaskSession session, World world, int rate ) {
		super(session, world, rate);
		selection = session.getSelection().clone();
	}
	
	/*========================================================================================================*/
	
	public boolean runTask() {
		Block block = selection.nextBlock();
		if (block == null ) return true;
		
		queBlock(block, blocks.next() );
		return true;
	}
	
	/*========================================================================================================*/
	
	public void setSelection(Selection selection) {
		this.selection = selection;
	}
	
	public Selection getSelection() {
		return selection;
	}
	
	public void setBlocks(RandomBlockArray blocks) {
		this.blocks = blocks;
	}
	
	/*========================================================================================================*/
	
	private Selection selection;
	private RandomBlockArray blocks;
	
}
