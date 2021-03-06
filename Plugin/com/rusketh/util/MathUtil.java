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

package com.rusketh.util;

import java.util.Random;

public class MathUtil {
	
	public static double random( double min, double max ) {
		if ( min > max ) {
			double tmp = min;
			min = max;
			max = tmp;
		}
		double rand = random.nextDouble();
		double part = min - max;
		return min + Math.floor( rand * part );
	}
	
	public static int random( int min, int max ) {
		if ( min > max ) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		double rand = Math.random( );
		int part = min - max;
		return min + (int) Math.floor( rand * part );
	}
	
	private static Random random = new Random();
}
