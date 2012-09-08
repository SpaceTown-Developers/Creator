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

public class DataHolder {
	
	public DataHolder( int id, String name ) {
		names = new HashMap< Integer, String >( );
		lookup = new HashMap< String, Integer >( );
		
		names.put( id, name );
		lookup.put( name.toLowerCase( ), id );
	}
	
	public DataHolder( int id, String name, String... ailas ) {
		names = new HashMap< Integer, String >( );
		lookup = new HashMap< String, Integer >( );
		
		add( id, name, ailas );
	}
	
	/*========================================================================================================*/
	
	public DataHolder add( int id, String name ) {
		names.put( id, name );
		lookup.put( name.toLowerCase( ), id );
		
		return this;
	}
	
	public DataHolder add( int id, String name, String... ailas ) {
		names.put( id, name );
		lookup.put( name.toLowerCase( ), id );
		
		for ( String a : ailas ) {
			lookup.put( a, id );
		}
		
		return this;
	}
	
	/*========================================================================================================*/
	
	public int get( String name ) {
		
		if ( lookup.containsValue( name ) ) return lookup.get( name );
		
		return -1; // Note: Invalid data type;
	}
	
	/*========================================================================================================*/
	
	public String name( int id ) {
		return names.get( id );
	}
	
	/*========================================================================================================*/
	
	public boolean valid( int id ) {
		return names.containsKey( id );
	}
	
	/*========================================================================================================*/
	
	public DataHolder prefixName( ) {
		prefixName = true;
		dataName = false;
		
		return this;
	}
	
	public DataHolder dataName( ) {
		dataName = true;
		prefixName = false;
		
		return this;
	}
	
	public String niceName( int id, String name ) {
		String nice = name( id );
		
		if ( nice.equalsIgnoreCase( "normal" ) ) return name;
		
		if ( prefixName ) return new StringBuilder( nice ).append( " " ).append( name ).toString( );
		
		if ( dataName ) return nice;
		
		return name;
	}
	
	/*========================================================================================================*/
	private HashMap< Integer, String >	names;
	private HashMap< String, Integer >	lookup;
	
	private boolean						prefixName	= false;
	private boolean						dataName	= false;
}
