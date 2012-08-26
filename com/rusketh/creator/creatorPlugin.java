package com.rusketh.creator;

import java.io.File;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.rusketh.creator.commands.helpCommands;
import com.rusketh.creator.commands.manager.commandManager;
import com.rusketh.creator.tasks.taskManager;

public class creatorPlugin extends JavaPlugin {
	
/*========================================================================================================*/
	
	public void onEnable() {
		logger = getServer().getLogger();
		logger.info("Loading Creator");
		
		loadConfig();
		
		if (!Enabled) {
			logger.info("Creator has been disabled.");
			
			return;
		}
		
		commandManager = new commandManager(this);
		taskManager = new taskManager(this);
		
		registerCommands();
	}
	
/*========================================================================================================*/	
	
	public void loadConfig() {
		try {
			configFile = new File(getDataFolder(), "config.yml");
			config = this.getConfig();
			
			if (!configFile.exists()) {
				config.set("enabled", true);
				config.set("usevault", true);
				config.set("blockrate", 500);
				config.set("maxblocks", -1);
				config.set("maxradius", -1);
				
			} else {
				
				config.load(configFile);
			}
			
			Enabled = config.getBoolean("enabled", true);
			Vault = config.getBoolean("usevault", true);
			BlockRate = config.getInt("blockrate", 500);
			MaxBlocks = config.getInt("maxblocks", -1);
			MaxRadius = config.getInt("maxradius", -1);
			
			commandSettings = config.getConfigurationSection("commands");
			
			if (commandSettings == null) {
				commandSettings = config.createSection("commands");
			}
			
			saveConfig();
			
		} catch (Exception e) {
			logger.info("Creator failed to load config");
			Enabled = false;
		}
	}
	
	public void saveConfig() {
		try {
			config.save(configFile);
		} catch (Exception e) {
			logger.info("Creator failed to save config");
		}
	}
	
	public ConfigurationSection getCommandNode() {
		return commandSettings;
	}
	
/*========================================================================================================*/
	
	@SuppressWarnings("unused")
	private boolean setupEconomy() {
		if (Vault) {
			RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	        if (economyProvider != null) {
	            economy = economyProvider.getProvider();
	        }

	        Vault = (economy != null);
	        
	        if (!Vault) {
	        	logger.info("Creator could not load Vault please check your vault plugin.");
	        	
	        } else if (!economy.isEnabled()) {
	        	logger.info("Creator has detected that vault is dissabled.");
	        	Vault = false;
	        }
		}
		
		return Vault;
	}
	
	public Economy getEconomy() {
		return economy;
	}
	
/*========================================================================================================*/
	
	public void registerCommands() {
		commandManager.registerCommands(new helpCommands(this));
		
		saveConfig();
	}
	
	public commandManager getCommandManager() {
		return commandManager;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] perams) {
		return commandManager.onCommand(sender, command.getName(), perams);
	}

/*========================================================================================================*/
	
	public taskManager getTaskManager() {
		return taskManager;
	}
	
/*========================================================================================================*/	
	
	public Logger logger;
	private static Economy economy;
	
	private commandManager commandManager;
	private taskManager taskManager;
	
	private File configFile;
	private FileConfiguration config;
	private ConfigurationSection commandSettings;
	
	public Boolean Enabled;
	public Boolean Vault;
	
	public int BlockRate;
	public int MaxBlocks;
	public int MaxRadius;
	
}
