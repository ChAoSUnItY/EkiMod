package com.chaos.eki.objects.blocks.multiblock;

import com.chaos.eki.utils.handler.RegistryHandler;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BarbedWireMultiBlock extends HorizontalBaseBlock {
    public static final EnumProperty<BarbedWireType> TYPES = EnumProperty.create("type", BarbedWireType.class);
    public static final VoxelShape[] SHAPES = new VoxelShape[] {
            Block.makeCuboidShape(0.0, 0.0, 7.0, 16.0, 16.0, 9.0),
            Block.makeCuboidShape(7.0, 0.0, 0.0, 9.0, 16.0, 16.0)
    };

    public BarbedWireMultiBlock() {
        super(RegistryHandler.DEFAULT_BLOCK_PROPERTIES);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction dir = state.get(FACING);
        return dir == Direction.NORTH || dir == Direction.SOUTH ? SHAPES[0] : SHAPES[1];
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(TYPES));
    }

    public enum BarbedWireType implements IStringSerializable {
        BOTTOM("bottom"), CENTER("center"), TOP("top");
        public static final BarbedWireType[] values = values();

        public final String name;

        BarbedWireType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
