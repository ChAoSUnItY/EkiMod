package chaos.mod.objects.block.machines;

import chaos.mod.Main;
import chaos.mod.objects.block.base.BlockHasFace;
import chaos.mod.tileentity.TileEnitityTicketVendor;
import chaos.mod.util.Reference;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockVendor extends BlockHasFace implements ITileEntityProvider{
	public BlockVendor(String name, Material material, CreativeTabs tab, boolean isOpaqueCube) {
		super(name, material, tab, isOpaqueCube);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		playerIn.openGui(Main.instance, Reference.GUITICKETVENDOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
		/*
		if (tileEntity instanceof TileEnitityTicketVendor) {
			TileEnitityTicketVendor vendor = (TileEnitityTicketVendor) tileEntity;
			if (!worldIn.isRemote) {
				if (Main.isApiModLoaded) {
					playerIn.openGui(Main.instance, Reference.GUITICKETVENDOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
					long amount = GrandEconomyApi.getBalance(playerIn.getUniqueID(), true);
					if(!(amount <= 100)) {
						GrandEconomyApi.takeFromBalance(playerIn.getUniqueID(), 100, true);
						vendor.outputTicket();
						return true;
					}
				}
				//vendor.outputTicket();
				
			}
		}
		return true;
		*/
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEnitityTicketVendor();
	}
}
