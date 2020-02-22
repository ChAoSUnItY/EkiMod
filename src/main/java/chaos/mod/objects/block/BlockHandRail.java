package chaos.mod.objects.block;

import java.util.List;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHandRail extends BlockBase {
	public static final AxisAlignedBB HAND_RAIL_NORTH_COLLISION_AABB = new AxisAlignedBB(0, 0, 0.625D, 1, 1.5D, 1);
	public static final AxisAlignedBB HAND_RAIL_EAST_COLLISION_AABB = new AxisAlignedBB(0, 0, 0, 0.375D, 1.5D, 1);
	public static final AxisAlignedBB HAND_RAIL_SOUTH_COLLISION_AABB = new AxisAlignedBB(0, 0, 0, 1, 1.5D, 0.375D);
	public static final AxisAlignedBB HAND_RAIL_WEST_COLLISION_AABB = new AxisAlignedBB(0.625D, 0, 0, 1, 1.5D, 1);
	
	public BlockHandRail(String name, Material material, CreativeTabs tab) {
		super(name, material, tab);
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos){
	    return false;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		switch(state.getValue(BlockHorizontal.FACING)){
		case NORTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_NORTH_COLLISION_AABB);
			break;
		case SOUTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_SOUTH_COLLISION_AABB);
			break;
		case EAST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_EAST_COLLISION_AABB);
			break;
		case WEST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_WEST_COLLISION_AABB);
		default:
			break;
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {BlockHorizontal.FACING});
	}
	
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockHorizontal.FACING).getIndex();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
}

