package com.rusketh.creator.blocks;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.BrewingStand;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Furnace;
import org.bukkit.block.Jukebox;
import org.bukkit.block.NoteBlock;
import org.bukkit.block.Sign;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


public class StoredBlock {
	
	public StoredBlock(Block block) {
		state = block.getState( );
	}
	
	/*========================================================================================================*/
	
	public void pushState(Block block) {
		block.setTypeId( state.getTypeId( ) );
		block.setTypeIdAndData(state.getTypeId(), state.getRawData(), false);
		
		if ( state instanceof Chest ) {
			Chest from = (Chest) state;
			Chest to = (Chest) block;
			
			copyInventory(from.getInventory( ), to.getInventory( ));
		
		} else if ( state instanceof Furnace ) {
			Furnace from = (Furnace) state;
			Furnace to = (Furnace) block;

			to.setBurnTime( from.getBurnTime( ) );
			to.setCookTime( from.getCookTime( ) );
			copyInventory(from.getInventory( ), to.getInventory( ));
			
		} else if ( state instanceof BrewingStand ) {
			BrewingStand from = (BrewingStand) state;
			BrewingStand to = (BrewingStand) block;

			to.setBrewingTime( from.getBrewingTime( ) );
			copyInventory(from.getInventory( ), to.getInventory( ));
			
		} else if ( state instanceof Sign ) {
			Sign from = (Sign) state;
			Sign to = (Sign) block;
			
			to.setLine( 1, from.getLine( 1 ) );
			to.setLine( 2, from.getLine( 2 ) );
			to.setLine( 3, from.getLine( 3 ) );
			to.setLine( 4, from.getLine( 4 ) );
			
		} else if ( state instanceof Dispenser ) {
			Dispenser from = (Dispenser) state;
			Dispenser to = (Dispenser) block;
			
			copyInventory(from.getInventory( ), to.getInventory( ));
			
		} else if (state instanceof Jukebox) {
			Jukebox from = (Jukebox) state;
			Jukebox to = (Jukebox) block;
			
			to.setPlaying( from.getPlaying( ) );
			
		} else if (state instanceof NoteBlock) {
			NoteBlock from = (NoteBlock) state;
			NoteBlock to = (NoteBlock) block;
			
			to.setNote( from.getNote( ) );
		} else if (state instanceof CreatureSpawner) {
			CreatureSpawner from = (CreatureSpawner) state;
			CreatureSpawner to = (CreatureSpawner) block;
			
			to.setSpawnedType( from.getSpawnedType( ) );
			to.setDelay( from.getDelay() );
		} else {
			special = false;
		}
	}
	
	/*========================================================================================================*/
	
	private void copyInventory(Inventory from, Inventory to) {
		to.clear( );
		
		for (int i = 0; i < from.getSize( ); i++) {
			ItemStack itemStack = from.getItem( i );
			if (itemStack != null ) to.setItem( i, itemStack.clone( ) );
		}
	}
	
	/*========================================================================================================*/
	
	public int getTypeId() {
		return state.getTypeId( );
	}
	
	public byte getDataByte() {
		return state.getData( ).getData( );
	}
	
	/*========================================================================================================*/
	
	public Vector getVector() {
		return new Vector(state.getX( ), state.getY( ), state.getZ( ));
	}
	
	public World getWorld() {
		return state.getWorld( );
	}
	
	/*========================================================================================================*/
	
	public Block getBlock() {
		return state.getBlock( );
	}
	
	public BlockState getState() {
		return state;
	}
	
	/*========================================================================================================*/
	
	public boolean isSpecial() {
		return special;
	}
	
	/*========================================================================================================*/
	
	private BlockState state;
	
	private boolean special = true;
}
