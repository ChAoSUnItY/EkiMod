package chaos.mod.objects.block;

import java.util.List;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHalfStair extends BlockHasFace {
	public static final AxisAlignedBB SIDE_BLOCK_NORTH_AABB = new AxisAlignedBB(0, 0, 0.5D, 1, 1, 1);
	public static final AxisAlignedBB SIDE_BLOCK_EAST_AABB = new AxisAlignedBB(0, 0, 0, 0.5D, 1, 1);
	public static final AxisAlignedBB SIDE_BLOCK_SOUTH_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 0.5D);
	public static final AxisAlignedBB SIDE_BLOCK_WEST_AABB = new AxisAlignedBB(0.5D, 0, 0, 1, 1, 1);
	public static final AxisAlignedBB STAIR_NORTH_TOP_AABB = new AxisAlignedBB(0, 0.5D, 0.5D, 1, 1, 1);
	public static final AxisAlignedBB STAIR_NORTH_BOT_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.5D, 1);
	public static final AxisAlignedBB STAIR_EAST_TOP_AABB = new AxisAlignedBB(0, 0, 0, 0.5D, 1, 1);
	public static final AxisAlignedBB STAIR_EAST_BOT_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.5D, 1);
	public static final AxisAlignedBB STAIR_SOUTH_TOP_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 0.5D);
	public static final AxisAlignedBB STAIR_SOUTH_BOT_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.5D, 1);
	public static final AxisAlignedBB STAIR_WEST_TOP_AABB = new AxisAlignedBB(0.5D, 0, 0, 1, 1, 1);
	public static final AxisAlignedBB STAIR_WEST_BOT_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.5D, 1);
	
	public BlockHalfStair(String name, Material material, CreativeTabs tab) {
		super(name, material, tab);
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		switch(state.getValue(BlockHorizontal.FACING)){
		case NORTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_NORTH_TOP_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_NORTH_BOT_AABB);
			break;
		case SOUTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_SOUTH_TOP_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_SOUTH_BOT_AABB);
			break;
		case EAST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_EAST_TOP_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_EAST_BOT_AABB);
			break;
		case WEST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_WEST_TOP_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_WEST_BOT_AABB);
		default:
			break;
		}
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(BlockHorizontal.FACING)) {
		case NORTH:
			return SIDE_BLOCK_NORTH_AABB;
		case SOUTH:
			return SIDE_BLOCK_SOUTH_AABB;
		case EAST:
			return SIDE_BLOCK_EAST_AABB;
		case WEST:
			return SIDE_BLOCK_WEST_AABB;
		default:
			return null;
		}
	}
}
