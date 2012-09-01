package com.rusketh.creator.listeners;

import org.bukkit.command.CommandException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.rusketh.creator.creatorPlugin;

public class PlayerListener implements Listener {
	
	public PlayerListener( creatorPlugin plugin ) {
		this.plugin = plugin;
	}
	
	/*========================================================================================================*/
	
	@EventHandler
	public void PlayerChat(AsyncPlayerChatEvent event) {
		if (plugin.cmdUse && event.getMessage( ).toLowerCase( ).startsWith( plugin.cmdPrefix )) {
			try {
				boolean result = plugin.getCommandManager( ).run( event.getPlayer( ), event.getMessage( ).substring( plugin.cmdPrefix.length( ) ).split( " " ) );
				if (result) event.setCancelled( true );
			} catch (CommandException e) {
				event.getPlayer().sendMessage( e.getMessage( ) );
			}
		}
	}
	
	/*========================================================================================================*/
	
	creatorPlugin	plugin;
}
