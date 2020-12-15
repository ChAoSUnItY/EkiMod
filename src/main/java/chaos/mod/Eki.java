package chaos.mod;

import java.io.File;

import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.proxy.ServerProxy;
import chaos.mod.util.Reference;
import chaos.mod.util.handlers.RegistryHandler;
import chaos.mod.util.utils.UtilTranslatable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Eki {
	public static final CreativeTabs BLOCK = new CreativeTabs(UtilTranslatable.getEki("block")) {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(BlockInit.RETAINING_WALL);
		}
	};
	public static final CreativeTabs STATION = new CreativeTabs(UtilTranslatable.getEki("station")) {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(BlockInit.HANDRAIL);
		}
	};
	public static final CreativeTabs MISC = new CreativeTabs(UtilTranslatable.getEki("misc")) {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return ItemInit.WRENCH;
		}
	};

	@Instance("eki")
	public static Eki instance;

	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
	public static ServerProxy proxy;

	public static File config;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.preInit(event);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
	}

	@EventHandler
	public void onServerStart(FMLServerStartingEvent event) {
		RegistryHandler.serverInit(event);
	}

	@EventHandler
	public void onServerStop(FMLServerStoppingEvent event) {
		RegistryHandler.serverStop(event);
	}

	@Config(modid = Reference.MODID)
	public static class EkiConfig {
		@Config.Comment("Multiplier for ticket calculation, formula: MULTIPLIER * (LENGTH TRAVELED / 100) = PRICE")
		@RangeInt(min = 1, max = 10000)
		public static int priceMultiplier = 100;

		@Config.Comment("Flag to decide should display nametag on valid stations or not.")
		public static boolean nametagDisplay = true;
		
		@Config.Comment("HotFix Here - 1.3.3 //TODO: FIX THE ACTUAL URL, BUT THIS FEATURE IS UNDER DISCUSSION WHETHER TURN TO DEPRECATED FEATURE OR KEEP UPDATING")
		public static boolean versionCheck = false;
	}
}
