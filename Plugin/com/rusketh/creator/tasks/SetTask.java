package com.rusketh.creator.tasks;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.rusketh.creator.blocks.RandomBlockArray;
import com.rusketh.creator.tasks.selection.Selection;
import com.rusketh.util.CreatorString;

public class SetTask extends Task {
	
	public String taskName() {
		return "Set Task";
	}
	
	/*========================================================================================================*/
	
	public SetTask( TaskSession session, World world, int rate ) {
		super(session, world, rate);
		setSelection(session.getSelection().clone());
	}
	
	/*========================================================================================================*/
	
	public boolean runTask() {
		for (int i = 0; (i < getRate() && selection.hasNextBlock()); i++) {
			queBlock(selection.nextBlock(), blocks.next(), true);
		}
		return !selection.hasNextBlock();
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
		if ( player != null ) player.sendMessage( new CreatorString("%ySet Task Completed:\n%gBlocks changed: %b").append(getCount()).append("\n%gTime Taken: %b",toTime(getTimeConsumed())).toString() );
		return true;
	}
	
	public boolean report() {
		Player player = getSession().getPlayer();
		if ( player != null ) player.sendMessage( new CreatorString("%ySet Task Status:\n%gBlocks changed: %b").append(getCount()).append("\n%gCurrent Time: %b",toTime(getTimeConsumed())).toString() );
		return true;
	}
	
	/*========================================================================================================*/
	
	private Selection selection;
	private RandomBlockArray blocks;
	
}
