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
import com.rusketh.creator.tasks.selection.BoxSelection;
import com.rusketh.creator.tasks.selection.Selection;

@SuppressWarnings( "unused" )
public class TaskSession {
	
	public TaskSession( CreatorPlugin plugin, Player player ) {
		this.plugin = plugin;
		this.player = player;
		
		selection = new BoxSelection(player.getWorld());
		
		blockRate = plugin.BlockRate;
		maxBlocks = plugin.MaxBlocks;
		if ( player.hasPermission( "creator.nolimit.maxblocks" ) ) maxBlocks = -1;
		
	}
	
	/*========================================================================================================*/
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	/*========================================================================================================*/
	
	public void setSelection(Selection selection) {
		this.selection = selection;
	}
	
	public Selection getSelection() {
		return selection;
	}
	
	/*========================================================================================================*/
	
	public int getBlockRate() {
		return blockRate;
	}
	
	public void setBlockRate(int blockRate) {
		this.blockRate = blockRate;
	}
	
	public int getMaxBlocks() {
		return maxBlocks;
	}
	
	/*========================================================================================================*/
	
	public void stop() {
		if (task != null) task.stopTask();
	}
	
	/*========================================================================================================*/
	
	
	
	/*========================================================================================================*/
	
	private CreatorPlugin plugin;
	private Player player;
	
	private int maxBlocks;
	private int blockRate;
	
	private Selection selection;
	private Task task;
	
	private ArrayList<Task> undoQue;
	private ArrayList<Task> reundoQue;
}
