package chaos.mod.init;

import java.util.ArrayList;
import java.util.List;

import chaos.mod.Main;
import chaos.mod.objects.block.BlockHandRail;
import chaos.mod.objects.block.BlockPlatformEdge;
import chaos.mod.objects.block.BlockPlatformEdgeOPF;
import chaos.mod.objects.block.base.BlockBase;
import chaos.mod.objects.block.base.BlockHasFace;
import chaos.mod.objects.block.base.BlockLights;
import chaos.mod.objects.block.machines.BlockAnchor;
import chaos.mod.objects.block.machines.BlockGate;
import chaos.mod.objects.block.machines.BlockVendor;
import chaos.mod.objects.block.slabs.BlockSideSlab;
import chaos.mod.objects.block.slabs.BlockSlabDoubleBaseCommon;
import chaos.mod.objects.block.slabs.BlockSlabHalfBaseCommon;
import chaos.mod.objects.block.stairs.BlockCustomStair;
import chaos.mod.objects.block.stairs.BlockStairsCommon;
import chaos.mod.objects.block.subblocks.BlockEnamelWall;
import chaos.mod.objects.block.subblocks.BlockTessera;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockInit {
	//DEFINE SECTION
	private static final CreativeTabs BLOCK = Main.eki_block_tab;
	private static final CreativeTabs STA = Main.eki_station_tab;
	private static final CreativeTabs LIGHT = Main.eki_misc_tab;
	private static final Material COMMON = Material.ROCK;
	//INIT SECTION
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	//STATION SECTION
	//station stuff
	public static final Block HANDRAIL = new BlockHandRail("handrail", COMMON, STA, 1);
	
	public static final Block HANDRAIL_CORNER = new BlockHandRail("handrail_corner", COMMON, STA, 2);
	//platforms
	public static final Block PLATFORM_EDGE = new BlockPlatformEdge("platform_edge", COMMON, STA, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK = new BlockPlatformEdge("platform_edge_with_guide_brick", COMMON, STA, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK_AND_LINE = new BlockPlatformEdge("platform_edge_with_guide_brick_and_line", COMMON, STA, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_LINE = new BlockPlatformEdge("platform_edge_with_line", COMMON, STA, false, false);
	
	public static final Block PLATFORM_ASPHALT_WITH_GUIDE_BRICK = new BlockPlatformEdge("platform_asphalt_with_guide_brick", COMMON, STA, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON = new BlockPlatformEdge("platform_edge_with_line_and_light_on", COMMON, STA, true, true);
	
	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF = new BlockPlatformEdge("platform_edge_with_line_and_light_off", COMMON, STA, false, true);
	
	public static final Block PLATFORM_EDGE_OPF = new BlockPlatformEdgeOPF("platform_edge_opf", COMMON, STA, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK_OPF = new BlockPlatformEdgeOPF("platform_edge_with_guide_brick_opf", COMMON, STA, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK_AND_LINE_OPF = new BlockPlatformEdgeOPF("platform_edge_with_guide_brick_and_line_opf", COMMON, STA, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_LINE_OPF = new BlockPlatformEdgeOPF("platform_edge_with_line_opf", COMMON, STA, false, false);

	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON_OPF = new BlockPlatformEdgeOPF("platform_edge_with_line_and_light_on_opf", COMMON, null, true, true);
	
	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF_OPF = new BlockPlatformEdgeOPF("platform_edge_with_line_and_light_off_opf", COMMON, STA, false, true);
	//decorations
	public static final Block WOODEN_BENCH = new BlockHasFace("wooden_bench", COMMON, STA, false);
	//machines
	public static final Block TICKET_VENDOR = new BlockVendor("ticket_vendor", COMMON, STA);
	
	public static final Block TICKET_GATE = new BlockGate("ticket_gate", COMMON, STA);
	
	public static final Block ANCHOR = new BlockAnchor("anchor", COMMON, STA);
	//pillars
	public static final Block STATION_PILLAR_BASE = new BlockBase("station_pillar_base", COMMON, STA);
	
	public static final Block STATION_PILLAR_CENTER = new BlockBase("station_pillar_center", COMMON, STA);
	
	public static final Block STATION_PILLAR_TOP = new BlockBase("station_pillar_top", COMMON, STA);
	
	public static final Block STATION_PILLAR_BASE_WITH_FIRE_EXTINGUISHER_IN = new BlockHasFace("station_pillar_base_with_fire_extinguisher_in", COMMON, STA, false);
	//beams and panels
	public static final Block PANEL_WHITE = new BlockHasFace("panel_white", COMMON, STA, false);
	
	public static final Block PANEL_BLACK = new BlockHasFace("panel_black", COMMON, STA, false);
	
	public static final Block PANEL_BEAM_WHITE = new BlockHasFace("panel_beam_white", COMMON, STA, false);
	
	public static final Block PANEL_BEAM_BLACK = new BlockHasFace("panel_beam_black", COMMON, STA, false);
	
	public static final Block PANEL_BEAM_WITH_CROSS_WHITE = new BlockHasFace("panel_beam_with_cross_white", COMMON, STA, false);
	
	public static final Block PANEL_BEAM_WITH_CROSS_BLACK = new BlockHasFace("panel_beam_with_cross_black", COMMON, STA, false);
	
	public static final Block BENDING_BEAM_WHITE = new BlockHasFace("bending_beam_white", COMMON, STA, false);
	
	public static final Block BENDING_BEAM_BLACK = new BlockHasFace("bending_beam_black", COMMON, STA, false);
	
	public static final Block SUPPORT_BEAM_WHITE = new BlockHasFace("support_beam_white", COMMON, STA, false);
	
	public static final Block SUPPORT_BEAM_BLACK = new BlockHasFace("support_beam_black", COMMON, STA, false);
	
	public static final Block BEAM_BLACK = new BlockHasFace("beam_black", COMMON, STA, false);
	
	public static final Block BEAM_WHITE = new BlockHasFace("beam_white", COMMON, STA, false);
	//BLOCK SECTION
	//blocks
	public static final Block RETAINING_WALL = new BlockHasFace("retaining_wall", COMMON, BLOCK, false);
	
	public static final Block RETAINING_WALL_CORNER = new BlockHasFace("retaining_wall_corner", COMMON, BLOCK, false);
	
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
	public static final Block ARCHITECTURAL_CONCRETE_STAIRS = new BlockStairsCommon("architectural_concrete_stairs", BLOCK, ARCHITECTURAL_CONCRETE.getDefaultState());
	
	public static final Block CONCRETE_STAIRS = new BlockStairsCommon("concrete_stairs", BLOCK, CONCRETE.getDefaultState());
	
	public static final Block NICHOGAKE_STAIRS = new BlockStairsCommon("nichogake_stairs", BLOCK, NICHOGAKE.getDefaultState());
	
	public static final Block WASHED_GRANOLITHIC_FINISH_STAIRS = new BlockStairsCommon("washed_granolithic_finish_stairs", BLOCK, NICHOGAKE.getDefaultState());
	
	public static final Block ASPHALT_STAIRS = new BlockStairsCommon("asphalt_stairs", BLOCK, ASPHALT.getDefaultState());
	//slabs
	public static final BlockSlab ARCHITECTURAL_CONCRETE_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("architectural_concrete_slab_double", COMMON, null, BlockInit.ARCHITECTURAL_CONCRETE_SLAB_HALF);
	
	public static final BlockSlab ARCHITECTURAL_CONCRETE_SLAB_HALF = new BlockSlabHalfBaseCommon("architectural_concrete_slab_half", COMMON, BLOCK, BlockInit.ARCHITECTURAL_CONCRETE_SLAB_HALF, BlockInit.ARCHITECTURAL_CONCRETE_SLAB_DOUBLE);
	
	public static final BlockSlab CONCRETE_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("concrete_slab_double", COMMON, null, BlockInit.CONCRETE_SLAB_HALF);
	
	public static final BlockSlab CONCRETE_SLAB_HALF = new BlockSlabHalfBaseCommon("concrete_slab_half", COMMON, BLOCK, BlockInit.CONCRETE_SLAB_HALF, BlockInit.CONCRETE_SLAB_DOUBLE);
	
	public static final BlockSlab NICHOGAKE_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("nichogake_slab_double", COMMON, null, BlockInit.NICHOGAKE_SLAB_HALF);
	
	public static final BlockSlab NICHOGAKE_SLAB_HALF = new BlockSlabHalfBaseCommon("nichogake_slab_half", COMMON, BLOCK, BlockInit.NICHOGAKE_SLAB_HALF, BlockInit.NICHOGAKE_SLAB_DOUBLE);
	
	public static final BlockSlab WASHED_GRANOLITHIC_FINISH_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("washed_granolithic_finish_slab_double", COMMON, null, BlockInit.WASHED_GRANOLITHIC_FINISH_SLAB_HALF);
	
	public static final BlockSlab WASHED_GRANOLITHIC_FINISH_SLAB_HALF = new BlockSlabHalfBaseCommon("washed_granolithic_finish_slab_half", COMMON, BLOCK, BlockInit.WASHED_GRANOLITHIC_FINISH_SLAB_HALF, BlockInit.WASHED_GRANOLITHIC_FINISH_SLAB_DOUBLE);
	
	public static final BlockSlab ASPHALT_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("asphalt_slab_double", COMMON, null, BlockInit.ASPHALT_SLAB_HALF);
	
	public static final BlockSlab ASPHALT_SLAB_HALF = new BlockSlabHalfBaseCommon("asphalt_slab_half", COMMON, BLOCK, BlockInit.ASPHALT_SLAB_HALF, BlockInit.ASPHALT_SLAB_DOUBLE);
	//side slabs
	public static final Block RETAINING_WALL_SIDE_SLAB = new BlockSideSlab("retaining_wall_side_slab", COMMON, BLOCK);
	
	public static final Block ARCHITECTURAL_CONCRETE_SIDE_SLAB = new BlockSideSlab("architectural_concrete_side_slab", COMMON, BLOCK);
	
	public static final Block CONCRETE_SIDE_SLAB = new BlockSideSlab("concrete_side_slab", COMMON, BLOCK);
	
	public static final Block NICHOGAKE_SIDE_SLAB = new BlockSideSlab("nichogake_side_slab", COMMON, BLOCK);
	
	public static final Block WASHED_GRANOLITHIC_FINISH_SIDE_SLAB = new BlockSideSlab("washed_granolithic_finish_side_slab", COMMON, BLOCK);
	
	public static final Block ASPHALT_SIDE_SLAB = new BlockSideSlab("asphalt_side_slab", COMMON, BLOCK);
	//custom stairs
	public static final Block STATION_STAIRS = new BlockCustomStair("station_stairs", COMMON, BLOCK, 1);
	
	public static final Block STATION_STAIRS_GENTLE_BOT = new BlockCustomStair("station_stairs_gentle_bot", COMMON, BLOCK, 2);
	
	public static final Block STATION_STAIRS_GENTLE_TOP = new BlockCustomStair("station_stairs_gentle_top", COMMON, BLOCK, 3);
	//LIGHT SECTION
	public static final Block GRILLE_LIGHT_WHITE = new BlockLights("grille_light_white", COMMON, LIGHT, 1F, false, false);
	
	public static final Block GRILLE_LIGHT_BLACK = new BlockLights("grille_light_black", COMMON, LIGHT, 1F, false, false);
	
	public static final Block TUNNEL_LIGHT = new BlockLights("tunnel_light", COMMON, LIGHT, 7/15F, true, true);
	
	public static final Block WALL_NEON_LIGHT = new BlockLights("wall_neon_light", COMMON, LIGHT, 1F, true, true);
}
