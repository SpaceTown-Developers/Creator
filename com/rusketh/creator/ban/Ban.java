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

public abstract class Ban {
	
	private String	banner, reason;
	private long	timeBanned, unban;
	
	protected Ban( String banner, String reason, long timeBanned, long unban ) {
		this.banner = banner;
		this.reason = reason;
		this.timeBanned = timeBanned;
		this.unban = unban;
	}
	
	protected Ban( ResultSet result ) throws SQLException {
		this.banner = result.getString( "banner" );
		this.reason = result.getString( "reason_banned" );
		this.timeBanned = result.getLong( "time_banned" );
		this.unban = result.getLong( "unban" );
	}
	
	/**
	 * @return the name of the name who issued the ban.
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
