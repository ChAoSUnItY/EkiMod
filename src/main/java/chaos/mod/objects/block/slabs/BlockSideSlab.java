package chaos.mod.objects.block.slabs;

import java.util.List;

import chaos.mod.objects.block.base.BlockHasFace;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSideSlab extends BlockHasFace {
	public static final AxisAlignedBB SIDE_BLOCK_NORTH_AABB = new AxisAlignedBB(0, 0, 0.5D, 1, 1, 1);
	public static final AxisAlignedBB SIDE_BLOCK_EAST_AABB = new AxisAlignedBB(0, 0, 0, 0.5D, 1, 1);
	public static final AxisAlignedBB SIDE_BLOCK_SOUTH_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 0.5D);
	public static final AxisAlignedBB SIDE_BLOCK_WEST_AABB = new AxisAlignedBB(0.5D, 0, 0, 1, 1, 1);

	public BlockSideSlab(String name, Material material, CreativeTabs tab) {
		super(name, material, tab, false);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		switch (state.getValue(BlockHorizontal.FACING)) {
		case NORTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, SIDE_BLOCK_NORTH_AABB);
			break;
		case SOUTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, SIDE_BLOCK_SOUTH_AABB);
			break;
		case EAST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, SIDE_BLOCK_EAST_AABB);
			break;
		case WEST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, SIDE_BLOCK_WEST_AABB);
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
