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

package com.rusketh.creator.masks;

import org.bukkit.block.Block;

public class AndMask extends Mask {
	
	public AndMask(Mask maskA, Mask maskB) {
		this.maskA = maskA;
		this.maskB = maskB;
	}
	
	/*========================================================================================================*/
	
	public void setMask1(Mask mask) {
		maskA = mask;
	}
	
	public void setMask2(Mask mask) {
		maskB = mask;
	}
	
	/*========================================================================================================*/
	
	public boolean check( Block block ) {
		return (maskA.check( block ) && maskB.check( block ));
	}
	
	/*========================================================================================================*/
	
	private Mask	maskA, maskB;
}
