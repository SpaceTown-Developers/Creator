package com.rusketh.creator.listeners;

import org.bukkit.event.Listener;

import com.rusketh.creator.creatorPlugin;

public class PlayerListener implements Listener {
	
	public PlayerListener( creatorPlugin plugin ) {
		this.plugin = plugin;
	}
	
	/*========================================================================================================*/
	
	creatorPlugin	plugin;
}
