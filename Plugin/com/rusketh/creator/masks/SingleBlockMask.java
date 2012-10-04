package com.rusketh.creator.masks;

import org.bukkit.block.Block;


public class SingleBlockMask extends Mask {
	
	public SingleBlockMask(int type, byte data) {
		this.type = type;
		this.data = data;
	}
	
	
	/*========================================================================================================*/
	
	public boolean check( Block block ) {
		return (block.getTypeId( ) == type && block.getData( ) == data);
	}
	
	/*========================================================================================================*/
	
	public SingleBlockMask clone() {
		return new SingleBlockMask(type, data);
	}
	
	/*========================================================================================================*/
	
	private int type;
	private byte data;
	
}
