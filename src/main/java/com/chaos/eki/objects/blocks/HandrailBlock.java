package com.chaos.eki.objects.blocks;

import com.chaos.eki_lib.utils.util.voxel_shapes.HorizontalVoxelShapes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class HandrailBlock extends StairsBlock {
    public static final HorizontalVoxelShapes[] COLLISION_SHAPES = new HorizontalVoxelShapes[] {
            new HorizontalVoxelShapes(
                    Block.makeCuboidShape(0.0, 0.0, 13.0, 16.0, 24.0, 16.0),
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 24.0, 3.0),
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 3.0, 24.0, 16.0),
                    Block.makeCuboidShape(13.0, 0.0, 0.0, 16.0, 24.0, 16.0)
            ),
            new HorizontalVoxelShapes(
                    VoxelShapes.or(
                            Block.makeCuboidShape(13.0, 0.0, 3.0, 16.0, 24.0, 16.0),
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 24.0, 3.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 3.0, 24.0, 13.0),
                            Block.makeCuboidShape(0.0, 0.0, 13.0, 16.0, 24.0, 16.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(0.0, 0.0, 13.0, 13.0, 24.0, 16.0),
                            Block.makeCuboidShape(13.0, 0.0, 0.0, 16.0, 24.0, 16.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(3.0, 0.0, 0.0, 16.0, 24.0, 3.0),
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 3.0, 24.0, 16.0)
                    )
            ),
            new HorizontalVoxelShapes(
                    Block.makeCuboidShape(0.0, 0.0, 13.0, 3.0, 24.0, 16.0),
                    Block.makeCuboidShape(13.0, 0.0, 0.0, 16.0, 24.0, 3.0),
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 3.0, 24.0, 3.0),
                    Block.makeCuboidShape(13.0, 0.0, 13.0, 16.0, 24.0, 16.0)
            )
    };

    public static final HorizontalVoxelShapes[] SELECTION_SHAPES = new HorizontalVoxelShapes[] {
            new HorizontalVoxelShapes(
                    Block.makeCuboidShape(0.0, 0.0, 13.0, 16.0, 16.0, 16.0),
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 3.0),
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 3.0, 16.0, 16.0),
                    Block.makeCuboidShape(13.0, 0.0, 0.0, 16.0, 16.0, 16.0)
            ),
            new HorizontalVoxelShapes(
                    VoxelShapes.or(
                            Block.makeCuboidShape(13.0, 0.0, 3.0, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 3.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 3.0, 16.0, 13.0),
                            Block.makeCuboidShape(0.0, 0.0, 13.0, 16.0, 16.0, 16.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(0.0, 0.0, 13.0, 13.0, 16.0, 16.0),
                            Block.makeCuboidShape(13.0, 0.0, 0.0, 16.0, 16.0, 16.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(3.0, 0.0, 0.0, 16.0, 16.0, 3.0),
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 3.0, 16.0, 16.0)
                    )
            ),
            new HorizontalVoxelShapes(
                    Block.makeCuboidShape(0.0, 0.0, 13.0, 3.0, 16.0, 16.0),
                    Block.makeCuboidShape(13.0, 0.0, 0.0, 16.0, 16.0, 3.0),
                    Block.makeCuboidShape(0.0, 0.0, 0.0, 3.0, 16.0, 3.0),
                    Block.makeCuboidShape(13.0, 0.0, 13.0, 16.0, 16.0, 16.0)
            )
    };

    public HandrailBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return getCorrectShapeFromDirection(state, COLLISION_SHAPES);
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return getCollisionShape(state, reader, pos, context);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return getCorrectShapeFromDirection(state, SELECTION_SHAPES);
    }

    private VoxelShape getCorrectShapeFromDirection(BlockState state, HorizontalVoxelShapes[] shapes) {
        Direction dir = state.get(FACING).getOpposite();
        StairsShape shape = state.get(SHAPE);
        switch (shape) {
            case STRAIGHT:
                return shapes[0].getByDirection(dir);
            case INNER_LEFT:
            case INNER_RIGHT:
                return shapes[1].getByDirection(shape == StairsShape.INNER_LEFT ? dir.rotateY() : dir.getOpposite());
            case OUTER_LEFT:
            case OUTER_RIGHT:
            default:
                return shapes[2].getByDirection(shape == StairsShape.OUTER_LEFT ? dir.rotateYCCW() : dir);
        }
    }
}
