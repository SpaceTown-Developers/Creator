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

package com.rusketh.creator.ban;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;

import com.rusketh.creator.MysqlManager;
import com.rusketh.creator.Extensions.Extension;
import com.rusketh.creator.commands.CommandInput;
import com.rusketh.creator.commands.CreateCommand;

public class BanExtension extends Extension {
	
	public boolean enable( ) {
		loadConfig( );
		
		if ( !enabled ) return false;
		
		loadBans( );
		
		return true;
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
		if ( plugin.getMysqlManager( ).isEnabled( ) && useMysql ) {
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
		MysqlManager mysql = plugin.getMysqlManager( );
		
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
	
	private boolean addOfflineBan( OfflinePlayer ply, CommandSender sender, CommandInput input ) {
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
		
		addBan( "", ply.getName( ), sender.getName( ), unban, reason );
		
		return true;
	}
	
	private void mysqlAddBan( String name, String ip, String banner, long unban, String reason ) {
		if ( reason == null ) reason = "";
		
		try {
			MySqlBan banData = mysqlGetBan( name );
			
			MysqlManager mysql = plugin.getMysqlManager( );
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
	
	private MySqlBan mysqlGetBan( String name ) {
		MysqlManager mysql = plugin.getMysqlManager( );
		
		try {
			PreparedStatement query = mysql.query( "SELECT * from `creator_bans` WHERE user = ?" );
			query.setString( 1, name );
			
			ResultSet result = query.executeQuery( );
			if ( !result.next( ) ) return null;
			
			MySqlBan ban = new MySqlBan( result );
			
			result.close( );
			return ban;
		} catch ( SQLException e ) {
			// TODO: This...
			e.printStackTrace( );
		}
		
		return null;
	}
	
	/*========================================================================================================*/
	
	public final Pattern	pattern	= Pattern.compile( "([0-9]+)([yjdhmsw])" );
	
	public long toDate( String time, boolean future ) throws CommandException {
		Calendar cal = new GregorianCalendar( );
		time = time.toLowerCase( );
		
		if ( future ) {
			cal.setTimeInMillis( System.currentTimeMillis( ) );
		} else {
			cal.setTimeInMillis( 0 );
		}
		
		Matcher m = pattern.matcher( time );
		
		while ( m.find( ) ) {
			int count = Integer.parseInt( m.group( 1 ) );
			char c = m.group( 2 ).charAt( 0 );
			
			switch ( c ) {
				case 's':
					cal.set( Calendar.SECOND, count );
					break;
				case 'm':
					cal.set( Calendar.MINUTE, count );
					break;
				case 'h':
					cal.set( Calendar.HOUR_OF_DAY, count );
					break;
				case 'd':
					if ( future ) cal.set( Calendar.DAY_OF_YEAR, count );
					else cal.set( Calendar.DAY_OF_MONTH, count );
					break;
				case 'w':
					if ( future ) cal.set( Calendar.WEEK_OF_YEAR, count );
					break;
				case 'j':
					cal.set( Calendar.MONTH, count );
					break;
				case 'y':
					cal.set( Calendar.YEAR, count );
					break;
				default:
					throw new CommandException( "Invalid time format (e.g: 2d1h4m)." );
			}
		}
		
		if ( !future ) return ( cal.getTimeInMillis( ) + System.currentTimeMillis( ) ) / 1000;
		
		return cal.getTimeInMillis( ) / 1000;
	}
	
	/*========================================================================================================*/
	
	@CreateCommand( names = { "ban" }, example = "ban <player> <time> [reason -t]|| ban <player> [reason] -p", desc = "Get help with a command.", least = 1, most = 3, console = true, flags = { "p", "t", "e", "s" } )
	public boolean BanCommand( CommandSender sender, CommandInput input ) {
		if ( !enabled ) return false;
		
		Player target;
		
		if ( input.hasFlag( 'e' ) ) {
			OfflinePlayer ply = plugin.getServer( ).getOfflinePlayer( input.arg( 0 ) );
			
			if ( ply.isOnline( ) ) target = ply.getPlayer( );
			else {
				if ( ply.getFirstPlayed( ) == 0 ) {
					// TODO: Add message about banning a player that haven't played on the server
				}
				
				return addOfflineBan( ply, sender, input );
			}
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
	
	@CreateCommand( names = { "ipban" }, example = "ipban <player> <time> [reason -t]|| ipban <player> [reason] -p", desc = "Get help with a command.", least = 1, most = 3, console = true, flags = { "p", "t", "e", "s" } )
	public boolean IpBanCommand( CommandSender sender, CommandInput input ) {
		if ( !enabled ) return false;
		
		Player target;
		
		if ( input.hasFlag( 'e' ) ) {
			OfflinePlayer ply = plugin.getServer( ).getOfflinePlayer( input.arg( 0 ) );
			
			if ( ply.isOnline( ) ) target = ply.getPlayer( );
			else {
				if ( ply.getFirstPlayed( ) == 0 ) {
					// TODO: Add message about banning a player that haven't played on the server
				}
				
				return true; // addOfflineBan( ply, sender, input );
			}
		} else {
			target = plugin.getServer( ).getPlayer( input.arg( 0 ) );
		}
		
		if ( target == null ) throw new CommandException( "Player not found." );
		
		long unban;
		String reason = null;
		
		if ( input.flag( 'p' ) ) { // Permanent ban!
			if ( !( sender instanceof Player ) || ( (Player) sender ).hasPermission( "creator.ipban.perm" ) ) throw new CommandException( "Your not allowed to ban players permanently." );
			
			unban = 0;
			reason = input.arg( 1 );
			
		} else {
			unban = toDate( input.arg( 1 ), input.flag( 't' ) );
			
			if ( input.size( ) > 2 ) reason = input.arg( 2 );
			
			if ( unban == 0 ) throw new CommandException( "Ban lengh to short." );
		}
		
		// addBan( target, sender, unban, reason );
		
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
			} else if ( unban > ( System.currentTimeMillis( ) / 1000 ) ) {
				event.disallow( PlayerLoginEvent.Result.KICK_BANNED, "Banned!" ); // TODO: Tell then for how long.
			} else {
				// TODO: Remove Ban
			}
		}
	}
	
	/*========================================================================================================*/
	
	public void mysqlEvent( PlayerLoginEvent event ) {
		
		MySqlBan banData = mysqlGetBan( event.getPlayer( ).getName( ) );
		
		if ( banData == null ) return;
		
		long unban = banData.getUnban( );
		long bantime = banData.getTimeBanned( );
		long curtime = ( System.currentTimeMillis( ) / 1000 );
		
		if ( unban == 0 ) {
			event.disallow( PlayerLoginEvent.Result.KICK_BANNED, "permanently Banned!" );
		} else if ( unban > curtime ) {
			event.disallow( PlayerLoginEvent.Result.KICK_BANNED, "Banned!" ); // TODO: Tell then for how long.
		} else if ( unban < bantime && unban + bantime > curtime ) {
			// event.disallow( PlayerLoginEvent.Result.KICK_BANNED, "Banned2!" ); // TODO: Tell then for how long.
		} else {
			// TODO: Remove Ban
		}
	}
	
	/*========================================================================================================*/
	
	private File				bansFile;
	private YamlConfiguration	YamlBans;
	
	boolean						enabled;
	boolean						useMysql;
}
