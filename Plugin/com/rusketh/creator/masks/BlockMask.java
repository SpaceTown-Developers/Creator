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

import com.rusketh.creator.blocks.BlockArray;

public class BlockMask extends Mask {
	
	public boolean check( Block block ) {
		return blocks.contains( block.getTypeId( ), block.getData( ) );
	}
	
	/*========================================================================================================*/
	
	public void add( int type, byte data ) {
		blocks.put( type, data, true );
	}
	
	/*========================================================================================================*/
	
	public BlockMask clone() {
		BlockMask mask = new BlockMask( );
		mask.blocks = blocks.clone();
		return mask;
	}
	
	/*========================================================================================================*/
	
	private BlockArray< Boolean >	blocks	= new BlockArray< Boolean >( );
}
