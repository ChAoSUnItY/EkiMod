package com.chaos.eki.utils.handler;

import com.chaos.eki.Eki;
import com.chaos.eki.objects.blocks.*;
import com.chaos.eki.objects.blocks.multiblock.BarbedWireMultiBlock;
import com.chaos.eki.objects.blocks.multiblock.RetainingWallLadderMultiBlock;
import com.chaos.eki.objects.blocks.multiblock.RetainingWallMultiBlock;
import com.chaos.eki.objects.blocks.multiblock.StationPlatformMultiBlock;
import com.chaos.eki.objects.blocks.stairs.StationStairBlock;
import com.chaos.eki.objects.blocks.subblock.*;
import com.chaos.eki.objects.items.*;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import com.chaos.eki_lib.objects.blocks.base.LightableBlock;
import com.chaos.eki_lib.utils.util.registry.RegistryCollection;
import com.chaos.eki_lib.utils.util.registry.UtilSubblockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    public static final AbstractBlock.Properties DEFAULT_BLOCK_PROPERTIES = AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5f);
    public static final Item.Properties DEFAULT_PROPERTIES = new Item.Properties().group(Eki.ekiItemGroup);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Eki.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Eki.MOD_ID);

    //BLOCKS
    public static final RegistryObject<RetainingWallMultiBlock> HUGE_RETAINING_WALL = BLOCKS.register("retaining_wall", RetainingWallMultiBlock::new);
    public static final RegistryObject<RetainingWallLadderMultiBlock> HUGE_RETAINING_WALL_LADDER = BLOCKS.register("retaining_wall_ladder", RetainingWallLadderMultiBlock::new);
    public static final RegistryCollection<BuildingBaseBlock> CONCRETE_COL = RegistryCollection
            .create("concrete", DEFAULT_BLOCK_PROPERTIES, Eki.ekiItemGroup)
            .init(BuildingBaseBlock::new, BLOCKS, ITEMS);
    public static final RegistryCollection<BuildingBaseBlock> ARCHITECTURAL_CONCRETE_COL = RegistryCollection
            .create("architectural_concrete", DEFAULT_BLOCK_PROPERTIES, Eki.ekiItemGroup)
            .init(BuildingBaseBlock::new, BLOCKS, ITEMS);
    public static final RegistryCollection<BuildingBaseBlock> ASPHALT_COL = RegistryCollection
            .create("asphalt", DEFAULT_BLOCK_PROPERTIES, Eki.ekiItemGroup)
            .init(BuildingBaseBlock::new, BLOCKS, ITEMS);
    public static final RegistryCollection<BuildingBaseBlock> NICHOGAKE_COL = RegistryCollection
            .create("nichogake", DEFAULT_BLOCK_PROPERTIES, Eki.ekiItemGroup)
            .init(BuildingBaseBlock::new, BLOCKS, ITEMS);
    public static final RegistryCollection<BuildingBaseBlock> WASHED_GRANOLITHIC_FINISH_COL = RegistryCollection
            .create("washed_granolithic_finish", DEFAULT_BLOCK_PROPERTIES, Eki.ekiItemGroup)
            .init(BuildingBaseBlock::new, BLOCKS, ITEMS);

    // platforms
    public static final RegistryObject<StationPlatformMultiBlock> SP_COMMON = BLOCKS.register("platform_common",
            () -> new StationPlatformMultiBlock(false));
    public static final RegistryObject<StationPlatformMultiBlock> SP_GUIDE_BRICK = BLOCKS.register("platform_guide_brick",
            () -> new StationPlatformMultiBlock(false));
    public static final RegistryObject<StationPlatformMultiBlock> SP_GUIDE_BRICK_LINE = BLOCKS.register("platform_guide_brick_line",
            () -> new StationPlatformMultiBlock(false));
    public static final RegistryObject<StationPlatformMultiBlock> SP_LINE = BLOCKS.register("platform_line",
            () -> new StationPlatformMultiBlock(false));
    public static final RegistryObject<StationPlatformMultiBlock> SP_ASPHALT = BLOCKS.register("platform_asphalt",
            () -> new StationPlatformMultiBlock(false));
    public static final RegistryObject<StationPlatformMultiBlock> SP_ON = BLOCKS.register("platform_on",
            () -> new StationPlatformMultiBlock(false, true, true));
    public static final RegistryObject<StationPlatformMultiBlock> SP_OFF = BLOCKS.register("platform_off",
            () -> new StationPlatformMultiBlock(false, true, false));
    // opf platforms
    public static final RegistryObject<PowerableSideSlab<HorizontalBaseBlock>> SP_OPF_BASE = BLOCKS.register("platform_opf_base",
            () -> new PowerableSideSlab(SP_COMMON));
    public static final RegistryObject<StationPlatformMultiBlock> SP_COMMON_OPF = BLOCKS.register("platform_common_opf",
            () -> new StationPlatformMultiBlock(true));
    public static final RegistryObject<StationPlatformMultiBlock> SP_GUIDE_BRICK_OPF = BLOCKS.register("platform_guide_brick_opf",
            () -> new StationPlatformMultiBlock(true));
    public static final RegistryObject<StationPlatformMultiBlock> SP_GUIDE_BRICK_LINE_OPF = BLOCKS.register("platform_guide_brick_line_opf",
            () -> new StationPlatformMultiBlock(true));
    public static final RegistryObject<StationPlatformMultiBlock> SP_LINE_OPF = BLOCKS.register("platform_line_opf",
            () -> new StationPlatformMultiBlock(false));
    public static final RegistryObject<StationPlatformMultiBlock> SP_ASPHALT_OPF = BLOCKS.register("platform_asphalt_opf",
            () -> new StationPlatformMultiBlock(true));
    public static final RegistryObject<StationPlatformMultiBlock> SP_ON_OPF = BLOCKS.register("platform_on_opf",
            () -> new StationPlatformMultiBlock(true, true, true));
    public static final RegistryObject<StationPlatformMultiBlock> SP_OFF_OPF = BLOCKS.register("platform_off_opf",
            () -> new StationPlatformMultiBlock(true, true, false));
    // subblocks
    public static final RegistryObject<BuildingBaseBlock>[] ENAMEL_WALLS = new RegistryObject[EnamelWallType.values.length * EnamelWallPositionType.values.length];
    public static final RegistryObject<BuildingBaseBlock>[] TESSERA = new RegistryObject[TesseraWallType.values.length];
    // station stairs
    public static final RegistryObject<StationStairBlock> STATION_STAIR = BLOCKS.register("station_stairs", () -> new StationStairBlock(0));
    public static final RegistryObject<StationStairBlock> STATION_STAIR_GB = BLOCKS.register("station_stairs_gentle_bottom", () -> new StationStairBlock(1));
    public static final RegistryObject<StationStairBlock> STATION_STAIR_GT = BLOCKS.register("station_stairs_gentle_top", () -> new StationStairBlock(2));
    // handrail
    public static final RegistryObject<HandrailBlock> HANDRAIL = BLOCKS.register("handrail",
            () -> new HandrailBlock(() -> CONCRETE_COL.getBaseBlock().getBlock().get().getDefaultState(), DEFAULT_BLOCK_PROPERTIES));
    // pillars
    public static final RegistryObject<BuildingBaseBlock>[] STATION_PILLARS = new RegistryObject[StationPillarType.values.length];
    public static final RegistryObject<HorizontalBaseBlock> STATION_PILLAR_FE = BLOCKS.register("station_pillar_with_fire_extinguisher",
            () -> new HorizontalBaseBlock(AbstractBlock.Properties.from(CONCRETE_COL.getBaseBlock().getBlock().get())));
    //lights
    public static final RegistryObject<LightableBlock.LightableHorizontalBlock>[] GRILLE_LIGHTS = new RegistryObject[2];
    public static final RegistryObject<LightableBlock.LightableHorizontalBlock>[] EMBEDDED_GRILLE_LIGHTS = new RegistryObject[2];
    public static final RegistryObject<LightableBlock.LightableHorizontalBlock> TUNNEL_LIGHT = BLOCKS.register("tunnel_light",
            () -> new WallLightableBlock(7));
    public static final RegistryObject<LightableBlock.LightableHorizontalBlock> WALL_NEON_LIGHT = BLOCKS.register("wall_neon_light",
            () -> new WallLightableBlock(15));
    public static final RegistryObject<LightableBlock.LightableDirectionalBlock> PILLAR_LAMP = BLOCKS.register("pillar_lamp",
            () -> new LightableBlock.LightableDirectionalBlock(AbstractBlock.Properties.create(Material.GLASS), 15));
    public static final RegistryObject<LightableBlock.LightableDirectionalBlock> FLOOD_LIGHT = BLOCKS.register("flood_light", FloodLightBlock::new);
    //barbed wire
    public static final RegistryObject<BarbedWireMultiBlock> BARBED_WIRE = BLOCKS.register("barbed_wire", BarbedWireMultiBlock::new);

    //BLOCKS ITEMS
    public static final RegistryObject<RetainingWallMultiBlockItem> RETAINING_WALL_MULTI_BLOCK_ITEM = ITEMS.register("retaining_wall",
            () -> new RetainingWallMultiBlockItem(HUGE_RETAINING_WALL.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<RetainingWallLadderMultiBlockItem> RETAINING_WALL_LADDER_MULTI_BLOCK_ITEM = ITEMS.register("retaining_wall_ladder",
            () -> new RetainingWallLadderMultiBlockItem(HUGE_RETAINING_WALL_LADDER.get(), DEFAULT_PROPERTIES));
    // platform default item
    public static final RegistryObject<BlockItem> SP_COMMON_ITEM = ITEMS.register("platform_common",
            () -> new BlockItem(SP_COMMON.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> SP_GUIDE_BRICK_ITEM = ITEMS.register("platform_guide_brick",
            () -> new BlockItem(SP_GUIDE_BRICK.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> SP_GUIDE_BRICK_LINE_ITEM = ITEMS.register("platform_guide_brick_line",
            () -> new BlockItem(SP_GUIDE_BRICK_LINE.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> SP_LINE_ITEM = ITEMS.register("platform_line",
            () -> new BlockItem(SP_LINE.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> SP_ASPHALT_ITEM = ITEMS.register("platform_asphalt",
            () -> new BlockItem(SP_ASPHALT.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> SP_ON_ITEM = ITEMS.register("platform_on",
            () -> new BlockItem(SP_ON.get(), DEFAULT_PROPERTIES.group(null)));
    public static final RegistryObject<BlockItem> SP_OFF_ITEM = ITEMS.register("platform_off",
            () -> new BlockItem(SP_OFF.get(), DEFAULT_PROPERTIES.group(Eki.ekiItemGroup)));
    // opf platform item
    public static final RegistryObject<StationPlatformOPFMultiBlockItem> SP_COMMON_OPF_ITEM = ITEMS.register("platform_common_opf",
            () -> new StationPlatformOPFMultiBlockItem(SP_COMMON_OPF.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<StationPlatformOPFMultiBlockItem> SP_GUIDE_BRICK_OPF_ITEM = ITEMS.register("platform_guide_brick_opf",
            () -> new StationPlatformOPFMultiBlockItem(SP_GUIDE_BRICK_OPF.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<StationPlatformOPFMultiBlockItem> SP_GUIDE_BRICK_LINE_OPF_ITEM = ITEMS.register("platform_guide_brick_line_opf",
            () -> new StationPlatformOPFMultiBlockItem(SP_GUIDE_BRICK_LINE_OPF.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<StationPlatformOPFMultiBlockItem> SP_LINE_OPF_ITEM = ITEMS.register("platform_line_opf",
            () -> new StationPlatformOPFMultiBlockItem(SP_LINE_OPF.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<StationPlatformOPFMultiBlockItem> SP_ASPHALT_OPF_ITEM = ITEMS.register("platform_asphalt_opf",
            () -> new StationPlatformOPFMultiBlockItem(SP_ASPHALT_OPF.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<StationPlatformOPFMultiBlockItem> SP_ON_OPF_ITEM = ITEMS.register("platform_on_opf",
            () -> new StationPlatformOPFMultiBlockItem(SP_ON_OPF.get(), DEFAULT_PROPERTIES.group(null)));
    public static final RegistryObject<StationPlatformOPFMultiBlockItem> SP_OFF_OPF_ITEM = ITEMS.register("platform_off_opf",
            () -> new StationPlatformOPFMultiBlockItem(SP_OFF_OPF.get(), DEFAULT_PROPERTIES.group(Eki.ekiItemGroup)));
    // subblock
    public static final RegistryObject<BlockItem>[] ENAMEL_WALL_ITEMS = new RegistryObject[EnamelWallType.values.length * EnamelWallPositionType.values.length];
    public static final RegistryObject<BlockItem>[] TESSERA_ITEMS = new RegistryObject[TesseraWallType.values.length];
    // station stairs
    public static final RegistryObject<BlockItem> STATION_STAIR_ITEM = ITEMS.register("station_stairs",
            () -> new BlockItem(STATION_STAIR.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> STATION_STAIR_GB_ITEM = ITEMS.register("station_stairs_gentle_bottom",
            () -> new BlockItem(STATION_STAIR_GB.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> STATION_STAIR_GT_ITEM = ITEMS.register("station_stairs_gentle_top",
            () -> new BlockItem(STATION_STAIR_GT.get(), DEFAULT_PROPERTIES));
    // handrail
    public static final RegistryObject<BlockItem> HANDRAIL_ITEM = ITEMS.register("handrail",
            () -> new BlockItem(HANDRAIL.get(), DEFAULT_PROPERTIES));
    // pillars
    public static final RegistryObject<BlockItem>[] STATION_PILLAR_ITEMS = new RegistryObject[StationPillarType.values.length];
    public static final RegistryObject<BlockItem> STATION_PILLAR_FE_ITEM = ITEMS.register("station_pillar_with_fire_extinguisher",
            () -> new BlockItem(STATION_PILLAR_FE.get(), DEFAULT_PROPERTIES));
    //lights
    public static final RegistryObject<BlockItem>[] GRILLE_ITEMS = new RegistryObject[TesseraWallType.values.length];
    public static final RegistryObject<BlockItem>[] EMBEDDED_GRILLE_ITEMS = new RegistryObject[TesseraWallType.values.length];
    public static final RegistryObject<BlockItem> TUNNEL_LIGHT_ITEM = ITEMS.register("tunnel_light",
            () -> new BlockItem(TUNNEL_LIGHT.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> WALL_NEON_LIGHT_ITEM = ITEMS.register("wall_neon_light",
            () -> new BlockItem(WALL_NEON_LIGHT.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> PILLAR_LAMP_ITEM = ITEMS.register("pillar_lamp",
            () -> new BlockItem(PILLAR_LAMP.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> FLOOD_LIGHT_ITEM = ITEMS.register("flood_light",
            () -> new BlockItem(FLOOD_LIGHT.get(), DEFAULT_PROPERTIES));
    //barbed wire
    public static final RegistryObject<BarbedWireMultiBlockItem> BARBED_WIRE_BUNDLE_ITEM = ITEMS.register("barbed_wire_set",
            () -> new BarbedWireMultiBlockItem(BARBED_WIRE.get(), DEFAULT_PROPERTIES));

    //ITEMS
    public static final RegistryObject<RangeFinderItem> RANGE_FINDER = ITEMS.register("rangefinder", RangeFinderItem::new);

    static {
        final UtilSubblockRegistry usrBuilding = new UtilSubblockRegistry(BuildingBaseBlock::new, DEFAULT_PROPERTIES);
        usrBuilding.registerSubblocksWithTwoEnum(
                ENAMEL_WALLS,
                BLOCKS,
                ENAMEL_WALL_ITEMS,
                ITEMS,
                "enamel_wall",
                EnamelWallType.class,
                EnamelWallPositionType.class);

        usrBuilding.registerSubblocksWithOneEnum(
                TESSERA,
                BLOCKS,
                TESSERA_ITEMS,
                ITEMS,
                "tessera",
                TesseraWallType.class);

        usrBuilding.registerSubblocksWithOneEnum(
                STATION_PILLARS,
                BLOCKS,
                STATION_PILLAR_ITEMS,
                ITEMS,
                "station_pillar",
                StationPillarType.class);

        final UtilSubblockRegistry usrHorizontalLightable = new UtilSubblockRegistry(
                () -> new LightableBlock.LightableHorizontalBlock(AbstractBlock.Properties.create(Material.REDSTONE_LIGHT), 15) {
                    @Override
                    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
                        return VoxelShapes.empty();
                    }
                }, DEFAULT_PROPERTIES);

        usrHorizontalLightable.registerSubblocksWithOneEnum(
                GRILLE_LIGHTS,
                BLOCKS,
                GRILLE_ITEMS,
                ITEMS,
                "grille_light",
                BaseColorType.class);

        usrHorizontalLightable.registerSubblocksWithOneEnum(
                EMBEDDED_GRILLE_LIGHTS,
                BLOCKS,
                EMBEDDED_GRILLE_ITEMS,
                ITEMS,
                "embedded_grille_light",
                BaseColorType.class);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}
