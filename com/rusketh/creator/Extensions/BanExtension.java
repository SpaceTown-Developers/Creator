/*
 * Creator - Bukkit Plugin
 * Copyright (C) 2012 Rusketh <www.Rusketh.com>
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
import com.rusketh.util.CommandUtils;
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
				
				PreparedStatement q = mysql.query( "CREATE TABLE `creator_bans` ( `ip` text, `user` text, `banner` text, `reason_banned` longtext, `time_banned` text DEFAULT NULL, `unban` text DEFAULT NULL)" );
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
					query.setString( 5, String.valueOf( banData.getLong( "timebanned", 0 ) ) );
					query.setString( 6, String.valueOf( banData.getLong( "unban", 0 ) ) );
					
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
			YamlBans.set( name + ".timebanned", System.currentTimeMillis( ) );
			
			if ( reason != null ) YamlBans.set( name + ".reason", reason );
		}
	}
	
	private void mysqlAddBan( String name, String ip, String banner, long unban, String reason ) {
		if ( reason == null ) reason = "";
		
		try {
			mysqlManager mysql = plugin.mysqlManager( );
			
			MySQLBan q = mysqlGetBan( name );
			
			PreparedStatement query;
			
			// System.out.println( q.getName( ) );
			
			if ( q == null || q.getName( ).equals( null ) || q.getName( ).isEmpty( ) ) {
				query = mysql.query( "INSERT INTO `creator_bans` (ip, banner, reason_banned, time_banned, unban, user) VALUES (?, ?, ?, ?, ?, ?)" );
			} else {
				query = mysql.query( "UPDATE `creator_bans` SET ip = ?, banner = ?, reason_banned = ?, time_banned = ?, unban = ? WHERE user = ?" );
			}
			
			query.setString( 1, ip );
			query.setString( 2, banner );
			query.setString( 3, reason );
			query.setString( 4, String.valueOf( System.currentTimeMillis( ) ) );
			query.setString( 5, String.valueOf( unban ) );
			query.setString( 6, name );
			
			query.execute( );
			query.close( );
			
		} catch ( SQLException e ) {
			// TODO: This =P
			e.printStackTrace( ); // Temporary for debugging.
		}
	}
	
	/*========================================================================================================*/
	
	private MySQLBan mysqlGetBan( String name ) {
		mysqlManager mysql = plugin.mysqlManager( );
		
		try {
			PreparedStatement q = mysql.query( "SELECT * from `creator_bans` WHERE user = ?" );
			q.setString( 1, name );
			ResultSet result = q.executeQuery( );
			
			if ( !result.next( ) ) return null;
			
			String ip = result.getString( "ip" );
			String banner = result.getString( "banner" );
			String reason = result.getString( "reason_banned" );
			
			Calendar time = new GregorianCalendar( );
			time.setTimeInMillis( Long.parseLong( result.getString( "time_banned" ) ) );
			
			Calendar unban = new GregorianCalendar( );
			unban.setTimeInMillis( Long.parseLong( result.getString( "unban" ) ) );
			
			// System.out.println( name );
			// System.out.println( result.getString( "ip" ) );
			// System.out.println( result.getString( "banner" ) );
			// System.out.println( result.getString( "reason_banned" ) );
			// System.out.println( result.getString( "time_banned" ) );
			// System.out.println( result.getString( "unban" ) );
			
			q.close( );
			
			return new MySQLBan( name, ip, banner, reason, time, unban );
			
		} catch ( SQLException e ) {
			// TODO: This =P
			e.printStackTrace( ); // Temporary for debugging.
		}
		
		return null;
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
			unban = CommandUtils.toDate( input.arg( 1 ), input.flag( 't' ) );
			
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
			} else if ( unban > System.currentTimeMillis( ) ) {
				event.disallow( PlayerLoginEvent.Result.KICK_BANNED, "Banned!" ); // TODO: Tell then for how long.
			} else {
				// TODO: Remove Ban
			}
		}
	}
	
	/*========================================================================================================*/
	
	public void mysqlEvent( PlayerLoginEvent event ) {
		
		MySQLBan q = mysqlGetBan( event.getPlayer( ).getName( ) );
		
		if ( q == null || q.getName( ).equals( null ) || q.getName( ).isEmpty( ) ) return;
		
		long unban = q.getUnban( ).getTimeInMillis( );
		
		if ( unban == 0 ) {
			event.disallow( PlayerLoginEvent.Result.KICK_BANNED, "permanently Banned!" );
		} else if ( unban > System.currentTimeMillis( ) ) {
			event.disallow( PlayerLoginEvent.Result.KICK_BANNED, "Banned!" ); // TODO: Tell then for how long.
		} else {
			// TODO: Remove Ban
			System.out.println( "No longer banned :D" );
		}
	}
	
	/*========================================================================================================*/
	
	private creatorPlugin		plugin;
	
	private File				bansFile;
	private YamlConfiguration	YamlBans;
	
	boolean						enabled;
	boolean						useMysql;
}
