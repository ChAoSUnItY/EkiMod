package chaos.mod.util.handlers;

import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.tileentity.TileEntityTicketGate;
import chaos.mod.tileentity.TileEntityTicketVendor;
import chaos.mod.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityTicketVendor.class, new ResourceLocation(Reference.MODID, "ticket_vendor"));
		GameRegistry.registerTileEntity(TileEntityTicketGate.class, new ResourceLocation(Reference.MODID, "ticket_gate"));
		GameRegistry.registerTileEntity(TileEntityAnchor.class, new ResourceLocation(Reference.MODID, "anchor"));
	}
}
