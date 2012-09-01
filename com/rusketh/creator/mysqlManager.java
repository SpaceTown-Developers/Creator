package com.rusketh.creator;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;


public class mysqlManager {
	
	public mysqlManager(creatorPlugin plugin) {
		this.plugin = plugin;
		
		loadConfig();
		
		if (!enabled) return;
		
		connect();
	}
	
	/*========================================================================================================*/
	
	private void loadConfig() {
		FileConfiguration YamlConfig = plugin.getConfig( );
		
		if ( !YamlConfig.contains( "mysql.use" ) ) YamlConfig.set( "mysql.use", false );
		if ( !YamlConfig.contains( "mysql.host" ) ) YamlConfig.set( "mysql.host", "localhost" );
		if ( !YamlConfig.contains( "mysql.port" ) ) YamlConfig.set( "mysql.port", 3306 );
		if ( !YamlConfig.contains( "mysql.database" ) ) YamlConfig.set( "mysql.database", "creator" );
		if ( !YamlConfig.contains( "mysql.user" ) ) YamlConfig.set( "mysql.user", "username" );
		if ( !YamlConfig.contains( "mysql.pass" ) ) YamlConfig.set( "mysql.pass", "password" );
		
		enabled = YamlConfig.getBoolean( "mysql.use" );
		
		if (enabled) {
			host = YamlConfig.getString( "mysql.host" );
			port = YamlConfig.getInt( "mysql.port" );
			database = YamlConfig.getString( "mysql.database" );
			username = YamlConfig.getString( "mysql.user" );
			password = YamlConfig.getString( "mysql.pass" );
		}
	}
	
	/*========================================================================================================*/
	
	private void connect() {
		String url = new StringBuilder("jdbc:mysql://").append( host ).append(":").append(port).append("/").append( database ).toString( );
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch ( SQLException e ) {
			plugin.logger.warning("[Creator] Unable to connect to MYSQL database.");
			plugin.logger.warning(e.getMessage( ));
			enabled = false;
		} finally {
			plugin.logger.info( "[Creator] Connected to MYSQL database." );
		}
	}
	
	public boolean reconnect() {
		if (!enabled) return false;
		
		if (connection == null) connect();
		
		try {
			if (!connection.isClosed( )) disconnect();
			
			connect();
			
		} catch ( SQLException e ) {
			plugin.logger.warning("[Creator] Error reconnecting to MYSQL database.");
			plugin.logger.warning(e.getMessage( ));
			e.printStackTrace();
		}
		
		return enabled;
	}
	
	public void disconnect() {
		if (enabled && connection != null) {
			try {
				connection.close( );
			} catch ( SQLException e ) {
				plugin.logger.warning("[Creator] Error disconnecting from MYSQL database.");
				plugin.logger.warning(e.getMessage( ));
				e.printStackTrace();
			}
		}
	}
	
	/*========================================================================================================*/
	
	public boolean isEnabled() {
		return enabled;
	}
	
	/*========================================================================================================*/
	
	public PreparedStatement query(String query) {
		if (!enabled) return null;
		
		if (connection == null) connect();
		
		try {
			return connection.prepareStatement( query );
		} catch ( SQLException e ) {
			plugin.logger.warning("[Creator] Error preparing statment for MYSQL database.");
			plugin.logger.warning(e.getMessage( ));
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*========================================================================================================*/
	
	private creatorPlugin plugin;
	private Connection connection;
	
	private boolean enabled;
	
	private int port;
	private String host;
	private String database;
	private String username;
	private String password;
}
