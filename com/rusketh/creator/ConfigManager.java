package com.rusketh.creator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;

public class ConfigManager {
	
	private CreatorPlugin	plugin;
	
	protected ConfigManager( CreatorPlugin creatorPlugin ) {
		plugin = creatorPlugin;
	}
	
	
	/**	
	 * Creates a new {@link YamlConfiguration} and set the default options.
	 * @return {@link YamlConfiguration}
	 */
	public YamlConfiguration createConfig( ) {
		YamlConfiguration yamlFile = new YamlConfiguration( );
		YamlConfigurationOptions YamlOptions = yamlFile.options( );
		
		YamlOptions.pathSeparator( '.' );
		YamlOptions.copyDefaults( true );
		YamlOptions.copyHeader( true );
		
		return yamlFile;
	}
	
	/**
	 * Loads a configuration from disk.
	 * If the file do not exist, it will create it.
	 * @param name The filename that will be loaded.
	 * @return {@link YamlConfiguration} The configuration loaded.
	 */
	public YamlConfiguration loadConfig( String name ) {
		return loadConfig( name, new HashMap< String, Object >( ) );
	}
	
	public YamlConfiguration loadConfig( String name, Map< String, Object > defaults ) {
		if ( !name.matches( "[^%.]+%.yml" ) ) name = name.concat( ".yml" );
		
		File file = new File( plugin.getDataFolder( ), name );
		
		if ( !file.exists( ) ) {
			try {
				if ( !file.getParentFile( ).exists( ) ) file.mkdirs( );
				file.createNewFile( );
			} catch ( IOException e ) {
				plugin.getLogger( ).severe( "Unable to create ?!".replace( "?", name ) );
				return null;
			}
		}
		
		YamlConfiguration yamlFile = createConfig( );
		
		try {
			yamlFile.addDefaults( defaults );
			yamlFile.load( file );
			yamlFile.save( file );
		} catch ( IOException | InvalidConfigurationException e ) {
			plugin.getLogger( ).severe( "Unable to load ?!".replace( "?", name ) );
			return null;
		}
		
		return yamlFile;
	}
	
	public void saveConfig( YamlConfiguration config, String name ) {
		if ( !name.matches( "[^%.]+%.yml" ) ) name = name.concat( ".yml" );
		
		File file = new File( plugin.getDataFolder( ), name );
		
		if ( !file.exists( ) ) {
			try {
				if ( !file.getParentFile( ).exists( ) ) file.mkdirs( );
				file.createNewFile( );
			} catch ( IOException e ) {
				plugin.getLogger( ).severe( "Unable to create ?!".replace( "?", name ) );
				return;
			}
		}
		
		saveConfig( config, file );
	}
	
	private void saveConfig( YamlConfiguration config, File file ) {
		try {
			config.save( file );
		} catch ( IOException e ) {
			plugin.getLogger( ).severe( "Unable to save ?!".replace( "?", file.getName( ) ) );
		}
	}
	
}
