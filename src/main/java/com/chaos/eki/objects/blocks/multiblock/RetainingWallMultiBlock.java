package com.chaos.eki.objects.blocks.multiblock;

import com.chaos.eki.utils.util.TriFunction;
import com.chaos.eki_lib.objects.blocks.SideSlabBlock;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import com.chaos.eki_lib.utils.util.voxel_shapes.HorizontalVoxelShapes;
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

public class RetainingWallMultiBlock extends HorizontalBaseBlock {
    public static final EnumProperty<RetainingWallMultiBlockType> TYPES = EnumProperty.create("type", RetainingWallMultiBlockType.class);

    public static final HorizontalVoxelShapes SHAPE = new HorizontalVoxelShapes(
            Block.makeCuboidShape(0.0, 0.0, 1.0, 16.0, 16.0, 16.0),
            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 15.0),
            Block.makeCuboidShape(0.0, 0.0, 0.0, 15.0, 16.0, 16.0),
            Block.makeCuboidShape(1.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    );

    public RetainingWallMultiBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(5f)
                .harvestTool(ToolType.PICKAXE));

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
            case DR:
                return SHAPE.getByDirection(facing);
            default:
                return SideSlabBlock.SHAPES.getByDirection(facing);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(TYPES));
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
        public String getName() {
            return this.name;
        }
    }
}
