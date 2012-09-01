/*
 * Creator - Bukkit Plugin
 * Copyright (C) 2012 Rusketh <www.Rusketh.com>
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

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.rusketh.creator.Extensions.BanExtension;
import com.rusketh.creator.Extensions.HelpExtension;
import com.rusketh.creator.commands.manager.commandManager;
import com.rusketh.creator.tasks.TaskManager;

public class creatorPlugin extends JavaPlugin {
	
	/*========================================================================================================*/
	
	/**
	 * Called when Creator is enabled.
	 * @author Rusketh
	 */
	
	public void onEnable( ) {
		logger = getServer( ).getLogger( );
		logger.info( "Loading Creator" );
		
		configFile = new File( getDataFolder( ), "config.yml" );
		if ( !configFile.exists( ) ) {
			try {
				configFile.createNewFile( );
			} catch ( IOException e ) {
				getLogger( ).severe( "[Creator] Unable to create new configuration!" );
			}
		}
		
		reloadConfig( );
		
		if ( !Enabled ) {
			logger.info( "[Creator] disabled by config." );
			return;
		}
		
		setupEconomy( );
		
		mysqlManager = new mysqlManager( this );
		taskManager = new TaskManager( this );
		commandManager = new commandManager( this );
		
		registerExtensions( );
	}
	
	/*========================================================================================================*/
	
	/**
	 * Called when Creator is disabled.
	 * @author Rusketh
	 */
	
	public void onDisable( ) {
		if ( !Enabled ) return;
		
		taskManager.closeSessions( );
		mysqlManager.disconnect( );
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets the plugins main configuration file.
	 * @return {@link FileConfiguration}
	 * @author Rusketh
	 */
	
	@Override
	public FileConfiguration getConfig( ) {
		return YamlConfig;
	}
	
	/**
	 * Saves the plugins main configuration file.
	 * @author Rusketh
	 */
	
	@Override
	public void saveConfig( ) {
		try {
			YamlConfig.save( configFile );
		} catch ( Exception e ) {
			logger.info( "[Creator] Failed to save config" );
			Enabled = false;
		}
	}
	
	/**
	 * Reloads the plugins main configuration file.
	 * Creates default keys and values and loads.
	 * @author Rusketh
	 */
	
	@Override
	public void reloadConfig( ) {
		YamlConfig = new YamlConfiguration( );
		YamlConfig.options( ).pathSeparator( '.' );
		try {
			YamlConfig.load( configFile );
			
			if ( !YamlConfig.contains( "enabled" ) ) YamlConfig.set( "enabled", true );
			if ( !YamlConfig.contains( "usevault" ) ) YamlConfig.set( "usevault", false );
			if ( !YamlConfig.contains( "blockrate" ) ) YamlConfig.set( "blockrate", 500 );
			if ( !YamlConfig.contains( "maxblocks" ) ) YamlConfig.set( "maxblocks", -1 );
			if ( !YamlConfig.contains( "maxradius" ) ) YamlConfig.set( "maxradius", -1 );
			if ( !YamlConfig.contains( "commands.custom.enable" ) ) YamlConfig.set( "commands.custom.enable", true );
			if ( !YamlConfig.contains( "commands.custom.prefix" ) ) YamlConfig.set( "commands.custom.prefix", "!" );
			
			Enabled = YamlConfig.getBoolean( "enabled" );
			Vault = YamlConfig.getBoolean( "usevault" );
			BlockRate = YamlConfig.getInt( "blockrate" );
			MaxBlocks = YamlConfig.getInt( "maxblocks" );
			MaxRadius = YamlConfig.getInt( "maxradius" );
			cmdUse = YamlConfig.getBoolean( "commands.custom.enable" );
			cmdPrefix = YamlConfig.getString( "commands.custom.prefix" ).toLowerCase( );
			
		} catch ( Exception e ) {
			getLogger( ).severe( "[Creator] Unable to load configuration!" );
			Enabled = false;
		}
	}
	
	/**
	 * Used to save then reloads the configuration file.
	 * Calls {@link creatorPlugin.reloadConfig}
	 * @author Rusketh
	 */
	
	protected void refreshConfig( ) {
		try {
			YamlConfig.save( configFile );
			reloadConfig( );
		} catch ( IOException e ) {
			getLogger( ).warning( "[Creator] Failed to write changed config.yml: " + e.getMessage( ) );
			Enabled = false;
		}
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets and sets up the Vault Economy plugin.
	 * @author Rusketh
	 */
	
	private void setupEconomy( ) {
		if ( Vault && getServer( ).getPluginManager( ).getPlugin( "Vault" ) == null ) {
			logger.info( "[Creator] Vault not found." );
			
			Vault = false;
			
		} else if ( Vault ) {
			RegisteredServiceProvider< Economy > economyProvider = getServer( ).getServicesManager( ).getRegistration( net.milkbowl.vault.economy.Economy.class );
			
			if ( economyProvider != null ) {
				economy = economyProvider.getProvider( );
			}
			
			Vault = ( economy != null );
			
			if ( !Vault ) {
				logger.info( "[Creator] No Economy plugin found." );
				
			} else if ( !economy.isEnabled( ) ) {
				logger.info( "[Creator] Economy plugin is disabled." );
				
				Vault = false;
			} else {
				logger.info( "[Creator] Vault Economy enabled." );
			}
		}
	}
	
	/**
	 * Gets the Economy API.
	 * @return {@link Economy}
	 * @author Rusketh
	 */
	
	public Economy getEconomy( ) {
		return economy;
	}
	
	/*========================================================================================================*/
	
	/**
	 * Loads the Creator Extensions.
	 * 
	 * @author Rusketh
	 */
	
	private void registerExtensions( ) {
		new HelpExtension( this );
		new BanExtension( this );
		
		saveConfig( );
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets the Command Manager.
	 * The command manager registers and handels all the commands.
	 * @return {@link CommandManager}
	 * @author Rusketh
	 */
	
	public commandManager getCommandManager( ) {
		return commandManager;
	}
	
	/**
	 * Called by Bukkit when the /Creator command is called.
	 * @author Rusketh
	 */
	
	public boolean onCommand( CommandSender sender, Command command, String commandLabel, String[] args ) {
		if ( commandLabel.equalsIgnoreCase( "creator" ) || commandLabel.equalsIgnoreCase( "cr" ) ) {
			return commandManager.run( sender, args );
		}
		
		return false;
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets the mysql Manager.
	 * The mysql manager handels the mains mysql connection.
	 * @return {@link mysqlManager}
	 * @author Rusketh
	 */
	
	public mysqlManager mysqlManager( ) {
		return mysqlManager;
	}
	
	/*========================================================================================================*/
	
	/**
	 * Gets the Task Manager.
	 * The task manager handles all player build tasks.
	 * @return {@link TaskManager}
	 * @author Rusketh
	 */
	
	public TaskManager getTaskManager( ) {
		return taskManager;
	}
	
	/*========================================================================================================*/
	
	public Logger				logger;
	private static Economy		economy;
	
	private commandManager		commandManager;
	private TaskManager			taskManager;
	private mysqlManager		mysqlManager;
	
	private File				configFile;
	private YamlConfiguration	YamlConfig;
	
	public boolean				Enabled;
	public boolean				Vault;
	
	public int					BlockRate;
	public int					MaxBlocks;
	public int					MaxRadius;
	
	public boolean				cmdUse;
	public String				cmdPrefix;
}
