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

import java.util.HashMap;

public class BlockArray< A > {
	
	public HashMap< Byte, A > getValues( int type, boolean create ) {
		HashMap< Byte, A > values = blocks.get( type );
		
		if ( values == null && create ) {
			values = new HashMap< Byte, A >( );
			blocks.put( type, values );
		}
		
		return values;
	}
	
	/*========================================================================================================*/
	
	public void put( int type, byte data, A value ) {
		getValues( type, true ).put( data, value );
	}
	
	public A get( int type, byte data ) {
		return getValues( type, true ).get( data );
	}
	
	public boolean contains(int type, byte data) {
		HashMap< Byte, A > values = blocks.get( type );
		if (values == null) return false;
		return values.containsKey( data );
	}
	
	/*========================================================================================================*/
	
	public void clear() {
		blocks.clear( );
	}
	
	public void clear(int type) {
		HashMap< Byte, A > values = blocks.get( type );
		if (values != null) values.clear();
	}
	
	/*========================================================================================================*/
	
	@SuppressWarnings("unchecked")
	public BlockArray< A > clone() {
		BlockArray< A > array = new BlockArray< A >();
		array.blocks = (HashMap<Integer, HashMap<Byte, A>>) blocks.clone();
		return array;
	}
	
	/*========================================================================================================*/
	
	protected HashMap<Integer, HashMap< Byte, A >>	blocks	= new HashMap<Integer, HashMap< Byte, A >>( );
	
}
