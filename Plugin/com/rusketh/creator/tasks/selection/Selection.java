package com.rusketh.creator.tasks.selection;

import java.util.ArrayList;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;

import com.rusketh.util.Direction;

public abstract class Selection implements Cloneable {
	
	public Selection( World world ) {
		this.world = world;
		reset();
	}
	
	/*========================================================================================================*/
	
	public World getWorld( ) {
		return world;
	}
	
	public void setWorld( World world ) {
		this.world = world;
		reset( );
	}
	
	/*========================================================================================================*/
	
	public void setMin( Vector min ) {
		this.min = min;
	}
	
	public void setMax( Vector max ) {
		this.max = max;
	}
	
	/*========================================================================================================*/
	
	public Vector getMin( ) {
		return min;
	}
	
	public Vector getMax( ){
		return max;
	}
	
	public Vector getCenter( ) {
		return min.add( getSize().divide( new Vector(2, 2, 2 ) ) );
	}
	
	/*========================================================================================================*/
	
	public abstract int getVolume( );
	
	public Vector getSize( ) {
		return max.subtract( min );
	}
	
	/*========================================================================================================*/
	
	public abstract int expand(Direction dir, int amount);
	
	/*========================================================================================================*/
	
	public abstract Vector getFirst( );
	
	public abstract Vector getNext( );
	
	public abstract ArrayList< Vector > getNext( int count );
	
	/*========================================================================================================*/
	
	public abstract boolean contains( Vector pos );
	
	public abstract boolean isValid();
	
	/*========================================================================================================*/
	
	public abstract Selection clone() throws CloneNotSupportedException;
	
	/*========================================================================================================*/
	
	public void reset() {
		min = new Vector(0, 0, 0);
		max = new Vector(0, 0, 0);
	}
	
	/*========================================================================================================*/
	
	public abstract void wandEvent(Player player, Block block, Action action);
	
	/*========================================================================================================*/
	
	private World	world;
	private Vector 	min, max;
}
