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

import org.bukkit.block.Block;

public class TaskQue {
	
	public void add(Block block, int type) {
		this.block.add( block );
		this.type.add( type );
		this.data.add( (byte) 0 );
	}
	
	public void add(Block block, int type, byte data) {
		this.block.add( block );
		this.type.add( type );
		this.data.add( (byte) data );
	}
	
	/*========================================================================================================*/
	
	public void clear() {
		block.clear();
		type.clear();
		data.clear();
		
		pos = 0;
	}
	
	/*========================================================================================================*/
	
	public Block getBlock( ) {
		return block.get( pos );
	}
	
	public int getTypeId( ) {
		return type.get( pos );
	}
	
	public byte getData( ) {
		return data.get( pos );
	}
	
	/*========================================================================================================*/
	
	public int size() {
		return block.size( );
	}
	
	public int getPos() {
		return pos;
	}
	
	public void setPos(int i) {
		pos = i;
	}
	
	/*========================================================================================================*/
	
	public boolean valid() {
		return block.contains( pos );
	}
	
	public boolean hasNext() {
		return (pos < (block.size() - 1));
	}
	
	public boolean next() {
		return (pos++ < (block.size() - 1));
	}
	
	public void prev() {
		pos--;
	}
	
	/*========================================================================================================*/
	
	public void first() {
		pos = 0;
	}
	
	public void last() {
		pos = block.size() - 1;
	}
	
	/*========================================================================================================*/
	
	private ArrayList< Block >		block	= new ArrayList< Block >( );
	private ArrayList< Integer >	type	= new ArrayList< Integer >( );
	private ArrayList< Byte >		data	= new ArrayList< Byte >( );
	
	private int pos = 0;
}
