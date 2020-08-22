package chaos.mod.objects.block.fence;

import java.util.List;

import chaos.mod.init.BlockInit;
import chaos.mod.objects.block.base.BlockFourFace;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBarbedWires extends BlockFourFace {
	private static final AxisAlignedBB[] boxes = new AxisAlignedBB[] { new AxisAlignedBB(0F, 0F, 0.4375F, 1F, 1F, 0.5625F), new AxisAlignedBB(0.4375F, 0F, 0F, 0.5625F, 1F, 1F) };
	private final BarbedWireType type;

	public BlockBarbedWires(String name, BarbedWireType type) {
		super(name, null, Material.IRON, false);
		this.type = type;
		setSoundType(SoundType.METAL);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		EnumFacing face = state.getValue(FACING);
		if (facing == EnumFacing.UP)
			return false;

		if (stack.getItem() instanceof ItemBlock) {
			ItemBlock itemblk = (ItemBlock) stack.getItem();
			if (itemblk.getBlock() instanceof BlockBarbedWires && getType() == BarbedWireType.BASE) {
				for (;;) {
					IBlockState s = worldIn.getBlockState(pos);
					if (s.getBlock() instanceof BlockBarbedWires) {
						BlockBarbedWires blk = (BlockBarbedWires) s.getBlock();
						if (blk.getType() == BarbedWireType.TOP) {
							worldIn.setBlockState(pos, BlockInit.BARBED_WIRES_FENCE.getDefaultState().withProperty(FACING, face), 2);
							worldIn.setBlockState(pos.offset(EnumFacing.UP), BlockInit.BARBED_WIRES_TOP.getDefaultState().withProperty(FACING, face), 2);
							worldIn.playSound(null, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1F, 1F);
							return true;
						} else {
							pos = pos.offset(EnumFacing.UP);
							continue;
						}
					}
					return false;
				}
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = state.getValue(FACING);
		if (face == EnumFacing.NORTH || face == EnumFacing.SOUTH)
			return boxes[0];
		else {
			return boxes[1];
		}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		EnumFacing face = state.getValue(FACING);
		if (face == EnumFacing.NORTH || face == EnumFacing.SOUTH)
			addCollisionBoxToList(pos, entityBox, collidingBoxes, boxes[0]);
		else {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, boxes[1]);
		}
	}

	public BarbedWireType getType() {
		return type;
	}

	public enum BarbedWireType {
		BASE, FENCE, TOP;
	}
}
