package chaos.mod;

import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.proxy.ServerProxy;
import chaos.mod.util.Reference;
import chaos.mod.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
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
	public static final CreativeTabs BLOCK = new CreativeTabs("eki_block") {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(BlockInit.RETAINING_WALL);
		}
	};
	public static final CreativeTabs STATION = new CreativeTabs("eki_station") {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(BlockInit.HANDRAIL);
		}
	};
	public static final CreativeTabs MISC = new CreativeTabs("eki_misc") {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return ItemInit.WRENCH;
		}
	};

	@Instance
	public static Eki instance;

	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
	public static ServerProxy proxy;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.preInit();
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
	}

}
