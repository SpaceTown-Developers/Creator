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

package com.rusketh.creator.commands.manager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME )
public @interface commandAnote {
	
	/* All the possible names of the command!  */
	String[] names( );
	
	/*
	 * An example of how to use the command
	 * like: !tp <Player> <Player*>
	 */
	String example( ) default "";
	
	/* A brief description of what this command does! */
	String desc( ) default "";
	
	/* The least amount of arguments */
	int least( ) default 0;
	
	/* The most amount of arguments */
	int most( ) default -1;
	
	/* Can players use this command! */
	boolean player( ) default true;
	
	/* Can console use this command! */
	boolean console( ) default true;
	
	/* All the legal flags!  */
	String[] flags( ) default { };
	
	/* All the permissions needed to use this!  */
	String[] perms( ) default { };
	
	/* Price per use! */
	int usePrice( ) default 0;
	
	/* Price per block! */
	int blockPrice( ) default -1;
	
}
