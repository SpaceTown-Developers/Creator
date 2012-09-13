package com.rusketh.creator.tasks;

import java.util.ArrayList;

import org.bukkit.block.Block;

public class TaskQue {
	
	public void add(Block block, int type) {
		this.block.add( block );
		this.type.add( type );
		this.data.add( (byte) 0 );
	}
	
	public void add(Block block, int type, byte data) {
		this.block.add( block );
		this.type.add( type );
		this.data.add( (byte) data );
	}
	
	/*========================================================================================================*/
	
	public void clear() {
		block.clear();
		type.clear();
		data.clear();
		
		pos = 0;
	}
	
	/*========================================================================================================*/
	
	public Block getBlock( ) {
		return block.get( pos );
	}
	
	public int getTypeId( ) {
		return type.get( pos );
	}
	
	public byte getData( ) {
		return data.get( pos );
	}
	
	/*========================================================================================================*/
	
	public int size() {
		return block.size( );
	}
	
	public int getPos() {
		return pos;
	}
	
	public void setPos(int i) {
		pos = i;
	}
	
	/*========================================================================================================*/
	
	public boolean valid() {
		return (block.get( pos ) != null);
	}
	
	public boolean hasNext() {
		return (pos < (block.size() - 1));
	}
	
	public void next() {
		pos++;
	}
	
	public void prev() {
		pos--;
	}
	
	/*========================================================================================================*/
	
	public void first() {
		pos = 0;
	}
	
	public void last() {
		pos = block.size() - 1;
	}
	
	/*========================================================================================================*/
	
	private ArrayList< Block >		block	= new ArrayList< Block >( );
	private ArrayList< Integer >	type	= new ArrayList< Integer >( );
	private ArrayList< Byte >		data	= new ArrayList< Byte >( );
	
	private int pos = 0;
}
