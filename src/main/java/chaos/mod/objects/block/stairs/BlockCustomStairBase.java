package chaos.mod.objects.block.stairs;

import java.util.List;

import chaos.mod.Eki;
import chaos.mod.objects.block.base.BlockHorizontalBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCustomStairBase extends BlockHorizontalBase {
	public static final AxisAlignedBB STAIR_BASE_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.5D, 1);
	public static final AxisAlignedBB STAIR_NORTH_TOP_AABB = new AxisAlignedBB(0, 0.5D, 0.5D, 1, 1, 1);
	public static final AxisAlignedBB STAIR_EAST_TOP_AABB = new AxisAlignedBB(0, 0, 0, 0.5D, 1, 1);
	public static final AxisAlignedBB STAIR_SOUTH_TOP_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 0.5D);
	public static final AxisAlignedBB STAIR_WEST_TOP_AABB = new AxisAlignedBB(0.5D, 0, 0, 1, 1, 1);

	public static final AxisAlignedBB STAIR_N = new AxisAlignedBB(0F, 0F, 0F, 1F, 2F, 0.25F);
	public static final AxisAlignedBB STAIR_S = new AxisAlignedBB(0F, 0F, 0.75F, 1F, 2F, 1F);
	public static final AxisAlignedBB STAIR_E = new AxisAlignedBB(0.75F, 0F, 0F, 1F, 2F, 1F);
	public static final AxisAlignedBB STAIR_W = new AxisAlignedBB(0F, 0F, 0F, 0.25F, 2F, 1F);
	public final StairType stairType;
	public final StairHandRailType stairHType;

	public BlockCustomStairBase(String name, StairType Stype, StairHandRailType SHtype) {
		super(name, Eki.STATION, Material.ROCK, false);
		stairType = Stype;
		stairHType = SHtype;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		switch (state.getValue(net.minecraft.block.BlockHorizontal.FACING)) {
		case NORTH:
			switch (stairType) {
			case NORMAL:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_NORTH_TOP_AABB);
				break;
			case GENTLE_BOT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				break;
			case GENTLE_TOP:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
				break;
			default:
				break;
			}

			switch (stairHType) {
			case LEFT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_E);
				break;
			case RIGHT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_W);
				break;
			case NON:
				break;
			default:
				break;
			}
			break;
		case SOUTH:
			switch (stairType) {
			case NORMAL:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_SOUTH_TOP_AABB);
				break;
			case GENTLE_BOT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				break;
			case GENTLE_TOP:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
				break;
			default:
				break;
			}

			switch (stairHType) {
			case LEFT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_W);
				break;
			case RIGHT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_E);
				break;
			case NON:
				break;
			default:
				break;
			}
			break;
		case EAST:
			switch (stairType) {
			case NORMAL:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_EAST_TOP_AABB);
				break;
			case GENTLE_BOT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				break;
			case GENTLE_TOP:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
				break;
			default:
				break;
			}

			switch (stairHType) {
			case LEFT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_S);
				break;
			case RIGHT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_N);
				break;
			case NON:
				break;
			default:
				break;
			}
			break;
		case WEST:
			switch (stairType) {
			case NORMAL:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_WEST_TOP_AABB);
				break;
			case GENTLE_BOT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_BASE_AABB);
				break;
			case GENTLE_TOP:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
				break;
			default:
				break;
			}

			switch (stairHType) {
			case LEFT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_N);
				break;
			case RIGHT:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, STAIR_S);
				break;
			case NON:
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
		if (stairType == StairType.GENTLE_BOT)
			return STAIR_BASE_AABB;
		else {
			return FULL_BLOCK_AABB;
		}
	}

	public enum StairType {
		NORMAL, GENTLE_BOT, GENTLE_TOP
	}

	public enum StairHandRailType {
		LEFT, RIGHT, NON;
	}
}
