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

package com.rusketh.creator.Extensions;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.rusketh.creator.creatorPlugin;
import com.rusketh.creator.mysqlManager;
import com.rusketh.creator.commands.manager.CommandInput;
import com.rusketh.creator.commands.manager.commandAnote;
import com.rusketh.util.MySQLBan;

public class BanExtension implements Listener {
	
	public BanExtension( creatorPlugin plugin ) {
		this.plugin = plugin;
		
		loadConfig( );
		
		if ( !enabled ) return;
		
		loadBans( );
		
		plugin.getCommandManager( ).registerCommands( this );
		plugin.getServer( ).getPluginManager( ).registerEvents( this, plugin );
	}
	
	/*========================================================================================================*/
	
	private void loadConfig( ) {
		
		FileConfiguration YamlConfig = plugin.getConfig( );
		
		if ( !YamlConfig.contains( "bans.enable" ) ) YamlConfig.set( "bans.enable", false );
		if ( !YamlConfig.contains( "bans.mysql" ) ) YamlConfig.set( "bans.mysql", false );
		
		enabled = YamlConfig.getBoolean( "bans.enable" );
		useMysql = YamlConfig.getBoolean( "bans.mysql" );
	}
	
	/*========================================================================================================*/
	
	private void loadBans( ) {
		if ( plugin.mysqlManager( ).isEnabled( ) && useMysql ) {
			setUpDB( );
			return;
		}
		
		loadFromFile( );
	}
	
	private void loadFromFile( ) {
		bansFile = new File( plugin.getDataFolder( ), "bans.yml" );
		
		if ( !bansFile.exists( ) ) {
			try {
				bansFile.createNewFile( );
			} catch ( IOException e ) {
				plugin.logger.severe( "[Creator] Unable to create bans file!" );
			}
		}
		
		YamlBans = new YamlConfiguration( );
		YamlBans.options( ).pathSeparator( '.' );
		
		try {
			YamlBans.load( bansFile );
			
		} catch ( Exception e ) {
			plugin.logger.severe( "[Creator] Unable to load bans!" );
			enabled = false;
		}
	}
	
	/*========================================================================================================*/
	
	private void setUpDB( ) {
		mysqlManager mysql = plugin.mysqlManager( );
		
		try {
			ResultSet checkTable = mysql.query( "SHOW TABLES LIKE 'creator_bans'" ).executeQuery( );
			if ( !checkTable.next( ) ) {
				
				PreparedStatement q = mysql.query( "CREATE TABLE `creator_bans` ( `ip` text, `user` text, `banner` text, `reason_banned` longtext, `time_banned` bigint DEFAULT NULL, `unban` bigint DEFAULT NULL)" );
				q.execute( );
				q.close( );
				
				loadFromFile( );
				
				for ( String name : YamlBans.getKeys( false ) ) {
					ConfigurationSection banData = YamlBans.getConfigurationSection( name );
					
					PreparedStatement query = mysql.query( "INSERT INTO `creator_bans` (ip, user, banner, reason_banned, time_banned, unban) VALUES (?, ?, ?, ?, ?, ?)" );
					
					query.setString( 1, banData.getString( "ip", "" ) );
					query.setString( 2, name );
					query.setString( 3, banData.getString( "banner", "" ) );
					query.setString( 4, banData.getString( "reason", "" ) );
					query.setLong( 5, banData.getLong( "timebanned", 0 ) );
					query.setLong( 6, banData.getLong( "unban", 0 ) );
					
					query.execute( );
					query.close( );
				}
			}
			
		} catch ( SQLException e ) {
			plugin.logger.warning( "[Creator] Error creating MYSQL bans database." );
			plugin.logger.warning( "[Creator] Using to bans.YAML (not MYSQL)." );
			plugin.logger.warning( e.getMessage( ) );
			e.printStackTrace( );
			
			useMysql = false;
		}
		
	}
	
	/*========================================================================================================*/
	
	public void addBan( Player target, CommandSender banner, long unban, String reason ) {
		addBan( target.getAddress( ).toString( ), target.getName( ), banner.getName( ), unban, reason );
	}
	
	public void addBan( String ip, String name, String banner, long unban, String reason ) {
		if ( useMysql ) {
			mysqlAddBan( name, ip, banner, unban, reason );
		} else if ( name != null ) {
			YamlBans.set( name + ".ip", ip );
			YamlBans.set( name + ".banner", banner );
			YamlBans.set( name + ".unban", unban );
			YamlBans.set( name + ".timebanned", System.currentTimeMillis( ) / 1000 );
			
			if ( reason != null ) YamlBans.set( name + ".reason", reason );
		}
	}
	
	private void mysqlAddBan( String name, String ip, String banner, long unban, String reason ) {
		if ( reason == null ) reason = "";
		
		try {
			MySQLBan banData = mysqlGetBan( name );
			
			mysqlManager mysql = plugin.mysqlManager( );
			PreparedStatement query;
			
			if ( banData == null ) {
				query = mysql.query( "INSERT INTO `creator_bans` (ip, banner, reason_banned, time_banned, unban, user) VALUES (?, ?, ?, ?, ?, ?)" );
			} else {
				query = mysql.query( "UPDATE `creator_bans` SET ip = ?, banner = ?, reason_banned = ?, time_banned = ?, unban = ? WHERE user = ?" );
			}
			
			query.setString( 1, ip );
			query.setString( 2, banner );
			query.setString( 3, reason );
			query.setLong( 4, System.currentTimeMillis( ) / 1000 );
			query.setLong( 5, unban );
			query.setString( 6, name );
			
			query.execute( );
			query.close( );
			
		} catch ( SQLException e ) {
			// TODO: This...
			e.printStackTrace( );
		}
	}
	
	/*========================================================================================================*/
	
	private MySQLBan mysqlGetBan( String name ) {
		mysqlManager mysql = plugin.mysqlManager( );
		
		try {
			PreparedStatement query = mysql.query( "SELECT * from `creator_bans` WHERE user = ?" );
			query.setString( 1, name );
			
			ResultSet result = query.executeQuery( );
			
			if ( !result.next( ) ) return null;
			
			return new MySQLBan( result );
			
		} catch ( SQLException e ) {
			// TODO: This...
			e.printStackTrace( );
		}
		
		return null;
	}
	
	/*========================================================================================================*/
	
	public static final Pattern	pattern	= Pattern.compile( "([0-9]+)([yjdhmsw])" );
	
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
		
		if (future) {
			return (cal.getTimeInMillis( ) + System.currentTimeMillis( )) / 1000;
		}
		
		return cal.getTimeInMillis( ) / 1000;
	}
	
	/*========================================================================================================*/
	
	@commandAnote( names = { "ban" }, example = "ban <player> <time> [reason -t]|| ban <player> [reason] -p", desc = "Get help with a command.", least = 1, most = 3, console = true, flags = { "p", "t", "e", "s" } )
	public boolean BanCommand( CommandSender sender, CommandInput input ) {
		if ( !enabled ) return false;
		
		Player target;
		
		if ( input.hasFlag( 'e' ) ) {
			target = plugin.getServer( ).getPlayerExact( input.arg( 0 ) );
		} else {
			target = plugin.getServer( ).getPlayer( input.arg( 0 ) );
		}
		
		if ( target == null ) throw new CommandException( "Player not found." );
		
		long unban;
		String reason = null;
		
		if ( input.flag( 'p' ) ) { // Permanent ban!
			if ( !( sender instanceof Player ) || ( (Player) sender ).hasPermission( "creator.ban.perm" ) ) throw new CommandException( "Your not allowed to ban players permanently." );
			
			unban = 0;
			reason = input.arg( 1 );
			
		} else {
			unban = toDate( input.arg( 1 ), input.flag( 't' ) );
			
			if ( input.size( ) > 2 ) reason = input.arg( 2 );
			
			if ( unban == 0 ) throw new CommandException( "Ban lengh to short." );
		}
		
		addBan( target, sender, unban, reason );
		
		if ( !input.hasFlag( 's' ) ) {
			plugin.getServer( ).broadcastMessage( new StringBuilder( target.getDisplayName( ) ).append( " has been baned!" ).toString( ) );
			
			if ( reason != null ) {
				plugin.getServer( ).broadcastMessage( new StringBuilder( "Reason: " ).append( reason ).toString( ) );
			}
		}
		
		if ( reason != null ) {
			target.kickPlayer( new StringBuilder( "Banned: " ).append( reason ).toString( ) );
		} else {
			target.kickPlayer( "You have been banned from this server." );
		}
		
		return true;
	}
	
	/*========================================================================================================*/
	
	@EventHandler( priority = EventPriority.NORMAL )
	public void playerLogin( PlayerLoginEvent event ) {
		if ( !enabled ) return;
		
		if ( useMysql ) {
			mysqlEvent( event );
			return;
		} else {
			ConfigurationSection banData = YamlBans.getConfigurationSection( event.getPlayer( ).getName( ) );
			
			if ( banData == null ) return;
			
			long unban = banData.getLong( "unban", 0 );
			
			if ( unban == 0 ) {
				event.disallow( PlayerLoginEvent.Result.KICK_BANNED, "permanently Banned!" );
			} else if ( unban > (System.currentTimeMillis( ) / 1000) ) {
				event.disallow( PlayerLoginEvent.Result.KICK_BANNED, "Banned!" ); // TODO: Tell then for how long.
			} else {
				// TODO: Remove Ban
			}
		}
	}
	
	/*========================================================================================================*/
	
	public void mysqlEvent( PlayerLoginEvent event ) {
		
		MySQLBan banData = mysqlGetBan( event.getPlayer( ).getName( ) );
		
		if ( banData == null ) return;
		
		long unban = banData.getUnban( );
		
		if ( unban == 0 ) {
			event.disallow( PlayerLoginEvent.Result.KICK_BANNED, "permanently Banned!" );
		} else if ( unban > (System.currentTimeMillis( ) / 1000) ) {
			event.disallow( PlayerLoginEvent.Result.KICK_BANNED, "Banned!" ); // TODO: Tell then for how long.
		} else {
			// TODO: Remove Ban
		}
	}
	
	/*========================================================================================================*/
	
	private creatorPlugin		plugin;
	
	private File				bansFile;
	private YamlConfiguration	YamlBans;
	
	boolean						enabled;
	boolean						useMysql;
}
