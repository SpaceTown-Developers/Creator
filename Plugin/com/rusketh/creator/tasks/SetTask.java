package com.rusketh.creator.tasks;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.rusketh.creator.blocks.RandomBlockArray;
import com.rusketh.creator.tasks.selection.Selection;
import com.rusketh.util.CreatorString;

public class SetTask extends Task {
	
	public SetTask( TaskSession session, World world, int rate ) {
		super(session, world, rate);
		setSelection(session.getSelection().clone());
	}
	
	/*========================================================================================================*/
	
	public boolean runTask() {
		Block block = selection.nextBlock();
		if (block == null ) return true;
		
		getSession().getCreator().logger.info("Changed block at - ?".replace("?", block.getLocation().toVector().toString()));
		queBlock(block, blocks.next() );
		return false;
	}
	
	/*========================================================================================================*/
	
	public void setSelection(Selection selection) {
		selection.first();
		this.selection = selection;
	}
	
	public Selection getSelection() {
		return selection;
	}
	
	public void setBlocks(RandomBlockArray blocks) {
		this.blocks = blocks;
	}
	
	/*========================================================================================================*/
	
	public boolean finish() {
		Player player = getSession().getPlayer();
		if ( player != null ) player.sendMessage( new CreatorString("%gSuccessfully changed '%b").append(getCount()).append("'%g blocks.").toString() );
			
		return true;
	}
	
	/*========================================================================================================*/
	
	private Selection selection;
	private RandomBlockArray blocks;
	
}
