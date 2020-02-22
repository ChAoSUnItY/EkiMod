package chaos.mod.objects.block;

import java.util.List;
import java.util.Random;

import chaos.mod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockPlatformEdge extends BlockHasFace {
	public static final AxisAlignedBB PLATFORM_EDGE_TOP_AABB = new AxisAlignedBB(0, 0.84375D, 0, 1, 1, 1);
	public static final AxisAlignedBB PLATFORM_EDGE_NORTH_AABB = new AxisAlignedBB(0, 0, 0.5D, 1, 0.84375D, 1);
	public static final AxisAlignedBB PLATFORM_EDGE_EAST_AABB = new AxisAlignedBB(0, 0, 0, 0.5D, 0.84375D, 1);
	public static final AxisAlignedBB PLATFORM_EDGE_SOUTH_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.84375D, 0.5D);
	public static final AxisAlignedBB PLATFORM_EDGE_WEST_AABB = new AxisAlignedBB(0.5D, 0, 0, 1, 0.84375D, 1);
	private final boolean isOn;
	private final boolean lightable;
	
	public BlockPlatformEdge(String name, Material material, CreativeTabs tab, boolean isOn, boolean lightable) {
		super(name, material, tab);
		this.isOn = isOn;
		this.lightable = lightable;
		
		if(this.isOn) {
			setLightLevel(8/15F);
			setCreativeTab(null);
		}
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		switch (state.getValue(BlockHorizontal.FACING)) {
		case NORTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_NORTH_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_TOP_AABB);
			break;
		case SOUTH:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_SOUTH_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_TOP_AABB);
			break;
		case EAST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_EAST_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_TOP_AABB);
			break;
		case WEST:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_WEST_AABB);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, PLATFORM_EDGE_TOP_AABB);
		default:
			break;
		}
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			if (lightable) {
				if (this.isOn && !worldIn.isBlockPowered(pos)) {
					worldIn.scheduleBlockUpdate(pos, this, 0, 1);
				} else if (!this.isOn && worldIn.isBlockPowered(pos)) {
					switch (state.getValue(BlockHorizontal.FACING)) {
					case NORTH:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(2)));
						break;
					case EAST:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(3)));
						break;
					case SOUTH:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(0)));
						break;
					case WEST:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(1)));
						break;
					default:
						break;
					} 
					
				}
			}
		}
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		if (!worldIn.isRemote) {
			if (lightable) {
				if (this.isOn && !worldIn.isBlockPowered(pos)) {
					worldIn.scheduleBlockUpdate(pos, this, 0, 1);
				} else if (!this.isOn && worldIn.isBlockPowered(pos)) {
					switch (state.getValue(BlockHorizontal.FACING)) {
					case NORTH:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(2)));
						break;
					case EAST:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(3)));
						break;
					case SOUTH:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(0)));
						break;
					case WEST:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_ON.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(1)));
						break;
					default:
						break;
					} 
				}
			}
		}
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			if (lightable) {
				if (this.isOn && !worldIn.isBlockPowered(pos)) {
					switch (state.getValue(BlockHorizontal.FACING)) {
					case NORTH:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(2)));
						break;
					case EAST:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(3)));
						break;
					case SOUTH:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(0)));
						break;
					case WEST:
						worldIn.setBlockState(pos, BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(1)));
						break;
					default:
						break;
					} 
				}
			}
		}
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		ItemStack itemStack = new ItemStack(state.getBlock());
		ItemStack isOnItemStack = new ItemStack(BlockInit.PLATFORM_EDGE_WITH_LINE_AND_LIGHT_OFF);
		if(isOn) {
			return isOnItemStack;
		}
		return itemStack;
	}
}
