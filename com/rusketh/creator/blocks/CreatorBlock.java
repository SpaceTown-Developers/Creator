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

package com.rusketh.creator.blocks;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.EntityType;

public enum CreatorBlock {
	
	AIR( BlockID.AIR, "Air", "air" ),
	STONE( BlockID.STONE, "Stone", "stone", "rock" ),
	GRASS( BlockID.GRASS, "Grass", "grass" ),
	DIRT( BlockID.DIRT, "Dirt", "dirt" ),
	COBBLESTONE(
			BlockID.COBBLESTONE,
			"Cobblestone",
			"cobblestone",
			"cobble" ),
	WOOD(
			BlockID.WOOD,
			"Wood",
			"wood",
			"woodplank",
			"plank",
			"woodplanks",
			"planks" ),
	SAPLING(
			BlockID.SAPLING,
			"Sapling",
			"sapling",
			"seedling" ),
	BEDROCK(
			BlockID.BEDROCK,
			"Bedrock",
			"adminium",
			"bedrock" ),
	WATER(
			BlockID.WATER,
			"Water",
			"watermoving",
			"movingwater",
			"flowingwater",
			"waterflowing" ),
	STATIONARY_WATER(
			BlockID.STATIONARY_WATER,
			"Water (stationary)",
			"water",
			"waterstationary",
			"stationarywater",
			"stillwater" ),
	LAVA(
			BlockID.LAVA,
			"Lava",
			"lavamoving",
			"movinglava",
			"flowinglava",
			"lavaflowing" ),
	STATIONARY_LAVA(
			BlockID.STATIONARY_LAVA,
			"Lava (stationary)",
			"lava",
			"lavastationary",
			"stationarylava",
			"stilllava" ),
	SAND( BlockID.SAND, "Sand", "sand" ),
	GRAVEL( BlockID.GRAVEL, "Gravel", "gravel" ),
	GOLD_ORE( BlockID.GOLD_ORE, "Gold ore", "goldore" ),
	IRON_ORE( BlockID.IRON_ORE, "Iron ore", "ironore" ),
	COAL_ORE( BlockID.COAL_ORE, "Coal ore", "coalore" ),
	LOG(
			BlockID.LOG,
			"Log",
			"log",
			"tree",
			"pine",
			"oak",
			"birch",
			"redwood" ),
	LEAVES( BlockID.LEAVES, "Leaves", "leaves", "leaf" ),
	SPONGE( BlockID.SPONGE, "Sponge", "sponge" ),
	GLASS( BlockID.GLASS, "Glass", "glass" ),
	LAPIS_LAZULI_ORE(
			BlockID.LAPIS_LAZULI_ORE,
			"Lapis lazuli ore",
			"lapislazuliore",
			"blueore",
			"lapisore" ),
	LAPIS_LAZULI(
			BlockID.LAPIS_LAZULI_BLOCK,
			"Lapis lazuli",
			"lapislazuli",
			"lapislazuliblock",
			"bluerock" ),
	DISPENSER( BlockID.DISPENSER, "Dispenser", "dispenser" ),
	SANDSTONE( BlockID.SANDSTONE, "Sandstone", "sandstone" ),
	NOTE_BLOCK(
			BlockID.NOTE_BLOCK,
			"Note block",
			"musicblock",
			"noteblock",
			"note",
			"music",
			"instrument" ),
	BED( BlockID.BED, "Bed", "bed" ),
	POWERED_RAIL(
			BlockID.POWERED_RAIL,
			"Powered Rail",
			"poweredrail",
			"boosterrail",
			"poweredtrack",
			"boostertrack",
			"booster" ),
	DETECTOR_RAIL(
			BlockID.DETECTOR_RAIL,
			"Detector Rail",
			"detectorrail",
			"detector" ),
	PISTON_STICKY_BASE(
			BlockID.PISTON_STICKY_BASE,
			"Sticky Piston",
			"stickypiston" ),
	WEB( BlockID.WEB, "Web", "web", "spiderweb" ),
	LONG_GRASS(
			BlockID.LONG_GRASS,
			"Long grass",
			"longgrass",
			"tallgrass" ),
	DEAD_BUSH(
			BlockID.DEAD_BUSH,
			"Shrub",
			"deadbush",
			"shrub",
			"deadshrub",
			"tumbleweed" ),
	PISTON_BASE( BlockID.PISTON_BASE, "Piston", "piston" ),
	PISTON_EXTENSION(
			BlockID.PISTON_EXTENSION,
			"Piston extension",
			"pistonextendsion",
			"pistonhead" ),
	CLOTH( BlockID.CLOTH, "Wool", "cloth", "wool" ),
	PISTON_MOVING_PIECE(
			BlockID.PISTON_MOVING_PIECE,
			"Piston moving piece",
			"movingpiston" ),
	YELLOW_FLOWER(
			BlockID.YELLOW_FLOWER,
			"Yellow flower",
			"yellowflower",
			"flower" ),
	RED_FLOWER(
			BlockID.RED_FLOWER,
			"Red rose",
			"redflower",
			"redrose",
			"rose" ),
	BROWN_MUSHROOM(
			BlockID.BROWN_MUSHROOM,
			"Brown mushroom",
			"brownmushroom",
			"mushroom" ),
	RED_MUSHROOM(
			BlockID.RED_MUSHROOM,
			"Red mushroom",
			"redmushroom" ),
	GOLD_BLOCK(
			BlockID.GOLD_BLOCK,
			"Gold block",
			"gold",
			"goldblock" ),
	IRON_BLOCK(
			BlockID.IRON_BLOCK,
			"Iron block",
			"iron",
			"ironblock" ),
	DOUBLE_STEP(
			BlockID.DOUBLE_STEP,
			"Double step",
			"doubleslab",
			"doublestoneslab",
			"doublestep" ),
	STEP(
			BlockID.STEP,
			"Step",
			"slab",
			"stoneslab",
			"step",
			"halfstep" ),
	BRICK( BlockID.BRICK, "Brick", "brick", "brickblock" ),
	TNT( BlockID.TNT, "TNT", "tnt", "c4", "explosive" ),
	BOOKCASE(
			BlockID.BOOKCASE,
			"Bookcase",
			"bookshelf",
			"bookshelves",
			"bookcase",
			"bookcases" ),
	MOSSY_COBBLESTONE(
			BlockID.MOSSY_COBBLESTONE,
			"Cobblestone (mossy)",
			"mossycobblestone",
			"mossstone",
			"mossystone",
			"mosscobble",
			"mossycobble",
			"moss",
			"mossy",
			"sossymobblecone" ),
	OBSIDIAN( BlockID.OBSIDIAN, "Obsidian", "obsidian" ),
	TORCH(
			BlockID.TORCH,
			"Torch",
			"torch",
			"light",
			"candle" ),
	FIRE( BlockID.FIRE, "Fire", "fire", "flame", "flames" ),
	MOB_SPAWNER(
			BlockID.MOB_SPAWNER,
			"Mob spawner",
			"mobspawner",
			"spawner" ),
	WOODEN_STAIRS(
			BlockID.WOODEN_STAIRS,
			"Wooden stairs",
			"woodstair",
			"woodstairs",
			"woodenstair",
			"woodenstairs" ),
	CHEST(
			BlockID.CHEST,
			"Chest",
			"chest",
			"storage",
			"storagechest" ),
	REDSTONE_WIRE(
			BlockID.REDSTONE_WIRE,
			"Redstone wire",
			"redstone",
			"redstoneblock" ),
	DIAMOND_ORE(
			BlockID.DIAMOND_ORE,
			"Diamond ore",
			"diamondore" ),
	DIAMOND_BLOCK(
			BlockID.DIAMOND_BLOCK,
			"Diamond block",
			"diamond",
			"diamondblock" ),
	WORKBENCH(
			BlockID.WORKBENCH,
			"Workbench",
			"workbench",
			"table",
			"craftingtable",
			"crafting" ),
	CROPS(
			BlockID.CROPS,
			"Crops",
			"crops",
			"crop",
			"plant",
			"plants" ),
	SOIL( BlockID.SOIL, "Soil", "soil", "farmland" ),
	FURNACE( BlockID.FURNACE, "Furnace", "furnace" ),
	BURNING_FURNACE(
			BlockID.BURNING_FURNACE,
			"Furnace (burning)",
			"burningfurnace",
			"litfurnace" ),
	SIGN_POST(
			BlockID.SIGN_POST,
			"Sign post",
			"sign",
			"signpost" ),
	WOODEN_DOOR(
			BlockID.WOODEN_DOOR,
			"Wooden door",
			"wooddoor",
			"woodendoor",
			"door" ),
	LADDER( BlockID.LADDER, "Ladder", "ladder" ),
	MINECART_TRACKS(
			BlockID.MINECART_TRACKS,
			"Minecart tracks",
			"track",
			"tracks",
			"minecrattrack",
			"minecarttracks",
			"rails",
			"rail" ),
	COBBLESTONE_STAIRS(
			BlockID.COBBLESTONE_STAIRS,
			"Cobblestone stairs",
			"cobblestonestair",
			"cobblestonestairs",
			"cobblestair",
			"cobblestairs" ),
	WALL_SIGN( BlockID.WALL_SIGN, "Wall sign", "wallsign" ),
	LEVER(
			BlockID.LEVER,
			"Lever",
			"lever",
			"switch",
			"stonelever",
			"stoneswitch" ),
	STONE_PRESSURE_PLATE(
			BlockID.STONE_PRESSURE_PLATE,
			"Stone pressure plate",
			"stonepressureplate",
			"stoneplate" ),
	IRON_DOOR( BlockID.IRON_DOOR, "Iron Door", "irondoor" ),
	WOODEN_PRESSURE_PLATE(
			BlockID.WOODEN_PRESSURE_PLATE,
			"Wooden pressure plate",
			"woodpressureplate",
			"woodplate",
			"woodenpressureplate",
			"woodenplate",
			"plate",
			"pressureplate" ),
	REDSTONE_ORE(
			BlockID.REDSTONE_ORE,
			"Redstone ore",
			"redstoneore" ),
	GLOWING_REDSTONE_ORE(
			BlockID.GLOWING_REDSTONE_ORE,
			"Glowing redstone ore",
			"glowingredstoneore" ),
	REDSTONE_TORCH_OFF(
			BlockID.REDSTONE_TORCH_OFF,
			"Redstone torch (off)",
			"redstonetorchoff",
			"rstorchoff" ),
	REDSTONE_TORCH_ON(
			BlockID.REDSTONE_TORCH_ON,
			"Redstone torch (on)",
			"redstonetorch",
			"redstonetorchon",
			"rstorchon",
			"redtorch" ),
	STONE_BUTTON(
			BlockID.STONE_BUTTON,
			"Stone Button",
			"stonebutton",
			"button" ),
	SNOW( BlockID.SNOW, "Snow", "snow" ),
	ICE( BlockID.ICE, "Ice", "ice" ),
	SNOW_BLOCK(
			BlockID.SNOW_BLOCK,
			"Snow block",
			"snowblock" ),
	CACTUS( BlockID.CACTUS, "Cactus", "cactus", "cacti" ),
	CLAY( BlockID.CLAY, "Clay", "clay" ),
	SUGAR_CANE(
			BlockID.REED,
			"Reed",
			"reed",
			"cane",
			"sugarcane",
			"sugarcanes",
			"vine",
			"vines" ),
	JUKEBOX(
			BlockID.JUKEBOX,
			"Jukebox",
			"jukebox",
			"stereo",
			"recordplayer" ),
	FENCE( BlockID.FENCE, "Fence", "fence" ),
	PUMPKIN( BlockID.PUMPKIN, "Pumpkin", "pumpkin" ),
	NETHERRACK(
			BlockID.NETHERRACK,
			"Netherrack",
			"redmossycobblestone",
			"redcobblestone",
			"redmosstone",
			"redcobble",
			"netherstone",
			"netherrack",
			"nether",
			"hellstone" ),
	SOUL_SAND(
			BlockID.SLOW_SAND,
			"Soul sand",
			"slowmud",
			"mud",
			"soulsand",
			"hellmud" ),
	GLOWSTONE(
			BlockID.LIGHTSTONE,
			"Glowstone",
			"brittlegold",
			"glowstone",
			"lightstone",
			"brimstone",
			"australium" ),
	PORTAL( BlockID.PORTAL, "Portal", "portal" ),
	JACK_O_LANTERN(
			BlockID.JACKOLANTERN,
			"Pumpkin (on)",
			"pumpkinlighted",
			"pumpkinon",
			"litpumpkin",
			"jackolantern" ),
	CAKE( BlockID.CAKE_BLOCK, "Cake", "cake", "cakeblock" ),
	REDSTONE_REPEATER_OFF(
			BlockID.REDSTONE_REPEATER_OFF,
			"Redstone repeater (off)",
			"diodeoff",
			"redstonerepeater",
			"repeateroff",
			"delayeroff" ),
	REDSTONE_REPEATER_ON(
			BlockID.REDSTONE_REPEATER_ON,
			"Redstone repeater (on)",
			"diodeon",
			"redstonerepeateron",
			"repeateron",
			"delayeron" ),
	LOCKED_CHEST(
			BlockID.LOCKED_CHEST,
			"Locked chest",
			"lockedchest",
			"steveco",
			"supplycrate",
			"valveneedstoworkonep3nottf2kthx" ),
	TRAP_DOOR(
			BlockID.TRAP_DOOR,
			"Trap door",
			"trapdoor",
			"hatch",
			"floordoor" ),
	SILVERFISH_BLOCK(
			BlockID.SILVERFISH_BLOCK,
			"Silverfish block",
			"silverfish",
			"silver" ),
	STONE_BRICK(
			BlockID.STONE_BRICK,
			"Stone brick",
			"stonebrick",
			"sbrick",
			"smoothstonebrick" ),
	RED_MUSHROOM_CAP(
			BlockID.RED_MUSHROOM_CAP,
			"Red mushroom cap",
			"giantmushroomred",
			"redgiantmushroom",
			"redmushroomcap" ),
	BROWN_MUSHROOM_CAP(
			BlockID.BROWN_MUSHROOM_CAP,
			"Brown mushroom cap",
			"giantmushroombrown",
			"browngiantmushoom",
			"brownmushroomcap" ),
	IRON_BARS(
			BlockID.IRON_BARS,
			"Iron bars",
			"ironbars",
			"ironfence" ),
	GLASS_PANE(
			BlockID.GLASS_PANE,
			"Glass pane",
			"window",
			"glasspane",
			"glasswindow" ),
	MELON_BLOCK(
			BlockID.MELON_BLOCK,
			"Melon (block)",
			"melonblock" ),
	PUMPKIN_STEM(
			BlockID.PUMPKIN_STEM,
			"Pumpkin stem",
			"pumpkinstem" ),
	MELON_STEM(
			BlockID.MELON_STEM,
			"Melon stem",
			"melonstem" ),
	VINE( BlockID.VINE, "Vine", "vine", "vines", "creepers" ),
	FENCE_GATE(
			BlockID.FENCE_GATE,
			"Fence gate",
			"fencegate",
			"gate" ),
	BRICK_STAIRS(
			BlockID.BRICK_STAIRS,
			"Brick stairs",
			"brickstairs",
			"bricksteps" ),
	STONE_BRICK_STAIRS(
			BlockID.STONE_BRICK_STAIRS,
			"Stone brick stairs",
			"stonebrickstairs",
			"smoothstonebrickstairs" ),
	MYCELIUM(
			BlockID.MYCELIUM,
			"Mycelium",
			"fungus",
			"mycel" ),
	LILY_PAD(
			BlockID.LILY_PAD,
			"Lily pad",
			"lilypad",
			"waterlily" ),
	NETHER_BRICK(
			BlockID.NETHER_BRICK,
			"Nether brick",
			"netherbrick" ),
	NETHER_BRICK_FENCE(
			BlockID.NETHER_BRICK_FENCE,
			"Nether brick fence",
			"netherbrickfence",
			"netherfence" ),
	NETHER_BRICK_STAIRS(
			BlockID.NETHER_BRICK_STAIRS,
			"Nether brick stairs",
			"netherbrickstairs",
			"netherbricksteps",
			"netherstairs",
			"nethersteps" ),
	NETHER_WART(
			BlockID.NETHER_WART,
			"Nether wart",
			"netherwart",
			"netherstalk" ),
	ENCHANTMENT_TABLE(
			BlockID.ENCHANTMENT_TABLE,
			"Enchantment table",
			"enchantmenttable",
			"enchanttable" ),
	BREWING_STAND(
			BlockID.BREWING_STAND,
			"Brewing Stand",
			"brewingstand" ),
	CAULDRON( BlockID.CAULDRON, "Cauldron", "cauldron" ),
	END_PORTAL(
			BlockID.END_PORTAL,
			"End Portal",
			"endportal",
			"blackstuff",
			"airportal",
			"weirdblackstuff" ),
	END_PORTAL_FRAME(
			BlockID.END_PORTAL_FRAME,
			"End Portal Frame",
			"endportalframe",
			"airportalframe",
			"crystalblock" ),
	END_STONE(
			BlockID.END_STONE,
			"End Stone",
			"endstone",
			"enderstone",
			"endersand" ),
	DRAGON_EGG(
			BlockID.DRAGON_EGG,
			"Dragon Egg",
			"dragonegg",
			"dragons" ),
	REDSTONE_LAMP_OFF(
			BlockID.REDSTONE_LAMP_OFF,
			"Redstone lamp (off)",
			"redstonelamp",
			"redstonelampoff",
			"rslamp",
			"rslampoff",
			"rsglow",
			"rsglowoff" ),
	REDSTONE_LAMP_ON(
			BlockID.REDSTONE_LAMP_ON,
			"Redstone lamp (on)",
			"redstonelampon",
			"rslampon",
			"rsglowon" ),
	DOUBLE_WOODEN_STEP(
			BlockID.DOUBLE_WOODEN_STEP,
			"Double wood step",
			"doublewoodslab",
			"doublewoodstep" ),
	WOODEN_STEP(
			BlockID.WOODEN_STEP,
			"Wood step",
			"woodenslab",
			"woodslab",
			"woodstep",
			"woodhalfstep" ),
	COCOA_PLANT(
			BlockID.COCOA_PLANT,
			"Cocoa plant",
			"cocoplant",
			"cocoaplant" ),
	SANDSTONE_STAIRS(
			BlockID.SANDSTONE_STAIRS,
			"Sandstone stairs",
			"sandstairs",
			"sandstonestairs" ),
	EMERALD_ORE(
			BlockID.EMERALD_ORE,
			"Emerald ore",
			"emeraldore" ),
	ENDER_CHEST(
			BlockID.ENDER_CHEST,
			"Ender chest",
			"enderchest" ),
	TRIPWIRE_HOOK(
			BlockID.TRIPWIRE_HOOK,
			"Tripwire hook",
			"tripwirehook" ),
	TRIPWIRE( BlockID.TRIPWIRE, "Tripwire", "tripwire" ),
	EMERALD_BLOCK(
			BlockID.EMERALD_BLOCK,
			"Emerald block",
			"emeraldblock" ),
	SPRUCE_WOOD_STAIRS(
			BlockID.SPRUCE_WOOD_STAIRS,
			"Spruce wood stairs",
			"sprucestairs",
			"sprucewoodstairs" ),
	BIRCH_WOOD_STAIRS(
			BlockID.BIRCH_WOOD_STAIRS,
			"Birch wood stairs",
			"birchstairs",
			"birchwoodstairs" ),
	JUNGLE_WOOD_STAIRS(
			BlockID.JUNGLE_WOOD_STAIRS,
			"Jungle wood stairs",
			"junglestairs",
			"junglewoodstairs" ),
	
	// Note: These are just here because =D
	CAKE_BLOCK( BlockID.CAKE_BLOCK, "Cake", "cake" ),
	REED( BlockID.REED, "Reed", "reed" );
	
	/*========================================================================================================*/
	
	public static CreatorBlock get( int id ) {
		return ids.get( id );
	}
	
	public static CreatorBlock get( String name ) {
		return lookup.get( name );
	}
	
	private static final Map< Integer, CreatorBlock >	ids		= new HashMap< Integer, CreatorBlock >( );
	private static final Map< String, CreatorBlock >	lookup	= new HashMap< String, CreatorBlock >( );
	
	static {
		for ( CreatorBlock block : EnumSet.allOf( CreatorBlock.class ) ) {
			ids.put( block.id, block );
			for ( String key : block.lookupKeys ) {
				lookup.put( key, block );
			}
		}
	}
	
	/*========================================================================================================*/
	
	CreatorBlock( int id, String name, String lookupKey ) {
		this.id = id;
		this.name = name;
		this.lookupKeys = new String[] { lookupKey };
	}
	
	CreatorBlock( int id, String name, String... lookupKeys ) {
		this.id = id;
		this.name = name;
		this.lookupKeys = lookupKeys;
	}
	
	/*========================================================================================================*/
	
	public int getID( ) {
		return this.id;
	}
	
	public String getName( ) {
		return this.name;
	}
	
	private final int		id;
	private final String	name;
	private final String[]	lookupKeys;
	
	/*========================================================================================================*/
	
	private DataHolder		dataValues		= null;
	
	static final DataHolder	woodData		= new DataHolder( 0, "Oak" ).add( 1, "Pine" ).add( 1, "Spruce" ).add( 2, "Birch" ).add( 3, "Jungle" ).prefixName( );
	static final DataHolder	slabData		= new DataHolder( 0, "Stone" ).add( 1, "Sandstone" ).add( 2, "Wooden" ).add( 2, "Wood" ).add( 3, "Cobblestone" ).add( 3, "Cobble" ).add( 4, "Brick" ).add( 4, "StoneBrick" ).prefixName( );
	static final DataHolder	sandstoneData	= new DataHolder( 0, "Normal" ).add( 1, "Chiseled" ).add( 2, "Smooth" ).prefixName( );
	static final DataHolder	stoneData		= new DataHolder( 0, "Normal" ).add( 1, "Mossy" ).add( 2, "Cracked" ).add( 3, "Chiseled" ).prefixName( );
	static final DataHolder	woolData		= new DataHolder( 0, "White" ).add( 1, "Orange" ).add( 2, "Magenta" ).add( 3, "LightBlue" ).add( 4, "Yellow" ).add( 5, "Lime" ).add( 6, "Pink" ).add( 7, "Gray" ).add( 8, "LightGray" ).add( 9, "Cyan" ).add( 10, "Purple" ).add( 11, "Blue" ).add( 12, "Brown" ).add( 13, "Green" ).add( 14, "Red" ).add( 15, "Black" ).prefixName( );
	static final DataHolder	coalData		= new DataHolder( 0, "Normal" ).add( 1, "Charcoal" ).dataName( );
	static final DataHolder	dyeData			= new DataHolder( 0, "Ink Sac", "black", "blackdye" ).add( 1, "Rose Red", "red", "reddye" ).add( 2, "Cactus Green", "green", "greendye" ).add( 3, "Cocoa Beans", "brown", "browndye" ).add( 4, "Lapis Lazuli", "blue", "bluedye" ).add( 5, "Purple Dye", "purple", "purpledye" ).add( 6, "Cyan Dye", "cyan", "cyandye" ).add( 7, "Light Gray Dye", "lightgray", "lightgraydye" ).add( 8, "Light Dye", "gray", "graydye" ).add( 9, "Pink Dye", "pink", "pinkdye" ).add( 10, "Lime Dye", "lime", "limedye" ).add( 11, "Dandelion Yellow", "yellow", "yellowdye" ).add( 12, "Light Blue Dye", "lightblue", "lightbluedye" ).add( 13, "Magenta Dye", "magenta", "magentadye" ).add( 14, "Orange Dye", "orange", "orangedye" ).add( 15, "Bone Meal", "bonemeal", "white", "whitedye" ).dataName( );
	static final DataHolder	mobData			= new DataHolder( EntityType.CREEPER.getTypeId( ), "Creeper" ).add( EntityType.SKELETON.getTypeId( ), "Skeleton" ).add( EntityType.SPIDER.getTypeId( ), "Spider" ).add( EntityType.ZOMBIE.getTypeId( ), "Zombie" ).add( EntityType.SLIME.getTypeId( ), "Slime" ).add( EntityType.GHAST.getTypeId( ), "Ghast" ).add( EntityType.PIG_ZOMBIE.getTypeId( ), "Zombie Pigman", "pigman" ).add( EntityType.ENDERMAN.getTypeId( ), "Endman" ).add( EntityType.CAVE_SPIDER.getTypeId( ), "Cave Spider", "cavespider" ).add( EntityType.SILVERFISH.getTypeId( ), "Silverfish" ).add( EntityType.BLAZE.getTypeId( ), "Blaze" ).add( EntityType.MAGMA_CUBE.getTypeId( ), "Magma Cube", "magamacube" ).add( EntityType.GHAST.getTypeId( ), "Giant" ).add( EntityType.ENDER_DRAGON.getTypeId( ), "Ender Dragon", "enderdragon", "dragon" ).add( EntityType.PIG.getTypeId( ), "Pig" ).add( EntityType.SHEEP.getTypeId( ), "Sheep" ).add( EntityType.COW.getTypeId( ), "Cow" ).add( EntityType.CHICKEN.getTypeId( ), "Chicken" ).add( EntityType.SQUID.getTypeId( ), "Squid" ).add( EntityType.WOLF.getTypeId( ), "Wolf" ).add( EntityType.MUSHROOM_COW.getTypeId( ), "Mooshroom" ).add( EntityType.OCELOT.getTypeId( ), "Ocelot" ).add( EntityType.SNOWMAN.getTypeId( ), "Snow Golem", "snowgolem", "snowman" ).add( EntityType.IRON_GOLEM.getTypeId( ), "Iron Golem", "irongolem", "golem" ).add( EntityType.VILLAGER.getTypeId( ), "Villager" ).prefixName( );
	static final DataHolder	potionData		= new DataHolder( 16, "Awkward Potion", "awkward" ).add( 32, "Thick Potion", "thick" ).add( 64, "Mundane Potion", "mundane" ).add( 8193, "Regeneration Potion", "regeneration" ).add( 8194, "Swiftness Potion", "swiftness" ).add( 8195, "Fire Resistance Potion", "fireresistance" ).add( 8196, "Poison Potion", "poison" ).add( 8197, "Healing Potion", "healing" ).add( 8200, "Weakness Potion", "weakness" ).add( 8201, "Strength Potion", "strength" ).add( 8202, "Slowness Potion", "slowness" ).add( 8204, "Harming Potion", "harming" ).add( 8225, "Regeneration Potion II", "regeneration2" ).add( 8226, "Swiftness Potion II", "swiftness2" ).add( 8228, "Poison Potion II", "poison2" ).add( 8229, "Healing Potion II", "healing2" ).add( 8233, "Strength Potion II", "strength2" ).add( 8236, "Harming Potion II", "harming2" ).add( 8257, "Regeneration Potion (extended)", "regeneratione" ).add( 8258, "Swiftness Potion (extended)", "swiftnesse" ).add( 8259, "Fire Resistance Potion (extended)", "fireresistancee" ).add( 8260, "Poison Potion (extended)", "poisone" ).add( 8264, "Weakness Potion (extended)", "weaknesse" ).add( 8265, "Strength Potion (extended)", "strengthe" ).add( 8266, "Slowness Potion (extended)", "slownesse" ).add( 16378, "Fire Resistance Splash", "fireresistancesplash" ).add( 16385, "Regeneration Splash", "regenerationsplash" ).add( 16386, "Swiftness Splash", "swiftnesssplash" ).add( 16388, "Poison Splash", "poisonsplash" ).add( 16389, "Healing Splash", "healingsplash" ).add( 16392, "Weakness Splash", "weaknesssplash" ).add( 16393, "Strength Splash", "strengthsplash" ).add( 16394, "Slowness Splash", "slownesssplash" ).add( 16396, "Harming Splash", "harmingsplash" ).add( 16418, "Swiftness Splash II", "swiftnesssplash2" ).add( 16420, "Poison Splash II", "poisonsplash2" ).add( 16421, "Healing Splash II", "healingsplash2" ).add( 16425, "Strength Splash II", "strengthsplash2" ).add( 16428, "Harming Splash II", "harmingsplash2" ).add( 16471, "Regeneration Splash II", "regenerationsplash2" ).add( 16449, "Regeneration Splash", "regenerationsplashe" ).add( 16450, "Swiftness Splash", "swiftnesssplashe" ).add( 16451, "Fire Resistance Splash", "fireresistancesplashe" ).add( 16452, "Poison Splash", "poisonsplashe" ).add( 16456, "Weakness Splash", "weaknesssplashe" ).add( 16457, "Strength Splash", "strengthsplashe" ).add( 16458, "Slowness Splash", "slownesssplashe" ).dataName( );
	
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
	
	public int getDataValue( String name ) {
		if ( this.dataValues == null ) return -1;
		return this.dataValues.get( name );
	}
	
	public boolean validDataValue( int value ) {
		if ( this.dataValues == null ) return false;
		return this.dataValues.valid( value );
	}
	
	public String nameDataValue( int value ) {
		if ( this.dataValues == null ) return null;
		return this.dataValues.name( value );
	}
	
	public DataHolder dataValues() {
		return dataValues;
	}
	
	/*========================================================================================================*/
	
	public String niceName( int damage ) {
		if ( this.dataValues == null ) return this.name;
		return this.dataValues.niceName( damage, this.name );
	}
	
	/*========================================================================================================*/
	
	private boolean	shouldPlaceLast	= false;
	
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
	
	public boolean shouldPlaceLast( ) {
		return this.shouldPlaceLast;
	}
	
	/*========================================================================================================*/
	
	private boolean	shouldPlaceFinal	= false;
	
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
	
	public boolean shouldPlaceFinal( ) {
		return this.shouldPlaceFinal;
	}
	
	/*========================================================================================================*/
	
	private boolean	canPassThrough	= false;
	
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
	
	public boolean canPassThrough( ) {
		return this.canPassThrough;
	}
	
	/*========================================================================================================*/
	
	private boolean	isContainerBlock	= false;
	
	static {
		DISPENSER.isContainerBlock = true;
		FURNACE.isContainerBlock = true;
		BURNING_FURNACE.isContainerBlock = true;
		CHEST.isContainerBlock = true;
		BREWING_STAND.isContainerBlock = true;
	}
	
	public boolean isContainerCreatorBlock( ) {
		return this.isContainerBlock;
	}
	
	/*========================================================================================================*/
	
	private boolean	isRedstoneBlock	= false;
	
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
	
	public boolean isRedstoneCreatorBlock( ) {
		return this.isRedstoneBlock;
	}
	
	/*========================================================================================================*/
	
	private boolean	canTransferRedstone	= false;
	
	static {
		REDSTONE_TORCH_OFF.canTransferRedstone = true;
		REDSTONE_TORCH_ON.canTransferRedstone = true;
		REDSTONE_WIRE.canTransferRedstone = true;
		REDSTONE_REPEATER_OFF.canTransferRedstone = true;
		REDSTONE_REPEATER_ON.canTransferRedstone = true;
	}
	
	public boolean canTransferRedstone( ) {
		return this.canTransferRedstone;
	}
	
	/*========================================================================================================*/
	
	private boolean	isRedstoneSource	= false;
	
	static {
		DETECTOR_RAIL.isRedstoneSource = true;
		REDSTONE_TORCH_OFF.isRedstoneSource = true;
		REDSTONE_TORCH_ON.isRedstoneSource = true;
		LEVER.isRedstoneSource = true;
		STONE_PRESSURE_PLATE.isRedstoneSource = true;
		WOODEN_PRESSURE_PLATE.isRedstoneSource = true;
		STONE_BUTTON.isRedstoneSource = true;
		TRIPWIRE_HOOK.isRedstoneSource = true;
	}
	
	public boolean isRedstoneSource( ) {
		return this.isRedstoneSource;
	}
	
	/*========================================================================================================*/
	
	private boolean	isRailBlock	= false;
	
	static {
		POWERED_RAIL.isRailBlock = true;
		DETECTOR_RAIL.isRailBlock = true;
		MINECART_TRACKS.isRailBlock = true;
	}
	
	public boolean isRailCreatorBlock( ) {
		return this.isRailBlock;
	}
	
	/*========================================================================================================*/
	
	private boolean	isNaturalTerrainBlock	= false;
	
	static {
		STONE.isNaturalTerrainBlock = true;
		GRASS.isNaturalTerrainBlock = true;
		DIRT.isNaturalTerrainBlock = true;
		BEDROCK.isNaturalTerrainBlock = true;
		SAND.isNaturalTerrainBlock = true;
		GRAVEL.isNaturalTerrainBlock = true;
		CLAY.isNaturalTerrainBlock = true;
		MYCELIUM.isNaturalTerrainBlock = true;
		
		// hell
		NETHERRACK.isNaturalTerrainBlock = true;
		SOUL_SAND.isNaturalTerrainBlock = true;
		GLOWSTONE.isNaturalTerrainBlock = true;
		
		// ores
		COAL_ORE.isNaturalTerrainBlock = true;
		IRON_ORE.isNaturalTerrainBlock = true;
		GOLD_ORE.isNaturalTerrainBlock = true;
		LAPIS_LAZULI_ORE.isNaturalTerrainBlock = true;
		DIAMOND_ORE.isNaturalTerrainBlock = true;
		REDSTONE_ORE.isNaturalTerrainBlock = true;
		GLOWING_REDSTONE_ORE.isNaturalTerrainBlock = true;
		EMERALD_ORE.isNaturalTerrainBlock = true;
	}
	
	public boolean isNaturalTerrainCreatorBlock( ) {
		return this.isNaturalTerrainBlock;
	}
	
	/*========================================================================================================*/
	
	private boolean	emitsLight	= false;
	
	static {
		LAVA.emitsLight = true;
		STATIONARY_LAVA.emitsLight = true;
		BROWN_MUSHROOM.emitsLight = true;
		RED_MUSHROOM.emitsLight = true;
		TORCH.emitsLight = true;
		FIRE.emitsLight = true;
		BURNING_FURNACE.emitsLight = true;
		GLOWING_REDSTONE_ORE.emitsLight = true;
		REDSTONE_TORCH_ON.emitsLight = true;
		GLOWSTONE.emitsLight = true;
		PORTAL.emitsLight = true;
		JACK_O_LANTERN.emitsLight = true;
		REDSTONE_REPEATER_ON.emitsLight = true;
		LOCKED_CHEST.emitsLight = true;
		BROWN_MUSHROOM_CAP.emitsLight = true;
		RED_MUSHROOM_CAP.emitsLight = true;
		END_PORTAL.emitsLight = true;
		REDSTONE_LAMP_ON.emitsLight = true;
		ENDER_CHEST.emitsLight = true;
	}
	
	public boolean emitsLight( ) {
		return this.emitsLight;
	}
	
	/*========================================================================================================*/
	
	private boolean	isTranslucent	= false;
	
	static {
		AIR.isTranslucent = true;
		SAPLING.isTranslucent = true;
		WATER.isTranslucent = true;
		STATIONARY_WATER.isTranslucent = true;
		LEAVES.isTranslucent = true;
		GLASS.isTranslucent = true;
		BED.isTranslucent = true;
		POWERED_RAIL.isTranslucent = true;
		DETECTOR_RAIL.isTranslucent = true;
		WEB.isTranslucent = true;
		LONG_GRASS.isTranslucent = true;
		DEAD_BUSH.isTranslucent = true;
		PISTON_EXTENSION.isTranslucent = true;
		YELLOW_FLOWER.isTranslucent = true;
		RED_FLOWER.isTranslucent = true;
		BROWN_MUSHROOM.isTranslucent = true;
		RED_MUSHROOM.isTranslucent = true;
		TORCH.isTranslucent = true;
		FIRE.isTranslucent = true;
		MOB_SPAWNER.isTranslucent = true;
		WOODEN_STAIRS.isTranslucent = true;
		CHEST.isTranslucent = true;
		REDSTONE_WIRE.isTranslucent = true;
		CROPS.isTranslucent = true;
		SIGN_POST.isTranslucent = true;
		WOODEN_DOOR.isTranslucent = true;
		LADDER.isTranslucent = true;
		MINECART_TRACKS.isTranslucent = true;
		COBBLESTONE_STAIRS.isTranslucent = true;
		WALL_SIGN.isTranslucent = true;
		LEVER.isTranslucent = true;
		STONE_PRESSURE_PLATE.isTranslucent = true;
		IRON_DOOR.isTranslucent = true;
		WOODEN_PRESSURE_PLATE.isTranslucent = true;
		REDSTONE_TORCH_OFF.isTranslucent = true;
		REDSTONE_TORCH_ON.isTranslucent = true;
		STONE_BUTTON.isTranslucent = true;
		SNOW.isTranslucent = true;
		ICE.isTranslucent = true;
		CACTUS.isTranslucent = true;
		REED.isTranslucent = true;
		FENCE.isTranslucent = true;
		PORTAL.isTranslucent = true;
		CAKE_BLOCK.isTranslucent = true;
		REDSTONE_REPEATER_OFF.isTranslucent = true;
		REDSTONE_REPEATER_ON.isTranslucent = true;
		TRAP_DOOR.isTranslucent = true;
		IRON_BARS.isTranslucent = true;
		GLASS_PANE.isTranslucent = true;
		PUMPKIN_STEM.isTranslucent = true;
		MELON_STEM.isTranslucent = true;
		VINE.isTranslucent = true;
		FENCE_GATE.isTranslucent = true;
		BRICK_STAIRS.isTranslucent = true;
		STONE_BRICK_STAIRS.isTranslucent = true;
		LILY_PAD.isTranslucent = true;
		NETHER_BRICK_FENCE.isTranslucent = true;
		NETHER_BRICK_STAIRS.isTranslucent = true;
		NETHER_WART.isTranslucent = true;
		ENCHANTMENT_TABLE.isTranslucent = true;
		BREWING_STAND.isTranslucent = true;
		CAULDRON.isTranslucent = true;
		WOODEN_STEP.isTranslucent = true;
		COCOA_PLANT.isTranslucent = true;
		SANDSTONE_STAIRS.isTranslucent = true;
		ENDER_CHEST.isTranslucent = true;
		TRIPWIRE_HOOK.isTranslucent = true;
		TRIPWIRE.isTranslucent = true;
		SPRUCE_WOOD_STAIRS.isTranslucent = true;
		BIRCH_WOOD_STAIRS.isTranslucent = true;
		JUNGLE_WOOD_STAIRS.isTranslucent = true;
	}
	
	public boolean isTranslucent( ) {
		return this.isTranslucent;
	}
	
	/*========================================================================================================*/
	
	private CreatorItemStack	dropStack;
	private boolean		noDrop	= false;
	
	static {
		STONE.dropStack = new CreatorItemStack( BlockID.COBBLESTONE );
		GRASS.dropStack = new CreatorItemStack( BlockID.DIRT );
		COAL_ORE.dropStack = new CreatorItemStack( ItemID.COAL );
		WEB.dropStack = new CreatorItemStack( ItemID.STRING );
		REDSTONE_WIRE.dropStack = new CreatorItemStack( ItemID.REDSTONE_DUST );
		DIAMOND_ORE.dropStack = new CreatorItemStack( ItemID.DIAMOND );
		CROPS.dropStack = new CreatorItemStack( ItemID.SEEDS );
		SOIL.dropStack = new CreatorItemStack( BlockID.DIRT );
		BURNING_FURNACE.dropStack = new CreatorItemStack( BlockID.FURNACE );
		SIGN_POST.dropStack = new CreatorItemStack( ItemID.SIGN );
		WALL_SIGN.dropStack = new CreatorItemStack( ItemID.SIGN );
		GLOWING_REDSTONE_ORE.dropStack = new CreatorItemStack( ItemID.REDSTONE_DUST, (byte) 0, 4 );
		REDSTONE_ORE.dropStack = new CreatorItemStack( ItemID.REDSTONE_DUST, (byte) 0, 4 );
		REDSTONE_TORCH_OFF.dropStack = new CreatorItemStack( BlockID.REDSTONE_TORCH_ON );
		REED.dropStack = new CreatorItemStack( ItemID.SUGAR_CANE_ITEM );
		CAKE.dropStack = new CreatorItemStack( ItemID.CAKE_ITEM );
		REDSTONE_REPEATER_OFF.dropStack = new CreatorItemStack( ItemID.REDSTONE_REPEATER );
		REDSTONE_REPEATER_ON.dropStack = new CreatorItemStack( ItemID.REDSTONE_REPEATER );
		PUMPKIN_STEM.dropStack = new CreatorItemStack( ItemID.PUMPKIN_SEEDS );
		MELON_STEM.dropStack = new CreatorItemStack( ItemID.MELON_SEEDS );
		MYCELIUM.dropStack = new CreatorItemStack( BlockID.DIRT );
		NETHER_WART.dropStack = new CreatorItemStack( ItemID.NETHER_WART_SEED );
		BREWING_STAND.dropStack = new CreatorItemStack( ItemID.BREWING_STAND );
		CAULDRON.dropStack = new CreatorItemStack( ItemID.CAULDRON );
		REDSTONE_LAMP_ON.dropStack = new CreatorItemStack( BlockID.REDSTONE_LAMP_OFF );
		COCOA_PLANT.dropStack = new CreatorItemStack( ItemID.INK_SACK, (byte) 3, 3 );
		EMERALD_ORE.dropStack = new CreatorItemStack( ItemID.EMERALD );
		TRIPWIRE.dropStack = new CreatorItemStack( ItemID.STRING );
		LAPIS_LAZULI_ORE.dropStack = new CreatorItemStack( ItemID.INK_SACK, (byte) 4, 4 );
		BED.dropStack = new CreatorItemStack( ItemID.BED_ITEM );
		LONG_GRASS.dropStack = new CreatorItemStack( ItemID.SEEDS );
		DOUBLE_STEP.dropStack = new CreatorItemStack( BlockID.STEP, (byte) 0, 2 );
		
		AIR.noDrop = true;
		BEDROCK.noDrop = true;
		SILVERFISH_BLOCK.noDrop = true;
		VINE.noDrop = true;
		END_PORTAL.noDrop = true;
		END_PORTAL_FRAME.noDrop = true;
	}
	
	public CreatorItemStack getDropedItems( int data ) {
		if ( noDrop ) return null;
		
		if ( dropStack != null ) {
			CreatorItemStack stack = dropStack.clone( );
			
			if ( data != 0 && stack.getDataByte( ) == 0 ) stack.setData( (byte) data );
			
			return stack;
		}
		
		return new CreatorItemStack( id, (byte) data, 1 );
	}
	
	public boolean hasNoDrop() {
		return noDrop;
	}
}
