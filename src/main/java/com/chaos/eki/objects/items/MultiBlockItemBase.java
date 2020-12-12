package com.chaos.eki.objects.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MultiBlockItemBase extends BlockItem {
    public MultiBlockItemBase(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public ActionResultType tryPlace(BlockItemUseContext context) {
        if (!context.canPlace()) {
            return ActionResultType.FAIL;
        } else {
            BlockItemUseContext ctx = this.getBlockItemUseContext(context);
            if (ctx == null) {
                return ActionResultType.FAIL;
            } else {
                BlockState blockstate = this.getStateForPlacement(ctx);
                if (blockstate == null) {
                    return ActionResultType.FAIL;
                } else if (!this.placeBlock(ctx, blockstate)) {
                    return ActionResultType.FAIL;
                } else {
                    BlockPos blockpos = ctx.getPos();
                    World world = ctx.getWorld();
                    PlayerEntity playerentity = ctx.getPlayer();
                    ItemStack itemstack = ctx.getItem();
                    BlockState state = world.getBlockState(blockpos);
                    Block block = state.getBlock();
                    if (block == blockstate.getBlock()) {
                        this.onBlockPlaced(blockpos, world, playerentity, itemstack, state);
                        block.onBlockPlacedBy(world, blockpos, state, playerentity, itemstack);
                        if (playerentity instanceof ServerPlayerEntity) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerentity, blockpos, itemstack);
                        }
                    }

                    SoundType soundtype = state.getSoundType(world, blockpos, context.getPlayer());
                    world.playSound(playerentity, blockpos, this.getPlaceSound(state, world, blockpos, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    if (playerentity == null || !playerentity.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }

                    return ActionResultType.SUCCESS;
                }
            }
        }
    }
}
