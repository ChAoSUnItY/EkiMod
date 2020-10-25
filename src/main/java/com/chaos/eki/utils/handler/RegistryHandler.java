package com.chaos.eki.utils.handler;

import com.chaos.eki.Eki;
import com.chaos.eki.objects.blocks.multiblock.RetainingWallMultiBlock;
import com.chaos.eki.objects.items.RetainingWallMultiBlockItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    public static final Item.Properties DEFAULT_PROPERTIES = com.chaos.eki_lib.utils.handlers.RegistryHandler.DEFAULT_ITEM_BLOCK_PROPERTIES.group(Eki.ekiItemGroup);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Eki.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Eki.MOD_ID);

    //BLOCKS
    //public static final RegistryObject<RetainingWallBlock> RETAINING_WALL = BLOCKS.register("retaining_wall", () -> new RetainingWallBlock(RetainingWallBlock.WallType.STRAIGHT));
    //public static final RegistryObject<RetainingWallBlock> RETAINING_WALL_CORNER = BLOCKS.register("retaining_wall_corner", () -> new RetainingWallBlock(RetainingWallBlock.WallType.CORNER));
    public static final RegistryObject<RetainingWallMultiBlock> HUGE_RETAINING_WALL = BLOCKS.register("huge_retaining_wall", RetainingWallMultiBlock::new);

    //BLOCKS ITEMS
    //public static final RegistryObject<BlockItem> RETAINING_WALL_ITEM = ITEMS.register("retaining_wall", () -> new BlockItem(RETAINING_WALL.get(), DEFAULT_PROPERTIES));
    //public static final RegistryObject<BlockItem> RETAINING_WALL_CORNER_ITEM = ITEMS.register("retaining_wall_corner", () -> new BlockItem(RETAINING_WALL_CORNER.get(), DEFAULT_PROPERTIES));
    public static final RegistryObject<RetainingWallMultiBlockItem> RETAINING_WALL_MULTI_BLOCK_ITEM = ITEMS.register("huge_retaining_wall", () -> new RetainingWallMultiBlockItem(HUGE_RETAINING_WALL.get(), DEFAULT_PROPERTIES));
}
