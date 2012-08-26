package com.rusketh.creator.commands.manager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import com.rusketh.creator.creatorPlugin;

public class command {
	
	
	public command(creatorPlugin plugin, commandAnote anote, Object baseClass, Method method) {
		this.plugin = plugin;
		this.baseClass = baseClass;
		this.method = method;
		
		this.name = anote.names()[0];
		this.example = anote.example();
		this.desc = anote.desc();
		this.least = anote.least();
		this.most = anote.most();
		this.player = anote.player();
		this.console = anote.console();
		this.perms = anote.perms();
		
		settings = plugin.getCommandNode().getConfigurationSection(this.name);
		
		if (settings == null) {
			settings = plugin.getCommandNode().createSection(this.name);
			
			settings.set("enabled", true);
			
			if (anote.usePrice() != -1) {
				settings.set("price", anote.usePrice());
			}
			
			if (anote.blockPrice() != -1) {
				settings.set("blockprice", anote.blockPrice());
			}
		}
		
		this.enabled = settings.getBoolean("enabled", true);
		this.usePrice = settings.getInt("price", anote.usePrice());
		this.blockPrice = settings.getInt("blockprice", anote.blockPrice());
	}

/*========================================================================================================*/

	public ConfigurationSection getConfig() {
		return settings;
	}

/*========================================================================================================*/
	
	public boolean execute(creatorPlugin plugin, CommandSender sender, String[] perams) {
		if (!this.enabled) {
			throw new CommandException("This command has been disabled.");
		}
		
		int size = perams.length -1;
		
		// Note: Going to use string builders here, Java is shit at combining strings with out them!
		if (size < this.least && this.least != -1) {
			throw new CommandException( new StringBuilder("Not enogh perameters. [").append(size).append("/").append(this.least).append("]\n").append(this.example).toString() );
		} else if (size > this.most && this.most != -1) {
			throw new CommandException( new StringBuilder("Too meany perameters. [").append(size).append("/").append(this.most).append("]\n").append(this.example).toString() );
		}
		
		boolean isPlayer = (sender instanceof Player);
		
		if (isPlayer && !this.player) {
			throw new CommandException("This command can not be called by players.");
		} else if ( !isPlayer && !this.console) {
			throw new CommandException("This command can not be called from console.");
		}
		
		if (isPlayer) { // Note: Check permissions?
			Player player = (Player)sender;
			
			if (!hasPermission(player)) { // Note: Player is not allowed.
				throw new CommandException("Sorry but you do not have permission to do that.");
			}
			
			// Note: Check vault is enabled and that player could be charged for this.
			if (plugin.Vault && !player.hasPermission("creator.commands.free") && !player.hasPermission(new StringBuilder("creator.commands.").append(this.name).append(".free").toString()) ) {
				
				// Note: Check vault prices.
				if (this.usePrice > 0 && plugin.getEconomy().getBalance(player.getName()) < this.usePrice) {
					throw new CommandException("Sorry but you can not afford to do that.");
				}
			}
		}
		
		// Note: All checks have been passed =D
		
		String[] perameters = new String[size];
		
		for (int i = 0; i <= size - 1; i++) {
			perameters[i] = perams[i + 1];
		}
		
		return invoke(sender, perameters);
	}

/*========================================================================================================*/
	
	public boolean invoke(CommandSender sender, String[] perameters) {
		try {
			return (Boolean) this.method.invoke(this.baseClass, this, sender, perameters);
		} catch (InvocationTargetException e) {
			
			plugin.logger.info(new StringBuilder("Creator failed to invoke command ").append(this.name).toString());
			
			if (e.getCause() != null) {
				plugin.logger.info(e.getCause().getMessage());
				e.getCause().printStackTrace();
			}
			
			throw new CommandException("Oooops, somthing went horribad.");
			
		} catch (Exception e) {
			plugin.logger.info(new StringBuilder("Creator failed to invoke command ").append(this.name).toString());
			
			e.printStackTrace();
			
			throw new CommandException("Oooops, somthing went horribad.");
		}
	}
	
/*========================================================================================================*/
	
	public boolean hasPermission(Player player) {
		if (this.perms.length == 0) {
			return true; //No permission nodes.
		}
		
		boolean allowed = false;
		
		for (String perm : this.perms) {
			if (player.hasPermission(perm)) {
				allowed = true;
				break;
			} 
		}
		
		return allowed;
	}
	
/*========================================================================================================*/
	
	public String getHelp() {
		
		StringBuilder msg = new StringBuilder(ChatColor.YELLOW.toString()).append(this.example).append(" ");
		
		if (this.usePrice > 0) { //Add price data
			msg.append("(£").append(ChatColor.RED.toString()).append(this.usePrice).append(")");
		}
		
		return msg.append("\n").append(ChatColor.BLUE.toString()).append(this.desc).toString();
	}
	
/*========================================================================================================*/
	
	private creatorPlugin plugin;
	private Object baseClass;
	private Method method;

	private ConfigurationSection settings;
	
	private String name;
	private String example;
	private String desc;
	
	private int least;
	private int most;
	public int usePrice;
	public int blockPrice;
	
	private boolean player;
	private boolean console;
	private boolean enabled;
	
	private String[] perms;
	
	
}
