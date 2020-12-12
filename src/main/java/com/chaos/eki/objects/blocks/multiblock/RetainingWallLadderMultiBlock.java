package com.chaos.eki.objects.blocks.multiblock;

import com.chaos.eki.utils.handler.RegistryHandler;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import com.chaos.eki_lib.utils.util.voxel_shapes.HorizontalVoxelShapes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class RetainingWallLadderMultiBlock extends HorizontalBaseBlock {
    public static final EnumProperty<RetainingWallLadderType> TYPES = EnumProperty.create("type", RetainingWallLadderType.class);

    public static final HorizontalVoxelShapes[] SHAPES = new HorizontalVoxelShapes[]{
            new HorizontalVoxelShapes(
                    VoxelShapes.or(
                            Block.makeCuboidShape(0.0, 0.0, 4.0, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(0.0, 0.0, 1.0, 16.0, 8.0, 4.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 12.0),
                            Block.makeCuboidShape(0.0, 0.0, 12.0, 16.0, 8.0, 15.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 12.0, 16.0, 16.0),
                            Block.makeCuboidShape(12.0, 0.0, 0.0, 15.0, 8.0, 16.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(4.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(1.0, 0.0, 0.0, 4.0, 8.0, 16.0)
                    )
            ),
            new HorizontalVoxelShapes(
                    VoxelShapes.or(
                            Block.makeCuboidShape(0.0, 0.0, 12.0, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(0.0, 0.0, 8.0, 16.0, 8.0, 12.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 4.0),
                            Block.makeCuboidShape(0.0, 0.0, 4.0, 16.0, 8.0, 8.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(0.0, 0.0, 0.0, 4.0, 16.0, 16.0),
                            Block.makeCuboidShape(4.0, 0.0, 0.0, 8.0, 8.0, 16.0)
                    ),
                    VoxelShapes.or(
                            Block.makeCuboidShape(12.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                            Block.makeCuboidShape(8.0, 0.0, 0.0, 12.0, 8.0, 16.0)
                    )
            )
    };

    public RetainingWallLadderMultiBlock() {
        super(RegistryHandler.DEFAULT_BLOCK_PROPERTIES);
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onReplaced(state, worldIn, pos, newState, isMoving);
        if (state.get(TYPES) == RetainingWallLadderType.DOWN && worldIn.getBlockState(pos.up()).getBlock() instanceof RetainingWallLadderMultiBlock)
            worldIn.removeBlock(pos.up(), false);
        else if (state.get(TYPES) == RetainingWallLadderType.UP && worldIn.getBlockState(pos.down()).getBlock() instanceof RetainingWallLadderMultiBlock)
            worldIn.removeBlock(pos.down(), false);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(TYPES)) {
            case DOWN:
                return SHAPES[0].getByDirection(state.get(FACING));
            default:
                return SHAPES[1].getByDirection(state.get(FACING));
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(TYPES));
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return RegistryHandler.RETAINING_WALL_LADDER_MULTI_BLOCK_ITEM.get().getDefaultInstance();
    }

    public enum RetainingWallLadderType implements IStringSerializable {
        DOWN("down"), UP("up");
        public static final RetainingWallLadderType[] values = values();

        public final String name;

        RetainingWallLadderType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
