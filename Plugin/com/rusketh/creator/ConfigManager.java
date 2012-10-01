package com.rusketh.creator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;

/*
 * TODO:
 * Add throws and exceptions
 */

public class ConfigManager {
	
	private CreatorPlugin	plugin;
	
	public ConfigManager( CreatorPlugin creatorPlugin ) {
		plugin = creatorPlugin;
	}
	
	/**
	 * Creates a new {@link YamlConfiguration} and set the default options.
	 * 
	 * @return {@link YamlConfiguration}
	 * 
	 * @author Oskar
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
	 * Creates a new {@link YamlConfiguration} and set the default options.
	 * 
	 * @param defaults The default values for the new configuration.
	 * @return {@link YamlConfiguration}
	 * 
	 * @author Oskar
	 */
	public YamlConfiguration createConfig( Map< String, Object > defaults ) {
		YamlConfiguration yamlFile = new YamlConfiguration( );
		YamlConfigurationOptions YamlOptions = yamlFile.options( );
		
		YamlOptions.pathSeparator( '.' );
		YamlOptions.copyDefaults( true );
		YamlOptions.copyHeader( true );
		
		yamlFile.addDefaults( defaults );
		
		return yamlFile;
	}
	
	/**
	 * Loads a configuration from disk.
	 * 
	 * @param name The name of the configuration file that will be loaded.
	 * @return {@link YamlConfiguration} The configuration loaded.
	 * 
	 * @throws {@link FileNotFoundException}
	 * @throws {@link IOException }
	 * @throws {@link InvalidConfigurationException}
	 * 
	 * @author Oskar
	 */
	public YamlConfiguration loadConfig( String name ) throws FileNotFoundException, IOException, InvalidConfigurationException {
		if ( !name.matches( "[^%.]+%.yml" ) ) name = name.concat( ".yml" );
		YamlConfiguration yamlFile = createConfig( );
		yamlFile.load( name );
		return yamlFile;
	}
	
	/**
	 * Saves a configuration the the specified path.
	 * 
	 * @param config The configuration to be saved.
	 * @param name The name of the file where the configuration will be saved.
	 * 
	 * @throws IOException
	 * 
	 * @author Oskar
	 */
	public void saveConfig( YamlConfiguration config, String name ) throws IOException {
		if ( !name.matches( "[^%.]+%.yml" ) ) name = name.concat( ".yml" );
		
		File file = new File( plugin.getDataFolder( ), name );
		
		if ( !file.exists( ) ) {
			if ( !file.getParentFile( ).exists( ) ) file.mkdirs( );
			file.createNewFile( );
		}
		
		config.save( file );
	}
}
