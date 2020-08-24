package chaos.mod.proxy;

import chaos.mod.objects.block.gui.GuiAnchor;
import chaos.mod.objects.item.gui.GuiBarbedWireSetConfig;
import chaos.mod.objects.item.gui.GuiMainPage;
import chaos.mod.objects.item.gui.GuiStationMap;
import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.util.Reference;
import chaos.mod.util.handlers.RenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends ServerProxy {
	public static KeyBinding[] binds;

	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	public void registerVariantsRnderer(Item item, int meta, String filename, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), id));
	}

	public void registerTESRs() {
		RenderHandler.registerTESRs();
	}

	public void displayManager() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiMainPage(Minecraft.getMinecraft().thePlayer.getPosition(), null));
	}

	public void displayBarbedWireSetConfig(ItemStack stack, EntityPlayer player, EnumHand hand) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiBarbedWireSetConfig(stack, player, hand));
	}

	public void displayAnchor(TileEntityAnchor te) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiAnchor(te));
	}

	@Override
	public void displayStationMap(EntityPlayer player) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiStationMap(player));
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		/*
		 * binds = new KeyBinding[1]; binds[0] = new KeyBinding("key.viewSta.desc",
		 * Keyboard.KEY_M, "key.eki.category");
		 * ClientRegistry.registerKeyBinding(binds[0]);
		 */
	}
}
