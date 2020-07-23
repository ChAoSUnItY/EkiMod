package chaos.mod;

import java.io.File;

import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.proxy.ServerProxy;
import chaos.mod.util.Reference;
import chaos.mod.util.handlers.RegistryHandler;
import chaos.mod.util.utils.UtilTranslatable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Eki {
	public static boolean isApiModLoaded;
	public static final CreativeTabs BLOCK = new CreativeTabs(UtilTranslatable.getEki("block")) {
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() {
			return new ItemStack(BlockInit.RETAINING_WALL);
		}
	};
	public static final CreativeTabs STATION = new CreativeTabs(UtilTranslatable.getEki("station")) {
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() {
			return new ItemStack(BlockInit.HANDRAIL);
		}
	};
	public static final CreativeTabs MISC = new CreativeTabs(UtilTranslatable.getEki("misc")) {
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemInit.WRENCH);
		}
	};

	@Instance
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
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		isApiModLoaded = RegistryHandler.modChecker();
	}

	@Config(modid = Reference.MODID, category = "Ticket System")
    public static class EkiConfig {
        @Config.Comment("Multiplier for ticket calculation, formula: MULTIPLIER * (LENGTH TRAVELED / 100) = PRICE")
        @RangeInt(min = 1, max = 10000)
        public static int priceMultiplier = 100;
    }
}
