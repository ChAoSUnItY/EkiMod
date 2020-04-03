package chaos.mod.objects.block.machines;

import chaos.mod.Main;
import chaos.mod.objects.block.base.BlockHasFace;
import chaos.mod.tileentity.TileEntityTicketVendor;
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

public class BlockVendor extends BlockHasFace implements ITileEntityProvider {
	public BlockVendor(String name, Material material, CreativeTabs tab) {
		super(name, material, tab, false);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) playerIn.openGui(Main.instance, Reference.GUITICKETVENDOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
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
