package com.chaos.eki.objects.blocks;

import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import com.chaos.eki_lib.utils.util.voxel_shapes.HorizontalVoxelShapes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import java.util.stream.Stream;

public class RetainingWallBlock extends HorizontalBaseBlock {
    public final WallType type;

    public static final HorizontalVoxelShapes STRAIGHT_SHAPES = new HorizontalVoxelShapes(
            Stream.of(
                    Block.makeCuboidShape(0, 0, 2, 16, 16, 16),
                    Block.makeCuboidShape(14.5, 0, 0, 16, 16, 2),
                    Block.makeCuboidShape(0, 0, 0, 1.5, 16, 2),
                    Block.makeCuboidShape(1.5, 9.5, 0, 14.5, 16, 2),
                    Block.makeCuboidShape(14.5, 14, -2, 16, 16, 0),
                    Block.makeCuboidShape(14.5, 0, -2, 16, 2, 0),
                    Block.makeCuboidShape(0, 14, -2, 1.5, 16, 0),
                    Block.makeCuboidShape(0, 0, -2, 1.5, 2, 0),
                    Block.makeCuboidShape(1.5, 0, 0, 14.5, 0.1, 2)
            ),
            Stream.of(
                    Block.makeCuboidShape(0, 0, 0, 16, 16, 14),
                    Block.makeCuboidShape(0, 0, 14, 1.5, 16, 16),
                    Block.makeCuboidShape(14.5, 0, 14, 16, 16, 16),
                    Block.makeCuboidShape(1.5, 9.5, 14, 14.5, 16, 16),
                    Block.makeCuboidShape(0, 14, 16, 1.5, 16, 18),
                    Block.makeCuboidShape(0, 0, 16, 1.5, 2, 18),
                    Block.makeCuboidShape(14.5, 14, 16, 16, 16, 18),
                    Block.makeCuboidShape(14.5, 0, 16, 16, 2, 18),
                    Block.makeCuboidShape(1.5, 0, 14, 14.5, 0.1, 16)
            ),
            Stream.of(
                    Block.makeCuboidShape(0, 0, 0, 14, 16, 16),
                    Block.makeCuboidShape(14, 0, 14.5, 16, 16, 16),
                    Block.makeCuboidShape(14, 0, 0, 16, 16, 1.5),
                    Block.makeCuboidShape(14, 9.5, 1.5, 16, 16, 14.5),
                    Block.makeCuboidShape(16, 14, 14.5, 18, 16, 16),
                    Block.makeCuboidShape(16, 0, 14.5, 18, 2, 16),
                    Block.makeCuboidShape(16, 14, 0, 18, 16, 1.5),
                    Block.makeCuboidShape(16, 0, 0, 18, 2, 1.5),
                    Block.makeCuboidShape(14, 0, 1.5, 16, 0.1, 14.5)
            ),
            Stream.of(
                    Block.makeCuboidShape(2, 0, 0, 16, 16, 16),
                    Block.makeCuboidShape(0, 0, 0, 2, 16, 1.5),
                    Block.makeCuboidShape(0, 0, 14.5, 2, 16, 16),
                    Block.makeCuboidShape(0, 9.5, 1.5, 2, 16, 14.5),
                    Block.makeCuboidShape(-2, 14, 0, 0, 16, 1.5),
                    Block.makeCuboidShape(-2, 0, 0, 0, 2, 1.5),
                    Block.makeCuboidShape(-2, 14, 14.5, 0, 16, 16),
                    Block.makeCuboidShape(-2, 0, 14.5, 0, 2, 16),
                    Block.makeCuboidShape(0, 0, 1.5, 2, 0.1, 14.5)
            ));

    public static final HorizontalVoxelShapes CORNER_SHAPES = new HorizontalVoxelShapes(
            Stream.of(
                    Block.makeCuboidShape(0, 0, 2, 14, 16, 16),
                    Block.makeCuboidShape(14, 0, 0, 16, 16, 2),
                    Block.makeCuboidShape(0, 0, 0, 1.5, 16, 2),
                    Block.makeCuboidShape(1.5, 9.5, 0, 14, 16, 2),
                    Block.makeCuboidShape(14, 14, -2, 16, 16, 0),
                    Block.makeCuboidShape(14, 0, -2, 16, 2, 0),
                    Block.makeCuboidShape(0, 14, -2, 1.5, 16, 0),
                    Block.makeCuboidShape(0, 0, -2, 1.5, 2, 0),
                    Block.makeCuboidShape(16, 0, 0, 18, 2, 2),
                    Block.makeCuboidShape(16, 14, 0, 18, 16, 2),
                    Block.makeCuboidShape(16, 14, 14.5, 18, 16, 16),
                    Block.makeCuboidShape(16, 0, 14.5, 18, 2, 16),
                    Block.makeCuboidShape(14, 9.5, 2, 16, 16, 14.5),
                    Block.makeCuboidShape(14, 0, 14.5, 16, 16, 16),
                    Block.makeCuboidShape(14, 0, 2, 16, 0.1, 14.5),
                    Block.makeCuboidShape(1.5, 0, 0, 14, 0.1, 2)
            ),
            Stream.of(
                    Block.makeCuboidShape(2, 0, 0, 16, 16, 14),
                    Block.makeCuboidShape(0, 0, 14, 2, 16, 16),
                    Block.makeCuboidShape(14.5, 0, 14, 16, 16, 16),
                    Block.makeCuboidShape(2, 9.5, 14, 14.5, 16, 16),
                    Block.makeCuboidShape(0, 14, 16, 2, 16, 18),
                    Block.makeCuboidShape(0, 0, 16, 2, 2, 18),
                    Block.makeCuboidShape(14.5, 14, 16, 16, 16, 18),
                    Block.makeCuboidShape(14.5, 0, 16, 16, 2, 18),
                    Block.makeCuboidShape(-2, 0, 14, 0, 2, 16),
                    Block.makeCuboidShape(-2, 14, 14, 0, 16, 16),
                    Block.makeCuboidShape(-2, 14, 0, 0, 16, 1.5),
                    Block.makeCuboidShape(-2, 0, 0, 0, 2, 1.5),
                    Block.makeCuboidShape(0, 9.5, 1.5, 2, 16, 14),
                    Block.makeCuboidShape(0, 0, 0, 2, 16, 1.5),
                    Block.makeCuboidShape(0, 0, 1.5, 2, 0.1, 14),
                    Block.makeCuboidShape(2, 0, 14, 14.5, 0.1, 16)
            ),
            Stream.of(
                    Block.makeCuboidShape(0, 0, 0, 14, 16, 14),
                    Block.makeCuboidShape(14, 0, 14, 16, 16, 16),
                    Block.makeCuboidShape(14, 0, 0, 16, 16, 1.5),
                    Block.makeCuboidShape(14, 9.5, 1.5, 16, 16, 14),
                    Block.makeCuboidShape(16, 14, 14, 18, 16, 16),
                    Block.makeCuboidShape(16, 0, 14, 18, 2, 16),
                    Block.makeCuboidShape(16, 14, 0, 18, 16, 1.5),
                    Block.makeCuboidShape(16, 0, 0, 18, 2, 1.5),
                    Block.makeCuboidShape(14, 0, 16, 16, 2, 18),
                    Block.makeCuboidShape(14, 14, 16, 16, 16, 18),
                    Block.makeCuboidShape(0, 14, 16, 1.5, 16, 18),
                    Block.makeCuboidShape(0, 0, 16, 1.5, 2, 18),
                    Block.makeCuboidShape(1.5, 9.5, 14, 14, 16, 16),
                    Block.makeCuboidShape(0, 0, 14, 1.5, 16, 16),
                    Block.makeCuboidShape(1.5, 0, 14, 14, 0.1, 16),
                    Block.makeCuboidShape(14, 0, 1.5, 16, 0.1, 14)
            ),
            Stream.of(
                    Block.makeCuboidShape(2, 0, 2, 16, 16, 16),
                    Block.makeCuboidShape(0, 0, 0, 2, 16, 2),
                    Block.makeCuboidShape(0, 0, 14.5, 2, 16, 16),
                    Block.makeCuboidShape(0, 9.5, 2, 2, 16, 14.5),
                    Block.makeCuboidShape(-2, 14, 0, 0, 16, 2),
                    Block.makeCuboidShape(-2, 0, 0, 0, 2, 2),
                    Block.makeCuboidShape(-2, 14, 14.5, 0, 16, 16),
                    Block.makeCuboidShape(-2, 0, 14.5, 0, 2, 16),
                    Block.makeCuboidShape(0, 0, -2, 2, 2, 0),
                    Block.makeCuboidShape(0, 14, -2, 2, 16, 0),
                    Block.makeCuboidShape(14.5, 14, -2, 16, 16, 0),
                    Block.makeCuboidShape(14.5, 0, -2, 16, 2, 0),
                    Block.makeCuboidShape(2, 9.5, 0, 14.5, 16, 2),
                    Block.makeCuboidShape(14.5, 0, 0, 16, 16, 2),
                    Block.makeCuboidShape(2, 0, 0, 14.5, 0.1, 2),
                    Block.makeCuboidShape(0, 0, 2, 2, 0.1, 14.5)
            ));

    public RetainingWallBlock(WallType type) {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .hardnessAndResistance(5f)
                .harvestTool(ToolType.PICKAXE)
                .setRequiresTool());
        this.type = type;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (type) {
            case STRAIGHT:
                switch (state.get(FACING)) {
                    case SOUTH:
                        return STRAIGHT_SHAPES.getSouth();
                    case EAST:
                        return STRAIGHT_SHAPES.getEast();
                    case WEST:
                        return STRAIGHT_SHAPES.getWest();
                    case NORTH:
                    default:
                        return STRAIGHT_SHAPES.getNorth();
                }
            case CORNER:
            default:
                switch (state.get(FACING)) {
                    case SOUTH:
                        return CORNER_SHAPES.getSouth();
                    case EAST:
                        return CORNER_SHAPES.getEast();
                    case WEST:
                        return CORNER_SHAPES.getWest();
                    case NORTH:
                    default:
                        return CORNER_SHAPES.getNorth();
                }
        }
    }

    public enum WallType implements IStringSerializable {
        STRAIGHT("straight"), CORNER("corner");

        private String name;

        WallType(String name) {
            this.name = name;
        }

        @Override
        public String getString() {
            return this.name;
        }
    }
}
