package com.rusketh.creator.tasks;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.rusketh.creator.creatorPlugin;

public class taskManager {
	
	public taskManager( creatorPlugin plugin ) {
		this.plugin = plugin;
		
		sessions = new HashMap<String, taskSession>( );
		
		reloadSessions( );
	}
	
	/*========================================================================================================*/
	
	public void reloadSessions( ) {
		for ( Player player : plugin.getServer().getOnlinePlayers() ) {
			taskSession oldSession = sessions.get( player.getName() );
			taskSession newSession = new taskSession (plugin, player );
			
			if ( oldSession != null ) {
				//oldSession.stop();
			}
			
			sessions.put( player.getName(), newSession );
		}
	}
	
	creatorPlugin					plugin;
	HashMap<String, taskSession>	sessions;
}