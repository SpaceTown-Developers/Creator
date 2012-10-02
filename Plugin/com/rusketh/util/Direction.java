/*
 * Creator - Bukkit Plugin
 * 
 * This file is based on WorldEdit by Sk89q and other contributers.
 * Used in accordance with GNU guidelines, all credits to original authors.
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

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public enum Direction {
	NORTH(new Vector(-1, 0, 0), new Vector(0, 0, 1), true),
	NORTH_EAST((new Vector(-1, 0, -1)).normalize(), (new Vector(-1, 0, 1)).normalize(), false),
	EAST(new Vector(0, 0, -1), new Vector(-1, 0, 0), true),
	SOUTH_EAST((new Vector(1, 0, -1)).normalize(), (new Vector(-1, 0, -1)).normalize(), false),
	SOUTH(new Vector(1, 0, 0), new Vector(0, 0, -1), true),
	SOUTH_WEST((new Vector(1, 0, 1)).normalize(), (new Vector(1, 0, -1)).normalize(), false),
	WEST(new Vector(0, 0, 1), new Vector(1, 0, 0), true),
	NORTH_WEST((new Vector(-1, 0, 1)).normalize(), (new Vector(1, 0, 1)).normalize(), false),
	UP(new Vector(0, 1, 0), new Vector(0, 0, 1), true),
	DOWN(new Vector(0, -1, 0), new Vector(0, 0, 1), true);

	private Vector dir;
	private boolean isOrthogonal;

	Direction(Vector vec, Vector leftDir, boolean isOrthogonal) {
		this.dir = vec;
		this.isOrthogonal = isOrthogonal;
	}

	public Vector vector() {
		return dir;
	}
	
	public boolean isOrthogonal() {
		return isOrthogonal;
	}
	
	/*========================================================================================================*/
	
	public double getX() {
		return this.dir.getX();
	}
	
	public double getY() {
		return this.dir.getY();
	}
	
	public double getZ() {
		return this.dir.getZ();
	}
	
	/*========================================================================================================*/
	
	public static Direction getDirection(Player player) {
		return getDirection(player, 0);
	}
	
	public static Direction getDirection(Player player, double offset) {
		return getDirection((player.getLocation().getYaw() + offset - 90) % 360);
	}
		
	public static Direction getDirection(double rot) {
		if (rot < 0) rot += 360.0;
			
		if (0 <= rot && rot < 22.5) {
			return Direction.NORTH;
		} else if (22.5 <= rot && rot < 67.5) {
			return Direction.NORTH_EAST;
		} else if (67.5 <= rot && rot < 112.5) {
			return Direction.EAST;
		} else if (112.5 <= rot && rot < 157.5) {
			return Direction.SOUTH_EAST;
		} else if (157.5 <= rot && rot < 202.5) {
			return Direction.SOUTH;
		} else if (202.5 <= rot && rot < 247.5) {
			return Direction.SOUTH_WEST;
		} else if (247.5 <= rot && rot < 292.5) {
			return Direction.WEST;
		} else if (292.5 <= rot && rot < 337.5) {
			return Direction.NORTH_WEST;
		} else if (337.5 <= rot && rot < 360.0) {
			return Direction.NORTH;
		} else {
			return null;
		}
	}
	
	/*========================================================================================================*/
	
	public static Direction getDirection(Player player, String dir) {
		switch (dir) {
			case "n": case "north": return NORTH;
			case "ne": case "northeast": return NORTH_EAST;
			case "e": case "east": return EAST;
			case "se": case "southeast": return SOUTH_EAST;
			case "s": case "south": return EAST;
			case "sw": case "southwest": return SOUTH_WEST;
			case "w": case "west": return WEST;
			case "nw": case "northwest": return NORTH_WEST;
			case "u": case "up": return UP;
			case "d": case "down": return DOWN;
			case "m": case "me":
			case "f": case "forward": return getDirection(player);
			case "b": case "back": return getDirection(player, 180);
			case "l": case "left": return getDirection(player, -90);
			case "r": case "right": return getDirection(player, 90);
		}
		
		return null;
	}
}