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

package com.rusketh.creator.module;

import org.bukkit.Server;

import com.rusketh.creator.CreatorPlugin;
import com.rusketh.creator.Extensions.Extension;

public abstract class CreatorModule {
	
	protected boolean setUp( CreatorPlugin plugin ) {
		this.plugin = plugin;
		
		enabled = enable( );
		if ( !enabled ) return false;
		
		return true;
	}
	
	/*========================================================================================================*/
	
	protected CreatorPlugin getCreator( ) {
		return plugin;
	}
	
	protected Server getServer() {
		return plugin.getServer();
	}
	
	/*========================================================================================================*/
	
	protected void setEnabled( boolean enabled ) {
		if (enabled == this.enabled) return;
		else if (!enabled) disable();
		else enabled = enable( );
		this.enabled = enabled;
	}
	
	protected boolean isEnabled( ) {
		return this.enabled;
	}
	
	/*========================================================================================================*/
	
	public boolean enable( ) {
		return true;
	}
	
	public void disable() {
		
	}

	/*========================================================================================================*/
	
	public void registerExtension(Extension extension) {
		plugin.getExtensionManager().registerExtension(extension);
	}
	
	/*========================================================================================================*/
	
	protected CreatorPlugin	plugin;
	protected boolean		enabled;
	protected String 		name;
}
