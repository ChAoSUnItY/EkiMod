package com.chaos.eki.objects.blocks;

import com.chaos.eki.utils.handler.RegistryHandler;
import com.chaos.eki_lib.objects.blocks.base.LightableBlock;
import com.chaos.eki_lib.utils.util.voxel_shapes.HorizontalVoxelShapes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class FloodLightBlock extends LightableBlock.LightableDirectionalBlock {
    public static final VoxelShape[] UP_DOWN_SHAPE = {
            Block.makeCuboidShape(5.0, 0.0, 4.0, 11.0, 4.0, 12.0),
            Block.makeCuboidShape(5.0, 12.0, 4.0, 11.0, 16.0, 12.0)
    };
    public static final HorizontalVoxelShapes SIDE_SHAPES = new HorizontalVoxelShapes(
            Block.makeCuboidShape(5.0, 5.65, 10.2, 11.0, 13.45, 16.0),
            Block.makeCuboidShape(5.0, 5.65, 0.0, 11.0, 13.45, 5.8),
            Block.makeCuboidShape(0.0, 5.65, 5.0, 5.8, 13.45, 11.0),
            Block.makeCuboidShape(10.2, 5.65, 5.0, 16.0, 13.45, 11.0)
    );

    public FloodLightBlock() {
        super(RegistryHandler.DEFAULT_BLOCK_PROPERTIES , 15);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case UP:
                return UP_DOWN_SHAPE[0];
            case DOWN:
                return UP_DOWN_SHAPE[1];
            default:
                return SIDE_SHAPES.getByDirection(state.get(FACING));
        }
    }
}
