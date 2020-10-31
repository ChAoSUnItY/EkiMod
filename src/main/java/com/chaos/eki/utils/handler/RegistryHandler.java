package com.chaos.eki.utils.handler;

import com.chaos.eki.Eki;
import com.chaos.eki.objects.blocks.BuildingBaseBlock;
import com.chaos.eki.objects.blocks.SideSlabBlock;
import com.chaos.eki.objects.blocks.multiblock.RetainingWallMultiBlock;
import com.chaos.eki.objects.blocks.multiblock.StationPlatformMultiBlock;
import com.chaos.eki.objects.items.RetainingWallMultiBlockItem;
import com.chaos.eki.objects.items.StationPlatformOPFMultiBlockItem;
import com.chaos.eki_lib.objects.blocks.base.BaseBlock;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    public static final AbstractBlock.Properties DEFAULT_BLOCK_PROPERTIES = AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5f);
    public static final Item.Properties DEFAULT_PROPERTIES = com.chaos.eki_lib.utils.handlers.RegistryHandler.DEFAULT_ITEM_BLOCK_PROPERTIES.group(Eki.ekiItemGroup);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Eki.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Eki.MOD_ID);

    //BLOCKS
    //public static final RegistryObject<RetainingWallBlock> RETAINING_WALL = BLOCKS.register("retaining_wall", () -> new RetainingWallBlock(RetainingWallBlock.WallType.STRAIGHT));
    //public static final RegistryObject<RetainingWallBlock> RETAINING_WALL_CORNER = BLOCKS.register("retaining_wall_corner", () -> new RetainingWallBlock(RetainingWallBlock.WallType.CORNER));
    public static final RegistryObject<RetainingWallMultiBlock> HUGE_RETAINING_WALL = BLOCKS.register("huge_retaining_wall", RetainingWallMultiBlock::new);
    public static final RegistryObject<BuildingBaseBlock> CONCRETE = BLOCKS.register("concrete", BuildingBaseBlock::new);
    public static final RegistryObject<StairsBlock> CONCRETE_STAIRS = BLOCKS.register("concrete_stairs",
            () -> new StairsBlock(() -> CONCRETE.get().getDefaultState(), DEFAULT_BLOCK_PROPERTIES));
    public static final RegistryObject<SlabBlock> CONCRETE_SLAB = BLOCKS.register("concrete_slab",
            () -> new SlabBlock(DEFAULT_BLOCK_PROPERTIES));
    public static final RegistryObject<BuildingBaseBlock> ARCHITECTURAL_CONCRETE = BLOCKS.register("architectural_concrete", BuildingBaseBlock::new);
    public static final RegistryObject<StairsBlock> ARCHITECTURAL_CONCRETE_STAIRS = BLOCKS.register("architectural_concrete_stairs",
            () -> new StairsBlock(() -> ARCHITECTURAL_CONCRETE.get().getDefaultState(), DEFAULT_BLOCK_PROPERTIES));
    public static final RegistryObject<SlabBlock> ARCHITECTURAL_CONCRETE_SLAB = BLOCKS.register("architectural_concrete_slab",
            () -> new SlabBlock(DEFAULT_BLOCK_PROPERTIES));
    public static final RegistryObject<BuildingBaseBlock> ASPHALT = BLOCKS.register("asphalt", BuildingBaseBlock::new);
    public static final RegistryObject<StairsBlock> ASPHALT_STAIRS = BLOCKS.register("asphalt_stairs",
            () -> new StairsBlock(() -> ASPHALT.get().getDefaultState(), DEFAULT_BLOCK_PROPERTIES));
    public static final RegistryObject<SlabBlock> ASPHALT_SLAB = BLOCKS.register("asphalt_slab",
            () -> new SlabBlock(DEFAULT_BLOCK_PROPERTIES));
    public static final RegistryObject<BuildingBaseBlock> NICHOGAKE = BLOCKS.register("nichogake", BuildingBaseBlock::new);
    public static final RegistryObject<StairsBlock> NICHOGAKE_STAIRS = BLOCKS.register("nichogake_stairs",
            () -> new StairsBlock(() -> NICHOGAKE.get().getDefaultState(), DEFAULT_BLOCK_PROPERTIES));
    public static final RegistryObject<SlabBlock> NICHOGAKE_SLAB = BLOCKS.register("nichogake_slab",
            () -> new SlabBlock(DEFAULT_BLOCK_PROPERTIES));
    public static final RegistryObject<BuildingBaseBlock> WASHED_GRANOLITHIC_FINISH = BLOCKS.register("washed_granolithic_finish", BuildingBaseBlock::new);
    public static final RegistryObject<StairsBlock> WASHED_GRANOLITHIC_FINISH_STAIRS = BLOCKS.register("washed_granolithic_finish_stairs",
            () -> new StairsBlock(() -> WASHED_GRANOLITHIC_FINISH.get().getDefaultState(), DEFAULT_BLOCK_PROPERTIES));
    public static final RegistryObject<SlabBlock> WASHED_GRANOLITHIC_FINISH_SLAB = BLOCKS.register("washed_granolithic_finish_slab",
            () -> new SlabBlock(DEFAULT_BLOCK_PROPERTIES));

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
            () -> new StationPlatformMultiBlock(false, true ,false));
    // opf platforms
    public static final RegistryObject<SideSlabBlock.PowerableSideSlab<HorizontalBaseBlock>> SP_OPF_BASE = BLOCKS.register("platform_opf_base",
            () -> new SideSlabBlock.PowerableSideSlab(CONCRETE));
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
            () -> new StationPlatformMultiBlock(true, true ,false));

    //BLOCKS ITEMS
    //public static final RegistryObject<BlockItem> RETAINING_WALL_ITEM = ITEMS.register("retaining_wall", () -> new BlockItem(RETAINING_WALL.get(), DEFAULT_PROPERTIES));
    //public static final RegistryObject<BlockItem> RETAINING_WALL_CORNER_ITEM = ITEMS.register("retaining_wall_corner", () -> new BlockItem(RETAINING_WALL_CORNER.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<RetainingWallMultiBlockItem> RETAINING_WALL_MULTI_BLOCK_ITEM = ITEMS.register("huge_retaining_wall",
            () -> new RetainingWallMultiBlockItem(HUGE_RETAINING_WALL.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> CONCRETE_ITEM = ITEMS.register("concrete",
            () -> new BlockItem(CONCRETE.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> CONCRETE_STAIRS_ITEM = ITEMS.register("concrete_stairs",
            () -> new BlockItem(CONCRETE_STAIRS.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> CONCRETE_SLAB_ITEM = ITEMS.register("concrete_slab",
            () -> new BlockItem(CONCRETE_SLAB.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> ARCHITECTURAL_CONCRETE_ITEM = ITEMS.register("architectural_concrete",
            () -> new BlockItem(ARCHITECTURAL_CONCRETE.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> ARCHITECTURAL_CONCRETE_STAIRS_ITEM = ITEMS.register("architectural_concrete_stairs",
            () -> new BlockItem(ARCHITECTURAL_CONCRETE_STAIRS.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> ARCHITECTURAL_CONCRETE_SLAB_ITEM = ITEMS.register("architectural_concrete_slab",
            () -> new BlockItem(ARCHITECTURAL_CONCRETE_SLAB.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> ASPHALT_ITEM = ITEMS.register("asphalt",
            () -> new BlockItem(ASPHALT.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> ASPHALT_STAIRS_ITEM = ITEMS.register("asphalt_stairs",
            () -> new BlockItem(ASPHALT_STAIRS.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> ASPHALT_ISLAB_ITEM = ITEMS.register("asphalt_slab",
            () -> new BlockItem(ASPHALT_SLAB.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> NICHOGAKE_ITEM = ITEMS.register("nichogake",
            () -> new BlockItem(NICHOGAKE.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> NICHOGAKE_STAIRS_ITEM = ITEMS.register("nichogake_stairs",
            () -> new BlockItem(NICHOGAKE_STAIRS.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> NICHOGAKE_SLAB_ITEM = ITEMS.register("nichogake_slab",
            () -> new BlockItem(NICHOGAKE_SLAB.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> WASHED_GRANOLITHIC_FINISH_ITEM = ITEMS.register("washed_granolithic_finish",
            () -> new BlockItem(WASHED_GRANOLITHIC_FINISH.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> WASHED_GRANOLITHIC_FINISH_STAIRS_ITEM = ITEMS.register("washed_granolithic_finish_stairs",
            () -> new BlockItem(WASHED_GRANOLITHIC_FINISH_STAIRS.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<BlockItem> WASHED_GRANOLITHIC_FINISH_SLAB_ITEM = ITEMS.register("washed_granolithic_finish_slab",
            () -> new BlockItem(WASHED_GRANOLITHIC_FINISH_SLAB.get(), DEFAULT_PROPERTIES));
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
}
