package com.rusketh.creator.commands.manager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.rusketh.creator.creatorPlugin;

public class command {
	
	public command( creatorPlugin plugin, commandAnote anote, Object baseClass, Method method ) {
		this.plugin = plugin;
		this.baseClass = baseClass;
		this.method = method;
		
		this.name = anote.names( )[0];
		this.example = anote.example( );
		this.desc = anote.desc( );
		this.least = anote.least( );
		this.most = anote.most( );
		this.player = anote.player( );
		this.console = anote.console( );
		this.perms = anote.perms( );
		
		this.flags = new HashMap< Character, Boolean >( );

		for ( String flag : anote.flags( ) ) {
			if (flag.length( ) == 1) {
				this.flags.put( flag.charAt( 0 ) , false);
			} else if (flag.length( ) == 2 && flag.charAt( 1 ) == '*') {
				this.flags.put( flag.charAt( 0 ) , true);
			} else {
				plugin.logger.warning( new StringBuilder("[Creator] invalid flag '").append( flag ).append("' for command ").append(this.name).toString() );
			}
		}
		
		
		FileConfiguration settings = plugin.getConfig( );
		
		if ( settings.getConfigurationSection( "commands." + this.name ) == null ) {
			this.plugin.getConfig( ).set( "commands." + this.name + ".enabled", true );
			
			if ( anote.usePrice( ) != -1 ) {
				settings.set( "commands." + this.name + ".price", anote.usePrice( ) );
			}
			
			if ( anote.blockPrice( ) != -1 ) {
				settings.set( "commands." + this.name + ".blockprice", anote.blockPrice( ) );
			}
		}
		
		this.enabled = settings.getBoolean( "commands." + this.name + ".enabled" );
		this.usePrice = settings.getInt( "commands." + this.name + ".price" );
		this.blockPrice = settings.getInt( "commands." + this.name + ".blockprice" );
	}
	
	/*========================================================================================================*/
	
	public ConfigurationSection getConfig( ) {
		return plugin.getConfig( ).getConfigurationSection( "commands." + this.name );
	}
	
	/*========================================================================================================*/
	
	public boolean execute( creatorPlugin plugin, CommandSender sender, String[] args ) {
		if ( !this.enabled ) { throw new CommandException( "This command has been disabled." ); }
		
		CommandInput input = new CommandInput(args, flags);
		
		int size = input.size();
		
		// Note: Going to use string builders here, Java is shit at combining strings with out them!
		if ( size < this.least && this.least != -1 ) {
			throw new CommandException( new StringBuilder( "Not enogh perameters. [" ).append( size ).append( "/" ).append( this.least ).append( "]\n" ).append( this.example ).toString( ) );
		} else if ( size > this.most && this.most != -1 ) { throw new CommandException( new StringBuilder( "Too meany perameters. [" ).append( size ).append( "/" ).append( this.most ).append( "]\n" ).append( this.example ).toString( ) ); }
		
		boolean isPlayer = ( sender instanceof Player );
		
		if ( isPlayer && !this.player ) {
			throw new CommandException( "This command can not be called by players." );
		} else if ( !isPlayer && !this.console ) { throw new CommandException( "This command can not be called from console." ); }
		
		if ( isPlayer ) { // Note: Check permissions?
			Player player = (Player) sender;
			
			if ( !hasPermission( player ) ) { // Note: Player is not allowed.
				throw new CommandException( "Sorry but you do not have permission to do that." );
			}
			
			// Note: Check vault is enabled and that player could be charged for this.
			if ( plugin.Vault && !player.hasPermission( "creator.commands.free" ) && !player.hasPermission( new StringBuilder( "creator.commands." ).append( this.name ).append( ".free" ).toString( ) ) ) {
				
				// Note: Check vault prices.
				if ( this.usePrice > 0 && plugin.getEconomy( ).getBalance( player.getName( ) ) < this.usePrice ) { throw new CommandException( "Sorry but you can not afford to do that." ); }
			}
		}
		
		// Note: All checks have been passed =D
		
		return invoke( sender, input );
	}
	
	/*========================================================================================================*/
	
	public boolean invoke( CommandSender sender, CommandInput input ) {
		try {
			return (Boolean) this.method.invoke( this.baseClass, this, sender, input );
		} catch ( InvocationTargetException e ) {
			
			plugin.logger.info( new StringBuilder( "Creator failed to invoke command " ).append( this.name ).toString( ) );
			
			if ( e.getCause( ) != null ) {
				plugin.logger.info( e.getCause( ).getMessage( ) );
				e.getCause( ).printStackTrace( );
			}
			
			throw new CommandException( "Oooops, somthing went horribad." );
			
		} catch ( Exception e ) {
			plugin.logger.info( new StringBuilder( "Creator failed to invoke command " ).append( this.name ).toString( ) );
			
			e.printStackTrace( );
			
			throw new CommandException( "Oooops, somthing went horribad." );
		}
	}
	
	/*========================================================================================================*/
	
	public boolean hasPermission( Player player ) {
		if ( this.perms.length == 0 ) { return true; // No permission nodes.
		}
		
		boolean allowed = false;
		
		for ( String perm : this.perms ) {
			if ( player.hasPermission( perm ) ) {
				allowed = true;
				break;
			}
		}
		
		return allowed;
	}
	
	/*========================================================================================================*/
	
	public String getHelp( ) {
		
		StringBuilder msg = new StringBuilder( ChatColor.YELLOW.toString( ) ).append( this.example ).append( " " );
		
		if ( this.usePrice > 0 ) { // Add price data
			msg.append( "(£" ).append( ChatColor.RED.toString( ) ).append( this.usePrice ).append( ")" );
		}
		
		return msg.append( "\n" ).append( ChatColor.BLUE.toString( ) ).append( this.desc ).toString( );
	}
	
	/*========================================================================================================*/
	
	private creatorPlugin	plugin;
	private Object			baseClass;
	private Method			method;
	
	private String			name;
	private String			example;
	private String			desc;
	
	private int				least;
	private int				most;
	public int				usePrice;
	public int				blockPrice;
	
	private boolean			player;
	private boolean			console;
	private boolean			enabled;
	
	private Map< Character, Boolean >	flags;
	private String[]		perms;
	
}
