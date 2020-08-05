package chaos.mod.proxy;

import chaos.mod.util.Reference;
import chaos.mod.util.handlers.TESRHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends ServerProxy{
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	public void registerVariantsRnderer(Item item, int meta, String filename, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), id));
	}
	
	public void registerTESRs() {
		TESRHandler.registerTESRs();
	}
}
