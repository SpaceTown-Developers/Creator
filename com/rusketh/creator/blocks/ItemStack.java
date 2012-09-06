package com.rusketh.creator.blocks;

public class ItemStack {
	
	public ItemStack( int id ) {
		this.id = id;
	}
	
	public ItemStack( int id, byte data ) {
		this.id = id;
		this.data = data;
		
		item = Item.get( id );
	}
	
	public ItemStack( int id, byte data, int ammount ) {
		this.id = id;
		this.data = data;
		this.ammount = ammount;
		
		item = Item.get( id );
	}
	
	/*========================================================================================================*/
	
	public void setID( int id ) {
		this.id = id;
		
		item = Item.get( id );
	}
	
	public int getID( ) {
		return this.id;
	}
	
	/*========================================================================================================*/
	
	public void setData( byte data ) {
		this.data = data;
	}
	
	public int getData( ) {
		return this.data;
	}
	
	/*========================================================================================================*/
	
	public void setDurability( short durability ) {
		this.durability = durability;
	}
	
	public int getDurability( ) {
		return this.durability;
	}
	
	/*========================================================================================================*/
	
	public void setAmmount( int ammount ) {
		this.ammount = ammount;
	}
	
	public int getAmmount( ) {
		return this.ammount;
	}
	
	/*========================================================================================================*/
	
	public org.bukkit.inventory.ItemStack toItemStack( ) {
		return new org.bukkit.inventory.ItemStack( id, ammount, durability, data );
	}
	
	/*========================================================================================================*/
	
	public Item getItem( ) {
		return item;
	}
	
	public String getName( ) {
		return item.getName( );
	}
	
	/*========================================================================================================*/
	
	public ItemStack clone( ) {
		ItemStack itemStack = new ItemStack( id, data, ammount );
		itemStack.setDurability( durability );
		return itemStack;
	}
	
	/*========================================================================================================*/
	
	private int		id			= 0;
	private byte	data		= 0;
	private short	durability	= 0;
	private int		ammount		= 1;
	
	private Item	item;
}
