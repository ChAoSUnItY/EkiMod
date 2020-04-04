package chaos.mod.objects.block.stairs;

import java.util.List;

import chaos.mod.objects.block.base.BlockHasFace;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCustomStair extends BlockHasFace {
	public static final AxisAlignedBB STAIR_BASE_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.5D, 1);
	public static final AxisAlignedBB STAIR_NORTH_TOP_AABB = new AxisAlignedBB(0, 0.5D, 0.5D, 1, 1, 1);
	public static final AxisAlignedBB STAIR_EAST_TOP_AABB = new AxisAlignedBB(0, 0, 0, 0.5D, 1, 1);
	public static final AxisAlignedBB STAIR_SOUTH_TOP_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 0.5D);
	public static final AxisAlignedBB STAIR_WEST_TOP_AABB = new AxisAlignedBB(0.5D, 0, 0, 1, 1, 1);
	public final int type; // 1 Stands for Full one ,2 Stands for Bottom one, 3 Stands for Top one.

	public BlockCustomStair(String name, Material material, CreativeTabs tab, int type) {
		super(name, material, tab, false);
		this.type = type;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		switch (state.getValue(FACING)) {
		case NORTH:
			switch (type) {
			case 1:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_NORTH_TOP_AABB);
				break;
			case 2:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				break;
			case 3:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
				break;
			default:
				break;
			}
			break;
		case SOUTH:
			switch (type) {
			case 1:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_SOUTH_TOP_AABB);
				break;
			case 2:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				break;
			case 3:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
				break;
			default:
				break;
			}
			break;
		case EAST:
			switch (type) {
			case 1:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_EAST_TOP_AABB);
				break;
			case 2:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				break;
			case 3:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
				break;
			default:
				break;
			}
			break;
		case WEST:
			switch (type) {
			case 1:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_WEST_TOP_AABB);
				break;
			case 2:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				break;
			case 3:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (type) {
		case 1:
			return FULL_BLOCK_AABB;
		case 2:
			return STAIR_BASE_AABB;
		case 3:
			return FULL_BLOCK_AABB;
		default:
			return FULL_BLOCK_AABB;

		}
	}
}
