package chaos.mod.objects.block.machines;

import java.util.List;

import chaos.mod.Eki;
import chaos.mod.init.ItemInit;
import chaos.mod.objects.block.base.BlockBase;
import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.util.utils.UtilTextFormer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAnchor extends BlockBase implements ITileEntityProvider {
	public BlockAnchor(String name) {
		super(name, Eki.STATION);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		ItemStack stack = playerIn.getHeldItem(hand);
		if (!worldIn.isRemote) {
			if (tileEntity instanceof TileEntityAnchor) {
				TileEntityAnchor tileEntityAnchor = (TileEntityAnchor) tileEntity;
				if (stack.getItem() == ItemInit.WRENCH) {
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setIntArray("pos", new int[] { pos.getX(), pos.getZ(), pos.getY() });
					stack.setTagCompound(nbt);
					TextComponentString text = new TextComponentString(
							I18n.format("chat.type.text.anchoredamounts", tileEntityAnchor.getAnchoredObjectsAmount()));
					UtilTextFormer.form(text, TextFormatting.WHITE, false, false);
					playerIn.sendMessage(text);
				}
			}
		}
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(TextFormatting.GRAY + I18n.format(getUnlocalizedName() + ".tooltip"));
		tooltip.add(TextFormatting.DARK_RED + I18n.format(getUnlocalizedName() + ".warning.tooltip"));
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityAnchor();
	}

}
