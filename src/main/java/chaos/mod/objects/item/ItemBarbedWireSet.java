package chaos.mod.objects.item;

import chaos.mod.Eki;
import chaos.mod.init.BlockInit;
import chaos.mod.objects.block.base.BlockFourFace;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBarbedWireSet extends ItemBase {
	public ItemBarbedWireSet(String name) {
		super(name);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("size"))
			return;
		else {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("size", 1);
			stack.setTagCompound(tag);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (worldIn.isRemote) {
			Eki.proxy.displayBarbedWireSetConfig(playerIn.getHeldItem(handIn), playerIn, handIn);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if (facing != EnumFacing.UP)
			return EnumActionResult.FAIL;

		if (!block.isReplaceable(worldIn, pos))
			pos = pos.offset(facing);

		ItemStack itemstack = player.getHeldItem(hand);

		if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack) && worldIn.mayPlace(BlockInit.BARBED_WIRES_BASE, pos, false, facing, (Entity) null)) {
			IBlockState state = BlockInit.BARBED_WIRES_BASE.getDefaultState();

			if (placeBlockAt(itemstack, player, worldIn, pos, facing, hitX, hitY, hitZ, state)) {
				state = worldIn.getBlockState(pos);
				SoundType soundtype = state.getBlock().getSoundType(state, worldIn, pos, player);
				worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
				itemstack.shrink(1);
			}

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
		BlockPos p = pos;
		EnumFacing facing = player.getHorizontalFacing().getOpposite();
		world.setBlockState(p, BlockInit.BARBED_WIRES_BASE.getDefaultState().withProperty(BlockFourFace.FACING, facing), 2);
		p = p.offset(EnumFacing.UP);
		for (int i = 0; i < stack.getTagCompound().getInteger("size"); i++) {
			world.setBlockState(p, BlockInit.BARBED_WIRES_FENCE.getDefaultState().withProperty(BlockFourFace.FACING, facing), 2);
			p = p.offset(EnumFacing.UP);
		}
		world.setBlockState(p, BlockInit.BARBED_WIRES_TOP.getDefaultState().withProperty(BlockFourFace.FACING, facing), 2);
		return true;
	}
}
