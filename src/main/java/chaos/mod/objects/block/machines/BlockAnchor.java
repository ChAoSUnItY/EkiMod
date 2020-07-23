package chaos.mod.objects.block.machines;

import java.util.List;

import chaos.mod.Eki;
import chaos.mod.objects.block.base.BlockBase;
import chaos.mod.objects.item.ItemWrench;
import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.util.utils.UtilBlockPos;
import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAnchor extends BlockBase implements ITileEntityProvider {
	public BlockAnchor(String name) {
		super(name, Eki.STATION);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		ItemStack stack = playerIn.getHeldItem(hand);
		if (!worldIn.isRemote && te instanceof TileEntityAnchor) {
			TileEntityAnchor teA = (TileEntityAnchor) te;
			if (stack.getItem() instanceof ItemWrench) {
				System.out.println(pos);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setIntArray("pos", UtilBlockPos.getIntArray(pos));
				stack.setTagCompound(nbt);
				playerIn.sendMessage(new UtilTCString(TranslateType.CHAT, "anchorAmount", teA.gatesPos.size()).applyFormat(TextFormatting.WHITE));
				return true;
			}
		}
		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (!worldIn.isRemote && te instanceof TileEntityAnchor) {
			TileEntityAnchor teA = (TileEntityAnchor) te;
			teA.resetAllGate();
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(new UtilTranslatable(getUnlocalizedName() + ".tooltip").applyFormat(TextFormatting.GRAY).getFormattedText());
		tooltip.add(new UtilTranslatable(getUnlocalizedName() + ".warning.tooltip").applyFormat(TextFormatting.DARK_RED).getFormattedText());
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityAnchor();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityAnchor();
	}

}
