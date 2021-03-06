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
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class RetainingWallMultiBlockItem extends MultiBlockItemBase {
    public RetainingWallMultiBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Nonnull
    @Override
    protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
        BlockPos pos = context.getPos();
        BlockState newState = RegistryHandler.HUGE_RETAINING_WALL
                .get()
                .getDefaultState()
                .with(HorizontalBaseBlock.FACING, context.getPlacementHorizontalFacing().getOpposite());
        World world = context.getWorld();
        switch (context.getPlacementHorizontalFacing()) {
            case NORTH:
                return setupMultiBlock(world, pos, newState, (p, x, y) -> p.east(x).up(y));
            case SOUTH:
                return setupMultiBlock(world, pos, newState, (p, x, y) -> p.west(x).up(y));
            case EAST:
                return setupMultiBlock(world, pos, newState, (p, x, y) -> p.south(x).up(y));
            case WEST:
                return setupMultiBlock(world, pos, newState, (p, x, y) -> p.north(x).up(y));
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
                if (world.getBlockState(targetPos).getCollisionShape(world, targetPos).isEmpty() ||
                        world.getBlockState(targetPos).getBlockHardness(world, targetPos) == 0.0F)
                    flag |= world.setBlockState(targetPos,
                            state.with(RetainingWallMultiBlock.TYPES, RetainingWallMultiBlock.RetainingWallMultiBlockType.VALUES[j + i * 2]));
            }
        return flag;
    }
}
