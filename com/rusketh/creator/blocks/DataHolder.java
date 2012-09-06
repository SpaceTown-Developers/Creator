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

import java.util.ArrayList;
import java.util.HashMap;

public class DataHolder {
	
	public DataHolder( int id, String name ) {
		names = new HashMap< Integer, ArrayList< String >>( );
		add( id, name );
	}
	
	public DataHolder( int id, String name, String... ailas ) {
		names = new HashMap< Integer, ArrayList< String >>( );
		add( id, name, ailas );
	}
	
	/*========================================================================================================*/
	
	public DataHolder add( int id, String name ) {
		ArrayList< String > list = names.get( id );
		
		if ( list == null ) {
			list = new ArrayList< String >( );
			names.put( id, list );
		}
		
		list.add( name );
		
		return this;
	}
	
	public DataHolder add( int id, String name, String... ailas ) {
		ArrayList< String > list = names.get( id );
		
		if ( list == null ) {
			list = new ArrayList< String >( );
			names.put( id, list );
		}
		
		for ( String a : ailas ) {
			list.add( a );
		}
		
		return this;
	}
	
	/*========================================================================================================*/
	
	public int get( String name ) {
		
		for ( int id : names.keySet( ) ) {
			for ( String alias : names.get( id ) ) {
				if ( alias.equalsIgnoreCase( name ) ) return id;
			}
		}
		
		return -1; // Note: Invalid data type;
	}
	
	/*========================================================================================================*/
	
	public String name( int id ) {
		ArrayList< String > list = names.get( id );
		
		if ( list == null ) return null;
		
		return list.get( 0 );
	}
	
	/*========================================================================================================*/
	
	public boolean valid( int id ) {
		return ( names.get( id ) != null );
	}
	
	/*========================================================================================================*/
	
	HashMap< Integer, ArrayList< String >>	names;
}
