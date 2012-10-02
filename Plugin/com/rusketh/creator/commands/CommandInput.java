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

package com.rusketh.creator.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.CommandException;

import com.rusketh.creator.exceptions.CmdException;

public class CommandInput {
	
	/**
	 * Generates Arguments and Flags.
	 * 
	 * @author Rusketh
	 * @param args String to phase into arguments and flags. 
	 * @param flags Keys of valid flags and values of true if extra input is supported. 
	 */
	
	public CommandInput( String args, Map< Character, Boolean > flags ) {
		this( args.split( " " ), flags );
	}
	
	/**
	 * Generates Arguments and Flags.
	 * 
	 * @author Rusketh
	 * @param args Strings to phase into arguments and flags. 
	 * @param flags Keys of valid flags and values of true if extra input is supported. 
	 */
	
	public CommandInput( String[] args, Map< Character, Boolean > validFlags ) {
		command = args[0];
		
		origonalArgs = args;
		phasedArgs = new ArrayList< String >( args.length );
		
		flags = new HashSet< Character >( );
		flagValues = new HashMap< Character, String >( );
		
		if ( args.length == 1 ) return;
		
		boolean foundFlag = false;
		
		for ( int i = 1; i < args.length; i++ ) {
			String arg = args[i];
			
			if ( arg.length( ) == 0 ) continue;
			
			char first = arg.charAt( 0 );
			
			switch ( first ) {
				case '-':
					foundFlag = true;
					char flag = arg.charAt( 1 );
					
					if ( !validFlags.containsKey( flag ) ) {
						throw new CommandException( new StringBuilder( "Uknown flag '-" ).append( flag ).append( "'." ).toString( ) );
					} else if ( flags.contains( flag ) || flagValues.containsKey( flag ) ) {
						throw new CommandException( new StringBuilder( "Flag '-" ).append( flag ).append( "' may not be used twice." ).toString( ) );
					} else if ( arg.length( ) == 2 ) {
						if ( !validFlags.get( flag ) ) {
							flags.add( flag );
						} else {
							throw new CommandException( new StringBuilder( "No value given for flag '-" ).append( flag ).append( "'." ).toString( ) );
						}
					} else if ( arg.charAt( 2 ) == ':' ) {
						if ( arg.length( ) == 3 ) {
							throw new CommandException( new StringBuilder( "No value given for flag '-" ).append( flag ).append( "'." ).toString( ) );
						} else if ( validFlags.get( flag ) ) {
							flagValues.put( flag, arg.substring( 3 ) );
						} else {
							throw new CommandException( new StringBuilder( "Flag '-" ).append( flag ).append( "' can not take a value." ).toString( ) );
						}
					}
					
					break;
				
				case '"':
				case '\'':
					if ( foundFlag ) throw new CommandException( "Miss placed flag in command arguments." );
					
					StringBuilder buffer = new StringBuilder( );
					
					for ( int k = i; k <= args.length; k++ ) {
						String v = args[k];
						
						if ( k == i ) v = v.substring( 1 );
						
						if ( v.charAt( v.length( ) - 1 ) == first ) {
							buffer.append( v.substring( 0, v.length( ) - 1 ) );
							break;
						} else if ( i == args.length ) {
							throw new CommandException( "Unfished string in command arguments." );
						} else {
							buffer.append( " " ).append( v );
						}
					}
					
					phasedArgs.add( buffer.toString( ) );
					break;
				
				default:
					if ( foundFlag ) throw new CommandException( "Missplaced flag in command arguments." );
					
					phasedArgs.add( arg );
			}
		}
	}
	
	/*========================================================================================================*/
	
	/**
	 * @author Rusketh
	 * @param command The commands class.
	 */
	 
	public void setCommand( Command command ) {
		calledCommand = command;
	}
	
	/**
	 * @author Rusketh
	 * @return The called command as a class.
	 */
	
	public Command getComamnd( ) {
		return calledCommand;
	}
	
	/*========================================================================================================*/
	
	/**
	 * @author Rusketh
	 * @return The total arguments.
	 */
	
	public int size( ) {
		return phasedArgs.size( );
	}
	
	/**
	 * @author Rusketh
	 * @param i Argument index starting at 0.
	 * @return The argument at index (i).
	 */
	
	public String arg( int i ) {
		return phasedArgs.get( i );
	}
	
	/**
	 * Converts an argument to a number.
	 * 
	 * @author Rusketh
	 * @param i Argument index starting at 0.
	 * @return The argument at index (i) as integer.
	 */
	
	public int argInt( int i ) {
		String value = phasedArgs.get( i );
		if ( value == null ) return 0;
		
		try {
			return Integer.parseInt( value );
		} catch ( NumberFormatException e ) {
			throw new CmdException("%rImproper value for argument '").append( i + 1 ).append( "' (Number exspected)." );
		}
	}
	
	/**
	 * Converts an argument to a boolean.
	 * 
	 * @author Rusketh
	 * @param i Argument index starting at 0.
	 * @param def The default value is argument does not exist.
	 * @return The argument at index (i) as boolean.
	 */
	
	public boolean argBool( int i , boolean def ) {
		String value = phasedArgs.get( i );
		if ( value == null )
			return def;
		else if ( value.equalsIgnoreCase( "false" ) || value.equalsIgnoreCase( "f" ) ) {
			return false;
		} else if ( value.equalsIgnoreCase( "true" ) || value.equalsIgnoreCase( "t" ) ) {
			return true;
		} else {
			throw new CmdException( "%Improper value for argument '").append( i + 1 ).append( "' (True|False exspected)." );
		}
	}
	
	/**
	 * Converts an argument to a boolean.
	 * 
	 * @author Rusketh
	 * @param i Argument index starting at 0.
	 * @return The argument at index (i) as boolean.
	 */
	
	public boolean argBool( int i ) {
		String value = phasedArgs.get( i );
		
		if ( value == null || value.equalsIgnoreCase( "false" ) || value.equalsIgnoreCase( "f" ) ) {
			return false;
		} else if ( value.equalsIgnoreCase( "true" ) || value.equalsIgnoreCase( "t" ) ) {
			return true;
		} else {
			throw new CmdException( "%Improper value for argument '").append( i + 1 ).append( "' (True|False exspected)." );
		}
	}
	
	/**
	 * @author Rusketh
	 * @return Original arguments before phasing.
	 */
	
	public String[] origonalArgs( ) {
		return origonalArgs;
	}
	
	/*========================================================================================================*/
	
	/**
	 * @author Rusketh
	 * @param flag The char of the flag.
	 * @return Boolean if the flag exists or not.
	 */
	
	public boolean hasFlag( char flag ) {
		return ( flags.contains( flag ) || flagValues.containsKey( flag ) );
	}
	
	/**
	 * @author Rusketh
	 * @param flag The char of the flag.
	 * @return The flag if used.
	 */
	
	public boolean flag( char flag ) {
		return flags.contains( flag );
	}
	
	/**
	 * @author Rusketh
	 * @param flag The char of the flag.
	 * @return The flag as a string
	 */
	
	public String flagString( char flag ) {
		return flagValues.get( flag );
	}
	
	/**
	 * @author Rusketh
	 * @param flag The char of the flag.
	 * @return The flag as a integer
	 */
	
	public int flagInt( char flag ) {
		String value = flagValues.get( flag );
		if ( value == null ) return 0;
		
		try {
			return Integer.parseInt( value );
		} catch ( NumberFormatException e ) {
			throw new CommandException( new StringBuilder( "Improper value for flag '-" ).append( flag ).append( "' (Number exspected)." ).toString( ) );
		}
	}
	
	/**
	 * @author Rusketh
	 * @param flag The char of the flag.
	 * @return The flag as a boolean
	 */
	
	public boolean flagBool( char flag ) {
		String value = flagValues.get( flag );
		if ( value == null || value.equalsIgnoreCase( "false" ) || value.equalsIgnoreCase( "f" ) ) {
			return false;
		} else if ( value.equalsIgnoreCase( "true" ) || value.equalsIgnoreCase( "t" ) ) {
			return true;
		} else {
			throw new CommandException( new StringBuilder( "Improper value for flag '-" ).append( flag ).append( "' (True|False exspected)." ).toString( ) );
		}
	}
	
	/*========================================================================================================*/
	
	/**
	 * @author Rusketh
	 * @return The called command.
	 */
	public String getCommand( ) {
		return command;
	}
	
	/*========================================================================================================*/
	
	private String						command;
	private Command						calledCommand;
	
	private String[]					origonalArgs;
	private ArrayList< String >			phasedArgs;
	
	private Set< Character >			flags;
	private Map< Character, String >	flagValues;
}
