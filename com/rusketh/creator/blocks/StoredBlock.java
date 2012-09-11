package com.rusketh.creator.blocks;

import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.Inventory;


public class StoredBlock {
	
	public StoredBlock(Block block) {
		switch ( block.getTypeId( ) ) {
			
			case BlockID.CHEST:
				storeInventory( ((Chest) block).getInventory( ));
				
			case BlockID.FURNACE:
				storeInventory( ((Furnace) block).getInventory( ) );
		}
	}
	
	/*========================================================================================================*/
	
	private void storeInventory(Inventory inventory) {
		
	}
	
	/*========================================================================================================*/
}
