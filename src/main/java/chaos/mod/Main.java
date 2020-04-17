package chaos.mod;

import chaos.mod.creativetabs.Eki_block;
import chaos.mod.creativetabs.Eki_misc;
import chaos.mod.creativetabs.Eki_station;
import chaos.mod.proxy.ServerProxy;
import chaos.mod.util.Reference;
import chaos.mod.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	public static boolean isApiModLoaded;
	public static final CreativeTabs eki_block_tab = new Eki_block("eki_block");
	public static final CreativeTabs eki_station_tab = new Eki_station("eki_station");
	public static final CreativeTabs eki_misc_tab =new Eki_misc("eki_misc");
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
	public static ServerProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.preInit();
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {}
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		isApiModLoaded = RegistryHandler.modChecker();
	}
	
}
