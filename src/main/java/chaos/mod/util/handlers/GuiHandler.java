package chaos.mod.util.handlers;

import chaos.mod.objects.block.container.ContainerTicketVendor;
import chaos.mod.objects.block.gui.GuiTicketVendor;
import chaos.mod.tileentity.TileEnitityTicketVendor;
import chaos.mod.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUITICKETVENDOR) {
			return new ContainerTicketVendor(player.inventory,
					(TileEnitityTicketVendor) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
		/*
		 * switch (ID) { case Reference.GUITICKETVENDOR: return new
		 * ContainerTicketVendor(player.inventory, (TileEnitityTicketVendor)
		 * world.getTileEntity(new BlockPos(x, y, z))); default: return null; }
		 */
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUITICKETVENDOR) {
			return new GuiTicketVendor(player.inventory,
					(TileEnitityTicketVendor) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
		/*
		switch (ID) {
		case Reference.GUITICKETVENDOR:
			return new GuiTicketVendor(player.inventory,
					(TileEnitityTicketVendor) world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
		*/
	}
}
