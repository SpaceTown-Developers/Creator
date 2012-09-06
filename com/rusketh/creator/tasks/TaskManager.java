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

package com.rusketh.creator.tasks;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.rusketh.creator.CreatorPlugin;

public class TaskManager {
	
	public TaskManager( CreatorPlugin plugin ) {
		this.plugin = plugin;
		
		sessions = new HashMap< String, TaskSession >( );
		
		reloadSessions( );
	}
	
	public void reloadSessions( ) {
		for ( Player player : plugin.getServer( ).getOnlinePlayers( ) ) {
			TaskSession oldSession = sessions.get( player.getName( ) );
			TaskSession newSession = new TaskSession( plugin, player );
			
			if ( oldSession != null ) {
				// oldSession.stop();
			}
			
			sessions.put( player.getName( ), newSession );
		}
	}
	
	public void closeSessions( ) {
		
	}
	
	CreatorPlugin					plugin;
	HashMap< String, TaskSession >	sessions;
}
