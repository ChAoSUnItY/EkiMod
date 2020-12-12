package com.chaos.eki.objects.blocks.stairs;

import com.chaos.eki.utils.handler.RegistryHandler;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import com.chaos.eki_lib.utils.util.voxel_shapes.HorizontalVoxelShapes;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class StationStairBlock extends HorizontalBaseBlock {
    public static final HorizontalVoxelShapes BASE_SHAPE = new HorizontalVoxelShapes(
            Stream.of(
                    Block.makeCuboidShape(0.0, 8.0, 8.0, 16.0, 12.0, 16.0),
                    Block.makeCuboidShape(0.0, 12.0, 12.0, 16.0, 16.0, 16.0),
                    Block.makeCuboidShape(0.0, 4.0, 4.0, 16.0, 8.0, 16.0),
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0)
            ),
            Stream.of(
                    Block.makeCuboidShape(0.0, 8.0, 0.0, 16.0, 12.0, 8.0),
                    Block.makeCuboidShape(0.0, 12.0, 0.0, 16.0, 16.0, 4.0),
                    Block.makeCuboidShape(0.0, 4.0, 0.0, 16.0, 8.0, 12.0),
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0)
            ),
            Stream.of(
                    Block.makeCuboidShape(0.0, 8.0, 0.0, 8.0, 12.0, 16.0),
                    Block.makeCuboidShape(0.0, 12.0, 0.0, 4.0, 16.0, 16.0),
                    Block.makeCuboidShape(0.0, 4.0, 0.0, 12.0, 8.0, 16.0),
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0)
            ),
            Stream.of(
                    Block.makeCuboidShape(8.0, 8.0, 0.0, 16.0, 12.0, 16.0),
                    Block.makeCuboidShape(12.0, 12.0, 0.0, 16.0, 16.0, 16.0),
                    Block.makeCuboidShape(4.0, 4.0, 0.0, 16.0, 8.0, 16.0),
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0)
            )
    );
    protected static final VoxelShape BOTTOM_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape TOP_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public final int type;

    public StationStairBlock(int type) {
        super(Block.Properties.from(RegistryHandler.CONCRETE_COL.getBaseBlock().getBlock().get()));
        this.type = type;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (type) {
            case 1:
                return BOTTOM_SHAPE;
            case 2:
                return TOP_SHAPE;
            case 0:
            default:
                return BASE_SHAPE.getByDirection(state.get(FACING));
        }
    }
}
