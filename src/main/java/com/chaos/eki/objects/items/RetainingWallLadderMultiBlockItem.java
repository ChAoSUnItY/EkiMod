package com.chaos.eki.objects.items;

import com.chaos.eki.objects.blocks.multiblock.RetainingWallLadderMultiBlock;
import com.chaos.eki.utils.handler.RegistryHandler;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.world.World;

public class RetainingWallLadderMultiBlockItem extends MultiBlockItemBase {
    public RetainingWallLadderMultiBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
        boolean flag = false;
        BlockState newState = RegistryHandler.HUGE_RETAINING_WALL_LADDER
                .get()
                .getDefaultState()
                .with(HorizontalBaseBlock.FACING, context.getPlacementHorizontalFacing().getOpposite());
        World world = context.getWorld();
        for (int i = 0; i < 2; i++) {
            if (world.getBlockState(context.getPos().up(i)).getCollisionShape(world, context.getPos().up(i)).isEmpty() ||
                    world.getBlockState(context.getPos().up(i)).getBlockHardness(world, context.getPos().up(i)) == 0.0F)
                flag |= world.setBlockState(
                        context.getPos().up(i),
                        newState.with(
                                RetainingWallLadderMultiBlock.TYPES,
                                RetainingWallLadderMultiBlock.RetainingWallLadderType.values[i])
                );
        }
        return flag;
    }
}
