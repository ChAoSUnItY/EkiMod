package chaos.mod.init;

import java.util.ArrayList;
import java.util.List;

import chaos.mod.Main;
import chaos.mod.objects.block.base.BlockBase;
import chaos.mod.objects.block.base.BlockSlabBase;
import chaos.mod.objects.block.base.BlockStairsBase;
import chaos.mod.objects.block.subblocks.BlockEnamelWall;
import chaos.mod.objects.block.subblocks.BlockTessera;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockInit {
	//private static final CreativeTabs STA = Main.eki_station_tab;
	private static final CreativeTabs BLOCK = Main.eki_block_tab;
	//private static final CreativeTabs MISC = Main.eki_misc_tab;
	private static final Material COMMON = Material.rock;
	//INIT SECTION
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final List<Block> META_BLOCKS = new ArrayList<Block>();
	
	public static final List<BlockSlab> FULL_SLAB = new ArrayList<BlockSlab>();
	
	public static final List<BlockSlab> HALF_SLAB = new ArrayList<BlockSlab>();
	//BLOCK SECTION
	//blocks
	public static final Block ARCHITECTURAL_CONCRETE = new BlockBase("architectural_concrete", COMMON, BLOCK);
	
	public static final Block CONCRETE = new BlockBase("concrete", COMMON, BLOCK);
	
	public static final Block NICHOGAKE = new BlockBase("nichogake", COMMON, BLOCK);
	
	public static final Block WASHED_GRANOLITHIC_FINISH = new BlockBase("washed_granolithic_finish", COMMON, BLOCK);
	
	public static final Block ASPHALT = new BlockBase("asphalt", COMMON, BLOCK);
	
	public static final Block ENAMEL_WALL_TOP = new BlockEnamelWall("enamel_wall_top", COMMON, BLOCK);
	
	public static final Block ENAMEL_WALL_BOT = new BlockEnamelWall("enamel_wall_bot", COMMON, BLOCK);
	
	public static final Block ENAMEL_WALL_S = new BlockEnamelWall("enamel_wall_s", COMMON, BLOCK);
	
	public static final Block TESSERA = new BlockTessera("tessera", COMMON, BLOCK);
	//stairs
	public static final Block ARCHITECTURAL_CONCRETE_STAIRS = new BlockStairsBase(ARCHITECTURAL_CONCRETE, BLOCK);
	
	public static final Block CONCRETE_STAIRS = new BlockStairsBase(CONCRETE, BLOCK);
	
	public static final Block NICHOGAKE_STAIRS = new BlockStairsBase(NICHOGAKE, BLOCK);
	
	public static final Block WASHED_GRANOLITHIC_FINISH_STAIRS = new BlockStairsBase(WASHED_GRANOLITHIC_FINISH, BLOCK);
	
	public static final Block ASPHALT_STAIRS = new BlockStairsBase(ASPHALT, BLOCK);
	//slabs
	public static final BlockSlab ARCHITECTURAL_CONCRETE_HALF = new BlockSlabBase(false, ARCHITECTURAL_CONCRETE, COMMON, BLOCK);
	
	public static final BlockSlab ARCHITECTURAL_CONCRETE_FULL = new BlockSlabBase(true, ARCHITECTURAL_CONCRETE, COMMON, BLOCK);
	
	public static final BlockSlab CONCRETE_SLAB_DOUBLE = new BlockSlabBase(false, CONCRETE, COMMON, BLOCK);
	
	public static final BlockSlab CONCRETE_SLAB_HALF = new BlockSlabBase(true, CONCRETE, COMMON, BLOCK);
	
	public static final BlockSlab NICHOGAKE_SLAB_DOUBLE = new BlockSlabBase(false, NICHOGAKE, COMMON, BLOCK);
	
	public static final BlockSlab NICHOGAKE_SLAB_HALF = new BlockSlabBase(true, NICHOGAKE, COMMON, BLOCK);
	
	public static final BlockSlab WASHED_GRANOLITHIC_FINISH_SLAB_DOUBLE = new BlockSlabBase(false, WASHED_GRANOLITHIC_FINISH, COMMON, BLOCK);
	
	public static final BlockSlab WASHED_GRANOLITHIC_FINISH_SLAB_HALF = new BlockSlabBase(true, WASHED_GRANOLITHIC_FINISH, COMMON, BLOCK);
	
	public static final BlockSlab ASPHALT_SLAB_DOUBLE = new BlockSlabBase(false, ASPHALT, COMMON, BLOCK);
	
	public static final BlockSlab ASPHALT_SLAB_HALF = new BlockSlabBase(true, ASPHALT, COMMON, BLOCK);
}

