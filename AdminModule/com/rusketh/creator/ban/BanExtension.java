package com.rusketh.creator.ban;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.command.CommandException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import com.rusketh.creator.ConfigManager;
import com.rusketh.creator.MysqlManager;
import com.rusketh.creator.Extensions.Extension;

public class BanExtension extends Extension {
	
	protected String name = "admin.ban";
	
	/*========================================================================================================*/
	
	public boolean enable( ) {
		if ( !loadConfig( ) ) return false;
		loadBans( );
		
		configManager = plugin.getConfigManager( );
		
		YamlConfiguration c = configManager.createConfig( );
		
		configManager.saveConfig( c, "test" );
		
		return true;
	}
	
	/*========================================================================================================*/
	
	private boolean loadConfig( ) {
		YamlConfiguration YamlConfig = (YamlConfiguration) plugin.getConfig( );
		
		YamlConfig.addDefault( "bans.enable", false );
		YamlConfig.addDefault( "bans.mysql", false );
		YamlConfig.addDefault( "bans.ipbans", false );
		
		useMysql = YamlConfig.getBoolean( "bans.mysql" );
		ipBans = YamlConfig.getBoolean( "bans.ipbans" );
		
		return YamlConfig.getBoolean( "bans.enable" );
	}
	
	/*========================================================================================================*/
	
	private void loadBans( ) {
		if ( useMysql && plugin.getMysqlManager( ).isEnabled( ) ) {
			setUpDB( );
			return;
		}
		
		YamlBans = plugin.getConfigManager( ).loadConfig( "bans" );
		if ( ipBans ) YamlIpBans = plugin.getConfigManager( ).loadConfig( "ipbans" );
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
				
				useMysql = false;
				loadBans( );
				useMysql = true;
				
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
			
			/* TODO:
			 * Create ip bans table in the DB.
			 */
			if ( ipBans ) {
				for ( @SuppressWarnings( "unused" ) String name : YamlIpBans.getKeys( false ) ) {
				}
			}
			
		} catch ( SQLException e ) {
			plugin.getLogger( ).warning( "Error creating MYSQL ban database." );
			plugin.getLogger( ).warning( "Reverting to bans.yml." );
			plugin.getLogger( ).warning( e.getMessage( ) );
			e.printStackTrace( );
			
			useMysql = false;
		}
	}
	
	private void saveDB( Ban ban, boolean ipban) {
		try {
			MysqlManager mysql = plugin.getMysqlManager( );
			PreparedStatement query;
			
			if ( ipban ) {
				if ( !ipBans ) return;
				if ( getBan( ban.getName( ), true ) == null ) {
					query = mysql.query( "INSERT INTO `creator_bans` (ip, banner, reason_banned, time_banned, unban, user) VALUES (?, ?, ?, ?, ?, ?)" );
				} else {
					query = mysql.query( "UPDATE `creator_bans` SET ip = ?, banner = ?, reason_banned = ?, time_banned = ?, unban = ? WHERE user = ?" );
				}
			} else
			
			if ( getBan( ban.getName( ) ) == null ) {
				query = mysql.query( "INSERT INTO `creator_bans` (ip, banner, reason_banned, time_banned, unban, user) VALUES (?, ?, ?, ?, ?, ?)" );
			} else {
				query = mysql.query( "UPDATE `creator_bans` SET ip = ?, banner = ?, reason_banned = ?, time_banned = ?, unban = ? WHERE user = ?" );
			}
			
			query.setString( 1, ban.getIp( ) );
			query.setString( 2, ban.getBanner( ) );
			query.setString( 3, ban.getReason( ) );
			query.setLong( 4, ban.getTimeBanned( ) );
			query.setLong( 5, ban.getUnban( ) );
			query.setString( 6, ban.getName( ) );
			
			query.execute( );
			query.close( );
			
		} catch ( SQLException e ) {
			// TODO: This...
			e.printStackTrace( );
		}
	}

	private Ban getBan( String name, boolean b ) {
		return null;
	}

	private Ban getBan( String name ) {
		return null;
	}

	/*========================================================================================================*/
	
	public void addBan( String ip, String name, String banner, long unban, String reason, boolean ipban ) {
		if ( useMysql ) {
			saveDB( new Ban( name, ip, banner, reason, System.currentTimeMillis( ) / 1000, unban ), ipban );
		} else if ( name != null ) { 
			
		}
	}
	
	/*========================================================================================================*/
	
	public void addIpBan( String ip, String name, String banner, long unban, String reason ) {
		
	}
	
	/*========================================================================================================*/
	
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
					if ( future )
						cal.set( Calendar.DAY_OF_YEAR, count );
					else
						cal.set( Calendar.DAY_OF_MONTH, count );
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
	
	private YamlConfiguration	YamlBans;
	private YamlConfiguration	YamlIpBans;
	
	private ConfigManager		configManager;
	
	private boolean				ipBans;
	private boolean				useMysql;
	
	public final Pattern		pattern	= Pattern.compile( "([0-9]+)([yjdhmsw])" );
	
}
