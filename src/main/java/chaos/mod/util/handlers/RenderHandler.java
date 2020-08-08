package chaos.mod.util.handlers;

import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.tileentity.render.RenderAnchor;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class RenderHandler {
	public static void registerTESRs() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAnchor.class, new RenderAnchor());
	}
}
