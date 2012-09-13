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

package com.rusketh.creator.blocks;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.command.CommandException;
import org.bukkit.inventory.ItemStack;

import com.rusketh.creator.Extensions.ItemExtension;
import com.rusketh.creator.exceptions.WildDataException;
import com.rusketh.util.MathUtil;

public class RandomBlockList {
	
	public RandomBlockList( String input ) {
		for ( String string : input.split( "," ) ) {
			
			int chance = 1;
			Matcher m = pattern.matcher( string );
			
			if ( m.find( ) ) {
				chance = Integer.parseInt( m.group( 1 ) );
				string = m.group( 2 );
			}
			
			try {
				add(ItemExtension.stringToItemStack( string ), chance);
			} catch ( WildDataException e ) {
				for ( int data : CreatorItem.get(e.typeId).dataValues( ).values( ) ) add( new CreatorItemStack(e.typeId, (byte) data), chance);
			}
		}
	}
	
	/*========================================================================================================*/
	
	private void add(ItemStack item, int chance) {
		if ( CreatorBlock.get( item.getTypeId( ) ) == null  ) throw new CommandException( new StringBuilder("'").append( CreatorItem.get( item.getTypeId() ).niceName( (int) item.getData().getData( ) ) ).append("' is not a placeable block.").toString() );
			
		max = max + chance;
		blocks.add(new RandomBlock(item, chance));
	}
	
	/*========================================================================================================*/
	
	public ItemStack next() {
		int total = 0;
		int rand = MathUtil.random(0, max);
		
		for (RandomBlock random : blocks) {
			total = total + random.chance;
			if (rand <= total ) return random.item;
		}
		
		throw new RuntimeException("Failed to get a random itemstack.");
	}
	
	/*========================================================================================================*/
	
	private int max = 0;
	private ArrayList< RandomBlock >	blocks	= new ArrayList< RandomBlock >( );
	
	public final Pattern	pattern	= Pattern.compile( "([0-9]+)%(.+)" );
	
	/*========================================================================================================*/
	
	private class RandomBlock {
		
		public RandomBlock(ItemStack item, int chance) {
			this.item = item;
			this.chance = chance;
		}
		
		public int chance;
		public ItemStack item;
	}
	
}
