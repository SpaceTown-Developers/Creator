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
import java.sql.SQLException;

public class MySqlBan extends Ban {
	
	private String	name, ip;
	
	public MySqlBan( String name, String ip, String banner, String reason, long timeBanned, long unban ) {
		super( banner, reason, timeBanned, unban );
		this.name = name;
		this.ip = ip;
	}
	
	public MySqlBan( ResultSet result ) throws SQLException {
		super( result );
		this.name = result.getString( "user" );
		this.ip = result.getString( "ip" );
	}
	
	/**
	 * @return the name of the banned player.
	 */
	public String getName( ) {
		return this.name;
	}
	
	/**
	 * @return the users ip from when they where banned.
	 */
	public String getIp( ) {
		return this.ip;
	}
	
}
