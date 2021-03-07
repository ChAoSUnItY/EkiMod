package chaos.mod.objects.block.backport;

import chaos.mod.Eki;
import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.objects.block.backport.item.ImmersiveRetainingWallLadderBlockItem;
import chaos.mod.util.interfaces.IModelRegister;
import chaos.mod.util.utils.AABBTransformer;
import chaos.mod.util.utils.UtilTranslatable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ImmersiveRetainingWallLadderBlock extends Block implements IModelRegister {
    public static final PropertyEnum<EnumFacing> FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<ImmersiveRetainingWallLadderType> TYPE = PropertyEnum.create("type", ImmersiveRetainingWallLadderType.class);

    public static final AxisAlignedBB[] DOWN_N_AABB = new AABBTransformer[]{
            new AABBTransformer(0.0, 0.0, 4.0, 16.0, 16.0, 16.0),
            new AABBTransformer(0.0, 0.0, 1.0, 16.0, 8.0, 4.0)
    };
    public static final AxisAlignedBB[] DOWN_S_AABB = new AABBTransformer[]{
            new AABBTransformer(0.0, 0.0, 0.0, 16.0, 16.0, 12.0),
            new AABBTransformer(0.0, 0.0, 12.0, 16.0, 8.0, 15.0)
    };
    public static final AxisAlignedBB[] DOWN_E_AABB = new AABBTransformer[]{
            new AABBTransformer(0.0, 0.0, 0.0, 12.0, 16.0, 16.0),
            new AABBTransformer(12.0, 0.0, 0.0, 15.0, 8.0, 16.0)
    };
    public static final AxisAlignedBB[] DOWN_W_AABB = new AABBTransformer[]{
            new AABBTransformer(4.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            new AABBTransformer(1.0, 0.0, 0.0, 4.0, 8.0, 16.0)
    };

    public static final AxisAlignedBB[] UP_N_AABB = new AABBTransformer[]{
            new AABBTransformer(0.0, 0.0, 12.0, 16.0, 16.0, 16.0),
            new AABBTransformer(0.0, 0.0, 8.0, 16.0, 8.0, 12.0)
    };
    public static final AxisAlignedBB[] UP_S_AABB = new AABBTransformer[]{
            new AABBTransformer(0.0, 0.0, 0.0, 16.0, 16.0, 4.0),
            new AABBTransformer(0.0, 0.0, 4.0, 16.0, 8.0, 8.0)
    };
    public static final AxisAlignedBB[] UP_E_AABB = new AABBTransformer[]{
            new AABBTransformer(0.0, 0.0, 0.0, 4.0, 16.0, 16.0),
            new AABBTransformer(4.0, 0.0, 0.0, 8.0, 8.0, 16.0)
    };
    public static final AxisAlignedBB[] UP_W_AABB = new AABBTransformer[]{
            new AABBTransformer(12.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            new AABBTransformer(8.0, 0.0, 0.0, 12.0, 8.0, 16.0)
    };

    public ImmersiveRetainingWallLadderBlock() {
        super(Material.ROCK);
        setUnlocalizedName(UtilTranslatable.getEki("immersive_retaining_wall_ladder"));
        setRegistryName("immersive_retaining_wall_ladder");
        setCreativeTab(Eki.BLOCK);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ImmersiveRetainingWallLadderBlockItem(this).setRegistryName(getRegistryName()));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        for (AxisAlignedBB aabb : getAABB(state))
            addCollisionBoxToList(pos, entityBox, collidingBoxes, aabb);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
        if (state.getValue(TYPE) == ImmersiveRetainingWallLadderType.UP && worldIn.getBlockState(pos.down()).getBlock() instanceof ImmersiveRetainingWallLadderBlock) {
            worldIn.setBlockToAir(pos.down());
        } else if (state.getValue(TYPE) == ImmersiveRetainingWallLadderType.DOWN && worldIn.getBlockState(pos.up()).getBlock() instanceof ImmersiveRetainingWallLadderBlock) {
            worldIn.setBlockToAir(pos.up());
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, TYPE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int r = 0;
        r += state.getValue(FACING).getHorizontalIndex() * 2;
        r += state.getValue(TYPE).ordinal();

        return r;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta / 4))
                .withProperty(TYPE, ImmersiveRetainingWallLadderType.VALUES[meta % 2]);
    }

    private AxisAlignedBB[] getAABB(IBlockState state) {
        EnumFacing facing = state.getValue(FACING);
        ImmersiveRetainingWallLadderType type = state.getValue(TYPE);

        switch (type) {
            case DOWN:
                switch (facing) {
                    case NORTH:
                        return DOWN_N_AABB;
                    case SOUTH:
                        return DOWN_S_AABB;
                    case EAST:
                        return DOWN_E_AABB;
                    case WEST:
                        return DOWN_W_AABB;
                }
            case UP:
                switch (facing) {
                    case NORTH:
                        return UP_N_AABB;
                    case SOUTH:
                        return UP_S_AABB;
                    case EAST:
                        return UP_E_AABB;
                    case WEST:
                        return UP_W_AABB;
                }
        }

        return new AxisAlignedBB[0];
    }

    public enum ImmersiveRetainingWallLadderType implements IStringSerializable {
        DOWN("down"), UP("up");

        public static final ImmersiveRetainingWallLadderType[] VALUES = values();
        private final String type;

        ImmersiveRetainingWallLadderType(String type) {
            this.type = type;
        }

        @Override
        public String getName() {
            return type;
        }
    }
}
