package chaos.mod.init;

import java.util.ArrayList;
import java.util.List;

import chaos.mod.Main;
import chaos.mod.objects.block.base.BlockBase;
import chaos.mod.objects.block.subblocks.BlockEnamelWall;
import chaos.mod.objects.block.subblocks.BlockTessera;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockInit {
	//private static final CreativeTabs STA = Main.eki_station_tab;
	private static final CreativeTabs BLOCK = Main.eki_block_tab;
	//private static final CreativeTabs MISC = Main.eki_misc_tab;
	private static final Material COMMON = Material.rock;
	//INIT SECTION
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final List<Block> METABLOCKS = new ArrayList<Block>();
	
	public static final Block ARCHITECTURAL_CONCRETE = new BlockBase("architectural_concrete", COMMON, BLOCK);
	
	public static final Block CONCRETE = new BlockBase("concrete", COMMON, BLOCK);
	
	public static final Block NICHOGAKE = new BlockBase("nichogake", COMMON, BLOCK);
	
	public static final Block WASHED_GRANOLITHIC_FINISH = new BlockBase("washed_granolithic_finish", COMMON, BLOCK);
	
	public static final Block ASPHALT = new BlockBase("asphalt", COMMON, BLOCK);
	
	public static final Block ENAMEL_WALL_TOP = new BlockEnamelWall("enamel_wall_top", COMMON, BLOCK);
	
	public static final Block ENAMEL_WALL_BOT = new BlockEnamelWall("enamel_wall_bot", COMMON, BLOCK);
	
	public static final Block ENAMEL_WALL_S = new BlockEnamelWall("enamel_wall_s", COMMON, BLOCK);
	
	public static final Block TESSERA = new BlockTessera("tessera", COMMON, BLOCK);
}
