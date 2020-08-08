package chaos.mod.util.handlers;

import chaos.mod.Eki;
import chaos.mod.commands.CommandForceSaveStations;
import chaos.mod.commands.CommandNametagDisplay;
import chaos.mod.commands.CommandStations;
import chaos.mod.commands.CommandTeleportStation;
import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.util.Reference;
import chaos.mod.util.interfaces.IModelRegister;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * This mod is remade by ChAoS_UnItY, owned by noto. This file is created for
 * 1.12.2 version of station mod.
 */
@EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
		Eki.proxy.registerTESRs();
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (Item item : ItemInit.ITEMS) {
			if (item instanceof IModelRegister)
				((IModelRegister) item).registerModels();
		}

		for (Block block : BlockInit.BLOCKS) {
			if (block instanceof IModelRegister)
				((IModelRegister) block).registerModels();
		}
	}

	public static void preInit(FMLPreInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Eki.instance, new GuiHandler());
		PacketHandler.registerMessages(Reference.MODID);
	}

	public static void init(FMLInitializationEvent event) {

	}

	public static void postInit(FMLPostInitializationEvent event) {

	}

	public static void serverInit(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandNametagDisplay());
		event.registerServerCommand(new CommandForceSaveStations());
		event.registerServerCommand(new CommandStations());
		event.registerServerCommand(new CommandTeleportStation());
		StationHandler.INSTANCE.init(event.getServer().getEntityWorld());
	}

	public static void serverStop(FMLServerStoppingEvent event) {
		StationHandler.INSTANCE.saveAll();
	}

	public static boolean modChecker() {
		return Loader.isModLoaded("grandeconomy");
	}
}
