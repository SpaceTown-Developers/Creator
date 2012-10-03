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

import org.bukkit.entity.Player;

import com.rusketh.creator.CreatorPlugin;
import com.rusketh.creator.exceptions.CmdException;
import com.rusketh.creator.exceptions.MaxBlocksChangedException;
import com.rusketh.creator.masks.Mask;
import com.rusketh.creator.tasks.selection.BoxSelection;
import com.rusketh.creator.tasks.selection.Selection;
import com.rusketh.util.CreatorString;

public class TaskSession {
	
	public TaskSession( CreatorPlugin plugin, Player player ) {
		this.plugin = plugin;
		this.player = player;
		
		selection = new BoxSelection( player.getWorld( ) );
		
		blockRate = plugin.BlockRate;
		maxBlocks = plugin.MaxBlocks;
		if ( player.hasPermission( "creator.nolimit.maxblocks" ) ) maxBlocks = -1;
		
	}
	
	/*========================================================================================================*/
	
	public void setPlayer( Player player ) {
		this.player = player;
	}
	
	public Player getPlayer( ) {
		return player;
	}
	
	/*========================================================================================================*/
	
	public void setSelection( Selection selection ) {
		this.selection = selection;
	}
	
	public Selection getSelection( ) {
		return selection;
	}
	
	/*========================================================================================================*/
	
	/** WARNING: Make sure the current task has finished safly first. */
	public void setTask(Task task) {
		this.task = task;
	}
	
	public boolean taskRunning() {
		return task != null;
	}
	
	/*========================================================================================================*/
	
	public int getBlockRate( ) {
		return blockRate;
	}
	
	public void setBlockRate( int blockRate ) {
		this.blockRate = blockRate;
	}
	
	public int getMaxBlocks( ) {
		return maxBlocks;
	}
	
	/*========================================================================================================*/
	
	public void setMask(Mask mask) {
		this.mask = mask;
	}
	
	public Mask getMask() {
		return mask;
	}
	
	/*========================================================================================================*/
	
	public boolean startTask( Task newTask, boolean useMask ) {
		if ( task != null ) return false;
		
		if ( useMask && mask != null ) task.setMask( mask );
		
		if ( player.hasPermission( "creator.useInventory" ) ) task.setBag( new TaskBag( player ) );
		
		return true;
	}
	
	/*========================================================================================================*/
	
	public void stopTask( ) {
		if ( task == null ) return;
		task.stopTask( );
		addUndo(task);
		task = null;
	}
	
	/*========================================================================================================*/
	
	public void runTask() {
		if ( task != null ) try {
			if ( task.run( ) ) stopTask();
			
		} catch ( MaxBlocksChangedException e ) {
			player.sendMessage( new CreatorString("%rOperation aborted - Maxamum blocks changed. (%R").append( task.counter ).append(" blocks changed%r)").toString() );
			stopTask( );
			
		} catch ( Exception e ) {
			player.sendMessage( new CreatorString("%rOperation aborted - Somthing whent wrong. (%R").append( task.counter ).append(" blocks changed%r)").toString());
			stopTask( );
		}
	}
	
	/*========================================================================================================*/
	
	public void addUndo(Task task) {
		if ( task instanceof UndoTask ) {
			undoPos--;
		} else if ( task instanceof RedoTask ) {
			undoPos++;
		} else {
			undoPos++;
			undoQue.set( undoPos, task );
			for (int i = undoPos + 1; i < undoQue.size( ); i++) undoQue.set( i, null );
		}
		
		
		if (undoQue.size( ) > 10) {
			undoQue.remove( 0 );
			undoPos--; //Think this should work.
		}
	}
	
	public boolean undo() {
		if (undoPos == 0) throw new CmdException("%rNothing to undo.");
		return startTask(undoQue.get( undoPos ), false);
	}
	
	public boolean redo() {
		if ( task != null ) return false;
		Task newTask = undoQue.get( undoPos + 1 );
		
		if (newTask == null) throw new CmdException("%rNothing to redo.");
		return startTask(newTask, false);
	}
	
	/*========================================================================================================*/
	
	public boolean chargePrice( int price ) {
		if ( !plugin.Vault ) return true;
		return plugin.getEconomy( ).withdrawPlayer( player.getName( ), price ).transactionSuccess( );
	}
	
	/*========================================================================================================*/
	
	private CreatorPlugin		plugin;
	private Player				player;
	
	private int					maxBlocks;
	private int					blockRate;
	
	private Selection			selection;
	private Mask				mask;
	private Task				task;
	
	private int					undoPos = 0;
	private ArrayList< Task >	undoQue = new ArrayList< Task >();
	
}
