package com.chaos.eki.utils.handler;

import com.chaos.eki.Eki;
import com.chaos.eki.objects.blocks.BuildingBaseBlock;
import com.chaos.eki.objects.blocks.multiblock.RetainingWallMultiBlock;
import com.chaos.eki.objects.items.RetainingWallMultiBlockItem;
import com.chaos.eki_lib.objects.blocks.base.BaseBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
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
    public static final RegistryObject<StairsBlock> ARCHITECTURAL_CONCRETE_STAIRS = BLOCKS.register("concrete_stairs",
            () -> new StairsBlock(() -> ARCHITECTURAL_CONCRETE.get().getDefaultState(), DEFAULT_BLOCK_PROPERTIES));
    public static final RegistryObject<SlabBlock> ARCHITECTURAL_CONCRETE_SLAB = BLOCKS.register("concrete_slab",
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
}
