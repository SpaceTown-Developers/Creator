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

import org.bukkit.event.Listener;

import com.rusketh.creator.CreatorPlugin;
import com.rusketh.creator.module.CreatorModule;

/*
 * ==============================================================================================================
 * Base Extension
 * ==============================================================================================================
 */

public abstract class Extension extends CreatorModule implements Listener {
	
	public abstract String name();
	
	protected boolean setUp( CreatorPlugin plugin ) {
		this.plugin = plugin;
		
		enabled = enable( );
		if ( !enabled ) return false;
		
		plugin.getCommandManager( ).registerCommands( this );
		plugin.getServer( ).getPluginManager( ).registerEvents( this, plugin );
		
		return true;
	}
	
	/*========================================================================================================*/
	
	protected CreatorPlugin getCreator( ) {
		return plugin;
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
	
	protected CreatorPlugin	plugin;
	protected boolean		enabled = false;
}
