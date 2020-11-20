package com.chaos.eki.utils.handler;

import com.chaos.eki.Eki;
import com.chaos.eki.objects.blocks.BuildingBaseBlock;
import com.chaos.eki.objects.blocks.HandrailBlock;
import com.chaos.eki.objects.blocks.PowerableSideSlab;
import com.chaos.eki.objects.blocks.multiblock.RetainingWallMultiBlock;
import com.chaos.eki.objects.blocks.multiblock.StationPlatformMultiBlock;
import com.chaos.eki.objects.blocks.stairs.StationStairBlock;
import com.chaos.eki.objects.blocks.subblock.EnamelWallPositionType;
import com.chaos.eki.objects.blocks.subblock.EnamelWallType;
import com.chaos.eki.objects.blocks.subblock.StationPillarType;
import com.chaos.eki.objects.blocks.subblock.TesseraWallType;
import com.chaos.eki.objects.items.RetainingWallMultiBlockItem;
import com.chaos.eki.objects.items.StationPlatformOPFMultiBlockItem;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import com.chaos.eki_lib.utils.util.registry.RegistryCollection;
import com.chaos.eki_lib.utils.util.registry.UtilSubblockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    public static final AbstractBlock.Properties DEFAULT_BLOCK_PROPERTIES = AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5f);
    public static final Item.Properties DEFAULT_PROPERTIES = new Item.Properties().group(Eki.ekiItemGroup);
    public static final UtilSubblockRegistry REGISTRY_HELPER = new UtilSubblockRegistry(BuildingBaseBlock::new, DEFAULT_PROPERTIES);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Eki.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Eki.MOD_ID);

    //BLOCKS
    //public static final RegistryObject<RetainingWallBlock> RETAINING_WALL = BLOCKS.register("retaining_wall", () -> new RetainingWallBlock(RetainingWallBlock.WallType.STRAIGHT));
    //public static final RegistryObject<RetainingWallBlock> RETAINING_WALL_CORNER = BLOCKS.register("retaining_wall_corner", () -> new RetainingWallBlock(RetainingWallBlock.WallType.CORNER));
    public static final RegistryObject<RetainingWallMultiBlock> HUGE_RETAINING_WALL = BLOCKS.register("huge_retaining_wall", RetainingWallMultiBlock::new);
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

    //BLOCKS ITEMS
    //public static final RegistryObject<BlockItem> RETAINING_WALL_ITEM = ITEMS.register("retaining_wall", () -> new BlockItem(RETAINING_WALL.get(), DEFAULT_PROPERTIES));
    //public static final RegistryObject<BlockItem> RETAINING_WALL_CORNER_ITEM = ITEMS.register("retaining_wall_corner", () -> new BlockItem(RETAINING_WALL_CORNER.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<RetainingWallMultiBlockItem> RETAINING_WALL_MULTI_BLOCK_ITEM = ITEMS.register("huge_retaining_wall",
            () -> new RetainingWallMultiBlockItem(HUGE_RETAINING_WALL.get(), DEFAULT_PROPERTIES));
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

    static {
        REGISTRY_HELPER.registerSubblocksWithTwoEnum(
                ENAMEL_WALLS,
                BLOCKS,
                ENAMEL_WALL_ITEMS,
                ITEMS,
                "enamel_wall",
                EnamelWallType.class,
                EnamelWallPositionType.class);

        REGISTRY_HELPER.registerSubblocksWithOneEnum(
                TESSERA,
                BLOCKS,
                TESSERA_ITEMS,
                ITEMS,
                "tessera",
                TesseraWallType.class);

        REGISTRY_HELPER.registerSubblocksWithOneEnum(
                STATION_PILLARS,
                BLOCKS,
                STATION_PILLAR_ITEMS,
                ITEMS,
                "station_pillar",
                StationPillarType.class);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}
