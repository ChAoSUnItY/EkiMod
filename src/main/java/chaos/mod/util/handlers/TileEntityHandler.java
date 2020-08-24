package chaos.mod.util.handlers;

import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.tileentity.TileEntityTicketGate;
import chaos.mod.tileentity.TileEntityTicketVendor;
import chaos.mod.util.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityTicketVendor.class, Reference.MODID + "ticket_vendor");
		GameRegistry.registerTileEntity(TileEntityTicketGate.class, Reference.MODID + "ticket_gate");
		GameRegistry.registerTileEntity(TileEntityAnchor.class, Reference.MODID + "anchor");
	}
}
