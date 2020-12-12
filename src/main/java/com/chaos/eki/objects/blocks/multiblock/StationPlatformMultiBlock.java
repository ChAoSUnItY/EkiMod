package com.chaos.eki.objects.blocks.multiblock;

import com.chaos.eki.utils.handler.RegistryHandler;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import com.chaos.eki_lib.utils.util.voxel_shapes.HorizontalVoxelShapes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class StationPlatformMultiBlock extends HorizontalBaseBlock {
    public static final HorizontalVoxelShapes NORMAL_SHAPES = new HorizontalVoxelShapes(
            VoxelShapes.combineAndSimplify(Block.makeCuboidShape(0, 0, 8, 16, 16, 16),
                    Block.makeCuboidShape(0, 13.5, 0, 16, 16, 8),
                    IBooleanFunction.OR),
            VoxelShapes.combineAndSimplify(Block.makeCuboidShape(0, 0, 0, 16, 16, 8),
                    Block.makeCuboidShape(0, 13.5, 8, 16, 16, 16),
                    IBooleanFunction.OR),
            VoxelShapes.combineAndSimplify(Block.makeCuboidShape(0, 0, 0, 8, 16, 16),
                    Block.makeCuboidShape(8, 13.5, 0, 16, 16, 16),
                    IBooleanFunction.OR),
            VoxelShapes.combineAndSimplify(Block.makeCuboidShape(8, 0, 0, 16, 16, 16),
                    Block.makeCuboidShape(0, 13.5, 0, 8, 16, 16),
                    IBooleanFunction.OR)
    );
    public static final HorizontalVoxelShapes OPF_SHAPES = new HorizontalVoxelShapes(
            VoxelShapes.combineAndSimplify(Block.makeCuboidShape(0, 5.5, 0, 16, 8, 8),
                    Block.makeCuboidShape(0, 0, 8, 16, 8, 16),
                    IBooleanFunction.OR),
            VoxelShapes.combineAndSimplify(Block.makeCuboidShape(0, 5.5, 8, 16, 8, 16),
                    Block.makeCuboidShape(0, 0, 0, 16, 8, 8),
                    IBooleanFunction.OR),
            VoxelShapes.combineAndSimplify(Block.makeCuboidShape(8, 5.5, 0, 16, 8, 16),
                    Block.makeCuboidShape(0, 0, 0, 8, 8, 16),
                    IBooleanFunction.OR),
            VoxelShapes.combineAndSimplify(Block.makeCuboidShape(0, 5.5, 0, 8, 8, 16),
                    Block.makeCuboidShape(8, 0, 0, 16, 8, 16),
                    IBooleanFunction.OR)
    );
    public final PlatformType type;
    public final boolean lightable;
    public final boolean isOn;

    public StationPlatformMultiBlock(boolean isOPF) {
        this(isOPF, false, false);
    }

    public StationPlatformMultiBlock(boolean isOPF, boolean lightable, boolean isOn) {
        super(Block.Properties.from(RegistryHandler.CONCRETE_COL.getBaseBlock().getBlock().get()));
        this.type = isOPF ? PlatformType.OPF_FLOOR : PlatformType.NON_OPF_FLOOR;
        this.lightable = lightable;
        this.isOn = isOn;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return lightable ? 12 : 0;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!(newState.getBlock() instanceof StationPlatformMultiBlock))
            switch (type) {
                case OPF_FLOOR:
                    worldIn.removeBlock(pos.down(), false);
                    break;
                case NON_OPF_FLOOR:
                default:
                    // no need to remove further more.
            }
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote)
            if (lightable && isOn != worldIn.isBlockPowered(pos))
                if (isOn)
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                else
                    worldIn.setBlockState(pos, getMatchState(state, true), 2);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (isOn && !worldIn.isBlockPowered(pos))
            worldIn.setBlockState(pos, getMatchState(state, false), 2);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (type) {
            case NON_OPF_FLOOR:
                return NORMAL_SHAPES.getByDirection(state.get(FACING));
            case OPF_FLOOR:
            default:
                return OPF_SHAPES.getByDirection(state.get(FACING));
        }
    }

    private BlockState getMatchState(BlockState state, boolean lit) {
        return type == PlatformType.OPF_FLOOR ?
                (lit ?
                        RegistryHandler.SP_ON_OPF.get().getDefaultState().with(FACING, state.get(FACING)) :
                        RegistryHandler.SP_OFF_OPF.get().getDefaultState().with(FACING, state.get(FACING))
                ) :
                (lit ?
                        RegistryHandler.SP_ON.get().getDefaultState().with(FACING, state.get(FACING)) :
                        RegistryHandler.SP_OFF.get().getDefaultState().with(FACING, state.get(FACING))
                );
    }

    public enum PlatformType {
        NON_OPF_FLOOR, OPF_FLOOR
    }
}
