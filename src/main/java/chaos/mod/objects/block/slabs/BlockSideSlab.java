package chaos.mod.objects.block.slabs;

import java.util.List;

import chaos.mod.Eki;
import chaos.mod.objects.block.base.BlockBase;
import chaos.mod.objects.block.base.BlockFourFace;
import chaos.mod.util.utils.AABBTransformer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSideSlab extends BlockFourFace {
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0, 0, 0.5D, 1, 1, 1);
	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 0.5D);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0, 0, 0, 0.5D, 1, 1);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.5D, 0, 0, 1, 1, 1);

	public BlockSideSlab(String name, BlockBase heritageBlock) {
		super(name, Eki.BLOCK, heritageBlock.getMaterial(), false);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		switch (state.getValue(FACING)) {
		case NORTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
			break;
		case SOUTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
			break;
		case EAST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
			break;
		case WEST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
		default:
			break;
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(FACING)) {
		case NORTH:
			return new AxisAlignedBB(0, 0, 0.5D, 1, 1, 1);
		case SOUTH:
			return new AxisAlignedBB(0, 0, 0, 1, 1, 0.5D);
		case EAST:
			return new AxisAlignedBB(0, 0, 0, 0.5D, 1, 1);
		case WEST:
			return new AxisAlignedBB(0.5D, 0, 0, 1, 1, 1);
		default:
			return null;
		}
	}
}
