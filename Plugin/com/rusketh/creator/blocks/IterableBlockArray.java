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

import java.util.Iterator;

public class IterableBlockArray< A > extends BlockArray<A> implements Iterable<A> {
	
	public void first() {
		typeIterator = blocks.keySet( ).iterator( );
	}
	
	public boolean hasNext( ) {
		if ( dataIterator != null && dataIterator.hasNext( ) ) return true;
		return typeIterator.hasNext( );
	}
	
	public int nextBlockType() {
		type = typeIterator.next( );
		dataIterator = blocks.get( type ).keySet( ).iterator( );
		
		return type;
	}
	
	public A next( ) {
		if ( !hasNext() ) return null;
		if ( dataIterator == null || !dataIterator.hasNext( ) ) nextBlockType();
		data = dataIterator.next( );
		return blocks.get( type ).get( data );
	}
	
	/*========================================================================================================*/
	
	public int getTypeId() {
		return type;
	}
	
	public byte getData() {
		return data;
	}
	
	/*========================================================================================================*/
	
	public IBAIterator<A> iterator( ) {
		return new IBAIterator<A>(this);
	}
	
	/*========================================================================================================*/
	
	private Iterator< Integer > typeIterator;
	private Iterator<Byte> dataIterator;
	
	private int type;
	private byte data;
	
	/*========================================================================================================*/
	
	private class IBAIterator<B extends A>  implements Iterator<A> {
		
		public IBAIterator(IterableBlockArray< A > blockArray) {
			this.blocks = blockArray;
		}
		
		/*========================================================================================================*/
		
		public boolean hasNext( ) {
			return blocks.hasNext();
		}

		public A next( ) {
			return blocks.next( );
		}

		public void remove( ) {
			throw new UnsupportedOperationException();
		}
		
		/*========================================================================================================*/
		
		private IterableBlockArray< A > blocks;
	}
}
