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

import com.rusketh.creator.commands.helpCommands;
import com.rusketh.creator.commands.manager.commandManager;
import com.rusketh.creator.listeners.PlayerListener;
import com.rusketh.creator.tasks.taskManager;

public class creatorPlugin extends JavaPlugin {
	
	/*========================================================================================================*/
	
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
			logger.info( "Creator has been disabled." );
			
			return;
		}
		
		setupEconomy( );
		
		taskManager = new taskManager( this );
		taskManager.reloadSessions( );
		
		commandManager = new commandManager( this );
		registerCommands( );
		
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
	}
	
	/*========================================================================================================*/
	
	@Override
	public FileConfiguration getConfig( ) {
		return YamlConfig;
	}
	
	@Override
	public void saveConfig( ) {
		try {
			YamlConfig.save( configFile );
		} catch ( Exception e ) {
			logger.info( "[Creator] Failed to save config" );
			Enabled = false;
		}
	}
	
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
	
	public Economy getEconomy( ) {
		return economy;
	}
	
	/*========================================================================================================*/
	
	public void registerCommands( ) {
		commandManager.registerCommands( new helpCommands( this ) );
		
		saveConfig( );
	}
	
	public commandManager getCommandManager( ) {
		return commandManager;
	}
	
	public boolean onCommand( CommandSender sender, Command command, String commandLabel, String[] perams ) {
		if ( commandLabel.equalsIgnoreCase( "creator" ) || commandLabel.equalsIgnoreCase( "cr" ) ) {
			return commandManager.run( sender, perams );
		}
		
		return false;
	}
	
	/*========================================================================================================*/
	
	public taskManager getTaskManager( ) {
		return taskManager;
	}
	
	/*========================================================================================================*/
	
	public Logger				logger;
	private static Economy		economy;
	
	private commandManager		commandManager;
	private taskManager			taskManager;
	
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
