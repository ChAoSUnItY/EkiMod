package chaos.mod.util.handlers;

import chaos.mod.tileentity.TileEnitityTicketVendor;
import chaos.mod.util.Reference;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler extends TileEntity{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEnitityTicketVendor.class, new ResourceLocation(Reference.MODID + ":ticket_vendor"));
	}
}
