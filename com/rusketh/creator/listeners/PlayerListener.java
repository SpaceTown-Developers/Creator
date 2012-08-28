package com.rusketh.creator.listeners;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import com.rusketh.creator.creatorPlugin;

@SuppressWarnings( "deprecation" )
public class PlayerListener implements Listener {
	
	public PlayerListener( creatorPlugin plugin ) {
		this.plugin = plugin;
	}
	
	/*========================================================================================================*/
	
	@EventHandler
	public void PlayerChat(PlayerChatEvent event) {
		if (plugin.cmdUse && event.getMessage( ).toLowerCase( ).startsWith( plugin.cmdPrefix )) {
			plugin.getCommandManager( ).onCommand( (CommandSender) event.getPlayer( ), "cr", event.getMessage( ).substring( plugin.cmdPrefix.length( ) ).split( " " ) );
		}
	}
	
	/*========================================================================================================*/
	
	creatorPlugin	plugin;
}
