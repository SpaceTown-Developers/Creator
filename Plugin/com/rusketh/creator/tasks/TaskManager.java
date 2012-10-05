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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.rusketh.creator.CreatorPlugin;

public class TaskManager implements Listener, Runnable {
	
	public TaskManager( CreatorPlugin plugin ) {
		this.plugin = plugin;
		
		sessions = new HashMap< String, TaskSession >( );
		reloadSessions( );
		
		plugin.getServer( ).getPluginManager( ).registerEvents( this, plugin );
		plugin.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, this, 20L, 20L);
	}
	
	/*========================================================================================================*/
	
	public void reloadSessions( ) {
		for ( Player player : plugin.getServer( ).getOnlinePlayers( ) ) {
			TaskSession oldSession = sessions.get( player.getName( ) );
			TaskSession newSession = new TaskSession( plugin, player );
			
			if ( oldSession != null ) {
				oldSession.stopTask();
			}
			
			sessions.put( player.getName( ), newSession );
		}
	}
	
	public void closeSessions( ) {
		for ( Player player : plugin.getServer( ).getOnlinePlayers( ) ) {
			sessions.get( player.getName( ) ).stopTask();
		}
	}
	
	public TaskSession getSession(Player player) {
		return sessions.get(player.getName( ));
	}
	
	/*========================================================================================================*/
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		TaskSession oldSession = sessions.get( player.getName( ) );
		
		if ( oldSession == null ) {
			sessions.put( player.getName( ), new TaskSession( plugin, player ) );
		} else {
			oldSession.setPlayer(player);
			if ( oldSession.isTaskPaused() ) player.sendMessage("You have paused editor task (use resume).");
		}
	}
	
	/*========================================================================================================*/
	
	@EventHandler
	public void playerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		sessions.get( player.getName( ) ).pauseTask();
	}
	
	/*========================================================================================================*/
	
	public void run( ) {
		for (TaskSession session : sessions.values( )) session.runTask( );
	}
	
	/*========================================================================================================*/
	
	CreatorPlugin					plugin;
	HashMap< String, TaskSession >	sessions;
	
}
