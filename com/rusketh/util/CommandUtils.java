package com.rusketh.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.command.CommandException;

public class CommandUtils {
	
	public static List< String > getArgumentsList( String[] args ) {
		StringBuilder sb = new StringBuilder( );
		
		for ( String s : args )
			sb.append( s ).append( " " );
		
		return getArgumentsList( sb.toString( ) );
	}
	
	/*========================================================================================================*/
	
	public static List< String > getArgumentsList( String string ) {
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
	
	/*========================================================================================================*/
	
	public static long toDate( String time, boolean future ) throws CommandException {
		Calendar cal = new GregorianCalendar( );
		cal.setTimeInMillis( 0 );
		time = time.toLowerCase( );
		
		Matcher m = pattern.matcher( time );
		
		while ( m.find( ) ) {
			int count = Integer.parseInt( m.group( 1 ) );
			char c = m.group( 2 ).charAt( 0 );
			
			switch ( c ) {
				case 's':
					cal.add( count, Calendar.SECOND );
					break;
				case 'm':
					cal.add( count, Calendar.MINUTE );
					break;
				case 'h':
					cal.add( count, Calendar.HOUR_OF_DAY );
					break;
				case 'd':
					if ( future )
						cal.add( count, Calendar.DAY_OF_YEAR );
					else
						cal.add( count, Calendar.DAY_OF_MONTH );
					break;
				case 'w':
					if ( future ) cal.add( count, Calendar.WEEK_OF_YEAR );
					break;
				case 'j':
					cal.add( count, Calendar.MONTH );
					break;
				case 'y':
					cal.add( count, Calendar.YEAR );
					break;
				default:
					throw new CommandException( "Invalid time format (e.g: 2d1h4m)." );
			}
			
		}
		
		return cal.getTimeInMillis( );
	}
	
	/*========================================================================================================*/
	
	public static final Pattern	pattern	= Pattern.compile( "([0-9]+)([yjdhmsw])" );
}
