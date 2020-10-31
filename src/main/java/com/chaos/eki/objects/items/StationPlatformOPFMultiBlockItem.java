package com.chaos.eki.objects.items;

import com.chaos.eki.utils.handler.RegistryHandler;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
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
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class StationPlatformOPFMultiBlockItem extends BlockItem {
    public StationPlatformOPFMultiBlockItem(Block blockIn, Properties builder) {
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

                    return ActionResultType.func_233537_a_(world.isRemote);
                }
            }
        }
    }

    @Nonnull
    @Override
    protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
        BlockPos pos = context.getPos();
        Direction dir = context.getPlacementHorizontalFacing().getOpposite();
        BlockState newStateBase =
                RegistryHandler.SP_OPF_BASE.get().getDefaultState().with(HorizontalBaseBlock.FACING, dir),
                newStatePlatform = state.with(HorizontalBaseBlock.FACING, dir);
        World world = context.getWorld();
        return world.isAirBlock(pos.up()) ? world.setBlockState(pos, newStateBase, 11) && world.setBlockState(pos.up(), newStatePlatform, 11) : false;
    }
}
