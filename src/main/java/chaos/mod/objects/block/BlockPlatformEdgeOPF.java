package chaos.mod.objects.block;

import java.util.List;
import java.util.Random;

import chaos.mod.Eki;
import chaos.mod.init.BlockInit;
import chaos.mod.objects.block.base.BlockFourFace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPlatformEdgeOPF extends BlockFourFace {
	public static final AxisAlignedBB PLATFORM_EDGE_TOP_AABB = new AxisAlignedBB(0, 1.34375D, 0, 1, 1.5D, 1);
	public static final AxisAlignedBB PLATFORM_EDGE_WALL_NORTH_AABB = new AxisAlignedBB(0, 0, 0.5D, 1, 1.34375D, 1);
	public static final AxisAlignedBB PLATFORM_EDGE_WALL_SOUTH_AABB = new AxisAlignedBB(0, 0, 0, 1, 1.34375D, 0.5D);
	public static final AxisAlignedBB PLATFORM_EDGE_WALL_EAST_AABB = new AxisAlignedBB(0, 0, 0, 0.5D, 1.34375D, 1);
	public static final AxisAlignedBB PLATFORM_EDGE_WALL_WEST_AABB = new AxisAlignedBB(0.5D, 0, 0, 1, 1.34375D, 1);
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	private final boolean isOn;
	private final boolean lightable;

	public BlockPlatformEdgeOPF(String name, boolean isOn, boolean lightable) {
		super(name, isOn ? null : Eki.STATION, Material.ROCK, false);
		this.isOn = isOn;
		this.lightable = lightable;

		if (isOn) {
			setLightLevel(8 / 15F);
			setCreativeTab(null);
		}
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.SOLID;
	}

	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		switch (state.getValue(FACING)) {
		case NORTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_WALL_NORTH_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_TOP_AABB);
			break;
		case SOUTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_WALL_SOUTH_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_TOP_AABB);
			break;
		case EAST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_WALL_EAST_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_TOP_AABB);
			break;
		case WEST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_WALL_WEST_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_TOP_AABB);
		default:
			break;
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1, 1.5D, 1);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote && lightable)
			if (isOn && !worldIn.isBlockPowered(pos))
				worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF_OPF.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
			else if (!isOn && worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON_OPF.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
			}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote && lightable)
			if (isOn && !worldIn.isBlockPowered(pos))
				worldIn.scheduleUpdate(pos, this, 4);
			else if (!isOn && worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON_OPF.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
			}
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote && lightable)
			if (isOn && !worldIn.isBlockPowered(pos))
				worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF_OPF.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return isOn ? new ItemStack(BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF_OPF) : new ItemStack(this);
	}
}
