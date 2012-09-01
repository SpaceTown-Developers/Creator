package com.rusketh.creator;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.rusketh.creator.commands.manager.CommandInput;
import com.rusketh.creator.commands.manager.commandAnote;


public class BanManager {
	
	public BanManager(creatorPlugin plugin) {
		this.plugin = plugin;
		
		loadConfig();
		
		if (!enabled) return;
		
		loadBans();
		
		plugin.getCommandManager( ).registerCommands( this );
	}
	
	/*========================================================================================================*/
	
	private void loadConfig() {
		
		FileConfiguration YamlConfig = plugin.getConfig( );
		
		if ( !YamlConfig.contains( "bans.enable" ) ) YamlConfig.set( "bans.enable", false );
		if ( !YamlConfig.contains( "bans.mysql" ) ) YamlConfig.set( "bans.mysql", false );
		
		enabled = YamlConfig.getBoolean( "bans.enable" );
		useMysql = YamlConfig.getBoolean( "bans.mysql" );
	}
	
	/*========================================================================================================*/
	
	private void loadBans() {
		if (plugin.mysqlManager( ).isEnabled( ) && useMysql) {
			setUpDB();
			return;
		}
		
		loadFromFile();
	}
	
	private void loadFromFile() {
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
	
	private void setUpDB() {
		mysqlManager mysql = plugin.mysqlManager( );
		
		try {
			ResultSet checkTable = mysql.query( "SHOW TABLES LIKE 'creator_bans'" ).executeQuery( );
			if (!checkTable.next( )) {
				
				mysql.query("CREATE TABLE `creator_bans` ( `ip` text, `user` text, `banner` text, `reason_banned` longtext, `time_banned` int(11) DEFAULT NULL, `unban` int(11) DEFAULT NULL)" ).execute( );
			
				loadFromFile();
				
				for (String name : YamlBans.getKeys( true )) {
					ConfigurationSection banData = YamlBans.getConfigurationSection(name);
					
					
					PreparedStatement query = mysql.query("INSERT INTO `creator_bans` (ip, user, banner, time_banned, reason_banned, unban) VALUES (?, ?, ?, ?, ?, ?)");
					
					query.setString( 2, name );
					query.setString( 1, banData.getString( "ip", "" ) );
					query.setString( 3, banData.getString( "banner", "" ) );
					query.setInt( 4, banData.getInt( "timebanned", 0 ) );
					query.setString( 5, banData.getString( "reason", "" ) );
					query.setInt( 6, banData.getInt( "unban", 0 ) );
					
					query.execute( );
					
				}
			}
		
		} catch (SQLException e) {
			plugin.logger.warning("[Creator] Error creating MYSQL bans database.");
			plugin.logger.warning("[Creator] Using to bans.YAML (not MYSQL).");
			plugin.logger.warning(e.getMessage( ));
			e.printStackTrace();
			
			useMysql = false;
		}
	
	}
	
	/*========================================================================================================*/
	
	public void addBan(Player target, CommandSender banner, long unban, String reason) {
		addBan(target.getAddress().toString( ), target.getName( ), banner.getName( ), unban, reason );
	}
	
	public void addBan(String name, String ip, String banner, long unban, String reason) {
		if (useMysql) {
			//TODO: This!
		} else if (name != null) {
			ConfigurationSection banData = YamlBans.getConfigurationSection(name);
			
			if (banData == null) {
				banData = YamlBans.createSection( name );
			}
			
			banData.set( "ip", ip );
			banData.set( "banner", banner );
			banData.set( "unban", unban );
			banData.set( "timebaned", System.currentTimeMillis( ));
			
			if (reason != null) banData.set( "reason", reason );
		}
	}
	
	/*========================================================================================================*/
	
	public long toDate(String time, boolean future) {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis( 0 );
		time = time.toLowerCase( );
		
		HashSet<Character> used = new HashSet<Character>();
		StringBuilder buffer = new StringBuilder();
		
		for (int i = 0; i < time.length( ); i++){
			String v = time.substring( i, i );
			
			if (v.matches("([0-9]")) {
				buffer.append(v);
				
			} else if (v.matches("([yjdhms]") || (future && v == "w") ) {
				if (buffer.length( ) == 0 || used.contains( v )) throw new CommandException("Invalid time format (e.g: 2d1h4m).");
				
				try {
					int count = Integer.parseInt( buffer.toString() );
					
					if (v == "y") { cal.add( count, Calendar.YEAR );
						
					} else if (v == "j") { cal.add( count, Calendar.MONTH );
						
					} else if (v == "h") { cal.add( count, Calendar.HOUR_OF_DAY );
						
					} else if (v == "m") { cal.add( count, Calendar.MINUTE );
						
					} else if (v == "s") { cal.add( count, Calendar.SECOND );
						
					} else if (v == "w") { cal.add( count, Calendar.WEEK_OF_YEAR );
						
					} else if (v == "d") {
						if (future) { cal.add( count, Calendar.DAY_OF_YEAR );
						} else { cal.add( count, Calendar.DAY_OF_MONTH ); }
					}
				} catch (NumberFormatException e) {
					throw new CommandException("Invalid time format (e.g: 2d1h4m).");
				}
				
			} else {
				throw new CommandException("Invalid time format (e.g: 2d1h4m).");
			}
		}
		
		return cal.getTimeInMillis();
	}
	
	/*========================================================================================================*/
	
	@commandAnote( names = { "ban" }, example = "ban <player> <time> [reason -t]|| ban <player> [reason] -p", desc = "Get help with a command.", least = 1, most = 3, console = true, flags = {"p", "t", "e", "s"})
	public boolean BanCommand(CommandSender sender, CommandInput input) {
		if (!enabled) return false;
		
		Player target;
		
		if (input.hasFlag( 'e' )) {
			target = plugin.getServer( ).getPlayerExact( input.arg(0) );
		} else {
			target = plugin.getServer( ).getPlayer( input.arg(0) );
		}
		
		if (target == null) throw new CommandException("Player not found.");
		
		long unban;
		String reason;
	
		if (input.flag( 'p' )) { //Permanent ban!
			if ( !(sender instanceof Player) || ((Player)sender).hasPermission( "creator.ban.perm" )) throw new CommandException("Your not allowed to ban players permanently.");
			
			unban = 0;
			reason = input.arg(1);
			
		} else {
			unban = toDate(input.arg(1), input.flag( 't' ));
			reason = input.arg(2);
			
			if (unban == 0) throw new CommandException("Ban lengh to short.");
		}
		
		addBan(target, sender, unban, reason);
		
		if (!input.hasFlag( 's' )) {
			plugin.getServer( ).broadcastMessage( new StringBuilder(target.getDisplayName( )).append( " has been baned!").toString( ));
			
			if (reason != null) {
				plugin.getServer( ).broadcastMessage( new StringBuilder("Reason: ").append(reason).toString( ));
			}
		}
		
		if (reason != null ) {
			target.kickPlayer( new StringBuilder("Banned: " ).append( reason ).toString( ));
		} else {
			target.kickPlayer("You have been banned from the server.");
		}
		
		return true;
	}
	
	/*========================================================================================================*/
	
	private creatorPlugin 		plugin;
	
	private File				bansFile;
	private YamlConfiguration	YamlBans;
	
	boolean						enabled;
	boolean						useMysql;
}
