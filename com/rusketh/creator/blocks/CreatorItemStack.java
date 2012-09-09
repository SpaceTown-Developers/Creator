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

import org.bukkit.Material;

import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class CreatorItemStack extends ItemStack {
	
	public CreatorItemStack( ItemStack itemStack ) {
		super( itemStack.getTypeId( ), itemStack.getAmount( ), itemStack.getDurability( ), itemStack.getData( ).getData( ) );
		super.addEnchantments( itemStack.getEnchantments( ) );
		this.item = Item.get( itemStack.getTypeId( ) );
	}
	
	/*========================================================================================================*/
	
	public CreatorItemStack( int type ) {
		super( type );
		this.item = Item.get( type );
	}
	
	public CreatorItemStack( int type, byte data ) {
		super( type, 1, (short) 0, data );
		this.item = Item.get( type );
	}
	
	public CreatorItemStack( int type, byte data, int amount ) {
		super( type, amount, (short) 0, data );
		this.item = Item.get( type );
	}
	
	/*========================================================================================================*/
	
	public void setTypeId( int type ) {
		super.setTypeId( type );
		this.item = Item.get( type );
	}
	
	/*========================================================================================================*/
	
	public void setData( byte data ) {
		Material mat = super.getType( );
		
		if ( mat == null ) {
			super.setData( new MaterialData( super.getTypeId( ), data ) );
		} else {
			super.setData( mat.getNewData( data ) );
		}
	}
	
	public byte getDataByte( ) {
		return super.getData( ).getData( );
	}
	
	/*========================================================================================================*/
	
	public CreatorItemStack clone( ) {
		CreatorItemStack itemStack = (CreatorItemStack) clone( );
		itemStack.item = Item.get( itemStack.getTypeId( ) );
		
		return itemStack;
	}
	
	/*========================================================================================================*/
	
	public Item getItem( ) {
		if (this.item == null) this.item = Item.get( getTypeId( ) );
		return this.item;
	}
	
	public String getName( ) {
		return getItem().getName( );
	}
	
	public String niceName( ) {
		return getItem().niceName( (int) getDataByte( ) );
	}
	
	/*========================================================================================================*/
	
	private Item	item;
}