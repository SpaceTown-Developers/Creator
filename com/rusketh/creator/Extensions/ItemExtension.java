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

package com.rusketh.creator.Extensions;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.rusketh.creator.blocks.BlockID;
import com.rusketh.creator.blocks.Item;
import com.rusketh.creator.blocks.ItemID;
import com.rusketh.creator.blocks.ItemStack;
import com.rusketh.creator.commands.CommandInput;
import com.rusketh.creator.commands.CreateCommand;

public class ItemExtension extends Extension {
	
	public ItemStack stringToItemStack( String search ) throws CommandException {
		String[] split = search.split( ":" );
		
		Item item;
		
		if (StringUtils.isNumeric(split[0])) { 
			int id = Integer.parseInt( split[0] );
			item = Item.get( id );
		} else {
			item = Item.get( split[0] );
			
			if ( item == null & split.length == 1 ) return aliasToItemStack( split[0] );
		}
		
		if ( item == null ) throw new CommandException( new StringBuilder( "Can not find item '" ).append( split[0] ).append( "'." ).toString( ) );
		
		if ( split.length == 1 ) return new ItemStack( item.getID( ) );
		
		int data;
		
		if ( StringUtils.isNumeric(split[1]) ) {
			data = Integer.parseInt( split[1] );
		} else {
			data = item.getDataValue( split[1] );
		}
		
		if ( !item.validDataValue( data ) ) throw new CommandException( new StringBuilder( item.getName( ) ).append( " does not have type '" ).append( split[1] ).append( "'." ).toString( ) );
		
		return new ItemStack( item.getID( ), (byte) data );
	}
	
	/*========================================================================================================*/
	
	public ItemStack aliasToItemStack( String alias ) {
		int data = Item.CLOTH.getDataValue( alias );
		if ( data != -1 ) return new ItemStack( BlockID.CLOTH, (byte) data );
		
		data = Item.INK_SACK.getDataValue( alias );
		if ( data != -1 ) return new ItemStack( ItemID.INK_SACK, (byte) data );
		
		data = Item.LOG.getDataValue( alias );
		if ( data != -1 ) return new ItemStack( BlockID.LOG, (byte) data );
		
		data = Item.COAL.getDataValue( alias );
		if ( data != -1 ) return new ItemStack( ItemID.COAL, (byte) data );
		
		data = Item.SPAWN_EGG.getDataValue( alias );
		if ( data != -1 ) return new ItemStack( ItemID.SPAWN_EGG, (byte) data );
		
		throw new CommandException( new StringBuilder( "Can not find item '" ).append( alias ).append( "'." ).toString( ) );
	}
	
	/*========================================================================================================*/
	
	@SuppressWarnings( "deprecation" )
	@CreateCommand(
			names = { "i", "item", "give" },
			example = "i <id>[:<data> <amount> -p:<player>]",
			desc = "Easily obtain an item.",
			least = 1,
			most = 2,
			console = false,
			flags = { "p*", "d" },
			perms = { "creator.item.give" } )
	public boolean ItemCommand( CommandSender sender, CommandInput input ) {
		
		ItemStack itemStack = stringToItemStack( input.arg( 0 ) );
		
		Player player = (Player) sender;
		if ( !player.isOp( ) && player.hasPermission( new StringBuilder( "creator.blockitem." ).append( itemStack.getID( ) ).toString( ) ) ) throw new CommandException( new StringBuilder( "You are not allowed to spawn '" ).append( itemStack.niceName( ) ).append( "'." ).toString( ) );
		
		if ( input.hasFlag( 'p' ) ) {
			if ( !player.hasPermission( "creator.item.give.other" ) ) throw new CommandException( "You are not allowed to give items to players" );
			
			player = plugin.getServer( ).getPlayer( input.flagString( 'p' ) );
			if ( player == null ) throw new CommandException( "Player was not found." );
		}
		
		int amount = 1;
		
		if ( input.size( ) == 2 ) {
			amount = input.argInt( 1 );
			
			if (itemStack.getAmount( ) < 1) {
				throw new CommandException("Invalid item amount!");
			} else if (itemStack.getItem( ).shouldNotStack( )) {
				amount = 1;
			} else if (itemStack.getAmount( ) < 64) {
				amount = 64;
			}
		}
		
		itemStack.setAmount( amount );
		
		if ( input.hasFlag( 'd' ) ) {
			player.getWorld().dropItemNaturally(player.getLocation(), itemStack.toItemStack( ));
		} else {
			player.getInventory( ).addItem( itemStack.toItemStack( ) );
			player.updateInventory( ); // Not actually deprecated is just a work around.
		}
		
		player.sendMessage( new StringBuilder( "Giving you " ).append( amount ).append( " '" ).append( itemStack.niceName( ) ).append( "'." ).toString( ) );
		return true;
	}
	
	/*========================================================================================================*/
	
	@SuppressWarnings( "deprecation" )
	@CreateCommand(
			names = { "clear" },
			example = "clear [-a|-s -p:<player>]",
			desc = "Easily clear your inventory.",
			least = 0,
			most = 0,
			console = false,
			flags = { "p*", "a", "s" },
			perms = { "creator.item.clear" } )
	public boolean ClearCommand( CommandSender sender, CommandInput input ) {
		
		Player player = (Player) sender;
		if ( input.hasFlag( 'p' ) ) {
			if ( !player.hasPermission( "creator.item.clear.other" ) ) throw new CommandException( "You are not allowed to give items to players" );
			
			player = plugin.getServer( ).getPlayer( input.flagString( 'p' ) );
			if ( player == null ) throw new CommandException( "Player was not found." );
		}
		
		if ( input.hasFlag( 's' ) ) {
			player.getInventory( ).setItemInHand( null );
			player.sendMessage( "Equipted item was cleared." );
		} else {
			Inventory inventory = player.getInventory( );
			
			if ( input.hasFlag( 'a' ) ) {
				for ( int i = 0; i <= 39; i++ )
					inventory.setItem( i, null );
				player.sendMessage( "Your inventory has been cleared" );
			} else {
				for ( int i = 9; i < 36; i++ )
					inventory.setItem( i, null );
				player.sendMessage( "Your inventory has been cleared, use -a to clear all." );
			}
		}
		
		player.updateInventory( ); // Not actually deprecated is just a work around.
		
		return true;
	}
	
	/*========================================================================================================*/
	
	@SuppressWarnings( "deprecation" )
	@CreateCommand(
			names = { "more" },
			example = "more",
			desc = "Easily refil item.",
			least = 0,
			most = 0,
			console = false,
			flags = { },
			perms = { "creator.item.more" } )
	public boolean MoreCommand( CommandSender sender, CommandInput input ) {
		
		Player player = (Player) sender;
		org.bukkit.inventory.ItemStack stack = player.getInventory( ).getItemInHand( );
		
		if (stack == null) {
			throw new CommandException( "Your hand is empty." );
		} else if ( Item.get( stack.getTypeId( ) ).shouldNotStack( ) ) {
			throw new CommandException( "Your current item can not stack." );
		}
		
		stack.setAmount( 64 );
		player.updateInventory( ); // Not actually deprecated is just a work around.
		
		player.sendMessage( "Your hand just refilled." );
		
		return true;
	}
}

