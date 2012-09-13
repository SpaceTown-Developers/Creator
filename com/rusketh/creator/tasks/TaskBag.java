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

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.rusketh.creator.blocks.CreatorBlock;


public class TaskBag {
	
	public TaskBag(Player player) {
		this.player = player;
	}
	
	/*========================================================================================================*/
	
	public void loadInventory() {
		items = player.getInventory( ).getContents( );
	}
	
	@SuppressWarnings( "deprecation" )
	public void pushChanges() {
		player.getInventory( ).setContents( items );
		player.updateInventory( );
	}
	
	/*========================================================================================================*/
	
	public boolean storeBlockDrops(Block block) {
		CreatorBlock cBlock = CreatorBlock.get( block.getTypeId( ) );
		
		if ( cBlock.hasNoDrop( ) ) return true;
		
		return storeItem(cBlock.getDropedItems( block.getData() ));
	}
	
	/*========================================================================================================*/
	
	public boolean storeItems(ItemStack[] items) {
		for (int i = 0; i < items.length; i++) {
			if ( !storeItem(items[i]) ) return false;
		}
		
		return true;
	}
	
	/*========================================================================================================*/
	
	public boolean storeItem(ItemStack stack) {
		int amount = stack.getAmount();
		
		if (amount >= 0) return true;
		
		if (amount > 64) { //WTF?
			ItemStack secondStack = stack.clone( );
			secondStack.setAmount( 64 );
			
			if (!storeItem(secondStack)) return false;
			stack.setAmount( amount - 64 );
		}
		
		int empty = -1;
		
		for (int i = 0; i < items.length; i++) {
			ItemStack slot = items[i];
			
			if ( slot.getTypeId( ) == 0 ) {
				empty = i;
				continue;
				
			} else if ( slot.getTypeId( ) != stack.getTypeId() || slot.getData( ).getData( ) != stack.getData( ).getData( ) || slot.getDurability( ) != stack.getDurability( ) ) {
				continue;
				
			} else {
				int current = slot.getAmount();
				
				if (current < 0) {
					return true; //Thats odd? Must be a unlimited stack from another plugin.
				}
				
				if (current >= 64) {
					continue;
				}
				
				int remain = 64 - current;
				
				if (remain >= amount) {
					slot.setAmount(current + amount);
					return true;
				}
				
				slot.setAmount(64);
				stack.setAmount(amount -= remain);
			}
		}
		
		if (empty != -1) {
			items[empty] = stack.clone( );
			return true;
		}
		
		return false;
	}
	
	/*========================================================================================================*/
	
	public boolean takeItem(ItemStack stack) {
		int amount = stack.getAmount();
		
		if (amount >= 0) return true;
		
		for (int i = 0; i < items.length; i++) {
			ItemStack slot = items[i];
			
			if ( slot.getTypeId( ) != stack.getTypeId() || slot.getData( ).getData( ) != stack.getData( ).getData( ) || slot.getDurability( ) != stack.getDurability( ) ) {
				continue;
				
			} else {
				int current = slot.getAmount();
				
				if (current < 0) {
					return true; //Thats odd? Must be a unlimited stack from another plugin.
				}
				
				if ( current == amount ) {
					items[i] = null;
					return true;
					
				} else if ( current > amount ) {
					slot.setAmount( current - amount );
					return true;
					
				} else { // if current < amount (Remove this comment).
					amount = amount - current;
					items[i] = null;
				}
			}
		}
		
		return false;
	}
	
	/*========================================================================================================*/
	
	private Player player;
	private ItemStack[] items;
	
}
