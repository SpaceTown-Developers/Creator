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
	
	public CreatorPlugin getCreator() {
		return plugin;
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
	
	/** WARNING: Make sure the current task has finished safely first. */
	public void setTask(Task task) {
		this.task = task;
	}
	
	public Task getTask() {
		return task;
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
		
		if ( useMask && mask != null ) newTask.setMask( mask.clone() );
		
		if ( player != null && player.hasPermission( "creator.useInventory" ) ) newTask.setBag( new TaskBag( player ) );
		
		task = newTask;
				
		return true;
	}
	
	/*========================================================================================================*/
	
	public void stopTask( ) {
		if ( task == null ) return;
		task.stopTask( );
		addUndo(task);
		task = null;
		paused = false;
	}
	
	/*========================================================================================================*/
	
	public void runTask() {
		if ( task != null && !paused ) {
			try {
				if ( task.run( ) ) stopTask();
				
			} catch ( MaxBlocksChangedException e ) {
				player.sendMessage( new CreatorString("%rOperation aborted - Maxamum blocks changed. (%R").append( task.counter ).append(" blocks changed%r)").toString() );
				stopTask( );
				
			} catch ( Exception e ) {
				player.sendMessage( new CreatorString("%rOperation aborted - Somthing whent wrong. (%R").append( task.counter ).append(" blocks changed%r)").toString());
				stopTask( );
				
				plugin.debug(e);
			}
		}
	}
	
	/*========================================================================================================*/
	
	public void addUndo(Task task) {
		if ( task instanceof UndoTask ) {
			undoPos--;
		} else if ( task instanceof RedoTask ) {
			undoPos++;
		} else {
			if (undoQue.size( ) >= 10) undoQue.remove( 0 );
			
			if ( undoQue.size() - 1 < undoPos ) {
				undoQue.add(task);
			} else {
				undoQue.set( undoPos, task );
			}
			
			for (int i = undoPos; i < undoQue.size( ); i++) undoQue.set( i, null );
		}
	}
	
	public boolean undo() {
		if ( !undoQue.contains(undoPos) ) throw new CmdException("%rNothing to undo.");
		return startTask(undoQue.get( undoPos - 1 ), false);
	}
	
	public boolean redo() {
		if ( !undoQue.contains(undoPos) ) throw new CmdException("%rNothing to redo.");
		return startTask(undoQue.get( undoPos ), false);
	}
	
	/*========================================================================================================*/
	
	public boolean isTaskPaused() {
		return paused;
	}
	
	public void pauseTask() {
		paused = true;
	}
	
	public void resumeTask() {
		paused = false;
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
	
	private boolean				paused = false;
	
	private int					undoPos = 0;
	private ArrayList< Task >	undoQue = new ArrayList< Task >();
	
}
