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

package com.rusketh.creator.Extensions;

import java.util.HashMap;

import org.bukkit.event.Listener;

import com.rusketh.creator.CreatorPlugin;


public class ExtensionManager {
	
	public ExtensionManager( CreatorPlugin plugin ) {
		this.plugin = plugin;
		this.extensions = new HashMap<String, Extension>();
		
		registerExtensions();
	}
	
	/*========================================================================================================*/
	
	public void registerExtension(String name, Extension extension) {
		if (extension.setUp( plugin )) {
			extensions.put( name, extension );
		}
	}
	
	/*=========================================================================================================
		Extensions Registered Here.
	==========================================================================================================*/
	
	private void registerExtensions() {
		registerExtension("help", new HelpExtension() );
		registerExtension("ban", new BanExtension() );
	}
	
	public Extension getExtension(String name) {
		return extensions.get( name );
	}
	
	/*========================================================================================================*/
	
	private CreatorPlugin				plugin;
	private HashMap<String, Extension> 	extensions;
	
}




/*==============================================================================================================
	Base Extension
==============================================================================================================*/

abstract class Extension implements Listener {
	
	protected boolean setUp( CreatorPlugin plugin ) {
		this.plugin = plugin;
		
		enabled = enable();
		if ( !enabled ) return false;
		
		plugin.getCommandManager( ).registerCommands( this );
		plugin.getServer( ).getPluginManager( ).registerEvents( this, plugin );
		
		return true;
	}
	
	/*========================================================================================================*/
	
	protected CreatorPlugin getCreator() {
		return plugin;
	}
	
	/*========================================================================================================*/
	
	protected void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	protected boolean isEnabled() {
		return this.enabled;
	}
	
	/*========================================================================================================*/
	
	public boolean enable() {
		return true;
	}
	
	/*========================================================================================================*/
	
	protected CreatorPlugin		plugin;
	protected boolean			enabled;
}