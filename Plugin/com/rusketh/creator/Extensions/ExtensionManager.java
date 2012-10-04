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

import com.rusketh.creator.CreatorPlugin;

public class ExtensionManager {
	
	public ExtensionManager( CreatorPlugin plugin ) {
		this.plugin = plugin;
		this.extensions = new HashMap< String, Extension >( );
		
		registerExtensions( );
	}
	
	/*========================================================================================================*/
	
	public void registerExtension( Extension extension ) {
		if (extension == null || extension.name() == null ) return;
		try {
			registerExtension(extension.name() , extension);
		} catch ( Exception e ) {
			plugin.logger.info( "[Creator] Error registering extension '?'.".replace("?", extension.name()) );
			plugin.debug(e);
		}
	}
	
	public void registerExtension( String name, Extension extension ) {
		if (extension == null) return;
		if ( extension.setUp( plugin ) ) {
			extensions.put( name, extension );
		}
	}
	
	/*========================================================================================================*/
	
	public void shutDown() {
		for (Extension extension : extensions.values()) extension.setEnabled(false);
	}
	
	/*=========================================================================================================
		Extensions Registered Here.
	==========================================================================================================*/
	
	private void registerExtensions( ) {
		registerExtension( new CreatorExtension( ) );
		registerExtension( new ItemExtension( ) );
		registerExtension( new SelectionExtension( ) );
		registerExtension( new EditExtension( ) );
		
		plugin.saveConfig( );
	}
	
	public Extension getExtension( String name ) {
		return extensions.get( name );
	}
	
	/*========================================================================================================*/
	
	private CreatorPlugin					plugin;
	private HashMap< String, Extension >	extensions;
	
}
