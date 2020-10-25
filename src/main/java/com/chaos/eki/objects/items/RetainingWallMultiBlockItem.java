package com.chaos.eki.objects.items;

import com.chaos.eki.objects.blocks.multiblock.RetainingWallMultiBlock;
import com.chaos.eki.utils.handler.RegistryHandler;
import com.chaos.eki.utils.util.TriFunction;
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
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class RetainingWallMultiBlockItem extends BlockItem {
    public RetainingWallMultiBlockItem(Block blockIn, Properties builder) {
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
        Direction dir = context.getNearestLookingDirection();
        BlockPos pos = context.getPos();
        DirectionProperty dirProp = HorizontalBaseBlock.FACING;
        EnumProperty<RetainingWallMultiBlock.RetainingWallMultiBlockType> types = RetainingWallMultiBlock.TYPES;
        BlockState newState = RegistryHandler.HUGE_RETAINING_WALL.get().getDefaultState().with(dirProp, context.getPlacementHorizontalFacing().getOpposite());
        World world = context.getWorld();
        switch (dir) {
            case NORTH:
                return setupMultiBlock(world, pos, newState, (p, x ,y) -> p.east(x).up(y));
            case SOUTH:
                return setupMultiBlock(world, pos, newState, (p, x ,y) -> p.west(x).up(y));
            case EAST:
                return setupMultiBlock(world, pos, newState, (p, x ,y) -> p.south(x).up(y));
            case WEST:
                return  setupMultiBlock(world, pos, newState, (p, x ,y) -> p.north(x).up(y));
            default:
                return false;
        }
    }

    @Nonnull
    private boolean setupMultiBlock(World world, BlockPos pos, BlockState state, TriFunction<BlockPos, Integer, Integer, BlockPos> posFunction) {
        boolean flag = false;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                BlockPos targetPos = posFunction.apply(pos, j, i);
                if (world.isAirBlock(targetPos))
                    flag = world.setBlockState(targetPos,
                            state.with(RetainingWallMultiBlock.TYPES, RetainingWallMultiBlock.RetainingWallMultiBlockType.VALUES[j + i * 2]));
            }
        return flag;
    }
}
