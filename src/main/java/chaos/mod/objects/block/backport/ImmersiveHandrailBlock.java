package chaos.mod.objects.block.backport;

import chaos.mod.Eki;
import chaos.mod.objects.block.base.BlockFourFace;
import chaos.mod.util.utils.AABBTransformer;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.block.BlockStairs.EnumShape;

public class ImmersiveHandrailBlock extends BlockFourFace {
    public static final PropertyEnum<EnumShape> SHAPE = PropertyEnum.create("shape", EnumShape.class);

    public static final AxisAlignedBB STRAIGHT_N_AABB = new AABBTransformer(0.0, 0.0, 13.0, 16.0, 24.0, 16.0);
    public static final AxisAlignedBB STRAIGHT_S_AABB = new AABBTransformer(0.0, 0.0, 0.0, 16.0, 24.0, 3.0);
    public static final AxisAlignedBB STRAIGHT_E_AABB = new AABBTransformer(0.0, 0.0, 0.0, 3.0, 24.0, 16.0);
    public static final AxisAlignedBB STRAIGHT_W_AABB = new AABBTransformer(13.0, 0.0, 0.0, 16.0, 24.0, 16.0);

    public static final AxisAlignedBB[] INNER_N_AABB = new AABBTransformer[] {
            new AABBTransformer(13.0, 0.0, 3.0, 16.0, 24.0, 16.0),
            new AABBTransformer(0.0, 0.0, 0.0, 16.0, 24.0, 3.0)
    };
    public static final AxisAlignedBB[] INNER_S_AABB = new AABBTransformer[] {
            new AABBTransformer(0.0, 0.0, 0.0, 3.0, 24.0, 13.0),
            new AABBTransformer(0.0, 0.0, 13.0, 16.0, 24.0, 16.0)
    };
    public static final AxisAlignedBB[] INNER_E_AABB = new AABBTransformer[] {
            new AABBTransformer(0.0, 0.0, 13.0, 13.0, 24.0, 16.0),
            new AABBTransformer(13.0, 0.0, 0.0, 16.0, 24.0, 16.0)
    };
    public static final AxisAlignedBB[] INNER_W_AABB = new AABBTransformer[] {
            new AABBTransformer(3.0, 0.0, 0.0, 16.0, 24.0, 3.0),
            new AABBTransformer(0.0, 0.0, 0.0, 3.0, 24.0, 16.0)
    };

    public static final AxisAlignedBB OUTER_N_AABB = new AABBTransformer(0.0, 0.0, 13.0, 3.0, 24.0, 16.0);
    public static final AxisAlignedBB OUTER_S_AABB = new AABBTransformer(13.0, 0.0, 0.0, 16.0, 24.0, 3.0);
    public static final AxisAlignedBB OUTER_E_AABB = new AABBTransformer(0.0, 0.0, 0.0, 3.0, 24.0, 3.0);
    public static final AxisAlignedBB OUTER_W_AABB = new AABBTransformer(13.0, 0.0, 13.0, 16.0, 24.0, 16.0);

    public ImmersiveHandrailBlock() {
        super("immersive_handrail", Eki.STATION, Material.WOOD, false);
        useNeighborBrightness = true;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        for (AxisAlignedBB aabb : getAABB(getActualState(state, worldIn, pos)))
            addCollisionBoxToList(pos, entityBox, collidingBoxes, aabb);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        // We don't let BlockFourFace change our block state.
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        // We don't let BlockFourFace change our block state.
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer)
                .withProperty(FACING, placer.getHorizontalFacing())
                .withProperty(SHAPE, EnumShape.STRAIGHT);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(SHAPE, getShape(state, worldIn, pos));
    }

    private EnumShape getShape(IBlockState state, IBlockAccess world, BlockPos pos) {
        EnumFacing facing = state.getValue(FACING);
        IBlockState stateA = world.getBlockState(pos.offset(facing));

        if (isImmersiveHandrail(stateA)) {
            EnumFacing facingA = stateA.getValue(FACING);

            if (facingA.getAxis() != state.getValue(FACING).getAxis()) {
                if (facingA == facing.rotateYCCW())
                    return EnumShape.OUTER_LEFT;

                return EnumShape.OUTER_RIGHT;
            }
        }

        IBlockState stateB = world.getBlockState(pos.offset(facing.getOpposite()));

        if (isImmersiveHandrail(stateB)) {
            EnumFacing facingB = stateB.getValue(FACING);

            if (facingB.getAxis() != state.getValue(FACING).getAxis()) {
                if (facingB == facing.rotateYCCW())
                    return EnumShape.INNER_LEFT;

                return EnumShape.INNER_RIGHT;
            }
        }

        return EnumShape.STRAIGHT;
    }

    private boolean isImmersiveHandrail(IBlockState state) {
        return state.getBlock() instanceof ImmersiveHandrailBlock;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Nullable
    @Override
    public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
        List<RayTraceResult> list = Lists.newArrayList();

        for (AxisAlignedBB aabb : getAABB(getActualState(blockState, worldIn, pos)))
            list.add(rayTrace(pos, start, end, aabb));

        RayTraceResult raytraceresult = null;
        double d1 = 0.0D;

        for (RayTraceResult result : list) {
            if (result != null) {
                double d0 = result.hitVec.squareDistanceTo(end);

                if (d0 > d1) {
                    raytraceresult = result;
                    d1 = d0;
                }
            }
        }

        return raytraceresult;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        EnumFacing enumfacing = state.getValue(FACING);
        BlockStairs.EnumShape blockstairs$enumshape = state.getValue(SHAPE);

        switch (mirrorIn) {
            case LEFT_RIGHT:

                if (enumfacing.getAxis() == EnumFacing.Axis.Z) {
                    switch (blockstairs$enumshape) {
                        case OUTER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_LEFT);
                        case INNER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.INNER_LEFT);
                        case INNER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.INNER_RIGHT);
                        default:
                            return state.withRotation(Rotation.CLOCKWISE_180);
                    }
                }

                break;
            case FRONT_BACK:

                if (enumfacing.getAxis() == EnumFacing.Axis.X) {
                    switch (blockstairs$enumshape) {
                        case OUTER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.OUTER_LEFT);
                        case INNER_RIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.INNER_RIGHT);
                        case INNER_LEFT:
                            return state.withRotation(Rotation.CLOCKWISE_180).withProperty(SHAPE, BlockStairs.EnumShape.INNER_LEFT);
                        case STRAIGHT:
                            return state.withRotation(Rotation.CLOCKWISE_180);
                    }
                }
        }

        return super.withMirror(state, mirrorIn);
    }

    private List<AxisAlignedBB> getAABB(IBlockState state) {
        EnumFacing facing = state.getValue(FACING);
        EnumShape shape = state.getValue(SHAPE);

        switch (shape) {
            case STRAIGHT:
                switch (facing) {
                    case NORTH:
                        return Lists.newArrayList(STRAIGHT_S_AABB);
                    case SOUTH:
                        return Lists.newArrayList(STRAIGHT_N_AABB);
                    case EAST:
                        return Lists.newArrayList(STRAIGHT_W_AABB);
                    case WEST:
                        return Lists.newArrayList(STRAIGHT_E_AABB);
                }
                break;
            case INNER_LEFT:
            case INNER_RIGHT:
                switch (shape == EnumShape.INNER_LEFT ? facing.rotateY() : facing.getOpposite()) {
                    case NORTH:
                        return Lists.newArrayList(INNER_S_AABB);
                    case SOUTH:
                        return Lists.newArrayList(INNER_N_AABB);
                    case EAST:
                        return Lists.newArrayList(INNER_W_AABB);
                    case WEST:
                        return Lists.newArrayList(INNER_E_AABB);
                }
                break;
            case OUTER_LEFT:
            case OUTER_RIGHT:
                switch (shape == EnumShape.OUTER_LEFT ? facing.rotateYCCW() : facing) {
                    case NORTH:
                        return Lists.newArrayList(OUTER_S_AABB);
                    case SOUTH:
                        return Lists.newArrayList(OUTER_N_AABB);
                    case EAST:
                        return Lists.newArrayList(OUTER_W_AABB);
                    case WEST:
                        return Lists.newArrayList(OUTER_E_AABB);
                }
                break;
        }

        return Lists.newArrayList();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, SHAPE);
    }
}
