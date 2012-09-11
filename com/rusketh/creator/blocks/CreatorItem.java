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

public enum CreatorItem {
	// Blocks
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
	CAULDRON( BlockID.CAULDRON, "Cauldron" ),
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
	
	// Items
	IRON_SHOVEL(
			ItemID.IRON_SHOVEL,
			"Iron shovel",
			"ironshovel" ),
	IRON_PICK(
			ItemID.IRON_PICK,
			"Iron pick",
			"ironpick",
			"ironpickaxe" ),
	IRON_AXE( ItemID.IRON_AXE, "Iron axe", "ironaxe" ),
	FLINT_AND_TINDER(
			ItemID.FLINT_AND_TINDER,
			"Flint and tinder",
			"flintandtinder",
			"lighter",
			"flintandsteel",
			"flintsteel",
			"flintandiron",
			"flintnsteel",
			"flintniron",
			"flintntinder" ),
	RED_APPLE(
			ItemID.RED_APPLE,
			"Red apple",
			"redapple",
			"apple" ),
	BOW( ItemID.BOW, "Bow", "bow" ),
	ARROW( ItemID.ARROW, "Arrow", "arrow" ),
	COAL( ItemID.COAL, "Coal", "coal" ),
	DIAMOND( ItemID.DIAMOND, "Diamond", "diamond" ),
	IRON_BAR(
			ItemID.IRON_BAR,
			"Iron bar",
			"ironbar",
			"iron" ),
	GOLD_BAR(
			ItemID.GOLD_BAR,
			"Gold bar",
			"goldbar",
			"gold" ),
	IRON_SWORD(
			ItemID.IRON_SWORD,
			"Iron sword",
			"ironsword" ),
	WOOD_SWORD(
			ItemID.WOOD_SWORD,
			"Wooden sword",
			"woodsword" ),
	WOOD_SHOVEL(
			ItemID.WOOD_SHOVEL,
			"Wooden shovel",
			"woodshovel" ),
	WOOD_PICKAXE(
			ItemID.WOOD_PICKAXE,
			"Wooden pickaxe",
			"woodpick",
			"woodpickaxe" ),
	WOOD_AXE( ItemID.WOOD_AXE, "Wooden axe", "woodaxe" ),
	STONE_SWORD(
			ItemID.STONE_SWORD,
			"Stone sword",
			"stonesword" ),
	STONE_SHOVEL(
			ItemID.STONE_SHOVEL,
			"Stone shovel",
			"stoneshovel" ),
	STONE_PICKAXE(
			ItemID.STONE_PICKAXE,
			"Stone pickaxe",
			"stonepick",
			"stonepickaxe" ),
	STONE_AXE(
			ItemID.STONE_AXE,
			"Stone pickaxe",
			"stoneaxe" ),
	DIAMOND_SWORD(
			ItemID.DIAMOND_SWORD,
			"Diamond sword",
			"diamondsword" ),
	DIAMOND_SHOVEL(
			ItemID.DIAMOND_SHOVEL,
			"Diamond shovel",
			"diamondshovel" ),
	DIAMOND_PICKAXE(
			ItemID.DIAMOND_PICKAXE,
			"Diamond pickaxe",
			"diamondpick",
			"diamondpickaxe" ),
	DIAMOND_AXE(
			ItemID.DIAMOND_AXE,
			"Diamond axe",
			"diamondaxe" ),
	STICK( ItemID.STICK, "Stick", "stick" ),
	BOWL( ItemID.BOWL, "Bowl", "bowl" ),
	MUSHROOM_SOUP(
			ItemID.MUSHROOM_SOUP,
			"Mushroom soup",
			"mushroomsoup",
			"soup",
			"brbsoup" ),
	GOLD_SWORD(
			ItemID.GOLD_SWORD,
			"Golden sword",
			"goldsword" ),
	GOLD_SHOVEL(
			ItemID.GOLD_SHOVEL,
			"Golden shovel",
			"goldshovel" ),
	GOLD_PICKAXE(
			ItemID.GOLD_PICKAXE,
			"Golden pickaxe",
			"goldpick",
			"goldpickaxe" ),
	GOLD_AXE( ItemID.GOLD_AXE, "Golden axe", "goldaxe" ),
	STRING( ItemID.STRING, "String", "string" ),
	FEATHER( ItemID.FEATHER, "Feather", "feather" ),
	SULPHUR(
			ItemID.SULPHUR,
			"Sulphur",
			"sulphur",
			"sulfur",
			"gunpowder" ),
	WOOD_HOE( ItemID.WOOD_HOE, "Wooden hoe", "woodhoe" ),
	STONE_HOE( ItemID.STONE_HOE, "Stone hoe", "stonehoe" ),
	IRON_HOE( ItemID.IRON_HOE, "Iron hoe", "ironhoe" ),
	DIAMOND_HOE(
			ItemID.DIAMOND_HOE,
			"Diamond hoe",
			"diamondhoe" ),
	GOLD_HOE( ItemID.GOLD_HOE, "Golden hoe", "goldhoe" ),
	SEEDS( ItemID.SEEDS, "Seeds", "seeds", "seed" ),
	WHEAT( ItemID.WHEAT, "Wheat", "wheat" ),
	BREAD( ItemID.BREAD, "Bread", "bread" ),
	LEATHER_HELMET(
			ItemID.LEATHER_HELMET,
			"Leather helmet",
			"leatherhelmet",
			"leatherhat" ),
	LEATHER_CHEST(
			ItemID.LEATHER_CHEST,
			"Leather chestplate",
			"leatherchest",
			"leatherchestplate",
			"leathervest",
			"leatherbreastplate",
			"leatherplate",
			"leathercplate",
			"leatherbody" ),
	LEATHER_PANTS(
			ItemID.LEATHER_PANTS,
			"Leather pants",
			"leatherpants",
			"leathergreaves",
			"leatherlegs",
			"leatherleggings",
			"leatherstockings",
			"leatherbreeches" ),
	LEATHER_BOOTS(
			ItemID.LEATHER_BOOTS,
			"Leather boots",
			"leatherboots",
			"leathershoes",
			"leatherfoot",
			"leatherfeet" ),
	CHAINMAIL_HELMET(
			ItemID.CHAINMAIL_HELMET,
			"Chainmail helmet",
			"chainmailhelmet",
			"chainmailhat" ),
	CHAINMAIL_CHEST(
			ItemID.CHAINMAIL_CHEST,
			"Chainmail chestplate",
			"chainmailchest",
			"chainmailchestplate",
			"chainmailvest",
			"chainmailbreastplate",
			"chainmailplate",
			"chainmailcplate",
			"chainmailbody" ),
	CHAINMAIL_PANTS(
			ItemID.CHAINMAIL_PANTS,
			"Chainmail pants",
			"chainmailpants",
			"chainmailgreaves",
			"chainmaillegs",
			"chainmailleggings",
			"chainmailstockings",
			"chainmailbreeches" ),
	CHAINMAIL_BOOTS(
			ItemID.CHAINMAIL_BOOTS,
			"Chainmail boots",
			"chainmailboots",
			"chainmailshoes",
			"chainmailfoot",
			"chainmailfeet" ),
	IRON_HELMET(
			ItemID.IRON_HELMET,
			"Iron helmet",
			"ironhelmet",
			"ironhat" ),
	IRON_CHEST(
			ItemID.IRON_CHEST,
			"Iron chestplate",
			"ironchest",
			"ironchestplate",
			"ironvest",
			"ironbreastplate",
			"ironplate",
			"ironcplate",
			"ironbody" ),
	IRON_PANTS(
			ItemID.IRON_PANTS,
			"Iron pants",
			"ironpants",
			"irongreaves",
			"ironlegs",
			"ironleggings",
			"ironstockings",
			"ironbreeches" ),
	IRON_BOOTS(
			ItemID.IRON_BOOTS,
			"Iron boots",
			"ironboots",
			"ironshoes",
			"ironfoot",
			"ironfeet" ),
	DIAMOND_HELMET(
			ItemID.DIAMOND_HELMET,
			"Diamond helmet",
			"diamondhelmet",
			"diamondhat" ),
	DIAMOND_CHEST(
			ItemID.DIAMOND_CHEST,
			"Diamond chestplate",
			"diamondchest",
			"diamondchestplate",
			"diamondvest",
			"diamondbreastplate",
			"diamondplate",
			"diamondcplate",
			"diamondbody" ),
	DIAMOND_PANTS(
			ItemID.DIAMOND_PANTS,
			"Diamond pants",
			"diamondpants",
			"diamondgreaves",
			"diamondlegs",
			"diamondleggings",
			"diamondstockings",
			"diamondbreeches" ),
	DIAMOND_BOOTS(
			ItemID.DIAMOND_BOOTS,
			"Diamond boots",
			"diamondboots",
			"diamondshoes",
			"diamondfoot",
			"diamondfeet" ),
	GOLD_HELMET(
			ItemID.GOLD_HELMET,
			"Gold helmet",
			"goldhelmet",
			"goldhat" ),
	GOLD_CHEST(
			ItemID.GOLD_CHEST,
			"Gold chestplate",
			"goldchest",
			"goldchestplate",
			"goldvest",
			"goldbreastplate",
			"goldplate",
			"goldcplate",
			"goldbody" ),
	GOLD_PANTS(
			ItemID.GOLD_PANTS,
			"Gold pants",
			"goldpants",
			"goldgreaves",
			"goldlegs",
			"goldleggings",
			"goldstockings",
			"goldbreeches" ),
	GOLD_BOOTS(
			ItemID.GOLD_BOOTS,
			"Gold boots",
			"goldboots",
			"goldshoes",
			"goldfoot",
			"goldfeet" ),
	FLINT( ItemID.FLINT, "Flint", "flint" ),
	RAW_PORKCHOP(
			ItemID.RAW_PORKCHOP,
			"Raw porkchop",
			"rawpork",
			"rawporkchop",
			"rawbacon",
			"baconstrips",
			"rawmeat" ),
	COOKED_PORKCHOP(
			ItemID.COOKED_PORKCHOP,
			"Cooked porkchop",
			"pork",
			"cookedpork",
			"cookedporkchop",
			"cookedbacon",
			"bacon",
			"meat" ),
	PAINTING( ItemID.PAINTING, "Painting", "painting" ),
	GOLD_APPLE(
			ItemID.GOLD_APPLE,
			"Golden apple",
			"goldapple",
			"goldenapple" ),
	SIGN( ItemID.SIGN, "Wooden sign", "sign" ),
	WOODEN_DOOR_ITEM(
			ItemID.WOODEN_DOOR_ITEM,
			"Wooden door",
			"wooddoor",
			"door" ),
	BUCKET( ItemID.BUCKET, "Bucket", "bucket", "bukkit" ),
	WATER_BUCKET(
			ItemID.WATER_BUCKET,
			"Water bucket",
			"waterbucket",
			"waterbukkit" ),
	LAVA_BUCKET(
			ItemID.LAVA_BUCKET,
			"Lava bucket",
			"lavabucket",
			"lavabukkit" ),
	MINECART(
			ItemID.MINECART,
			"Minecart",
			"minecart",
			"cart" ),
	SADDLE( ItemID.SADDLE, "Saddle", "saddle" ),
	IRON_DOOR_ITEM(
			ItemID.IRON_DOOR_ITEM,
			"Iron door",
			"irondoor" ),
	REDSTONE_DUST(
			ItemID.REDSTONE_DUST,
			"Redstone dust",
			"redstonedust",
			"reddust",
			"redstone",
			"dust",
			"wire" ),
	SNOWBALL( ItemID.SNOWBALL, "Snowball", "snowball" ),
	WOOD_BOAT(
			ItemID.WOOD_BOAT,
			"Wooden boat",
			"woodboat",
			"woodenboat",
			"boat" ),
	LEATHER(
			ItemID.LEATHER,
			"Leather",
			"leather",
			"cowhide" ),
	MILK_BUCKET(
			ItemID.MILK_BUCKET,
			"Milk bucket",
			"milkbucket",
			"milk",
			"milkbukkit" ),
	BRICK_BAR( ItemID.BRICK_BAR, "Brick", "brickbar" ),
	CLAY_BALL( ItemID.CLAY_BALL, "Clay", "clay" ),
	SUGAR_CANE_ITEM(
			ItemID.SUGAR_CANE_ITEM,
			"Sugar cane",
			"sugarcane",
			"reed",
			"reeds" ),
	PAPER( ItemID.PAPER, "Paper", "paper" ),
	BOOK( ItemID.BOOK, "Book", "book" ),
	SLIME_BALL(
			ItemID.SLIME_BALL,
			"Slime ball",
			"slimeball",
			"slime" ),
	STORAGE_MINECART(
			ItemID.STORAGE_MINECART,
			"Storage minecart",
			"storageminecart",
			"storagecart" ),
	POWERED_MINECART(
			ItemID.POWERED_MINECART,
			"Powered minecart",
			"poweredminecart",
			"poweredcart" ),
	EGG( ItemID.EGG, "Egg", "egg" ),
	COMPASS( ItemID.COMPASS, "Compass", "compass" ),
	FISHING_ROD(
			ItemID.FISHING_ROD,
			"Fishing rod",
			"fishingrod",
			"fishingpole" ),
	WATCH( ItemID.WATCH, "Watch", "watch", "clock", "timer" ),
	LIGHTSTONE_DUST(
			ItemID.LIGHTSTONE_DUST,
			"Glowstone dust",
			"lightstonedust",
			"glowstonedone",
			"brightstonedust",
			"brittlegolddust",
			"brimstonedust" ),
	RAW_FISH(
			ItemID.RAW_FISH,
			"Raw fish",
			"rawfish",
			"fish" ),
	COOKED_FISH(
			ItemID.COOKED_FISH,
			"Cooked fish",
			"cookedfish" ),
	INK_SACK(
			ItemID.INK_SACK,
			"Ink sac",
			"inksac",
			"ink",
			"dye",
			"inksack" ),
	BONE( ItemID.BONE, "Bone", "bone" ),
	SUGAR( ItemID.SUGAR, "Sugar", "sugar" ),
	CAKE_ITEM( ItemID.CAKE_ITEM, "Cake", "cake" ),
	BED_ITEM( ItemID.BED_ITEM, "Bed", "bed" ),
	REDSTONE_REPEATER(
			ItemID.REDSTONE_REPEATER,
			"Redstone repeater",
			"redstonerepeater",
			"diode",
			"delayer",
			"repeater" ),
	COOKIE( ItemID.COOKIE, "Cookie", "cookie" ),
	MAP( ItemID.MAP, "Map", "map" ),
	SHEARS( ItemID.SHEARS, "Shears", "shears", "scissors" ),
	MELON(
			ItemID.MELON,
			"Melon Slice",
			"melon",
			"melonslice" ),
	PUMPKIN_SEEDS(
			ItemID.PUMPKIN_SEEDS,
			"Pumpkin seeds",
			"pumpkinseed",
			"pumpkinseeds" ),
	MELON_SEEDS(
			ItemID.MELON_SEEDS,
			"Melon seeds",
			"melonseed",
			"melonseeds" ),
	RAW_BEEF(
			ItemID.RAW_BEEF,
			"Raw beef",
			"rawbeef",
			"rawcow",
			"beef" ),
	COOKED_BEEF(
			ItemID.COOKED_BEEF,
			"Steak",
			"steak",
			"cookedbeef",
			"cookedcow" ),
	RAW_CHICKEN(
			ItemID.RAW_CHICKEN,
			"Raw chicken",
			"rawchicken" ),
	COOKED_CHICKEN(
			ItemID.COOKED_CHICKEN,
			"Cooked chicken",
			"cookedchicken",
			"chicken",
			"grilledchicken" ),
	ROTTEN_FLESH(
			ItemID.ROTTEN_FLESH,
			"Rotten flesh",
			"rottenflesh",
			"zombiemeat",
			"flesh" ),
	ENDER_PEARL(
			ItemID.ENDER_PEARL,
			"Ender pearl",
			"pearl",
			"enderpearl" ),
	BLAZE_ROD( ItemID.BLAZE_ROD, "Blaze rod", "blazerod" ),
	GHAST_TEAR(
			ItemID.GHAST_TEAR,
			"Ghast tear",
			"ghasttear" ),
	GOLD_NUGGET(
			ItemID.GOLD_NUGGET,
			"Gold nuggest",
			"goldnugget" ),
	NETHER_WART_ITEM(
			ItemID.NETHER_WART_SEED,
			"Nether wart",
			"netherwart",
			"netherwartseed" ),
	POTION( ItemID.POTION, "Potion", "potion" ),
	GLASS_BOTTLE(
			ItemID.GLASS_BOTTLE,
			"Glass bottle",
			"glassbottle" ),
	SPIDER_EYE(
			ItemID.SPIDER_EYE,
			"Spider eye",
			"spidereye" ),
	FERMENTED_SPIDER_EYE(
			ItemID.FERMENTED_SPIDER_EYE,
			"Fermented spider eye",
			"fermentedspidereye",
			"fermentedeye" ),
	BLAZE_POWDER(
			ItemID.BLAZE_POWDER,
			"Blaze powder",
			"blazepowder" ),
	MAGMA_CREAM(
			ItemID.MAGMA_CREAM,
			"Magma cream",
			"magmacream" ),
	BREWING_STAND_ITEM(
			ItemID.BREWING_STAND,
			"Brewing stand",
			"brewingstand" ),
	CAULDRON_ITEM( ItemID.CAULDRON, "Cauldron", "cauldron" ),
	EYE_OF_ENDER(
			ItemID.EYE_OF_ENDER,
			"Eye of Ender",
			"eyeofender",
			"endereye" ),
	GLISTERING_MELON(
			ItemID.GLISTERING_MELON,
			"Glistering Melon",
			"glisteringmelon",
			"goldmelon" ),
	SPAWN_EGG(
			ItemID.SPAWN_EGG,
			"Spawn Egg",
			"spawnegg",
			"spawn",
			"mobspawnegg" ),
	BOTTLE_O_ENCHANTING(
			ItemID.BOTTLE_O_ENCHANTING,
			"Bottle o' Enchanting",
			"expbottle",
			"bottleoenchanting",
			"experiencebottle",
			"exppotion",
			"experiencepotion" ),
	FIRE_CHARGE(
			ItemID.FIRE_CHARGE,
			"Fire Charge",
			"firecharge",
			"firestarter",
			"firerock" ),
	BOOK_AND_QUILL(
			ItemID.BOOK_AND_QUILL,
			"Book and Quill",
			"bookandquill",
			"quill",
			"writingbook" ),
	WRITTEN_BOOK(
			ItemID.WRITTEN_BOOK,
			"Written Book",
			"writtenbook" ),
	EMERALD( ItemID.EMERALD, "Emerald", "emerald" ),
	DISC_13( ItemID.DISC_13, "Music Disc - 13", "disc_13" ),
	DISC_CAT(
			ItemID.DISC_CAT,
			"Music Disc - Cat",
			"disc_cat" ),
	DISC_BLOCKS(
			ItemID.DISC_BLOCKS,
			"Music Disc - blocks",
			"disc_blocks" ),
	DISC_CHIRP(
			ItemID.DISC_CHIRP,
			"Music Disc - chirp",
			"disc_chirp" ),
	DISC_FAR(
			ItemID.DISC_FAR,
			"Music Disc - far",
			"disc_far" ),
	DISC_MALL(
			ItemID.DISC_MALL,
			"Music Disc - mall",
			"disc_mall" ),
	DISC_MELLOHI(
			ItemID.DISC_MELLOHI,
			"Music Disc - mellohi",
			"disc_mellohi" ),
	DISC_STAL(
			ItemID.DISC_STAL,
			"Music Disc - stal",
			"disc_stal" ),
	DISC_STRAD(
			ItemID.DISC_STRAD,
			"Music Disc - strad",
			"disc_strad" ),
	DISC_WARD(
			ItemID.DISC_WARD,
			"Music Disc - ward",
			"disc_ward" ),
	DISC_11( ItemID.DISC_11, "Music Disc - 11", "disc_11" ),
	
	// deprecated
	@Deprecated
	GOLD_RECORD(
			ItemID.GOLD_RECORD,
			"Gold Record",
			"goldrecord",
			"golddisc" ),
	@Deprecated
	GREEN_RECORD(
			ItemID.GREEN_RECORD,
			"Green Record",
			"greenrecord",
			"greenddisc" );
	
	/*========================================================================================================*/
	
	public static CreatorItem get( int id ) {
		return ids.get( id );
	}
	
	public static CreatorItem get( String name ) {
		return lookup.get( name );
	}
	
	private static final Map< Integer, CreatorItem >	ids		= new HashMap< Integer, CreatorItem >( );
	private static final Map< String, CreatorItem >	lookup	= new HashMap< String, CreatorItem >( );
	
	static {
		for ( CreatorItem item : EnumSet.allOf( CreatorItem.class ) ) {
			ids.put( item.id, item );
			
			for ( String key : item.lookupKeys ) {
				lookup.put( key, item );
			}
		}
	}
	
	/*========================================================================================================*/
	
	CreatorItem( int id, String name, String lookupKey ) {
		this.id = id;
		this.name = name;
		this.lookupKeys = new String[] { lookupKey };
	}
	
	CreatorItem( int id, String name, String... lookupKeys ) {
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
	
	private DataHolder		dataValues	= null;
	
	static {
		WOOD.dataValues = CreatorBlock.woodData;
		SAPLING.dataValues = CreatorBlock.woodData;
		LOG.dataValues = CreatorBlock.woodData;
		LEAVES.dataValues = CreatorBlock.woodData;
		SANDSTONE.dataValues = CreatorBlock.sandstoneData;
		CLOTH.dataValues = CreatorBlock.woolData;
		DOUBLE_STEP.dataValues = CreatorBlock.slabData;
		STEP.dataValues = CreatorBlock.slabData;
		WOODEN_STAIRS.dataValues = CreatorBlock.woodData;
		STONE_BRICK.dataValues = CreatorBlock.stoneData;
		DOUBLE_WOODEN_STEP.dataValues = CreatorBlock.woodData;
		WOODEN_STEP.dataValues = CreatorBlock.woodData;
		COAL.dataValues = CreatorBlock.coalData;
		INK_SACK.dataValues = CreatorBlock.dyeData;
		SPAWN_EGG.dataValues = CreatorBlock.mobData;
		POTION.dataValues = CreatorBlock.potionData;
		// GLASS_BOTTLE.dataValues = ; -- ^^
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
	
	/*========================================================================================================*/
	
	public String niceName( int damage ) {
		if ( this.dataValues == null ) return this.name;
		return this.dataValues.niceName( damage, this.name );
	}
	
	/*========================================================================================================*/
	/*========================================================================================================*/
	
	private boolean	shouldNotStack	= false;
	
	static {
		IRON_SHOVEL.shouldNotStack = true;
		IRON_PICK.shouldNotStack = true;
		IRON_AXE.shouldNotStack = true;
		FLINT_AND_TINDER.shouldNotStack = true;
		BOW.shouldNotStack = true;
		IRON_SWORD.shouldNotStack = true;
		WOOD_SWORD.shouldNotStack = true;
		WOOD_SHOVEL.shouldNotStack = true;
		WOOD_PICKAXE.shouldNotStack = true;
		WOOD_AXE.shouldNotStack = true;
		STONE_SWORD.shouldNotStack = true;
		STONE_SHOVEL.shouldNotStack = true;
		STONE_PICKAXE.shouldNotStack = true;
		STONE_AXE.shouldNotStack = true;
		DIAMOND_SWORD.shouldNotStack = true;
		DIAMOND_SHOVEL.shouldNotStack = true;
		DIAMOND_PICKAXE.shouldNotStack = true;
		DIAMOND_AXE.shouldNotStack = true;
		BOWL.shouldNotStack = true;
		GOLD_SWORD.shouldNotStack = true;
		GOLD_SHOVEL.shouldNotStack = true;
		GOLD_PICKAXE.shouldNotStack = true;
		GOLD_AXE.shouldNotStack = true;
		WOOD_HOE.shouldNotStack = true;
		STONE_HOE.shouldNotStack = true;
		IRON_HOE.shouldNotStack = true;
		DIAMOND_HOE.shouldNotStack = true;
		GOLD_HOE.shouldNotStack = true;
		LEATHER_HELMET.shouldNotStack = true;
		LEATHER_CHEST.shouldNotStack = true;
		LEATHER_PANTS.shouldNotStack = true;
		LEATHER_BOOTS.shouldNotStack = true;
		CHAINMAIL_CHEST.shouldNotStack = true;
		CHAINMAIL_HELMET.shouldNotStack = true;
		CHAINMAIL_BOOTS.shouldNotStack = true;
		CHAINMAIL_PANTS.shouldNotStack = true;
		IRON_HELMET.shouldNotStack = true;
		IRON_CHEST.shouldNotStack = true;
		IRON_PANTS.shouldNotStack = true;
		IRON_BOOTS.shouldNotStack = true;
		DIAMOND_HELMET.shouldNotStack = true;
		DIAMOND_PANTS.shouldNotStack = true;
		DIAMOND_CHEST.shouldNotStack = true;
		DIAMOND_BOOTS.shouldNotStack = true;
		GOLD_HELMET.shouldNotStack = true;
		GOLD_CHEST.shouldNotStack = true;
		GOLD_PANTS.shouldNotStack = true;
		GOLD_BOOTS.shouldNotStack = true;
		WOODEN_DOOR_ITEM.shouldNotStack = true;
		WATER_BUCKET.shouldNotStack = true;
		LAVA_BUCKET.shouldNotStack = true;
		MINECART.shouldNotStack = true;
		SADDLE.shouldNotStack = true;
		IRON_DOOR_ITEM.shouldNotStack = true;
		WOOD_BOAT.shouldNotStack = true;
		MILK_BUCKET.shouldNotStack = true;
		STORAGE_MINECART.shouldNotStack = true;
		POWERED_MINECART.shouldNotStack = true;
		WATCH.shouldNotStack = true;
		CAKE_ITEM.shouldNotStack = true;
		BED_ITEM.shouldNotStack = true;
		MAP.shouldNotStack = true;
		SHEARS.shouldNotStack = true;
		DISC_13.shouldNotStack = true;
		DISC_CAT.shouldNotStack = true;
		DISC_BLOCKS.shouldNotStack = true;
		DISC_CHIRP.shouldNotStack = true;
		DISC_FAR.shouldNotStack = true;
		DISC_MALL.shouldNotStack = true;
		DISC_MELLOHI.shouldNotStack = true;
		DISC_STAL.shouldNotStack = true;
		DISC_STRAD.shouldNotStack = true;
		DISC_WARD.shouldNotStack = true;
		DISC_11.shouldNotStack = true;
	}
	
	public boolean shouldNotStack( ) {
		return this.shouldNotStack;
	}
}
