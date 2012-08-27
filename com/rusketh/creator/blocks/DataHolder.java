package com.rusketh.creator.blocks;

import java.util.ArrayList;
import java.util.HashMap;

public class DataHolder {
	
	public DataHolder( int id, String name ) {
		names = new HashMap< Integer, ArrayList< String >>( );
		add( id, name );
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
