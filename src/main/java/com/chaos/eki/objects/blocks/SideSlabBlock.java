package com.chaos.eki.objects.blocks;

import com.chaos.eki.objects.blocks.multiblock.StationPlatformMultiBlock;
import com.chaos.eki_lib.objects.blocks.base.BaseBlock;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import com.chaos.eki_lib.utils.util.voxel_shapes.HorizontalVoxelShapes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneDiodeBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;

import java.util.Random;

public class SideSlabBlock<T extends Block> extends HorizontalBaseBlock {
    public static final HorizontalVoxelShapes SHAPES = new HorizontalVoxelShapes(
            Block.makeCuboidShape(0.0, 0.0, 8.0, 16.0, 16.0, 16.0),
            Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 8.0),
            Block.makeCuboidShape(0.0, 0.0, 0.0, 8.0, 16.0, 16.0),
            Block.makeCuboidShape(8.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    );

    public SideSlabBlock(RegistryObject<T> parentBlock) {
        super(AbstractBlock.Properties.from(parentBlock.get()));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.getByDirection(state.get(FACING));
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock())
            return;

        if (worldIn.getBlockState(pos.up()).getBlock() instanceof StationPlatformMultiBlock) {
            StationPlatformMultiBlock SPblk = (StationPlatformMultiBlock) worldIn.getBlockState(pos.up()).getBlock();
            if (SPblk.type == StationPlatformMultiBlock.PlatformType.OPF_FLOOR)
                worldIn.removeBlock(pos.up(), false);
        }
    }

    public static class PowerableSideSlab<T extends Block> extends SideSlabBlock<T> {
        public static final BooleanProperty POWERED = RedstoneDiodeBlock.POWERED;

        public PowerableSideSlab(RegistryObject<T> parentBlock) {
            super(parentBlock);
            this.setDefaultState(this.getDefaultState().with(POWERED, false));
        }

        @Override
        public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
            return blockState.getWeakPower(blockAccess, pos, side);
        }

        @Override
        public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
            return blockState.get(POWERED) && side == Direction.DOWN ? 15 : 0; // provides power only to top
        }

        @Override
        public boolean canProvidePower(BlockState state) {
            return true;
        }

        @Override
        public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
            if (!worldIn.isRemote)
                if (state.get(POWERED) != worldIn.isBlockPowered(pos))
                    if (state.get(POWERED))
                        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4, TickPriority.EXTREMELY_HIGH);
                    else
                        worldIn.setBlockState(pos, state.with(POWERED, true), 2);
        }

        @Override
        public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
            if (state.get(POWERED) && !worldIn.isBlockPowered(pos))
                worldIn.setBlockState(pos, state.with(POWERED, false));
        }

        @Override
        protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
            super.fillStateContainer(builder);
            builder.add(POWERED);
        }
    }
}
