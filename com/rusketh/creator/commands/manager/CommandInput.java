package com.rusketh.creator.commands.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.CommandException;


public class CommandInput {
	
	public CommandInput(String args, Map< Character, Boolean > flags) {
		this(args.split(" "), flags);
	}
	
	public CommandInput(String[] args, Map< Character, Boolean > validFlags) {
		command = args[0];
		
		origonalArgs = args;
		phasedArgs = new ArrayList<String>(args.length);
		
		flags = new HashSet<Character>();
		flagValues = new HashMap<Character, String>();
		
		boolean foundFlag = false;
		
		for (int i = 1; i <= args.length; i++) {
			String arg = args[i];
			
			if (arg.length( ) == 0) continue;
			
			char first = arg.charAt( 0 );
			
			switch(first) {
				case  '-':
					foundFlag = true;
					char flag = arg.charAt( 1 );
					
					if (!validFlags.containsKey( flag )) {
						throw new CommandException(new StringBuilder("Uknown flag '-").append( flag ).append("'.").toString());
					} else if (flags.contains( flag )|| flagValues.containsKey( flag )) {
						throw new CommandException(new StringBuilder("Flag '-").append( flag ).append("' may not be used twice.").toString());
					} else if (arg.length( ) == 2) {
						if (!validFlags.get( flag )) {
							flags.add( flag );
						} else {
							throw new CommandException(new StringBuilder("No value given for flag '-").append( flag ).append("'.").toString());
						}
					} else if (arg.charAt( 2 ) == ':') {
						if (arg.length( ) == 3) {
							throw new CommandException(new StringBuilder("No value given for flag '-").append( flag ).append("'.").toString());
						} else if (validFlags.get( flag )) {
							flagValues.put( flag, arg.substring( 3 ) );
						} else {
							throw new CommandException(new StringBuilder("Flag '-").append( flag ).append("' can not take a value.").toString());
						}
					}
					
					break;
					
				case '"': case'\'':
					if (foundFlag) throw new CommandException("Miss placed flag in command arguments.");
					
					StringBuilder buffer = new StringBuilder();
					
					for (int k = i; k <= args.length; k++) {
						String v = args[k];
						
						if (k == i) v = v.substring( 1 );
						
						if (v.charAt( v.length( ) - 1 ) == first) {
							buffer.append(v.substring(0, v.length( ) - 1 ));
							break;
						} else if (i == args.length ) {
							throw new CommandException("Unfished string in command arguments.");
						} else {
							buffer.append( " " ).append(v);
						}
					}
					
					phasedArgs.add(buffer.toString( ));
					break;
					
				default:
					if (foundFlag) throw new CommandException("Miss placed flag in command arguments.");
					
					phasedArgs.add(arg);
			}
		}
	}
	
	/*========================================================================================================*/
	
	public int size() {
		return phasedArgs.size( );
	}
	
	public String arg(int i) {
		return phasedArgs.get( i );
	}
	
	public int argInt(int i) {
		String value = phasedArgs.get( i );
		if (value == null) return 0;
		
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new CommandException(new StringBuilder("Improper value for argument '").append( i ).append("' (Number exspected).").toString());
		}
	}
	
	public String[] origonalArgs() {
		return origonalArgs;
	}
	
	/*========================================================================================================*/
	
	public boolean hasFlag(char flag) {
		return (flags.contains( flag ) || flagValues.containsKey( flag ));
	}
	
	public boolean flag(char flag) {
		return flags.contains( flag );
	}
	
	public String flagString(char flag) {
		return flagValues.get( flag );
	}
	
	public int flagInt(char flag) {
		String value = flagValues.get( flag );
		if (value == null) return 0;
		
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new CommandException(new StringBuilder("Improper value for flag '-").append( flag ).append("' (Number exspected).").toString());
		}
	}
	
	public boolean flagBool(char flag) {
		String value = flagValues.get( flag );
		if (value == null || value.equalsIgnoreCase( "false" )) {
			return false;
		} else if ( value.equalsIgnoreCase( "true" )) {
			return true;
		} else {
			throw new CommandException(new StringBuilder("Improper value for flag '-").append( flag ).append("' (True|False exspected).").toString());
		}
	}
	
	/*========================================================================================================*/
	
	public String getCommand() {
		return command;
	}
	
	/*========================================================================================================*/
	
	private String command;
	
	private String[] origonalArgs;
	private ArrayList<String> phasedArgs;
	
	private Set<Character> flags;
	private Map<Character, String> flagValues;
}
