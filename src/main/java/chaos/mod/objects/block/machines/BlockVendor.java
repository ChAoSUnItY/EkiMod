package chaos.mod.objects.block.machines;

import chaos.mod.Eki;
import chaos.mod.objects.block.base.BlockFourFace;
import chaos.mod.objects.item.ItemWrench;
import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.tileentity.TileEntityTicketVendor;
import chaos.mod.util.Reference;
import chaos.mod.util.utils.UtilBlockPos;
import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockVendor extends BlockFourFace implements ITileEntityProvider {
	public BlockVendor(String name) {
		super(name, Eki.STATION, Material.CIRCUITS, false);
		setSoundType(SoundType.METAL);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		boolean isRemote = worldIn.isRemote;
		if (te instanceof TileEntityTicketVendor) {
			TileEntityTicketVendor teTV = (TileEntityTicketVendor) te;
			if (heldItem == null)
				playerIn.openGui(Eki.instance, Reference.GUITICKETVENDOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
			else if (heldItem.getItem() instanceof ItemWrench) {
				if (heldItem.hasTagCompound()) {
					BlockPos targetPos = UtilBlockPos.getPos(heldItem.getTagCompound().getIntArray("pos"));
					TileEntity te2 = worldIn.getTileEntity(targetPos);
					if (te2 instanceof TileEntityAnchor) {
						TileEntityAnchor teA = (TileEntityAnchor) te2;
						if (teTV.isSame(targetPos))
							return true;
						if (!teTV.isBound()) {
							teTV.setAnchor(targetPos);
							teA.addGate(pos);
						} else {
							TileEntity te3 = worldIn.getTileEntity(teTV.getAnchor());
							TileEntityAnchor teA2 = (TileEntityAnchor) te3;
							teA2.deleteGate(pos);
							teTV.setAnchor(targetPos);
							teA.addGate(pos);
						}
						if (isRemote)
							playerIn.addChatMessage(new UtilTranslatable(TranslateType.CHAT, "anchorLinked").applyFormat(TextFormatting.GREEN));
					} else {
						if (isRemote)
							playerIn.addChatMessage(new UtilTranslatable(TranslateType.CHAT, "illegalAnchor").applyFormat(TextFormatting.RED));
					}
				} else {
					if (isRemote)
						playerIn.addChatMessage(new UtilTranslatable(TranslateType.CHAT, "missingAnchor").applyFormat(TextFormatting.RED));
				}
				return true;
			} else {
				playerIn.openGui(Eki.instance, Reference.GUITICKETVENDOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
				return true;
			}
		}
		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (!worldIn.isRemote && te instanceof TileEntityTicketVendor) {
			if (!((TileEntityTicketVendor) te).isBound())
				return;
			TileEntity te2 = worldIn.getTileEntity(((TileEntityTicketVendor) te).getAnchor());
			if (te2 instanceof TileEntityAnchor) {
				((TileEntityAnchor) te2).deleteGate(pos);
			}
		}
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityTicketVendor();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTicketVendor();
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}
}
