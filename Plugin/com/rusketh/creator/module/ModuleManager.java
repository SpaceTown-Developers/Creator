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
 * 
 * ABANDONED!
 * ABANDONED!
 * ABANDONED!
 * ABANDONED!
 * ABANDONED!
 * ABANDONED!
 * ABANDONED!
 * ABANDONED!
 */

package com.rusketh.creator.module;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.rusketh.creator.CreatorPlugin;

public class ModuleManager {
	
	public ModuleManager(CreatorPlugin plugin) {
		this.plugin = plugin;
		
		directory = new File(plugin.getDataFolder( ), "modules");
		if ( directory.mkdirs() ) {
			plugin.logger.info("[Creator] -> No modules found.");
			return;
		}
		
		loadModules();
	}
	
	/*========================================================================================================*/
	
	public void loadModules() {
		moduleFiles = new HashMap<String, File> ();
		moduleYamls = new HashMap<String, YamlConfiguration> ();
		
		for ( File moduleFile : directory.listFiles() ) registerModule(moduleFile);
		
		for ( String name : moduleFiles.keySet() ) loadModule(name);
		
		moduleFiles.clear(); moduleYamls.clear();
		moduleFiles = null; moduleYamls = null;
	}
	
	/*========================================================================================================*/
	
	private void registerModule(File moduleFile) {
		if ( moduleFile.isDirectory() || !moduleFile.getName().endsWith(".jar") ) return;
		
		try {
			JarFile jarFile = new JarFile(moduleFile);
			JarEntry configEntry = jarFile.getJarEntry("Creator.yml");
			
			if ( configEntry != null ) {
				YamlConfiguration yamlConfig = new YamlConfiguration( );
				yamlConfig.load(jarFile.getInputStream(configEntry));
				
				String moduleName = yamlConfig.getString("name");
				
				if ( !moduleName.isEmpty() ) {
					moduleFiles.put(moduleName, moduleFile);
					moduleYamls.put(moduleName, yamlConfig);
				} else {
					plugin.logger.info("[Creator] -> Unkown module ? (skipping).".replace("?", moduleFile.getName()));
				}
			}
			
			jarFile.close();
			
		} catch (InvalidConfigurationException | IOException e) {
			plugin.logger.info("[Creator] -> Somthing when't wrong when loading '?' (Stage 2).".replace("?", moduleFile.getName()));
			plugin.debug(e);
		}
	}
	
	/*========================================================================================================*/
	
	private boolean loadModule(String name) {
		if ( modules.containsKey(name) ) return true;
		
		YamlConfiguration yamlConfig = moduleYamls.get(name);
		if ( yamlConfig == null ) return false;
		
		List<String> hardDepend = yamlConfig.getStringList("harddepend");
		if ( hardDepend != null && hardDepend.size() > 0 ) {
			for (String module : hardDepend ) {
				if ( !loadModule(module) ) {
					plugin.logger.info("[Creator] -> Module ? failed to load dependencies".replace("?", name));
					return false;
				}
			}
		}
		
		List<String> softDepend = yamlConfig.getStringList("softdepend");
		if ( softDepend != null && softDepend.size() > 0 ) {
			for (String module : softDepend ) loadModule(module);
		}
		
		/*========================================================================================================*/
		
		try {
			File file = moduleFiles.get(name);
			
			JarFile jarFile = new JarFile( file );
			String mainClass = yamlConfig.getString("main");
			
			if ( !mainClass.isEmpty() ) {
				URL[] urls = new URL[1];
				urls[0] = file.toURI().toURL();
	            
				URLClassLoader classLoader = new URLClassLoader(urls);
				Class<? extends CreatorModule> moduleClass = classLoader.loadClass(mainClass).asSubclass(CreatorModule.class);
				classLoader.close();
				
				CreatorModule module = moduleClass.newInstance();
				module.setUp(plugin);
				modules.put(name, module);
			}
			
			jarFile.close();
			
		} catch ( IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
			plugin.logger.info("[Creator] -> Somthing when't wrong when loading '?' (Stage 3).".replace("?", name));
			plugin.debug(e);
			return false;
		}
		
		return true;
	}
	
	/*========================================================================================================*/
	
	public void shutDown() {
		for ( CreatorModule module : modules.values() ) module.disable();
	}
	
	/*========================================================================================================*/
	
	CreatorPlugin plugin;
	File directory;
	
	HashMap<String, File> moduleFiles;
	HashMap<String, YamlConfiguration> moduleYamls;
	HashMap<String, CreatorModule> modules = new HashMap<String, CreatorModule>();
}
