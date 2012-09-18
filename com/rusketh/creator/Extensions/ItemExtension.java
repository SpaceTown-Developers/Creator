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

import org.bukkit.enchantments.Enchantment;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.rusketh.creator.blocks.BlockID;
import com.rusketh.creator.blocks.DataHolder;
import com.rusketh.creator.blocks.CreatorItem;
import com.rusketh.creator.blocks.ItemID;
import com.rusketh.creator.blocks.CreatorItemStack;
import com.rusketh.creator.commands.CommandInput;
import com.rusketh.creator.commands.CreateCommand;
import com.rusketh.creator.exceptions.CmdException;
import com.rusketh.creator.exceptions.WildDataException;
import com.rusketh.util.CreatorString;

public class ItemExtension extends Extension {
	
	public static ItemStack stringToItemStack( String search ) throws WildDataException {
		String[] split = search.split( ":" );
		
		CreatorItem item;
		
		if ( StringUtils.isNumeric( split[0] ) ) {
			int id = Integer.parseInt( split[0] );
			item = CreatorItem.get( id );
		} else {
			item = CreatorItem.get( split[0] );
			
			if ( item == null & split.length == 1 ) return aliasToItemStack( split[0] );
		}
		
		if ( item == null ) throw new CmdException("%rCan not find item '%c", split[0], "%r'.");
		
		if ( split.length == 1 ) return new CreatorItemStack( item.getID( ) );
		
		int data;
		
		if ( StringUtils.isNumeric( split[1] ) ) {
			data = Integer.parseInt( split[1] );
		} else if (split[1].equals( "*" )){
			throw new WildDataException(item.getID( ));
		} else {
			data = item.getDataValue( split[1] );
		}
		
		if ( !item.validDataValue( data ) ) throw new CmdException("%r'%c", item.getName( ),"%r' does not have type '%c", split[1], "%r'.");
		
		return new CreatorItemStack( item.getID( ), (byte) data );
	}
	
	/*========================================================================================================*/
	
	public static ItemStack aliasToItemStack( String alias ) {
		int data = CreatorItem.CLOTH.getDataValue( alias );
		if ( data != -1 ) return new CreatorItemStack( BlockID.CLOTH, (byte) data );
		
		data = CreatorItem.INK_SACK.getDataValue( alias );
		if ( data != -1 ) return new CreatorItemStack( ItemID.INK_SACK, (byte) data );
		
		data = CreatorItem.LOG.getDataValue( alias );
		if ( data != -1 ) return new CreatorItemStack( BlockID.LOG, (byte) data );
		
		data = CreatorItem.COAL.getDataValue( alias );
		if ( data != -1 ) return new CreatorItemStack( ItemID.COAL, (byte) data );
		
		data = CreatorItem.POTION.getDataValue( alias );
		if ( data != -1 ) return new CreatorItemStack( ItemID.POTION, (byte) data );
		
		data = CreatorItem.SPAWN_EGG.getDataValue( alias );
		if ( data != -1 ) return new CreatorItemStack( ItemID.SPAWN_EGG, (byte) data );
		
		throw new CmdException("%rCan not find item '%c", alias, "%r'.");
	}
	
	/*========================================================================================================*/
	
	public final static DataHolder	enchantments	= new DataHolder( Enchantment.PROTECTION_ENVIRONMENTAL.getId( ), "Protection", "protect" ).add( Enchantment.PROTECTION_FIRE.getId( ), "Fire Protection", "fireprotection", "fireprotect" ).add( Enchantment.PROTECTION_FALL.getId( ), "Feather Falling", "featherfalling", "featherfall", "fallprotect" ).add( Enchantment.PROTECTION_EXPLOSIONS.getId( ), "Blast Protection", "blastprotection", "blastprotect" ).add( Enchantment.PROTECTION_PROJECTILE.getId( ), "Projectile Protection", "projectileprotection", "projectileprotect", "bulletprotect" ).add( Enchantment.OXYGEN.getId( ), "Respiration", "oxygen", "breeth" ).add( Enchantment.WATER_WORKER.getId( ), "Aqua Affinity", "aquaaffinity", "waterworker", "aqua" ).add( Enchantment.DAMAGE_ALL.getId( ), "Sharpness", "sharp" ).add( Enchantment.DAMAGE_UNDEAD.getId( ), "Smite" ).add( Enchantment.DAMAGE_ARTHROPODS.getId( ), "Bane of Arthropods", "arthropods", "bane" ).add( Enchantment.KNOCKBACK.getId( ), "Knockback" ).add( Enchantment.FIRE_ASPECT.getId( ), "Fire Aspect", "fire" ).add( Enchantment.LOOT_BONUS_MOBS.getId( ), "Looting", "loot" ).add( Enchantment.DIG_SPEED.getId( ), "Efficiency" ).add( Enchantment.SILK_TOUCH.getId( ), "Silk Touch", "silktouch", "silk" ).add( Enchantment.DURABILITY.getId( ), "Unbreaking", "unbreak" ).add( Enchantment.LOOT_BONUS_BLOCKS.getId( ), "Fortune", "ritch" ).add( Enchantment.ARROW_DAMAGE.getId( ), "Power" ).add( Enchantment.ARROW_KNOCKBACK.getId( ), "Punch" ).add( Enchantment.ARROW_FIRE.getId( ), "Flame", "ignite" ).add( Enchantment.ARROW_INFINITE.getId( ), "Infinity", "infinate" );
	
	public static String enchant( String search, ItemStack item ) {
		String[] split = search.split( ":" );
		
		Enchantment ench;
		
		if ( StringUtils.isNumeric( split[0] ) ) {
			int id = Integer.parseInt( split[0] );
			ench = Enchantment.getById( id );
		} else {
			int id = enchantments.get( split[0] );
			ench = Enchantment.getById( id );
		}
		
		if ( ench == null ) throw new CmdException("%rCan not find enchantment '%c", split[0], "%r'.");
		
		int level = ench.getStartLevel( );
		
		if ( split.length > 1 ) {
			if ( StringUtils.isNumeric( split[1] ) ) {
				level = Integer.parseInt( split[1] );
			} else {
				throw new CmdException("%r'%c", split[1], "%r' is not a valid level (%Rtry a number?%r)." );
			}
			
			if ( level < ench.getStartLevel( ) ) throw new CmdException( "%r'%c", enchantments.name( ench.getId( ) ), "%r' does not support levels below '%c").append( ench.getStartLevel( ) ).append( "%r'.");
			
			if ( level > ench.getMaxLevel( ) ) throw new CmdException( "%r'%c", enchantments.name( ench.getId( ) ), "%r' does not support levels above '%c" ).append( ench.getMaxLevel( ) ).append( "%r'." );
		}
		
		if ( !ench.canEnchantItem( item ) ) throw new CmdException("%rYou can not enchant '%c" ).append( new CreatorItemStack(item) ).append( "%r' with '%c" ).append( enchantments.name( ench.getId( ) ) ).append( "%r'." );
		
		item.addUnsafeEnchantment( ench, level );
		
		return new CreatorString( enchantments.name( ench.getId( ) ) ).append( " level " ).append( level ).toString( );
	}
	
	/*========================================================================================================*/
	
	@SuppressWarnings( "deprecation" )
	@CreateCommand( names = { "i", "item", "give" }, example = "i <id>[:<data> <amount> -p:<player>]", desc = "Easily obtain an item.", least = 1, most = 2, console = false, flags = { "p*", "d", "e*", "m*" }, perms = { "creator.item.give" } )
	public boolean ItemCommand( CommandSender sender, CommandInput input ) {
		
		CreatorItemStack itemStack;
		
		try {
			itemStack = (CreatorItemStack) stringToItemStack( input.arg( 0 ) );
		} catch ( WildDataException e ) {
			throw new CmdException( "%rThis command does not support wildcards (%c*%r)." );
		}
		
		Player player = (Player) sender;
		if ( !player.isOp( ) && player.hasPermission( new StringBuilder( "creator.blockitem." ).append( itemStack.getTypeId( ) ).toString( ) ) ) throw new CommandException( new StringBuilder( "You are not allowed to spawn '" ).append( itemStack.niceName( ) ).append( "'." ).toString( ) );
		
		int amount = 1;
		
		if ( input.size( ) == 2 ) {
			amount = input.argInt( 1 );
			
			if ( itemStack.getAmount( ) < 1 ) {
				throw new CmdException( "%rInvalid item amount!" );
			} else if ( itemStack.getItem( ).shouldNotStack( ) ) {
				amount = 1;
			} else if ( itemStack.getAmount( ) < 64 ) {
				amount = 64;
			}
		}
		
		itemStack.setAmount( amount );
		
		CreatorString message = new CreatorString( "%gGiving you '%c" );
		
		if ( input.hasFlag( 'p' ) ) {
			if ( !player.hasPermission( "creator.item.give.other" ) ) throw new CommandException( "You are not allowed to give items to players" );
			
			player = plugin.getServer( ).getPlayer( input.flagString( 'p' ) );
			if ( player == null ) throw new CommandException( "Player was not found." );
			
			message = new CreatorString("%G").append( sender.getName( ) ).append( "%g has given you '%c" );
		}
		
		message.append( amount ).append( "%g' of '%c" ).append( itemStack ).append( "%g'" );
		
		if ( input.hasFlag( 'e' ) ) {
			String with = enchant( input.flagString( 'e' ), itemStack );
			
			message.append( " enchanted with '%c" ).append( with ).append( "%g'" );
		}
		
		/*if ( input.hasFlag( 'm' ) ) {
			if ( itemStack.getTypeId( ) != BlockID.MOB_SPAWNER) throw new CommandException( "The mob flag (-m) can only be used with 'Mob Spawners'" );
			
			int mob = Item.SPAWN_EGG.getDataValue( input.flagString( 'm' ) );
			if ( mob == -1 ) throw new CommandException( new StringBuilder( "Can not find mob '" ).append(input.flagString( 'm' )).append( "'." ).toString( ) );
			
			itemStack.setDurability( (short) mob );
			message.append( " of type '" ).append( Item.SPAWN_EGG.nameDataValue( mob ) ).append( "'" );
		} CURRENTLY IMPOSSIBLE TODO WITH BUKKIT */
		
		if ( input.hasFlag( 'd' ) ) {
			player.getWorld( ).dropItemNaturally( player.getLocation( ), itemStack );
		} else {
			player.getInventory( ).addItem( itemStack );
			player.updateInventory( ); // Not actually deprecated is just a work around.
		}
		
		player.sendMessage( message.append( "." ).toString( ) );
		
		return true;
	}
	
	/*========================================================================================================*/
	
	@SuppressWarnings( "deprecation" )
	@CreateCommand( names = { "clear" }, example = "clear [-a|-s -p:<player>]", desc = "Easily clear your inventory.", least = 0, most = 0, console = false, flags = { "p*", "a", "s" }, perms = { "creator.item.clear" } )
	public boolean ClearCommand( CommandSender sender, CommandInput input ) {
		
		Player player = (Player) sender;
		if ( input.hasFlag( 'p' ) ) {
			if ( !player.hasPermission( "creator.item.clear.other" ) ) throw new CmdException( "%rYou are not allowed to give items to players" );
			
			player = plugin.getServer( ).getPlayer( input.flagString( 'p' ) );
			if ( player == null ) throw new CmdException( "%rPlayer was not found." );
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
	@CreateCommand( names = { "more" }, example = "more", desc = "Easily refil item.", least = 0, most = 0, console = false, flags = { }, perms = { "creator.item.more" } )
	public boolean MoreCommand( CommandSender sender, CommandInput input ) {
		
		Player player = (Player) sender;
		ItemStack stack = player.getInventory( ).getItemInHand( );
		
		if ( stack == null ) {
			throw new CmdException( "%rYour hand is empty." );
		} else if ( CreatorItem.get( stack.getTypeId( ) ).shouldNotStack( ) ) {
			throw new CmdException( "%rYour current item can not stack." );
		}
		
		stack.setAmount( 64 );
		player.updateInventory( ); // Not actually deprecated is just a work around.
		
		player.sendMessage( "Your hand just refilled." );
		
		return true;
	}
	
	/*========================================================================================================*/
	
	@SuppressWarnings( "deprecation" )
	@CreateCommand( names = { "enchant" }, example = "enchant <enchantment>:<level>", desc = "Easily enchant an item.", least = 1, most = 1, console = false, flags = { }, perms = { "creator.item.enchant" } )
	public boolean EnchantCommand( CommandSender sender, CommandInput input ) {
		
		Player player = (Player) sender;
		ItemStack itemStack = player.getInventory( ).getItemInHand( );
		
		if ( itemStack == null ) throw new CommandException( "Your hand is empty." );
		
		String with = enchant( input.arg( 0 ), itemStack );
		player.updateInventory( ); // Not actually deprecated is just a work around.
		
		player.sendMessage( new CreatorString( "%gYour item has been enchanted with '%c", with ,"%g'." ).toString( ) );
		
		return true;
	}
}
