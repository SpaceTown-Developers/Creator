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

package com.rusketh.creator;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;


public class mysqlManager {
	
	/**
	 * mysql Manager handles the main mysql connection.
	 * @author Rusketh
	 */
	
	public mysqlManager(creatorPlugin plugin) {
		this.plugin = plugin;
		
		loadConfig();
		
		if (!enabled) return;
		
		connect();
	}
	
	/*========================================================================================================*/
	
	/**
	 * Loads the mysql connection settings from the main config.
	 * @author Rusketh
	 */
	
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
	
	/**
	 * Established the connection to the database.
	 * @author Rusketh
	 */
	
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
	
	/**
	 * Restablished the connection to the database.
	 * @author Rusketh
	 */
	
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
	
	/**
	 * closes the connection to the database.
	 * @author Rusketh
	 */
	
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
	
	/**
	 * Is the mysql manager enabled.
	 * Disabled by default in main config.
	 * Will disabled on connection error.
	 * @return {@link Boolean}
	 * @author Rusketh
	 */
	
	public boolean isEnabled() {
		return enabled;
	}
	
	/*========================================================================================================*/
	
	/**
	 * Used to get a query object from the main connection.
	 * @return {@link PreparedStatement}
	 * @author Rusketh
	 */
	
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
