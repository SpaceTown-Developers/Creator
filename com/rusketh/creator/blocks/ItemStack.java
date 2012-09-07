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

package com.rusketh.creator.blocks;

public class ItemStack {
	
	public ItemStack( int id ) {
		this.id = id;
		
		this.item = Item.get( id );
	}
	
	public ItemStack( int id, byte data ) {
		this.id = id;
		this.data = data;
		
		this.item = Item.get( id );
	}
	
	public ItemStack( int id, byte data, int amount ) {
		this.id = id;
		this.data = data;
		this.amount = amount;
		
		this.item = Item.get( id );
	}
	
	/*========================================================================================================*/
	
	public void setID( int id ) {
		this.id = id;
		
		this.item = Item.get( id );
	}
	
	public int getID( ) {
		return this.id;
	}
	
	/*========================================================================================================*/
	
	public void setData( byte data ) {
		this.data = data;
	}
	
	public int getData( ) {
		return this.data;
	}
	
	/*========================================================================================================*/
	
	public void setDurability( short durability ) {
		this.durability = durability;
	}
	
	public int getDurability( ) {
		return this.durability;
	}
	
	/*========================================================================================================*/
	
	public void setAmount( int amount ) {
		this.amount = amount;
	}
	
	public int getAmount( ) {
		return this.amount;
	}
	
	/*========================================================================================================*/
	
	public org.bukkit.inventory.ItemStack toItemStack( ) {
		return new org.bukkit.inventory.ItemStack( id, amount, durability, data );
	}
	
	/*========================================================================================================*/
	
	public Item getItem( ) {
		
		return this.item;
	}
	
	public String getName( ) {
		return this.item.getName( );
	}
	
	public String niceName() {
		return item.niceName( data );
	}
	
	/*========================================================================================================*/
	
	public ItemStack clone( ) {
		ItemStack itemStack = new ItemStack( id, data, amount );
		itemStack.setDurability( durability );
		return itemStack;
	}
	
	/*========================================================================================================*/
	
	private int		id			= 0;
	private byte	data		= 0;
	private short	durability	= 0;
	private int		amount		= 1;
	
	private Item	item;
}
