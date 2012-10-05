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
		blocks.add( block );
		types.add( type );
		datas.add( (byte) 0 );
	}
	
	public void add(Block block, int type, byte data) {
		blocks.add( block );
		types.add( type );
		datas.add( (byte) data );
	}
	
	/*========================================================================================================*/
	
	public void clear() {
		blocks.clear();
		types.clear();
		datas.clear();
		
		pos = 0;
	}
	
	/*========================================================================================================*/
	
	public Block getBlock( ) {
		return blocks.get( pos );
	}
	
	public int getTypeId( ) {
		return types.get( pos );
	}
	
	public byte getData( ) {
		return datas.get( pos );
	}
	
	/*========================================================================================================*/
	
	public int size() {
		return blocks.size( );
	}
	
	public int getPos() {
		return pos;
	}
	
	public void setPos(int i) {
		pos = i;
	}
	
	/*========================================================================================================*/
	
	public boolean valid() {
		return pos < blocks.size();
	}
	
	public boolean hasNext() {
		return (pos + 1) < blocks.size();
	}
	
	public boolean next() {
		pos++;
		return hasNext();
	}
	
	public void prev() {
		pos--;
	}
	
	/*========================================================================================================*/
	
	public void first() {
		pos = 0;
	}
	
	public void last() {
		pos = blocks.size() - 1;
	}
	
	/*========================================================================================================*/
	
	private ArrayList< Block >		blocks	= new ArrayList< Block >( );
	private ArrayList< Integer >	types	= new ArrayList< Integer >( );
	private ArrayList< Byte >		datas	= new ArrayList< Byte >( );
	
	private int pos = 0;
}
