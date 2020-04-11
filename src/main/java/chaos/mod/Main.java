package chaos.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import chaos.mod.creativetabs.Eki_block;
import chaos.mod.creativetabs.Eki_misc;
import chaos.mod.creativetabs.Eki_station;
import chaos.mod.init.BlockInit;
import chaos.mod.objects.block.item.ItemBlockMeta;
import chaos.mod.proxy.ServerProxy;
import chaos.mod.util.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlockWithMetadata;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	public static final Logger LOGGER = LogManager.getLogger("Eki Mod");
	public static final CreativeTabs eki_block_tab = new Eki_block("eki_block");
	//public static final CreativeTabs eki_station_tab = new Eki_station("eki_station");
	//public static final CreativeTabs eki_misc_tab =new Eki_misc("eki_misc");
	
	@Instance("EkiMod")
	public static Main instance;
	
	@SidedProxy(serverSide = Reference.SERVER, clientSide = Reference.CLIENT)
	public static ServerProxy proxy;
	
	@EventHandler
	public void preInit(FMLInitializationEvent event) {
		for(int i=0;i<BlockInit.BLOCKS.size();i++) {
			GameRegistry.registerBlock(BlockInit.BLOCKS.get(i), BlockInit.BLOCKS.get(i).getUnlocalizedName().substring(5));
		}
		
		for (int i=0;i<BlockInit.METABLOCKS.size(); i++) {
			GameRegistry.registerBlock(BlockInit.METABLOCKS.get(i), ItemBlockMeta.class, BlockInit.METABLOCKS.get(i).getUnlocalizedName());
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
	
	@EventHandler
	public void posInit(FMLInitializationEvent event) {
	}
}
