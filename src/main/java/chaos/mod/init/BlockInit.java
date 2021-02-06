package chaos.mod.init;

import java.util.List;

import chaos.mod.objects.block.base.BlockHorizontalBase;
import chaos.mod.objects.block.base.BlockLights;
import chaos.mod.objects.block.fence.BlockBarbedWiresBase;
import chaos.mod.objects.block.slabs.BlockSideSlabBase;
import chaos.mod.objects.block.stairs.BlockCustomStairBase;
import chaos.mod.objects.block.subblocks.BlockImmersiveRetainingWall;
import com.google.common.collect.Lists;

import chaos.mod.Eki;
import chaos.mod.objects.block.BlockHandRailBase;
import chaos.mod.objects.block.BlockPlatformEdgeBase;
import chaos.mod.objects.block.BlockPlatformEdgeOPFBase;
import chaos.mod.objects.block.base.BlockBase;
import chaos.mod.objects.block.base.BlockLights.BlockHorizontalBaseLights;
import chaos.mod.objects.block.base.BlockLights.BlockDirectionalBaseLights;
import chaos.mod.objects.block.fence.BlockBarbedWiresBase.BarbedWireType;
import chaos.mod.objects.block.machines.BlockAnchor;
import chaos.mod.objects.block.machines.BlockGateBase;
import chaos.mod.objects.block.machines.BlockVendorBase;
import chaos.mod.objects.block.slabs.BlockSlabDoubleBaseCommon;
import chaos.mod.objects.block.slabs.BlockSlabHalfBaseCommon;
import chaos.mod.objects.block.stairs.BlockStairsCommon;
import chaos.mod.objects.block.stairs.BlockCustomStairBase.StairHandRailType;
import chaos.mod.objects.block.stairs.BlockCustomStairBase.StairType;
import chaos.mod.objects.block.subblocks.BlockEnamelWall;
import chaos.mod.objects.block.subblocks.BlockTessera;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockInit {
	// DEFINE SECTION
	private static final CreativeTabs BLOCK = Eki.BLOCK;
	private static final CreativeTabs STA = Eki.STATION;
	// INIT SECTION
	public static final List<Block> BLOCKS = Lists.newArrayList();
	// STATION SECTION
	// station stuff
	public static final Block HANDRAIL = new BlockHandRailBase("handrail", 1);

	public static final Block HANDRAIL_CORNER = new BlockHandRailBase("handrail_corner", 2);

	public static final Block IMMERSIVE_RETAINING_WALL = new BlockImmersiveRetainingWall();
	// platforms
	public static final Block PLATFORM_EDGE = new BlockPlatformEdgeBase("platform_edge", false, false);

	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK = new BlockPlatformEdgeBase("platform_edge_with_guide_brick", false, false);

	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK_AND_LINE = new BlockPlatformEdgeBase("platform_edge_with_guide_brick_and_line", false, false);

	public static final Block PLATFORM_EDGE_WITH_LINE = new BlockPlatformEdgeBase("platform_edge_with_line", false, false);

	public static final Block PLATFORM_ASPHALT_WITH_GUIDE_BRICK = new BlockPlatformEdgeBase("platform_asphalt_with_guide_brick", false, false);

	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON = new BlockPlatformEdgeBase("platform_edge_with_line_and_light_on", true, true);

	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF = new BlockPlatformEdgeBase("platform_edge_with_line_and_light_off", false, true);

	public static final Block PLATFORM_EDGE_OPF = new BlockPlatformEdgeOPFBase("platform_edge_opf", false, false);

	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK_OPF = new BlockPlatformEdgeOPFBase("platform_edge_with_guide_brick_opf", false, false);

	public static final Block PLATFORM_EDGE_WITH_GUIDE_BRICK_AND_LINE_OPF = new BlockPlatformEdgeOPFBase("platform_edge_with_guide_brick_and_line_opf", false, false);

	public static final Block PLATFORM_EDGE_WITH_LINE_OPF = new BlockPlatformEdgeOPFBase("platform_edge_with_line_opf", false, false);

	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON_OPF = new BlockPlatformEdgeOPFBase("platform_edge_with_line_and_light_on_opf", true, true);

	public static final Block PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF_OPF = new BlockPlatformEdgeOPFBase("platform_edge_with_line_and_light_off_opf", false, true);
	// decorations
	public static final Block WOODEN_BENCH = new BlockHorizontalBase("wooden_bench", STA, Material.WOOD, false);
	// machines
	public static final Block TICKET_VENDOR = new BlockVendorBase("ticket_vendor");

	public static final Block TICKET_GATE = new BlockGateBase("ticket_gate");

	public static final Block ANCHOR = new BlockAnchor("anchor");
	// pillars
	public static final Block STATION_PILLAR_BASE = new BlockBase("station_pillar_base", STA, Material.ROCK);

	public static final Block STATION_PILLAR_CENTER = new BlockBase("station_pillar_center", STA, Material.ROCK);

	public static final Block STATION_PILLAR_TOP = new BlockBase("station_pillar_top", STA, Material.ROCK);

	public static final Block STATION_PILLAR_BASE_WITH_FIRE_EXTINGUISHER_IN = new BlockHorizontalBase("station_pillar_base_with_fire_extinguisher_in", STA, Material.ROCK, false);
	// beams
	public static final Block PANEL_BEAM_WHITE = new BlockHorizontalBase("panel_beam_white", STA, Material.IRON, false);

	public static final Block PANEL_BEAM_BLACK = new BlockHorizontalBase("panel_beam_black", STA, Material.IRON, false);

	public static final Block PANEL_BEAM_WITH_CROSS_WHITE = new BlockHorizontalBase("panel_beam_with_cross_white", STA, Material.IRON, false);

	public static final Block PANEL_BEAM_WITH_CROSS_BLACK = new BlockHorizontalBase("panel_beam_with_cross_black", STA, Material.IRON, false);

	public static final Block BENDING_BEAM_WHITE = new BlockHorizontalBase("bending_beam_white", STA, Material.IRON, false);

	public static final Block BENDING_BEAM_BLACK = new BlockHorizontalBase("bending_beam_black", STA, Material.IRON, false);

	public static final Block SUPPORT_BEAM_WHITE = new BlockHorizontalBase("support_beam_white", STA, Material.IRON, false);

	public static final Block SUPPORT_BEAM_BLACK = new BlockHorizontalBase("support_beam_black", STA, Material.IRON, false);

	public static final Block BEAM_BLACK = new BlockHorizontalBase("beam_black", STA, Material.IRON, false);

	public static final Block BEAM_WHITE = new BlockHorizontalBase("beam_white", STA, Material.IRON, false);
	// barbed wires
	public static final Block BARBED_WIRES_FENCE = new BlockBarbedWiresBase("barbed_wires_fence", BarbedWireType.FENCE);

	public static final Block BARBED_WIRES_BASE = new BlockBarbedWiresBase("barbed_wires_base", BarbedWireType.BASE);

	public static final Block BARBED_WIRES_TOP = new BlockBarbedWiresBase("barbed_wires_top", BarbedWireType.TOP);
	// BLOCK SECTION
	// blocks
	public static final Block RETAINING_WALL = new BlockHorizontalBase("retaining_wall", BLOCK, Material.ROCK, false);

	public static final Block RETAINING_WALL_CORNER = new BlockHorizontalBase("retaining_wall_corner", BLOCK, Material.ROCK, false);

	public static final Block ARCHITECTURAL_CONCRETE = new BlockBase("architectural_concrete", BLOCK, Material.ROCK);

	public static final Block CONCRETE = new BlockBase("concrete", BLOCK, Material.ROCK);

	public static final Block NICHOGAKE = new BlockBase("nichogake", BLOCK, Material.ROCK);

	public static final Block WASHED_GRANOLITHIC_FINISH = new BlockBase("washed_granolithic_finish", BLOCK, Material.ROCK);

	public static final Block ASPHALT = new BlockBase("asphalt", BLOCK, Material.ROCK);

	public static final Block ENAMEL_WALL_TOP = new BlockEnamelWall("enamel_wall_top");

	public static final Block ENAMEL_WALL_BOT = new BlockEnamelWall("enamel_wall_bot");

	public static final Block ENAMEL_WALL_S = new BlockEnamelWall("enamel_wall_s");

	public static final Block TESSERA = new BlockTessera("tessera");
	// stairs
	public static final Block ARCHITECTURAL_CONCRETE_STAIRS = new BlockStairsCommon("architectural_concrete_stairs", BLOCK, ARCHITECTURAL_CONCRETE);

	public static final Block CONCRETE_STAIRS = new BlockStairsCommon("concrete_stairs", BLOCK, CONCRETE);

	public static final Block NICHOGAKE_STAIRS = new BlockStairsCommon("nichogake_stairs", BLOCK, NICHOGAKE);

	public static final Block WASHED_GRANOLITHIC_FINISH_STAIRS = new BlockStairsCommon("washed_granolithic_finish_stairs", BLOCK, NICHOGAKE);

	public static final Block ASPHALT_STAIRS = new BlockStairsCommon("asphalt_stairs", BLOCK, ASPHALT);
	// slabs
	public static final BlockSlab ARCHITECTURAL_CONCRETE_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("architectural_concrete_slab_double", BlockInit.ARCHITECTURAL_CONCRETE_SLAB_HALF);

	public static final BlockSlab ARCHITECTURAL_CONCRETE_SLAB_HALF = new BlockSlabHalfBaseCommon("architectural_concrete_slab_half", BlockInit.ARCHITECTURAL_CONCRETE_SLAB_HALF,
			ARCHITECTURAL_CONCRETE_SLAB_DOUBLE);

	public static final BlockSlab CONCRETE_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("concrete_slab_double", BlockInit.CONCRETE_SLAB_HALF);

	public static final BlockSlab CONCRETE_SLAB_HALF = new BlockSlabHalfBaseCommon("concrete_slab_half", BlockInit.CONCRETE_SLAB_HALF, CONCRETE_SLAB_DOUBLE);

	public static final BlockSlab NICHOGAKE_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("nichogake_slab_double", BlockInit.NICHOGAKE_SLAB_HALF);

	public static final BlockSlab NICHOGAKE_SLAB_HALF = new BlockSlabHalfBaseCommon("nichogake_slab_half", BlockInit.NICHOGAKE_SLAB_HALF, NICHOGAKE_SLAB_DOUBLE);

	public static final BlockSlab WASHED_GRANOLITHIC_FINISH_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("washed_granolithic_finish_slab_double", BlockInit.WASHED_GRANOLITHIC_FINISH_SLAB_HALF);

	public static final BlockSlab WASHED_GRANOLITHIC_FINISH_SLAB_HALF = new BlockSlabHalfBaseCommon("washed_granolithic_finish_slab_half", BlockInit.WASHED_GRANOLITHIC_FINISH_SLAB_HALF,
			WASHED_GRANOLITHIC_FINISH_SLAB_DOUBLE);

	public static final BlockSlab ASPHALT_SLAB_DOUBLE = new BlockSlabDoubleBaseCommon("asphalt_slab_double", BlockInit.ASPHALT_SLAB_HALF);

	public static final BlockSlab ASPHALT_SLAB_HALF = new BlockSlabHalfBaseCommon("asphalt_slab_half", BlockInit.ASPHALT_SLAB_HALF, ASPHALT_SLAB_DOUBLE);
	// side slabs
	public static final Block RETAINING_WALL_SIDE_SLAB = new BlockSideSlabBase("retaining_wall_side_slab", (BlockBase) RETAINING_WALL);

	public static final Block ARCHITECTURAL_CONCRETE_SIDE_SLAB = new BlockSideSlabBase("architectural_concrete_side_slab", (BlockBase) ARCHITECTURAL_CONCRETE);

	public static final Block CONCRETE_SIDE_SLAB = new BlockSideSlabBase("concrete_side_slab", (BlockBase) CONCRETE);

	public static final Block NICHOGAKE_SIDE_SLAB = new BlockSideSlabBase("nichogake_side_slab", (BlockBase) NICHOGAKE);

	public static final Block WASHED_GRANOLITHIC_FINISH_SIDE_SLAB = new BlockSideSlabBase("washed_granolithic_finish_side_slab", (BlockBase) WASHED_GRANOLITHIC_FINISH);

	public static final Block ASPHALT_SIDE_SLAB = new BlockSideSlabBase("asphalt_side_slab", (BlockBase) ASPHALT);
	// custom stairs
	public static final Block STATION_STAIRS = new BlockCustomStairBase("station_stairs", StairType.NORMAL, StairHandRailType.NON);

	//public static final Block STATION_STAIRS_LEFT = new BlockCustomStair("station_stairs_left", StairType.NORMAL, StairHandRailType.LEFT);

	//public static final Block STATION_STAIRS_RIGHT = new BlockCustomStair("station_stairs_right", StairType.NORMAL, StairHandRailType.RIGHT);

	public static final Block STATION_STAIRS_GENTLE_BOT = new BlockCustomStairBase("station_stairs_gentle_bot", StairType.GENTLE_BOT, StairHandRailType.NON);

	//public static final Block STATION_STAIRS_GENTLE_BOT_LEFT = new BlockCustomStair("station_stairs_gentle_bot_left", StairType.GENTLE_BOT, StairHandRailType.LEFT);

	//public static final Block STATION_STAIRS_GENTLE_BOT_RIGHT = new BlockCustomStair("station_stairs_gentle_bot_right", StairType.GENTLE_BOT, StairHandRailType.RIGHT);

	public static final Block STATION_STAIRS_GENTLE_TOP = new BlockCustomStairBase("station_stairs_gentle_top", StairType.GENTLE_TOP, StairHandRailType.NON);

	//public static final Block STATION_STAIRS_GENTLE_TOP_LEFT = new BlockCustomStair("station_stairs_gentle_top_left", StairType.GENTLE_TOP, StairHandRailType.LEFT);

	//public static final Block STATION_STAIRS_GENTLE_TOP_RIGHT = new BlockCustomStair("station_stairs_gentle_top_right", StairType.GENTLE_TOP, StairHandRailType.RIGHT);
	// LIGHT SECTION
	public static final Block GRILLE_LIGHT_WHITE = new BlockHorizontalBaseLights("grille_light_white", 1F, Material.GLASS, false, false);

	public static final Block GRILLE_LIGHT_BLACK = new BlockHorizontalBaseLights("grille_light_black", 1F, Material.GLASS, false, false);

	public static final Block EMBEDDED_GRILLE_LIGHT_WHITE = new BlockHorizontalBaseLights("embedded_grille_light_white", 1F, Material.GLASS, true, false) {
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
			return new AxisAlignedBB(0F, 0.9375F, 0F, 1F, 1F, 1F);
		};
	};

	public static final Block EMBEDDED_GRILLE_LIGHT_BLACK = new BlockHorizontalBaseLights("embedded_grille_light_black", 1F, Material.GLASS, true, false) {
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
			return new AxisAlignedBB(0F, 0.9375F, 0F, 1F, 1F, 1F);
		};
	};

	public static final Block TUNNEL_LIGHT = new BlockLights.BlockHorizontalBaseLights("tunnel_light", 7 / 15F, Material.GLASS, true, true);

	public static final Block WALL_NEON_LIGHT = new BlockHorizontalBaseLights("wall_neon_light", 1F, Material.GLASS, true, true);

	public static final Block PILLAR_LAMP = new BlockDirectionalBaseLights("pillar_lamp", 1F, Material.GLASS, false, false);

	public static final Block FLOOD_LIGHT = new BlockDirectionalBaseLights("flood_light", 1F, Material.GLASS, false, false) {
		public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
			EnumFacing face = world.getBlockState(pos).getValue(FACING);
			return (face == EnumFacing.UP || face == EnumFacing.DOWN) ? false : true;
		};
	};
}
