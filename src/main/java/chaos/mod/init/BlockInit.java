package chaos.mod.init;

import java.util.ArrayList;
import java.util.List;

import chaos.mod.Main;
import chaos.mod.objects.block.BlockHandRail;
import chaos.mod.objects.block.BlockLights;
import chaos.mod.objects.block.BlockPlatformEdge;
import chaos.mod.objects.block.BlockPlatformEdgeOPF;
import chaos.mod.objects.block.base.BlockBase;
import chaos.mod.objects.block.base.BlockHasFace;
import chaos.mod.objects.block.slabs.BlockSideSlab;
import chaos.mod.objects.block.slabs.BlockSlabDoubleBaseCommon;
import chaos.mod.objects.block.slabs.BlockSlabHalfBaseCommon;
import chaos.mod.objects.block.stairs.BlockCustomStair;
import chaos.mod.objects.block.stairs.BlockStairsCommon;
import chaos.mod.objects.block.subblocks.BlockEnamelWall;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	//STATION SECTION
	//station stuff
	public static final Block HANDRAIL = new BlockHandRail("handrail", Material.ROCK, Main.eki_station_tab, 1);
	
	public static final Block HANDRAIL_CORNER = new BlockHandRail("handrail_corner", Material.ROCK, Main.eki_station_tab, 2);
	//platforms
	public static final Block PLATFORM_EDGE = new BlockPlatformEdge("platform_edge", Material.ROCK, Main.eki_station_tab, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK = new BlockPlatformEdge("platform_edge_with_guide_brick", Material.ROCK, Main.eki_station_tab, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK_AND_LINE = new BlockPlatformEdge("platform_edge_with_guide_brick_and_line", Material.ROCK, Main.eki_station_tab, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_LINE = new BlockPlatformEdge("platform_edge_with_line", Material.ROCK, Main.eki_station_tab, false, false);
	
	public static final Block PLATFORM_ASPHALT_WITH_GUIDE_BRICK = new BlockPlatformEdge("platform_asphalt_with_guide_brick", Material.ROCK, Main.eki_station_tab, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON = new BlockPlatformEdge("platform_edge_with_line_and_light_on", Material.ROCK, Main.eki_station_tab, true, true);
	
	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF = new BlockPlatformEdge("platform_edge_with_line_and_light_off", Material.ROCK, Main.eki_station_tab, false, true);
	
	public static final Block PLATFORM_EDGE_OPF = new BlockPlatformEdgeOPF("platform_edge_opf", Material.ROCK, Main.eki_station_tab, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK_OPF = new BlockPlatformEdgeOPF("platform_edge_with_guide_brick_opf", Material.ROCK, Main.eki_station_tab, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK_AND_LINE_OPF = new BlockPlatformEdgeOPF("platform_edge_with_guide_brick_and_line_opf", Material.ROCK, Main.eki_station_tab, false, false);
	
	public static final Block PLATFORM_EDGE_WITH_LINE_OPF = new BlockPlatformEdgeOPF("platform_edge_with_line_opf", Material.ROCK, Main.eki_station_tab, false, false);

	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON_OPF = new BlockPlatformEdgeOPF("platform_edge_with_line_and_light_on_opf", Material.ROCK, null, true, true);
	
	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF_OPF = new BlockPlatformEdgeOPF("platform_edge_with_line_and_light_off_opf", Material.ROCK, Main.eki_station_tab, false, true);
	//pillars
	public static final Block STATION_PILLAR_BASE = new BlockBase("station_pillar_base", Material.ROCK, Main.eki_station_tab);
	
	public static final Block STATION_PILLAR_CENTER = new BlockBase("station_pillar_center", Material.ROCK, Main.eki_station_tab);
	
	public static final Block STATION_PILLAR_TOP = new BlockBase("station_pillar_top", Material.ROCK, Main.eki_station_tab);
	
	public static final Block STATION_PILLAR_BASE_WITH_FIRE_EXTINGUISHER_IN = new BlockHasFace("station_pillar_base_with_fire_extinguisher_in", Material.ROCK, Main.eki_station_tab, false);
	//beams and panels
	public static final Block PANEL_WHITE = new BlockHasFace("panel_white", Material.ROCK, Main.eki_station_tab, true);
	
	public static final Block PANEL_BLACK = new BlockHasFace("panel_black", Material.ROCK, Main.eki_station_tab, true);
	
	public static final Block PANEL_BEAM_WHITE = new BlockHasFace("panel_beam_white", Material.ROCK, Main.eki_station_tab, true);
	
	public static final Block PANEL_BEAM_BLACK = new BlockHasFace("panel_beam_black", Material.ROCK, Main.eki_station_tab, true);
	
	public static final Block PANEL_BEAM_WITH_CROSS_WHITE = new BlockHasFace("panel_beam_with_cross_white", Material.ROCK, Main.eki_station_tab, true);
	
	public static final Block PANEL_BEAM_WITH_CROSS_BLACK = new BlockHasFace("panel_beam_with_cross_black", Material.ROCK, Main.eki_station_tab, true);
	
	public static final Block BENDING_BEAM_WHITE = new BlockHasFace("bending_beam_white", Material.ROCK, Main.eki_station_tab, true);
	
	public static final Block BENDING_BEAM_BLACK = new BlockHasFace("bending_beam_black", Material.ROCK, Main.eki_station_tab, true);
	
	public static final Block SUPPORT_BEAM_WHITE = new BlockHasFace("support_beam_white", Material.ROCK, Main.eki_station_tab, true);
	
	public static final Block SUPPORT_BEAM_BLACK = new BlockHasFace("support_beam_black", Material.ROCK, Main.eki_station_tab, true);
	
	public static final Block BEAM_BLACK = new BlockHasFace("beam_black", Material.ROCK, Main.eki_station_tab, true);
	
	public static final Block BEAM_WHITE = new BlockHasFace("beam_white", Material.ROCK, Main.eki_station_tab, true);
	//BLOCK SECTION
	//blocks
	public static final Block RETAINING_WALL = new BlockHasFace("retaining_wall", Material.ROCK, Main.eki_block_tab, false);
	
	public static final Block RETAINING_WALL_CORNER = new BlockHasFace("retaining_wall_corner", Material.ROCK, Main.eki_block_tab, false);
	
	public static final Block ARCHITECTURAL_CONCRETE = new BlockBase("architectural_concrete", Material.ROCK, Main.eki_block_tab);
	
	public static final Block CONCRETE = new BlockBase("concrete", Material.ROCK, Main.eki_block_tab);
	
	public static final Block NICHOGAKE = new BlockBase("nichogake", Material.ROCK, Main.eki_block_tab);
	
	public static final Block WASHED_GRANOLITHIC_FINISH = new BlockBase("washed_granolithic_finish", Material.ROCK, Main.eki_block_tab);
	
	public static final Block ASPHALT = new BlockBase("asphalt", Material.ROCK, Main.eki_block_tab);
	
	public static final Block ENAMEL_WALL_TOP = new BlockEnamelWall("enamel_wall_top", Material.ROCK, Main.eki_block_tab);
	
	public static final Block ENAMEL_WALL_BOT = new BlockEnamelWall("enamel_wall_bot", Material.ROCK, Main.eki_block_tab);
	
	public static final Block ENAMEL_WALL_S = new BlockEnamelWall("enamel_wall_s", Material.ROCK, Main.eki_block_tab);
	//stairs
	public static final Block ARCHITECTURAL_CONCRETE_STAIRS = new BlockStairsCommon("architectural_concrete_stairs", Main.eki_block_tab, ARCHITECTURAL_CONCRETE.getDefaultState());
	
	public static final Block CONCRETE_STAIRS = new BlockStairsCommon("concrete_stairs", Main.eki_block_tab, CONCRETE.getDefaultState());
	
	public static final Block NICHOGAKE_STAIRS = new BlockStairsCommon("nichogake_stairs", Main.eki_block_tab, NICHOGAKE.getDefaultState());
	
	public static final Block WASHED_GRANOLITHIC_FINISH_STAIRS = new BlockStairsCommon("washed_granolithic_finish_stairs", Main.eki_block_tab, NICHOGAKE.getDefaultState());
	
	public static final Block ASPHALT_STAIRS = new BlockStairsCommon("asphalt_stairs", Main.eki_block_tab, ASPHALT.getDefaultState());
	//slabs
	public static final BlockSlab ARCHITECTURAL_CONCRETE_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("architectural_concrete_slab_double", Material.ROCK, null, BlockInit.ARCHITECTURAL_CONCRETE_SLAB_HALF);
	
	public static final BlockSlab ARCHITECTURAL_CONCRETE_SLAB_HALF = new BlockSlabHalfBaseCommon("architectural_concrete_slab_half", Material.ROCK, Main.eki_block_tab, BlockInit.ARCHITECTURAL_CONCRETE_SLAB_HALF, BlockInit.ARCHITECTURAL_CONCRETE_SLAB_DOUBLE);
	
	public static final BlockSlab CONCRETE_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("concrete_slab_double", Material.ROCK, null, BlockInit.CONCRETE_SLAB_HALF);
	
	public static final BlockSlab CONCRETE_SLAB_HALF = new BlockSlabHalfBaseCommon("concrete_slab_half", Material.ROCK, Main.eki_block_tab, BlockInit.CONCRETE_SLAB_HALF, BlockInit.CONCRETE_SLAB_DOUBLE);
	
	public static final BlockSlab NICHOGAKE_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("nichogake_slab_double", Material.ROCK, null, BlockInit.NICHOGAKE_SLAB_HALF);
	
	public static final BlockSlab NICHOGAKE_SLAB_HALF = new BlockSlabHalfBaseCommon("nichogake_slab_half", Material.ROCK, Main.eki_block_tab, BlockInit.NICHOGAKE_SLAB_HALF, BlockInit.NICHOGAKE_SLAB_DOUBLE);
	
	public static final BlockSlab WASHED_GRANOLITHIC_FINISH_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("washed_granolithic_finish_slab_double", Material.ROCK, null, BlockInit.WASHED_GRANOLITHIC_FINISH_SLAB_HALF);
	
	public static final BlockSlab WASHED_GRANOLITHIC_FINISH_SLAB_HALF = new BlockSlabHalfBaseCommon("washed_granolithic_finish_slab_half", Material.ROCK, Main.eki_block_tab, BlockInit.WASHED_GRANOLITHIC_FINISH_SLAB_HALF, BlockInit.WASHED_GRANOLITHIC_FINISH_SLAB_DOUBLE);
	
	public static final BlockSlab ASPHALT_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("asphalt_slab_double", Material.ROCK, null, BlockInit.ASPHALT_SLAB_HALF);
	
	public static final BlockSlab ASPHALT_SLAB_HALF = new BlockSlabHalfBaseCommon("asphalt_slab_half", Material.ROCK, Main.eki_block_tab, BlockInit.ASPHALT_SLAB_HALF, BlockInit.ASPHALT_SLAB_DOUBLE);
	//side slabs
	public static final Block RETAINING_WALL_SIDE_SLAB = new BlockSideSlab("retaining_wall_side_slab", Material.ROCK, Main.eki_block_tab);
	
	public static final Block ARCHITECTURAL_CONCRETE_SIDE_SLAB = new BlockSideSlab("architectural_concrete_side_slab", Material.ROCK, Main.eki_block_tab);
	
	public static final Block CONCRETE_SIDE_SLAB = new BlockSideSlab("concrete_side_slab", Material.ROCK, Main.eki_block_tab);
	
	public static final Block NICHOGAKE_SIDE_SLAB = new BlockSideSlab("nichogake_side_slab", Material.ROCK, Main.eki_block_tab);
	
	public static final Block WASHED_GRANOLITHIC_FINISH_SIDE_SLAB = new BlockSideSlab("washed_granolithic_finish_side_slab", Material.ROCK, Main.eki_block_tab);
	
	public static final Block ASPHALT_SIDE_SLAB = new BlockSideSlab("asphalt_side_slab", Material.ROCK, Main.eki_block_tab);
	//custom stairs
	public static final Block STATION_STAIRS = new BlockCustomStair("station_stairs", Material.ROCK, Main.eki_block_tab, 1);
	
	public static final Block STATION_STAIRS_GENTLE_BOT = new BlockCustomStair("station_stairs_gentle_bot", Material.ROCK, Main.eki_block_tab, 2);
	
	public static final Block STATION_STAIRS_GENTLE_TOP = new BlockCustomStair("station_stairs_gentle_top", Material.ROCK, Main.eki_block_tab, 3);
	//LIGHT SECTION
	public static final Block GRILLE_LIGHT_WHITE = new BlockLights("grille_light_white", Material.ROCK, Main.eki_lights_tab, 1F, false, false);
	
	public static final Block GRILLE_LIGHT_BLACK = new BlockLights("grille_light_black", Material.ROCK, Main.eki_lights_tab, 1F, false, false);
	
	public static final Block TUNNEL_LIGHT = new BlockLights("tunnel_light", Material.ROCK, Main.eki_lights_tab, 7/15F, true, true);
	
	public static final Block WALL_NEON_LIGHT = new BlockLights("wall_neon_light", Material.ROCK, Main.eki_lights_tab, 1F, true, true);
}
