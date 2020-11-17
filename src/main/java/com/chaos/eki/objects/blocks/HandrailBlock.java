package com.chaos.eki.objects.blocks;

import com.chaos.eki.utils.handler.RegistryHandler;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import com.chaos.eki_lib.utils.util.voxel_shapes.HorizontalVoxelShapes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class HandrailBlock extends HorizontalBaseBlock {
    public static final HorizontalVoxelShapes SHAPES = new HorizontalVoxelShapes(
            Stream.of(
                    Block.makeCuboidShape(15.5, 0.0, 14.5, 16.0, 16.0, 16.0),
                    Block.makeCuboidShape(0.0, 0.0, 14.5, 0.5, 16.0, 16.0),
                    Block.makeCuboidShape(15.5, 15.52164, 12.85195, 16.0, 17.67164, 14.25195),
                    Block.makeCuboidShape(0.0, 15.52164, 12.85195, 0.5, 17.67164, 14.25195),
                    Block.makeCuboidShape(15.5, 15.77164, 12.50195, 16.0, 17.27164, 14.60195),
                    Block.makeCuboidShape(0.0, 15.77164, 12.50195, 0.5, 17.27164, 14.60195),
                    Block.makeCuboidShape(0.5, 15.75, 13.25, 15.5, 17.4, 13.9),
                    Block.makeCuboidShape(0.5, 16.0, 12.9, 15.5, 17.25, 14.15),
                    Block.makeCuboidShape(0.5, 16.25, 12.75, 15.5, 16.9, 14.4),
                    Block.makeCuboidShape(0.5, 0.0, 14.5, 15.5, 0.5, 16.0),
                    Block.makeCuboidShape(0.5, 3.9, 14.9, 15.5, 4.6, 15.6),
                    Block.makeCuboidShape(0.5, 7.9, 14.9, 15.5, 8.6, 15.6),
                    Block.makeCuboidShape(0.5, 11.9, 14.9, 15.5, 12.6, 15.6)
            ),
            Stream.of(
                    Block.makeCuboidShape(15.5, 0.0, 0.0, 16.0, 16.0, 1.5),
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 0.5, 16.0, 1.5),
                    Block.makeCuboidShape(15.5, 15.52164, 1.74805, 16.0, 17.67164, 3.14805),
                    Block.makeCuboidShape(0.0, 15.52164, 1.74805, 0.5, 17.67164, 3.14805),
                    Block.makeCuboidShape(15.5, 15.77164, 1.39805, 16.0, 17.27164, 3.49805),
                    Block.makeCuboidShape(0.0, 15.77164, 1.39805, 0.5, 17.27164, 3.49805),
                    Block.makeCuboidShape(0.5, 15.75, 2.1, 15.5, 17.4, 2.75),
                    Block.makeCuboidShape(0.5, 16.0, 1.85, 15.5, 17.25, 3.1),
                    Block.makeCuboidShape(0.5, 16.25, 1.6, 15.5, 16.9, 3.25),
                    Block.makeCuboidShape(0.5, 0.0, 0.0, 15.5, 0.5, 1.5),
                    Block.makeCuboidShape(0.5, 3.9, 0.4, 15.5, 4.6, 1.1),
                    Block.makeCuboidShape(0.5, 7.9, 0.4, 15.5, 8.6, 1.1),
                    Block.makeCuboidShape(0.5, 11.9, 0.4, 15.5, 12.6, 1.1)
            ),
            Stream.of(
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 1.5, 16.0, 0.5),
                    Block.makeCuboidShape(0.0, 0.0, 15.5, 1.5, 16.0, 16.0),
                    Block.makeCuboidShape(1.74805, 15.52164, 0.0, 3.14805, 17.67164, 0.5),
                    Block.makeCuboidShape(1.74805, 15.52164, 15.5, 3.14805, 17.67164, 16.0),
                    Block.makeCuboidShape(1.39805, 15.77164, 0.0, 3.49805, 17.27164, 0.5),
                    Block.makeCuboidShape(1.39805, 15.77164, 15.5, 3.49805, 17.27164, 16.0),
                    Block.makeCuboidShape(2.1, 15.75, 0.5, 2.75, 17.4, 15.5),
                    Block.makeCuboidShape(1.85, 16.0, 0.5, 3.1, 17.25, 15.5),
                    Block.makeCuboidShape(1.6, 16.25, 0.5, 3.25, 16.9, 15.5),
                    Block.makeCuboidShape(0.0, 0.0, 0.5, 1.5, 0.5, 15.5),
                    Block.makeCuboidShape(0.4, 3.9, 0.5, 1.1, 4.6, 15.5),
                    Block.makeCuboidShape(0.4, 7.9, 0.5, 1.1, 8.6, 15.5),
                    Block.makeCuboidShape(0.4, 11.9, 0.5, 1.1, 12.6, 15.5)
            ),
            Stream.of(
                    Block.makeCuboidShape(14.5, 0.0, 0.0, 16.0, 16.0, 0.5),
                    Block.makeCuboidShape(14.5, 0.0, 15.5, 16.0, 16.0, 16.0),
                    Block.makeCuboidShape(12.85195, 15.52164, 0.0, 14.25195, 17.67164, 0.5),
                    Block.makeCuboidShape(12.85195, 15.52164, 15.5, 14.25195, 17.67164, 16.0),
                    Block.makeCuboidShape(12.50195, 15.77164, 0.0, 14.60195, 17.27164, 0.5),
                    Block.makeCuboidShape(12.50195, 15.77164, 15.5, 14.60195, 17.27164, 16.0),
                    Block.makeCuboidShape(13.25, 15.75, 0.5, 13.9, 17.4, 15.5),
                    Block.makeCuboidShape(12.9, 16.0, 0.5, 14.15, 17.25, 15.5),
                    Block.makeCuboidShape(12.75, 16.25, 0.5, 14.4, 16.9, 15.5),
                    Block.makeCuboidShape(14.5, 0.0, 0.5, 16.0, 0.5, 15.5),
                    Block.makeCuboidShape(14.9, 3.9, 0.5, 15.6, 4.6, 15.5),
                    Block.makeCuboidShape(14.9, 7.9, 0.5, 15.6, 8.6, 15.5),
                    Block.makeCuboidShape(14.9, 11.9, 0.5, 15.6, 12.6, 15.5)
            )
    );

    public HandrailBlock() {
        super(Properties.from(RegistryHandler.CONCRETE_COL.getBaseBlock().getBlock().get()));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.getByDirection(state.get(FACING));
    }
}
