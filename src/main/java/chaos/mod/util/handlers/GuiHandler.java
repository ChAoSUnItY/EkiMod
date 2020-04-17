package chaos.mod.util.handlers;

import chaos.mod.objects.block.container.ContainerTicketVendor;
import chaos.mod.objects.block.gui.GuiTicketVendor;
import chaos.mod.tileentity.TileEntityTicketVendor;
import chaos.mod.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		switch (ID) {
		case Reference.GUITICKETVENDOR:
			return new ContainerTicketVendor(player.inventory, (TileEntityTicketVendor) world.getTileEntity(pos),
					player);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		switch (ID) {
		case Reference.GUITICKETVENDOR:
			return new GuiTicketVendor(player.inventory, (TileEntityTicketVendor) world.getTileEntity(pos), player);
		default:
			return null;
		}
	}
}
