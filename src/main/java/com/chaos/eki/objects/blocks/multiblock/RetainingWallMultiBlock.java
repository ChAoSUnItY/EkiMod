package com.chaos.eki.objects.blocks.multiblock;

import com.chaos.eki.utils.util.TriFunction;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import com.chaos.eki_lib.utils.util.voxel_shapes.HorizontalVoxelShapes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.stream.Stream;

public class RetainingWallMultiBlock extends HorizontalBaseBlock {
    public static final EnumProperty<RetainingWallMultiBlockType> TYPES = EnumProperty.create("type", RetainingWallMultiBlockType.class);

    public static final HorizontalVoxelShapes[] SHAPES = new HorizontalVoxelShapes[]{
            new HorizontalVoxelShapes(
                    Stream.of(
                            Block.makeCuboidShape(0.0, 0.0, 8.0, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(0.0, 3.0, 5.0, 16.0, 19.75, 7.0),
                            Block.makeCuboidShape(0.0, 0.0, 0.47, 16.0, 1.5, 8.0),
                            Block.makeCuboidShape(13.0, 3.0, 4.0, 16.0, 20.0, 5.0),
                            Block.makeCuboidShape(0.0, 1.5, 3.73, 16.0, 6.75, 8.0),
                            Block.makeCuboidShape(0.0, 6.5, 5.8, 16.0, 11.75, 8.0)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 8.0),
                            Block.makeCuboidShape(0.0, 3.0, 9.0, 16.0, 19.75, 11.0),
                            Block.makeCuboidShape(0.0, 0.0, 8.0, 16.0, 1.5, 15.53),
                            Block.makeCuboidShape(0.0, 3.0, 11.0, 3.0, 20.0, 12.0),
                            Block.makeCuboidShape(0.0, 1.5, 8.0, 16.0, 6.75, 12.27),
                            Block.makeCuboidShape(0.0, 6.5, 8.0, 16.0, 11.75, 10.2)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 8.0, 16.0, 16.0),
                            Block.makeCuboidShape(9.0, 3.0, 0.0, 11.0, 19.75, 16.0),
                            Block.makeCuboidShape(8.0, 0.0, 0.0, 15.53, 1.5, 16.0),
                            Block.makeCuboidShape(11.0, 3.0, 13.0, 12.0, 20.0, 16.0),
                            Block.makeCuboidShape(8.0, 1.5, 0.0, 12.27, 6.75, 16.0),
                            Block.makeCuboidShape(8.0, 6.5, 0.0, 10.2, 11.75, 16.0)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(8.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(5.0, 3.0, 0.0, 7.0, 19.75, 16.0),
                            Block.makeCuboidShape(0.47, 0.0, 0.0, 8.0, 1.5, 16.0),
                            Block.makeCuboidShape(4.0, 3.0, 0.0, 5.0, 20.0, 3.0),
                            Block.makeCuboidShape(3.73, 1.5, 0.0, 8.0, 6.75, 16.0),
                            Block.makeCuboidShape(5.8, 6.5, 0.0, 8.0, 11.75, 16.0)
                    )),
            new HorizontalVoxelShapes(
                    Stream.of(
                            Block.makeCuboidShape(0.0, 0.0, 8.0, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(0.0, 3.0, 5.0, 16.0, 19.75, 7.0),
                            Block.makeCuboidShape(0.0, 0.0, 0.47, 16.0, 1.5, 8.0),
                            Block.makeCuboidShape(0.0, 3.0, 4.0, 3.0, 20.0, 5.0),
                            Block.makeCuboidShape(0.0, 1.5, 3.73, 16.0, 6.75, 8.0),
                            Block.makeCuboidShape(0.0, 6.5, 5.8, 16.0, 11.75, 8.0)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 8.0),
                            Block.makeCuboidShape(0.0, 3.0, 9.0, 16.0, 19.75, 11.0),
                            Block.makeCuboidShape(0.0, 0.0, 8.0, 16.0, 1.5, 15.53),
                            Block.makeCuboidShape(13.0, 3.0, 11.0, 16.0, 20.0, 12.0),
                            Block.makeCuboidShape(0.0, 1.5, 8.0, 16.0, 6.75, 12.27),
                            Block.makeCuboidShape(0.0, 6.5, 8.0, 16.0, 11.75, 10.2)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 8.0, 16.0, 16.0),
                            Block.makeCuboidShape(9.0, 3.0, 0.0, 11.0, 19.75, 16.0),
                            Block.makeCuboidShape(8.0, 0.0, 0.0, 15.53, 1.5, 16.0),
                            Block.makeCuboidShape(11.0, 3.0, 0.0, 12.0, 20.0, 3.0),
                            Block.makeCuboidShape(8.0, 1.5, 0.0, 12.27, 6.75, 16.0),
                            Block.makeCuboidShape(8.0, 6.5, 0.0, 10.2, 11.75, 16.0)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(8.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(5.0, 3.0, 0.0, 7.0, 19.75, 16.0),
                            Block.makeCuboidShape(0.47, 0.0, 0.0, 8.0, 1.5, 16.0),
                            Block.makeCuboidShape(4.0, 3.0, 13.0, 5.0, 20.0, 16.0),
                            Block.makeCuboidShape(3.73, 1.5, 0.0, 8.0, 6.75, 16.0),
                            Block.makeCuboidShape(5.8, 6.5, 0.0, 8.0, 11.75, 16.0)
                    )),
            new HorizontalVoxelShapes(
                    Stream.of(
                            Block.makeCuboidShape(13.0, -1.0, 9.0, 16.0, 16.0, 10.0),
                            Block.makeCuboidShape(0.0, 0.0, 13.25, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(0.0, 2.25, 11.32, 16.0, 16.0, 13.32),
                            Block.makeCuboidShape(0.0, 12.0, 11.5, 16.0, 16.0, 14.0),
                            Block.makeCuboidShape(0.0, 0.0, 9.5, 16.0, 4.5, 13.25),
                            Block.makeCuboidShape(0.0, 4.5, 11.5, 16.0, 9.0, 13.25)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(0.0, -1.0, 6.0, 3.0, 16.0, 7.0),
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 2.75),
                            Block.makeCuboidShape(0.0, 2.25, 2.68, 16.0, 16.0, 4.68),
                            Block.makeCuboidShape(0.0, 12.0, 2.0, 16.0, 16.0, 4.5),
                            Block.makeCuboidShape(0.0, 0.0, 2.75, 16.0, 4.5, 6.5),
                            Block.makeCuboidShape(0.0, 4.5, 2.75, 16.0, 9.0, 4.5)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(6.0, -1.0, 13.0, 7.0, 16.0, 16.0),
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 2.75, 16.0, 16.0),
                            Block.makeCuboidShape(2.68, 2.25, 0.0, 4.68, 16.0, 16.0),
                            Block.makeCuboidShape(2.0, 12.0, 0.0, 4.5, 16.0, 16.0),
                            Block.makeCuboidShape(2.75, 0.0, 0.0, 6.5, 4.5, 16.0),
                            Block.makeCuboidShape(2.75, 4.5, 0.0, 4.5, 9.0, 16.0)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(9.0, -1.0, 0.0, 10.0, 16.0, 3.0),
                            Block.makeCuboidShape(13.25, 0.0, 0.0, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(11.32, 2.25, 0.0, 13.32, 16.0, 16.0),
                            Block.makeCuboidShape(11.5, 12.0, 0.0, 14.0, 16.0, 16.0),
                            Block.makeCuboidShape(9.5, 0.0, 0.0, 13.25, 4.5, 16.0),
                            Block.makeCuboidShape(11.5, 4.5, 0.0, 13.25, 9.0, 16.0)
                    )),
            new HorizontalVoxelShapes(
                    Stream.of(
                            Block.makeCuboidShape(0.0, -1.0, 9.0, 3.0, 16.0, 10.0),
                            Block.makeCuboidShape(0.0, 0.0, 13.25, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(0.0, 2.25, 11.32, 16.0, 16.0, 13.32),
                            Block.makeCuboidShape(0.0, 12.0, 11.5, 16.0, 16.0, 14.0),
                            Block.makeCuboidShape(0.0, 0.0, 9.5, 16.0, 4.5, 13.25),
                            Block.makeCuboidShape(0.0, 4.5, 11.5, 16.0, 9.0, 13.25)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(13.0, -1.0, 6.0, 16.0, 16.0, 7.0),
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 2.75),
                            Block.makeCuboidShape(0.0, 2.25, 2.68, 16.0, 16.0, 4.68),
                            Block.makeCuboidShape(0.0, 12.0, 2.0, 16.0, 16.0, 4.5),
                            Block.makeCuboidShape(0.0, 0.0, 2.75, 16.0, 4.5, 6.5),
                            Block.makeCuboidShape(0.0, 4.5, 2.75, 16.0, 9.0, 4.5)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(6.0, -1.0, 0.0, 7.0, 16.0, 3.0),
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 2.75, 16.0, 16.0),
                            Block.makeCuboidShape(2.68, 2.25, 0.0, 4.68, 16.0, 16.0),
                            Block.makeCuboidShape(2.0, 12.0, 0.0, 4.5, 16.0, 16.0),
                            Block.makeCuboidShape(2.75, 0.0, 0.0, 6.5, 4.5, 16.0),
                            Block.makeCuboidShape(2.75, 4.5, 0.0, 4.5, 9.0, 16.0)
                    ),
                    Stream.of(
                            Block.makeCuboidShape(9.0, -1.0, 13.0, 10.0, 16.0, 16.0),
                            Block.makeCuboidShape(13.25, 0.0, 0.0, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(11.32, 2.25, 0.0, 13.32, 16.0, 16.0),
                            Block.makeCuboidShape(11.5, 12.0, 0.0, 14.0, 16.0, 16.0),
                            Block.makeCuboidShape(9.5, 0.0, 0.0, 13.25, 4.5, 16.0),
                            Block.makeCuboidShape(11.5, 4.5, 0.0, 13.25, 9.0, 16.0)
                    ))
    };

    public RetainingWallMultiBlock() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .hardnessAndResistance(5f)
                .harvestTool(ToolType.PICKAXE)
                .setRequiresTool());

        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(TYPES, RetainingWallMultiBlockType.DL));
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        switch (state.get(TYPES)) {
            case DL:
                switch (state.get(FACING)) {
                    case NORTH:
                        removeBlock(worldIn, pos, (p, x, y) -> p.west(x).up(y));
                        break;
                    case SOUTH:
                        removeBlock(worldIn, pos, (p, x, y) -> p.east(x).up(y));
                        break;
                    case EAST:
                        removeBlock(worldIn, pos, (p, x, y) -> p.north(x).up(y));
                        break;
                    case WEST:
                        removeBlock(worldIn, pos, (p, x, y) -> p.south(x).up(y));
                        break;
                }
                break;
            case DR:
                switch (state.get(FACING)) {
                    case NORTH:
                        removeBlock(worldIn, pos, (p, x, y) -> p.east(x).up(y));
                        break;
                    case SOUTH:
                        removeBlock(worldIn, pos, (p, x, y) -> p.west(x).up(y));
                        break;
                    case EAST:
                        removeBlock(worldIn, pos, (p, x, y) -> p.south(x).up(y));
                        break;
                    case WEST:
                        removeBlock(worldIn, pos, (p, x, y) -> p.north(x).up(y));
                        break;
                }
                break;
            case UL:
                switch (state.get(FACING)) {
                    case NORTH:
                        removeBlock(worldIn, pos, (p, x, y) -> p.west(x).down(y));
                        break;
                    case SOUTH:
                        removeBlock(worldIn, pos, (p, x, y) -> p.east(x).down(y));
                        break;
                    case EAST:
                        removeBlock(worldIn, pos, (p, x, y) -> p.north(x).down(y));
                        break;
                    case WEST:
                        removeBlock(worldIn, pos, (p, x, y) -> p.south(x).down(y));
                        break;
                }
                break;
            case UR:
                switch (state.get(FACING)) {
                    case NORTH:
                        removeBlock(worldIn, pos, (p, x, y) -> p.east(x).down(y));
                        break;
                    case SOUTH:
                        removeBlock(worldIn, pos, (p, x, y) -> p.west(x).down(y));
                        break;
                    case EAST:
                        removeBlock(worldIn, pos, (p, x, y) -> p.south(x).down(y));
                        break;
                    case WEST:
                        removeBlock(worldIn, pos, (p, x, y) -> p.north(x).down(y));
                        break;
                }
                break;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction facing = state.get(FACING);
        switch (state.get(TYPES)) {
            case DL:
                return SHAPES[0].getByDirection(facing);
            case DR:
                return SHAPES[1].getByDirection(facing);
            case UL:
                return SHAPES[2].getByDirection(facing);
            case UR:
            default:
                return SHAPES[3].getByDirection(facing);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(TYPES);
    }

    private void removeBlock(World world, BlockPos pos, TriFunction<BlockPos, Integer, Integer, BlockPos> blockPosFunction) {
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                BlockPos targetPos = blockPosFunction.apply(pos, j, i);
                if (isPart(world, targetPos))
                    removeBlock(world, targetPos);
            }
    }

    private boolean isPart(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() instanceof RetainingWallMultiBlock;
    }

    private void removeBlock(World world, BlockPos pos) {
        world.removeBlock(pos, false);
    }

    public enum RetainingWallMultiBlockType implements IStringSerializable {
        DL("down_left"), DR("down_right"), UL("up_left"), UR("up_right");

        public static final RetainingWallMultiBlockType[] VALUES = values();
        public final String name;

        RetainingWallMultiBlockType(String name) {
            this.name = name;
        }

        @Override
        public String getString() {
            return this.name;
        }
    }
}
