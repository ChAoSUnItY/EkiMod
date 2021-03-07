package chaos.mod.objects.block.backport;

import chaos.mod.Eki;
import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.objects.block.backport.item.ImmersiveRetainingWallBlockItem;
import chaos.mod.objects.block.slabs.BlockSideSlab;
import chaos.mod.util.interfaces.IModelRegister;
import chaos.mod.util.interfaces.TriFunction;
import chaos.mod.util.utils.AABBTransformer;
import chaos.mod.util.utils.UtilTranslatable;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ImmersiveRetainingWallBlock extends Block implements IModelRegister {
    public static final PropertyEnum<EnumFacing> FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<ImmersiveRetainingWallType> TYPE = PropertyEnum.create("type", ImmersiveRetainingWallType.class);

    public static final AxisAlignedBB NORTH_AABB = new AABBTransformer(0.0, 0.0, 1.0, 16.0, 16.0, 16.0);
    public static final AxisAlignedBB SOUTH_AABB = new AABBTransformer(0.0, 0.0, 0.0, 16.0, 16.0, 15.0);
    public static final AxisAlignedBB EAST_AABB = new AABBTransformer(0.0, 0.0, 0.0, 15.0, 16.0, 16.0);
    public static final AxisAlignedBB WEST_AABB = new AABBTransformer(1.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public ImmersiveRetainingWallBlock() {
        super(Material.ROCK);
        setUnlocalizedName(UtilTranslatable.getEki("immersive_retaining_wall"));
        setRegistryName("immersive_retaining_wall");
        setCreativeTab(Eki.BLOCK);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ImmersiveRetainingWallBlockItem(this).setRegistryName(getRegistryName()));
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
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        for (AxisAlignedBB aabb : getAABB(state))
            addCollisionBoxToList(pos, entityBox, collidingBoxes, aabb);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        switch (state.getValue(TYPE)) {
            case DL:
                switch (state.getValue(FACING)) {
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
                switch (state.getValue(FACING)) {
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
                switch (state.getValue(FACING)) {
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
                switch (state.getValue(FACING)) {
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

    private void removeBlock(World world, BlockPos pos, TriFunction<BlockPos, Integer, Integer, BlockPos> blockPosFunction) {
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                BlockPos targetPos = blockPosFunction.apply(pos, j, i);
                if (isPart(world, targetPos))
                    removeBlock(world, targetPos);
            }
    }

    private boolean isPart(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() instanceof ImmersiveRetainingWallBlock;
    }

    private void removeBlock(World world, BlockPos pos) {
        world.setBlockToAir(pos);
    }

    private List<AxisAlignedBB> getAABB(IBlockState state) {
        EnumFacing facing = state.getValue(FACING);
        ImmersiveRetainingWallType type = state.getValue(TYPE);

        List<AxisAlignedBB> list = Lists.newArrayList();

        switch (type) {
            case DL:
            case DR:
                switch (facing) {
                    case NORTH:
                        list.add(SOUTH_AABB);
                        break;
                    case SOUTH:
                        list.add(NORTH_AABB);
                        break;
                    case EAST:
                        list.add(WEST_AABB);
                        break;
                    case WEST:
                        list.add(EAST_AABB);
                        break;
                }
                break;
            default:
                switch (facing) {
                    case NORTH:
                        list.add(BlockSideSlab.NORTH_AABB);
                        break;
                    case SOUTH:
                        list.add(BlockSideSlab.SOUTH_AABB);
                        break;
                    case EAST:
                        list.add(BlockSideSlab.EAST_AABB);
                        break;
                    case WEST:
                        list.add(BlockSideSlab.WEST_AABB);
                        break;
                }
        }

        return list;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, TYPE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        meta += state.getValue(FACING).getHorizontalIndex() * 4;
        meta += state.getValue(TYPE).ordinal();

        return meta;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getHorizontal(meta / 4);
        ImmersiveRetainingWallType type = ImmersiveRetainingWallType.VALUES[meta % 4];

        return getDefaultState().withProperty(FACING, facing)
                .withProperty(TYPE, type);
    }

    public enum ImmersiveRetainingWallType implements IStringSerializable {
        DL("down_left"), DR("down_right"), UL("up_left"), UR("up_right");

        public static final ImmersiveRetainingWallType[] VALUES = values();
        private final String type;

        ImmersiveRetainingWallType(String type) {
            this.type = type;
        }

        @Override
        public String getName() {
            return type;
        }
    }
}
