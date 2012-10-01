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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Hashtable;

public class Ban {
	
	private String						banner, reason,
			name, ip;
	private long						timeBanned, unban;
	private Hashtable< String, Object >	sqlDump	= new Hashtable< String, Object >( );
	
	protected Ban( String name, String ip, String banner, String reason, long timeBanned, long unban ) {
		this.banner = banner;
		this.reason = reason;
		this.timeBanned = timeBanned;
		this.unban = unban;
		this.name = name;
		this.ip = ip;
	}
	
	protected Ban( ResultSet result ) throws SQLException {
		ResultSetMetaData data = result.getMetaData( );
		
		for ( int i = 1; i <= data.getColumnCount( ); i++ )
			sqlDump.put( data.getColumnName( i ), result.getObject( i ) );
		
		this.banner = result.getString( "banner" );
		this.reason = result.getString( "reason_banned" );
		this.timeBanned = result.getLong( "time_banned" );
		this.unban = result.getLong( "unban" );
		this.ip = result.getString( "ip" );
		this.name = result.getString( "name" );
		
		result.first( );
	}
	
	/**
	 * @return a object with the data in the column specified. Null if invalid column or not created with a ResultSet object.
	 */
	protected Object getColumn( String name ) {
		return sqlDump.get( name );
	}
	
	/**
	 * @return the name of the banned player.
	 */
	public String getName( ) {
		return this.name;
	}
	
	/**
	 * @return the ip of the banned player.
	 */
	public String getIp( ) {
		return this.ip;
	}
	
	/**
	 * @return the name of the player who issued the ban.
	 */
	public String getBanner( ) {
		return this.banner;
	}
	
	/**
	 * @return the reason banned.
	 */
	public String getReason( ) {
		return this.reason;
	}
	
	/**
	 * @return the time & date banned.
	 */
	public long getTimeBanned( ) {
		return this.timeBanned;
	}
	
	/**
	 * @return the time & date of unban.
	 */
	public long getUnban( ) {
		return this.unban;
	}
}
