package chaos.mod.objects.block;

import java.util.List;

import chaos.mod.Eki;
import chaos.mod.objects.block.base.BlockHasFace;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHandRail extends BlockHasFace {
	public static final AxisAlignedBB HAND_RAIL_NORTH_AABB = new AxisAlignedBB(0, 0, 0.625D, 1, 1.5D, 1);
	public static final AxisAlignedBB HAND_RAIL_NORTH_CORNER_AABB = new AxisAlignedBB(0, 0, 0, 0.375D, 1.5D, 0.625D);
	public static final AxisAlignedBB HAND_RAIL_EAST_AABB = new AxisAlignedBB(0, 0, 0, 0.375D, 1.5D, 1);
	public static final AxisAlignedBB HAND_RAIL_EAST_CORNER_AABB = new AxisAlignedBB(0.375D, 0, 0, 1, 1.5D, 0.375D);
	public static final AxisAlignedBB HAND_RAIL_SOUTH_AABB = new AxisAlignedBB(0, 0, 0, 1, 1.5D, 0.375D);
	public static final AxisAlignedBB HAND_RAIL_SOUTH_CORNER_AABB = new AxisAlignedBB(1, 0, 0.375D, 0.625D, 1.5D, 1);
	public static final AxisAlignedBB HAND_RAIL_WEST_AABB = new AxisAlignedBB(0.625D, 0, 0, 1, 1.5D, 1);
	public static final AxisAlignedBB HAND_RAIL_WEST_CORNER_AABB = new AxisAlignedBB(0.625D, 0, 1, 0, 1.5D, 0.625D);
	public final int type; //1 Stands for Normal one, 2 Stands for Corner one
	
	public BlockHandRail(String name, int type) {
		super(name, Eki.STATION, false);
		this.type = type;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		switch(state.getValue(BlockHorizontal.FACING)){
		case NORTH:
			switch (type) {
			case 1:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_NORTH_AABB);
				break;
			case 2:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_NORTH_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_NORTH_CORNER_AABB);
				break;
			default:
				break;
			}
			break;
		case SOUTH:
			switch (type) {
			case 1:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_SOUTH_AABB);
				break;
			case 2:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_SOUTH_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_SOUTH_CORNER_AABB);
				break;
			default:
				break;
			}
			break;
		case EAST:
			switch (type) {
			case 1:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_EAST_AABB);
				break;
			case 2:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_EAST_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_EAST_CORNER_AABB);
				break;
			default:
				break;
			}
			break;
		case WEST:
			switch (type) {
			case 1:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_WEST_AABB);
				break;
			case 2:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_WEST_AABB);
				addCollisionBoxToList(pos, entityBox, collidingBoxes, HAND_RAIL_WEST_CORNER_AABB);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
}

