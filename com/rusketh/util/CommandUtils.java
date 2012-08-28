package com.rusketh.util;

import java.util.ArrayList;
import java.util.List;


public class CommandUtils {
	
	public List< String > getArgumentsList( String[] args ) {
		StringBuilder sb = new StringBuilder( );
		
		for ( String s : args )
			sb.append( s ).append( " " );
		
		return getArgumentsList( sb.toString( ) );
	}
	
	/*========================================================================================================*/
	
	public List< String > getArgumentsList( String string ) {
		boolean flag = false;
		List< String > data = new ArrayList< String >( );
		StringBuilder sb = new StringBuilder( );
		
		for ( char s : string.toCharArray( ) ) {
			switch ( s ) {
				case ( '"' ):
					flag = !flag;
					if ( sb.toString( ).isEmpty( ) ) break;
					data.add( sb.toString( ) );
					sb = new StringBuilder( );
					break;
				case ( ' ' ):
					if ( !flag ) {
						if ( sb.toString( ).isEmpty( ) ) break;
						data.add( sb.toString( ) );
						sb = new StringBuilder( );
						break;
					}
				default:
					sb.append( s );
					break;
			}
		}
		
		if ( !sb.toString( ).isEmpty( ) ) data.add( sb.toString( ) );
		return data;
	}
}
