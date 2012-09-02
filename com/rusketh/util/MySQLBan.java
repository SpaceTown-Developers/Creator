package com.rusketh.util;

import java.util.Calendar;

public class MySQLBan {
	
	public MySQLBan( String name, String ip, String banner, String reason, Calendar timeBanned, Calendar unban ) {
		this.name = name;
		this.ip = ip;
		this.banner = banner;
		this.reason = reason;
		this.timeBanned = timeBanned;
		this.unban = unban;
	}
	
	/**
	 * @return the name
	 */
	public String getName( ) {
		return name;
	}
	
	/**
	 * @return the ip
	 */
	public String getIp( ) {
		return ip;
	}
	
	/**
	 * @return the banner
	 */
	public String getBanner( ) {
		return banner;
	}
	
	/**
	 * @return the reason
	 */
	public String getReason( ) {
		return reason;
	}
	
	/**
	 * @return the time_banned
	 */
	public Calendar getTimeBanned( ) {
		return timeBanned;
	}
	
	/**
	 * @return the unban
	 */
	public Calendar getUnban( ) {
		return unban;
	}
	
	private String	name, ip, banner, reason;
	private Calendar	timeBanned, unban;
	
}
