/*
 * Creator - Bukkit Plugin
 * 
 * This file is based on WorldEdit by Sk89q and other contributers.
 * Used in accordance with GNU guidelines, all credits to origonal authors.
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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Block {
	
	AIR( BlockID.AIR, "Air", "air" ),
	STONE( BlockID.STONE, "Stone", "stone", "rock" ),
	GRASS( BlockID.GRASS, "Grass", "grass" ),
	DIRT( BlockID.DIRT, "Dirt", "dirt" ),
	COBBLESTONE( BlockID.COBBLESTONE, "Cobblestone", "cobblestone", "cobble" ),
	WOOD( BlockID.WOOD, "Wood", "wood", "woodplank", "plank", "woodplanks", "planks" ),
	SAPLING( BlockID.SAPLING, "Sapling", "sapling", "seedling" ),
	BEDROCK( BlockID.BEDROCK, "Bedrock", "adminium", "bedrock" ),
	WATER( BlockID.WATER, "Water", "watermoving", "movingwater", "flowingwater", "waterflowing" ),
	STATIONARY_WATER( BlockID.STATIONARY_WATER, "Water (stationary)", "water", "waterstationary", "stationarywater", "stillwater" ),
	LAVA( BlockID.LAVA, "Lava", "lavamoving", "movinglava", "flowinglava", "lavaflowing" ),
	STATIONARY_LAVA( BlockID.STATIONARY_LAVA, "Lava (stationary)", "lava", "lavastationary", "stationarylava", "stilllava" ),
	SAND( BlockID.SAND, "Sand", "sand" ), GRAVEL( BlockID.GRAVEL, "Gravel", "gravel" ),
	GOLD_ORE( BlockID.GOLD_ORE, "Gold ore", "goldore" ), IRON_ORE( BlockID.IRON_ORE, "Iron ore", "ironore" ),
	COAL_ORE( BlockID.COAL_ORE, "Coal ore", "coalore" ), LOG( BlockID.LOG, "Log", "log", "tree", "pine", "oak", "birch", "redwood" ),
	LEAVES( BlockID.LEAVES, "Leaves", "leaves", "leaf" ), SPONGE( BlockID.SPONGE, "Sponge", "sponge" ),
	GLASS( BlockID.GLASS, "Glass", "glass" ),
	LAPIS_LAZULI_ORE( BlockID.LAPIS_LAZULI_ORE, "Lapis lazuli ore", "lapislazuliore", "blueore", "lapisore" ),
	LAPIS_LAZULI( BlockID.LAPIS_LAZULI_BLOCK, "Lapis lazuli", "lapislazuli", "lapislazuliblock", "bluerock" ),
	DISPENSER( BlockID.DISPENSER, "Dispenser", "dispenser" ), SANDSTONE( BlockID.SANDSTONE, "Sandstone", "sandstone" ),
	NOTE_BLOCK( BlockID.NOTE_BLOCK, "Note block", "musicblock", "noteblock", "note", "music", "instrument" ),
	BED( BlockID.BED, "Bed", "bed" ),
	POWERED_RAIL( BlockID.POWERED_RAIL, "Powered Rail", "poweredrail", "boosterrail", "poweredtrack", "boostertrack", "booster" ),
	DETECTOR_RAIL( BlockID.DETECTOR_RAIL, "Detector Rail", "detectorrail", "detector" ),
	PISTON_STICKY_BASE( BlockID.PISTON_STICKY_BASE, "Sticky Piston", "stickypiston" ),
	WEB( BlockID.WEB, "Web", "web", "spiderweb" ),
	LONG_GRASS( BlockID.LONG_GRASS, "Long grass", "longgrass", "tallgrass" ),
	DEAD_BUSH( BlockID.DEAD_BUSH, "Shrub", "deadbush", "shrub", "deadshrub", "tumbleweed" ),
	PISTON_BASE( BlockID.PISTON_BASE, "Piston", "piston" ),
	PISTON_EXTENSION( BlockID.PISTON_EXTENSION, "Piston extension", "pistonextendsion", "pistonhead" ),
	CLOTH( BlockID.CLOTH, "Wool", "cloth", "wool" ),
	PISTON_MOVING_PIECE( BlockID.PISTON_MOVING_PIECE, "Piston moving piece", "movingpiston" ),
	YELLOW_FLOWER( BlockID.YELLOW_FLOWER, "Yellow flower", "yellowflower", "flower" ),
	RED_FLOWER( BlockID.RED_FLOWER, "Red rose", "redflower", "redrose", "rose" ),
	BROWN_MUSHROOM( BlockID.BROWN_MUSHROOM, "Brown mushroom", "brownmushroom", "mushroom" ),
	RED_MUSHROOM( BlockID.RED_MUSHROOM, "Red mushroom", "redmushroom" ),
	GOLD_BLOCK( BlockID.GOLD_BLOCK, "Gold block", "gold", "goldblock" ),
	IRON_BLOCK( BlockID.IRON_BLOCK, "Iron block", "iron", "ironblock" ),
	DOUBLE_STEP( BlockID.DOUBLE_STEP, "Double step", "doubleslab", "doublestoneslab", "doublestep" ),
	STEP( BlockID.STEP, "Step", "slab", "stoneslab", "step", "halfstep" ),
	BRICK( BlockID.BRICK, "Brick", "brick", "brickblock" ),
	TNT( BlockID.TNT, "TNT", "tnt", "c4", "explosive" ),
	BOOKCASE( BlockID.BOOKCASE, "Bookcase", "bookshelf", "bookshelves", "bookcase", "bookcases" ),
	MOSSY_COBBLESTONE( BlockID.MOSSY_COBBLESTONE, "Cobblestone (mossy)", "mossycobblestone", "mossstone", "mossystone", "mosscobble", "mossycobble", "moss", "mossy", "sossymobblecone" ),
	OBSIDIAN( BlockID.OBSIDIAN, "Obsidian", "obsidian" ),
	TORCH( BlockID.TORCH, "Torch", "torch", "light", "candle" ),
	FIRE( BlockID.FIRE, "Fire", "fire", "flame", "flames" ),
	MOB_SPAWNER( BlockID.MOB_SPAWNER, "Mob spawner", "mobspawner", "spawner" ),
	WOODEN_STAIRS( BlockID.WOODEN_STAIRS, "Wooden stairs", "woodstair", "woodstairs", "woodenstair", "woodenstairs" ),
	CHEST( BlockID.CHEST, "Chest", "chest", "storage", "storagechest" ),
	REDSTONE_WIRE( BlockID.REDSTONE_WIRE, "Redstone wire", "redstone", "redstoneblock" ),
	DIAMOND_ORE( BlockID.DIAMOND_ORE, "Diamond ore", "diamondore" ),
	DIAMOND_BLOCK( BlockID.DIAMOND_BLOCK, "Diamond block", "diamond", "diamondblock" ),
	WORKBENCH( BlockID.WORKBENCH, "Workbench", "workbench", "table", "craftingtable", "crafting" ),
	CROPS( BlockID.CROPS, "Crops", "crops", "crop", "plant", "plants" ),
	SOIL( BlockID.SOIL, "Soil", "soil", "farmland" ),
	FURNACE( BlockID.FURNACE, "Furnace", "furnace" ),
	BURNING_FURNACE( BlockID.BURNING_FURNACE, "Furnace (burning)", "burningfurnace", "litfurnace" ),
	SIGN_POST( BlockID.SIGN_POST, "Sign post", "sign", "signpost" ),
	WOODEN_DOOR( BlockID.WOODEN_DOOR, "Wooden door", "wooddoor", "woodendoor", "door" ),
	LADDER( BlockID.LADDER, "Ladder", "ladder" ),
	MINECART_TRACKS( BlockID.MINECART_TRACKS, "Minecart tracks", "track", "tracks", "minecrattrack", "minecarttracks", "rails", "rail" ),
	COBBLESTONE_STAIRS( BlockID.COBBLESTONE_STAIRS, "Cobblestone stairs", "cobblestonestair", "cobblestonestairs", "cobblestair", "cobblestairs" ),
	WALL_SIGN( BlockID.WALL_SIGN, "Wall sign", "wallsign" ),
	LEVER( BlockID.LEVER, "Lever", "lever", "switch", "stonelever", "stoneswitch" ),
	STONE_PRESSURE_PLATE( BlockID.STONE_PRESSURE_PLATE, "Stone pressure plate", "stonepressureplate", "stoneplate" ),
	IRON_DOOR( BlockID.IRON_DOOR, "Iron Door", "irondoor" ),
	WOODEN_PRESSURE_PLATE( BlockID.WOODEN_PRESSURE_PLATE, "Wooden pressure plate", "woodpressureplate", "woodplate", "woodenpressureplate", "woodenplate", "plate", "pressureplate" ),
	REDSTONE_ORE( BlockID.REDSTONE_ORE, "Redstone ore", "redstoneore" ),
	GLOWING_REDSTONE_ORE( BlockID.GLOWING_REDSTONE_ORE, "Glowing redstone ore", "glowingredstoneore" ),
	REDSTONE_TORCH_OFF( BlockID.REDSTONE_TORCH_OFF, "Redstone torch (off)", "redstonetorchoff", "rstorchoff" ),
	REDSTONE_TORCH_ON( BlockID.REDSTONE_TORCH_ON, "Redstone torch (on)", "redstonetorch", "redstonetorchon", "rstorchon", "redtorch" ),
	STONE_BUTTON( BlockID.STONE_BUTTON, "Stone Button", "stonebutton", "button" ),
	SNOW( BlockID.SNOW, "Snow", "snow" ),
	ICE( BlockID.ICE, "Ice", "ice" ),
	SNOW_BLOCK( BlockID.SNOW_BLOCK, "Snow block", "snowblock" ),
	CACTUS( BlockID.CACTUS, "Cactus", "cactus", "cacti" ),
	CLAY( BlockID.CLAY, "Clay", "clay" ),
	SUGAR_CANE( BlockID.REED, "Reed", "reed", "cane", "sugarcane", "sugarcanes", "vine", "vines" ),
	JUKEBOX( BlockID.JUKEBOX, "Jukebox", "jukebox", "stereo", "recordplayer" ),
	FENCE( BlockID.FENCE, "Fence", "fence" ), PUMPKIN( BlockID.PUMPKIN, "Pumpkin", "pumpkin" ),
	NETHERRACK( BlockID.NETHERRACK, "Netherrack", "redmossycobblestone", "redcobblestone", "redmosstone", "redcobble", "netherstone", "netherrack", "nether", "hellstone" ),
	SOUL_SAND( BlockID.SLOW_SAND, "Soul sand", "slowmud", "mud", "soulsand", "hellmud" ),
	GLOWSTONE( BlockID.LIGHTSTONE, "Glowstone", "brittlegold", "glowstone", "lightstone", "brimstone", "australium" ),
	PORTAL( BlockID.PORTAL, "Portal", "portal" ),
	JACK_O_LANTERN( BlockID.JACKOLANTERN, "Pumpkin (on)", "pumpkinlighted", "pumpkinon", "litpumpkin", "jackolantern" ),
	CAKE( BlockID.CAKE_BLOCK, "Cake", "cake", "cakeblock" ),
	REDSTONE_REPEATER_OFF( BlockID.REDSTONE_REPEATER_OFF, "Redstone repeater (off)", "diodeoff", "redstonerepeater", "repeateroff", "delayeroff" ),
	REDSTONE_REPEATER_ON( BlockID.REDSTONE_REPEATER_ON, "Redstone repeater (on)", "diodeon", "redstonerepeateron", "repeateron", "delayeron" ), LOCKED_CHEST( BlockID.LOCKED_CHEST, "Locked chest", "lockedchest", "steveco", "supplycrate", "valveneedstoworkonep3nottf2kthx" ),
	TRAP_DOOR( BlockID.TRAP_DOOR, "Trap door", "trapdoor", "hatch", "floordoor" ),
	SILVERFISH_BLOCK( BlockID.SILVERFISH_BLOCK, "Silverfish block", "silverfish", "silver" ),
	STONE_BRICK( BlockID.STONE_BRICK, "Stone brick", "stonebrick", "sbrick", "smoothstonebrick" ),
	RED_MUSHROOM_CAP( BlockID.RED_MUSHROOM_CAP, "Red mushroom cap", "giantmushroomred", "redgiantmushroom", "redmushroomcap" ),
	BROWN_MUSHROOM_CAP( BlockID.BROWN_MUSHROOM_CAP, "Brown mushroom cap", "giantmushroombrown", "browngiantmushoom", "brownmushroomcap" ),
	IRON_BARS( BlockID.IRON_BARS, "Iron bars", "ironbars", "ironfence" ),
	GLASS_PANE( BlockID.GLASS_PANE, "Glass pane", "window", "glasspane", "glasswindow" ),
	MELON_BLOCK( BlockID.MELON_BLOCK, "Melon (block)", "melonblock" ),
	PUMPKIN_STEM( BlockID.PUMPKIN_STEM, "Pumpkin stem", "pumpkinstem" ),
	MELON_STEM( BlockID.MELON_STEM, "Melon stem", "melonstem" ),
	VINE( BlockID.VINE, "Vine", "vine", "vines", "creepers" ),
	FENCE_GATE( BlockID.FENCE_GATE, "Fence gate", "fencegate", "gate" ),
	BRICK_STAIRS( BlockID.BRICK_STAIRS, "Brick stairs", "brickstairs", "bricksteps" ),
	STONE_BRICK_STAIRS( BlockID.STONE_BRICK_STAIRS, "Stone brick stairs", "stonebrickstairs", "smoothstonebrickstairs" ),
	MYCELIUM( BlockID.MYCELIUM, "Mycelium", "fungus", "mycel" ),
	LILY_PAD( BlockID.LILY_PAD, "Lily pad", "lilypad", "waterlily" ),
	NETHER_BRICK( BlockID.NETHER_BRICK, "Nether brick", "netherbrick" ),
	NETHER_BRICK_FENCE( BlockID.NETHER_BRICK_FENCE, "Nether brick fence", "netherbrickfence", "netherfence" ),
	NETHER_BRICK_STAIRS( BlockID.NETHER_BRICK_STAIRS, "Nether brick stairs", "netherbrickstairs", "netherbricksteps", "netherstairs", "nethersteps" ), NETHER_WART( BlockID.NETHER_WART, "Nether wart", "netherwart", "netherstalk" ),
	ENCHANTMENT_TABLE( BlockID.ENCHANTMENT_TABLE, "Enchantment table", "enchantmenttable", "enchanttable" ),
	BREWING_STAND( BlockID.BREWING_STAND, "Brewing Stand", "brewingstand" ),
	CAULDRON( BlockID.CAULDRON, "Cauldron", "cauldron" ),
	END_PORTAL( BlockID.END_PORTAL, "End Portal", "endportal", "blackstuff", "airportal", "weirdblackstuff" ),
	END_PORTAL_FRAME( BlockID.END_PORTAL_FRAME, "End Portal Frame", "endportalframe", "airportalframe", "crystalblock" ),
	END_STONE( BlockID.END_STONE, "End Stone", "endstone", "enderstone", "endersand" ),
	DRAGON_EGG( BlockID.DRAGON_EGG, "Dragon Egg", "dragonegg", "dragons" ),
	REDSTONE_LAMP_OFF( BlockID.REDSTONE_LAMP_OFF, "Redstone lamp (off)", "redstonelamp", "redstonelampoff", "rslamp", "rslampoff", "rsglow", "rsglowoff" ),
	REDSTONE_LAMP_ON( BlockID.REDSTONE_LAMP_ON, "Redstone lamp (on)", "redstonelampon", "rslampon", "rsglowon" ),
	DOUBLE_WOODEN_STEP( BlockID.DOUBLE_WOODEN_STEP, "Double wood step", "doublewoodslab", "doublewoodstep" ),
	WOODEN_STEP( BlockID.WOODEN_STEP, "Wood step", "woodenslab", "woodslab", "woodstep", "woodhalfstep" ),
	COCOA_PLANT( BlockID.COCOA_PLANT, "Cocoa plant", "cocoplant", "cocoaplant" ),
	SANDSTONE_STAIRS( BlockID.SANDSTONE_STAIRS, "Sandstone stairs", "sandstairs", "sandstonestairs" ),
	EMERALD_ORE( BlockID.EMERALD_ORE, "Emerald ore", "emeraldore" ),
	ENDER_CHEST( BlockID.ENDER_CHEST, "Ender chest", "enderchest" ),
	TRIPWIRE_HOOK( BlockID.TRIPWIRE_HOOK, "Tripwire hook", "tripwirehook" ),
	TRIPWIRE( BlockID.TRIPWIRE, "Tripwire", "tripwire" ),
	EMERALD_BLOCK( BlockID.EMERALD_BLOCK, "Emerald block", "emeraldblock" ),
	SPRUCE_WOOD_STAIRS( BlockID.SPRUCE_WOOD_STAIRS, "Spruce wood stairs", "sprucestairs", "sprucewoodstairs" ),
	BIRCH_WOOD_STAIRS( BlockID.BIRCH_WOOD_STAIRS, "Birch wood stairs", "birchstairs", "birchwoodstairs" ),
	JUNGLE_WOOD_STAIRS( BlockID.JUNGLE_WOOD_STAIRS, "Jungle wood stairs", "junglestairs", "junglewoodstairs" ),
	
	//Note: These are just here because =D
	CAKE_BLOCK( BlockID.CAKE_BLOCK, "Cake", "cake" ),
	REED( BlockID.REED, "Reed", "reed" );
	
	/*========================================================================================================*/
	
	public Block get(int id) {
		return ids.get( id );
	}
	
	public Block get(String name) {
		return lookup.get( name );
	}
	
	private static final Map< Integer, Block >	ids				= new HashMap< Integer, Block >( );
	private static final Map< String, Block >	lookup			= new HashMap< String, Block >( );
	
	static {
	        for (Block block : EnumSet.allOf(Block.class)) {
	            ids.put(block.id, block);
	            for (String key : block.lookupKeys) {
	                lookup.put(key, block);
	            }
	        }
	    }
	
	/*========================================================================================================*/
	
	Block( int id, String name, String lookupKey ) {
		this.id = id;
		this.name = name;
		this.lookupKeys = new String[] { lookupKey };
	}
	
	Block( int id, String name, String... lookupKeys ) {
		this.id = id;
		this.name = name;
		this.lookupKeys = lookupKeys;
	}
	
	/*========================================================================================================*/
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	private final int							id;
	private final String						name;
	private final String[]						lookupKeys;
	 
	/*========================================================================================================*/
	
	private DataHolder					dataValues		= null;
	
	static final DataHolder						woodData		= new DataHolder( 0, "Oak" ).add( 1, "Pine" ).add( 1, "Spruce" ).add( 2, "Birch" ).add( 3, "Jungle" );
	static final DataHolder						slabData		= new DataHolder( 0, "Stone" ).add( 1, "Sandstone" ).add( 2, "Wooden" ).add( 2, "Wood" ).add( 3, "Cobblestone" ).add( 3, "Cobble" ).add( 4, "Brick" ).add( 4, "StoneBrick" );
	static final DataHolder						sandstoneData	= new DataHolder( 0, "Normal" ).add( 1, "Chiseled" ).add( 2, "Smooth" );
	static final DataHolder						stoneData		= new DataHolder( 0, "Normal" ).add( 1, "Mossy" ).add( 2, "Cracked" ).add( 3, "Chiseled" );
	static final DataHolder						woolData		= new DataHolder( 0, "White" ).add( 1, "Orange" ).add( 2, "Magenta" ).add( 3, "LightBlue" ).add( 4, "Yellow" ).add( 5, "Lime" ).add( 6, "Pink" ).add( 7, "Gray" ).add( 8, "LightGray" ).add( 9, "Cyan" ).add( 10, "Purple" ).add( 11, "Blue" ).add( 12, "Brown" ).add( 13, "Green" ).add( 14, "Red" ).add( 15, "Black" );
	
	static {
		WOOD.dataValues = woodData;
		SAPLING.dataValues = woodData;
		LOG.dataValues = woodData;
		LEAVES.dataValues = woodData;
		SANDSTONE.dataValues = sandstoneData;
		CLOTH.dataValues = woolData;
		DOUBLE_STEP.dataValues = slabData;
		STEP.dataValues = slabData;
		WOODEN_STAIRS.dataValues = woodData;
		STONE_BRICK.dataValues = stoneData;
		DOUBLE_WOODEN_STEP.dataValues = woodData;
		WOODEN_STEP.dataValues = woodData;
	}
	
	public int getDataValue(String name) {
		if ( this.dataValues == null ) return -1;
		return this.dataValues.get( name );
	}
	
	public boolean validDataValue(int value) {
		if ( this.dataValues == null ) return false;
		return this.dataValues.valid( value );
	}
	
	public String nameDataValue(int value) {
		if ( this.dataValues == null ) return null;
		return this.dataValues.name( value );
	}
	
	/*========================================================================================================*/
	/*========================================================================================================*/
	
	private boolean shouldPlaceLast = false;
	
    static {
        SAPLING.shouldPlaceLast = true;
        BED.shouldPlaceLast = true;
        POWERED_RAIL.shouldPlaceLast = true;
        DETECTOR_RAIL.shouldPlaceLast = true;
        LONG_GRASS.shouldPlaceLast = true;
        DEAD_BUSH.shouldPlaceLast = true;
        PISTON_EXTENSION.shouldPlaceLast = true;
        YELLOW_FLOWER.shouldPlaceLast = true;
        RED_FLOWER.shouldPlaceLast = true;
        BROWN_MUSHROOM.shouldPlaceLast = true;
        RED_MUSHROOM.shouldPlaceLast = true;
        TORCH.shouldPlaceLast = true;
        FIRE.shouldPlaceLast = true;
        REDSTONE_WIRE.shouldPlaceLast = true;
        CROPS.shouldPlaceLast = true;
        LADDER.shouldPlaceLast = true;
        MINECART_TRACKS.shouldPlaceLast = true;
        LEVER.shouldPlaceLast = true;
        STONE_PRESSURE_PLATE.shouldPlaceLast = true;
        WOODEN_PRESSURE_PLATE.shouldPlaceLast = true;
        REDSTONE_TORCH_OFF.shouldPlaceLast = true;
        REDSTONE_TORCH_ON.shouldPlaceLast = true;
        STONE_BUTTON.shouldPlaceLast = true;
        SNOW.shouldPlaceLast = true;
        PORTAL.shouldPlaceLast = true;
        REDSTONE_REPEATER_OFF.shouldPlaceLast = true;
        REDSTONE_REPEATER_ON.shouldPlaceLast = true;
        TRAP_DOOR.shouldPlaceLast = true;
        VINE.shouldPlaceLast = true;
        LILY_PAD.shouldPlaceLast = true;
        NETHER_WART.shouldPlaceLast = true;
        PISTON_BASE.shouldPlaceLast = true;
        PISTON_STICKY_BASE.shouldPlaceLast = true;
        PISTON_EXTENSION.shouldPlaceLast = true;
        PISTON_MOVING_PIECE.shouldPlaceLast = true;
        COCOA_PLANT.shouldPlaceLast = true;
        TRIPWIRE_HOOK.shouldPlaceLast = true;
        TRIPWIRE.shouldPlaceLast = true;
    }
    
    public boolean shouldPlaceLast() {
    	return this.shouldPlaceLast;
    }
    
    /*========================================================================================================*/
    
    private boolean shouldPlaceFinal = false;
    
    static {
    	SIGN_POST.shouldPlaceFinal = true;
        WOODEN_DOOR.shouldPlaceFinal = true;
        WALL_SIGN.shouldPlaceFinal = true;
        IRON_DOOR.shouldPlaceFinal = true;
        CACTUS.shouldPlaceFinal = true;
        REED.shouldPlaceFinal = true;
        CAKE_BLOCK.shouldPlaceFinal = true;
        PISTON_EXTENSION.shouldPlaceFinal = true;
        PISTON_MOVING_PIECE.shouldPlaceFinal = true;
    }
    
    public boolean shouldPlaceFinal() {
    	return this.shouldPlaceFinal;
    }
    
    /*========================================================================================================*/
    
    private boolean canPassThrough = false;
    
    static {
		AIR.canPassThrough = true;
		WATER.canPassThrough = true;
		STATIONARY_WATER.canPassThrough = true;
		SAPLING.canPassThrough = true;
		POWERED_RAIL.canPassThrough = true;
		DETECTOR_RAIL.canPassThrough = true;
		WEB.canPassThrough = true;
		LONG_GRASS.canPassThrough = true;
		DEAD_BUSH.canPassThrough = true;
		YELLOW_FLOWER.canPassThrough = true;
		RED_FLOWER.canPassThrough = true;
		BROWN_MUSHROOM.canPassThrough = true;
		RED_MUSHROOM.canPassThrough = true;
		TORCH.canPassThrough = true;
		FIRE.canPassThrough = true;
		REDSTONE_WIRE.canPassThrough = true;
		CROPS.canPassThrough = true;
		SIGN_POST.canPassThrough = true;
		LADDER.canPassThrough = true;
		MINECART_TRACKS.canPassThrough = true;
		WALL_SIGN.canPassThrough = true;
		LEVER.canPassThrough = true;
		STONE_PRESSURE_PLATE.canPassThrough = true;
		WOODEN_PRESSURE_PLATE.canPassThrough = true;
		REDSTONE_TORCH_OFF.canPassThrough = true;
		REDSTONE_TORCH_ON.canPassThrough = true;
		STONE_BUTTON.canPassThrough = true;
		SNOW.canPassThrough = true;
		REED.canPassThrough = true;
		PORTAL.canPassThrough = true;
		REDSTONE_REPEATER_OFF.canPassThrough = true;
		REDSTONE_REPEATER_ON.canPassThrough = true;
		PUMPKIN_STEM.canPassThrough = true;
		MELON_STEM.canPassThrough = true;
		VINE.canPassThrough = true;
		NETHER_WART.canPassThrough = true;
		END_PORTAL.canPassThrough = true;
		TRIPWIRE_HOOK.canPassThrough = true;
		TRIPWIRE.canPassThrough = true;
    }
    
    public boolean canPassThrough() {
    	return this.canPassThrough;
    }
    
    /*========================================================================================================*/
    
    private boolean isContainerBlock = false;
    
    static {
        DISPENSER.isContainerBlock = true;
        FURNACE.isContainerBlock = true;
        BURNING_FURNACE.isContainerBlock = true;
        CHEST.isContainerBlock = true;
        BREWING_STAND.isContainerBlock = true;
    }
    
    public boolean isContainerBlock() {
    	return this.isContainerBlock;
    }
    
    /*========================================================================================================*/
    
	private boolean isRedstoneBlock = false;
    
	static {
		POWERED_RAIL.isRedstoneBlock = true;
		DETECTOR_RAIL.isRedstoneBlock = true;
		PISTON_STICKY_BASE.isRedstoneBlock = true;
		PISTON_BASE.isRedstoneBlock = true;
		LEVER.isRedstoneBlock = true;
		STONE_PRESSURE_PLATE.isRedstoneBlock = true;
		WOODEN_PRESSURE_PLATE.isRedstoneBlock = true;
		REDSTONE_TORCH_OFF.isRedstoneBlock = true;
		REDSTONE_TORCH_ON.isRedstoneBlock = true;
		STONE_BUTTON.isRedstoneBlock = true;
		REDSTONE_WIRE.isRedstoneBlock = true;
		WOODEN_DOOR.isRedstoneBlock = true;
		IRON_DOOR.isRedstoneBlock = true;
		TNT.isRedstoneBlock = true;
		DISPENSER.isRedstoneBlock = true;
		NOTE_BLOCK.isRedstoneBlock = true;
		REDSTONE_REPEATER_OFF.isRedstoneBlock = true;
		REDSTONE_REPEATER_ON.isRedstoneBlock = true;
		TRIPWIRE_HOOK.isRedstoneBlock = true;
	}
	
	public boolean isRedstoneBlock() {
    	return this.isRedstoneBlock;
    }
	
	 /*========================================================================================================*/
	 /*========================================================================================================*/
	
	//private int dropItem = this.id;
	//private int dropAmount = 1;
	//private int dropChance = 0;
	//TODO: Block drop and blockbag data =D
	
}
