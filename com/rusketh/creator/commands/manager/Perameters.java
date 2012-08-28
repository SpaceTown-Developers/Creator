package com.rusketh.creator.commands.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public class Perameters {
	
	public Perameters( CommandSender sender, command command, String[] perams ) {
		this.command = command;
		this.sender = sender;
		
		phasePerams( perams );
	}
	
	/*========================================================================================================*/
	
	public void phasePerams( String[] perameters ) {
		perams = new ArrayList< String >( );
		flags = new HashSet< String >( );
		
		if ( perameters.length == 0 ) return;
		
		boolean found = false;
		
		for ( String peram : perameters ) {
			
			if ( peram.startsWith( "-" ) ) {
				peram = peram.substring( 1 ).toLowerCase( );
				
				if ( !this.command.validFlag( peram ) ) { throw new CommandException( new StringBuilder( "Ivalid flag (" ).append( peram ).append( ")." ).toString( ) ); }
				
				this.flags.add( peram );
				
				found = true;
				
			} else if ( found ) {
				throw new CommandException( new StringBuilder( "Missplaced flag (" ).append( peram ).append( ") must appear after perameters." ).toString( ) );
				
			} else {
				this.perams.add( peram );
			}
		}
	}
	
	/*========================================================================================================*/
	
	public String getPeram( int peram ) {
		return this.perams.get( peram );
	}
	
	public int perams( ) {
		return this.perams.size( );
	}
	
	/*========================================================================================================*/
	
	public boolean hasFlag( String flag ) {
		return this.flags.contains( flag );
	}
	
	/*========================================================================================================*/
	
	public CommandSender getSender( ) {
		return this.sender;
	}
	
	/*========================================================================================================*/
	
	private command				command;
	private CommandSender		sender;
	
	private ArrayList< String >	perams;
	private Set< String >		flags;
}
