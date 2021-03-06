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

package com.rusketh.creator;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.rusketh.creator.Extensions.ExtensionManager;
import com.rusketh.creator.blocks.ItemID;
import com.rusketh.creator.commands.CommandManager;
import com.rusketh.creator.module.ModuleManager;
import com.rusketh.creator.tasks.TaskManager;

public class CreatorPlugin extends JavaPlugin {
	
	private ConsoleCommandSender	sender;
	
	public void logMessage( String string ) {
		sender.sendMessage( "[Creator] " + ChatColor.GREEN + string );
	}
	
	public void errorMessage( String string ) {
		sender.sendMessage( "[Creator] " + ChatColor.RED + string );
	}
	
	/*========================================================================================================*/
	
	/**
	 * Called when Creator is enabled.
	 * 
	 * @author Rusketh
	 */
	
	public void onEnable( ) {
			logger = getServer( ).getLogger( );
			sender = getServer( ).getConsoleSender( );
			getLogger( ).info( "Loading" );
			
			configFile = new File( getDataFolder( ), "config.yml" );
			if ( !configFile.exists( ) ) {
				try {
					configFile.getParentFile( ).mkdirs( );
					configFile.createNewFile( );
				} catch ( IOException e ) {
					getLogger( ).severe( "Unable to create new configuration!" );
				}
			}
			
			reloadConfig( );
			
			if ( !Enabled ) {
				getLogger( ).info( "Disabled by config." );
				return;
			}
			
			setupEconomy( );
	
			configManager = new ConfigManager( this );
			mysqlManager = new MysqlManager( this );
			taskManager = new TaskManager( this );
			commandManager = new CommandManager( this );
			extensionManager = new ExtensionManager( this );
			moduleManager = new ModuleManager( this );
			
			getLogger( ).info( "Loaded" );
	}
	
	/*========================================================================================================*/
	
	/**
	 * Called when Creator is disabled.
	 * 
	 * @author Rusketh
	 */
	
	public void onDisable( ) {
		if ( !Enabled ) return;
		
			moduleManager.shutDown();
			extensionManager.shutDown();
			taskManager.closeSessions( );
			mysqlManager.disconnect( );
	}
	
	/*========================================================================================================*/
	
	/**
	 * Reloads the entire plugin.
	 * 
	 * @author Rusketh
	 */
	
	public void reload() {
		onDisable( );
		
		logger = null;
		economy = null;
		commandManager = null;
		taskManager = null;
		mysqlManager = null;
		extensionManager = null;
		configManager = null;
		moduleManager = null;
		configFile = null;
		YamlConfig = null;
		cmdPrefix = null;
		
		System.gc( ); //Note: We force garbage collection!
		
		onEnable( );
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets the plugins main configuration file.
	 * 
	 * @return {@link FileConfiguration}
	 * @author Rusketh
	 */
	
	@Override
	public FileConfiguration getConfig( ) {
		return YamlConfig;
	}
	
	/**
	 * Saves the plugins main configuration file.
	 * 
	 * @author Rusketh
	 */
	
	@Override
	public void saveConfig( ) {
		try {
			YamlConfig.save( configFile );
		} catch ( Exception e ) {
			getLogger( ).info( "Failed to save config" );
			Enabled = false;
		}
	}
	
	/**
	 * Reloads the plugins main configuration file.
	 * Creates default keys and values and loads.
	 * 
	 * @author Rusketh
	 */
	
	@Override
	public void reloadConfig( ) {
		YamlConfig = new YamlConfiguration( );
		YamlConfigurationOptions YamlOptions = YamlConfig.options( );
		YamlOptions.pathSeparator( '.' );
		YamlOptions.copyDefaults( true );
		
		try {
			YamlConfig.load( configFile );
			
			YamlConfig.addDefault( "enabled", true );
			YamlConfig.addDefault( "usevault", false );
			YamlConfig.addDefault( "blockrate", 500 );
			YamlConfig.addDefault( "maxblocks", -1 );
			YamlConfig.addDefault( "maxradius", -1 );
			YamlConfig.addDefault( "commands.custom.enable", true );
			YamlConfig.addDefault( "commands.custom.prefix", "!" );
			YamlConfig.addDefault( "debug", false );
			YamlConfig.addDefault( "wandid", ItemID.WOOD_AXE );
			
			saveConfig( );
			
			Enabled = YamlConfig.getBoolean( "enabled" );
			Vault = YamlConfig.getBoolean( "usevault" );
			WandID = YamlConfig.getInt( "wandid" );
			BlockRate = YamlConfig.getInt( "blockrate" );
			MaxBlocks = YamlConfig.getInt( "maxblocks" );
			MaxRadius = YamlConfig.getInt( "maxradius" );
			cmdUse = YamlConfig.getBoolean( "commands.custom.enable" );
			cmdPrefix = YamlConfig.getString( "commands.custom.prefix" ).toLowerCase( );
			Debug = YamlConfig.getBoolean( "debug" );
			
			if ( Debug ) logger.info("[Creator] Debuggin enabled.");
			
		} catch ( Exception e ) {
			getLogger( ).severe( "Unable to load configuration!" );
			Enabled = false;
		}
	}
	
	/**
	 * Used to save then reloads the configuration file.
	 * Calls {@link CreatorPlugin.reloadConfig}
	 * 
	 * @author Rusketh
	 */
	
	protected void refreshConfig( ) {
		try {
			YamlConfig.save( configFile );
			reloadConfig( );
		} catch ( IOException e ) {
			getLogger( ).warning( "Failed to write changed config.yml: " + e.getMessage( ) );
			Enabled = false;
		}
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets and sets up the Vault Economy plugin.
	 * 
	 * @author Rusketh
	 */
	
	private void setupEconomy( ) {
		if ( Vault && getServer( ).getPluginManager( ).getPlugin( "Vault" ) == null ) {
			getLogger( ).info( "Vault not found." );
			
			Vault = false;
			
		} else if ( Vault ) {
			RegisteredServiceProvider< Economy > economyProvider = getServer( ).getServicesManager( ).getRegistration( net.milkbowl.vault.economy.Economy.class );
			
			if ( economyProvider != null ) {
				economy = economyProvider.getProvider( );
			}
			
			Vault = ( economy != null );
			
			if ( !Vault ) {
				getLogger( ).info( "No Economy plugin found." );
				
			} else if ( !economy.isEnabled( ) ) {
				getLogger( ).info( "Economy plugin is disabled." );
				
				Vault = false;
			} else {
				getLogger( ).info( "Vault Economy enabled." );
			}
		}
	}
	
	/**
	 * Gets the Economy API.
	 * 
	 * @return {@link Economy}
	 * @author Rusketh
	 */
	
	public Economy getEconomy( ) {
		return economy;
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets the Command Manager.
	 * The command manager registers and handles all the commands.
	 * 
	 * @return {@link CommandManager}
	 * @author Rusketh
	 */
	
	public CommandManager getCommandManager( ) {
		return commandManager;
	}
	
	/**
	 * Called by Bukkit when the /Creator command is called.
	 * 
	 * @author Rusketh
	 */
	
	public boolean onCommand( CommandSender sender, Command command, String commandLabel, String[] args ) {
		if ( commandLabel.equalsIgnoreCase( "creator" ) || commandLabel.equalsIgnoreCase( "cr" ) ) { return commandManager.run( sender, args ); }
		
		return false;
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets the mysql Manager.
	 * The mysql manager handels the mains mysql connection.
	 * 
	 * @return {@link MysqlManager}
	 * @author Rusketh
	 */
	
	public MysqlManager getMysqlManager( ) {
		return mysqlManager;
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets the Task Manager.
	 * The task manager handles all player build tasks.
	 * 
	 * @return {@link TaskManager}
	 * @author Rusketh
	 */
	
	public TaskManager getTaskManager( ) {
		return taskManager;
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets the Task Manager.
	 * The task manager handles all player build tasks.
	 * 
	 * @return {@link ExtensionManager}
	 * @author Rusketh
	 */
	
	public ExtensionManager getExtensionManager( ) {
		return extensionManager;
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets the Configuration Manager.
	 * The configuration manager handles all configuration loading.
	 * 
	 * @return {@link ConfigManager}
	 * @author Oskar
	 */
	
	public ConfigManager getConfigManager( ) {
		return configManager;
	}
	
	/*========================================================================================================*/
	
	/**
	 * Displays exception information if debugging is enabled.
	 * 
	 * @author Rusketh
	 */
	
	public void debug( Throwable e ) {
		if ( !Debug | e == null ) return;
		logger.info("[Creator] Debug: ?".replace("?", e.toString()));
		e.printStackTrace();
	}
	
	/*========================================================================================================*/
	
	public Logger				logger;
	private static Economy		economy;
	
	private CommandManager		commandManager;
	private TaskManager			taskManager;
	private MysqlManager		mysqlManager;
	private ExtensionManager	extensionManager;
	private ConfigManager		configManager;
	private ModuleManager		moduleManager;
	
	private File				configFile;
	private YamlConfiguration	YamlConfig;
	
	public boolean				Debug;
	public boolean				Enabled;
	public boolean				Vault;
	
	public int					WandID;
	public int					BlockRate;
	public int					MaxBlocks;
	public int					MaxRadius;
	
	public boolean				cmdUse;
	public String				cmdPrefix;
	
}
