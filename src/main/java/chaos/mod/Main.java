package chaos.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import chaos.mod.creativetabs.Eki_block;
import chaos.mod.creativetabs.Eki_misc;
import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.objects.block.item.ItemBlockMeta;
import chaos.mod.objects.block.item.ItemBlockSlab;
import chaos.mod.proxy.ServerProxy;
import chaos.mod.util.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	public static final Logger LOGGER = LogManager.getLogger("Eki Mod");
	public static final CreativeTabs eki_block_tab = new Eki_block("eki_block");
	public static final CreativeTabs eki_misc_tab = new Eki_misc("eki_misc");
	// public static final CreativeTabs eki_station_tab = new
	// Eki_station("eki_station");

	@Instance("EkiMod")
	public static Main instance;

	@SidedProxy(serverSide = Reference.SERVER, clientSide = Reference.CLIENT)
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLInitializationEvent event) {
		for (int i = 0; i < BlockInit.BLOCKS.size(); i++) {
			GameRegistry.registerBlock(BlockInit.BLOCKS.get(i),
					BlockInit.BLOCKS.get(i).getUnlocalizedName().substring(5));
		}
		LOGGER.info("Blocks Registered!");
		for (int i = 0; i < BlockInit.META_BLOCKS.size(); i++) {
			GameRegistry.registerBlock(BlockInit.META_BLOCKS.get(i), ItemBlockMeta.class,
					BlockInit.META_BLOCKS.get(i).getUnlocalizedName().substring(5));
		}
		LOGGER.info("Blocks /w meta Registered!");
		for (int i = 0; i < BlockInit.HALF_SLAB.size(); i++) {
			GameRegistry.registerBlock(BlockInit.HALF_SLAB.get(i), ItemBlockSlab.class,
					BlockInit.HALF_SLAB.get(i).getUnlocalizedName().substring(5), BlockInit.HALF_SLAB.get(i),
					BlockInit.FULL_SLAB.get(i));
			GameRegistry.registerBlock(BlockInit.FULL_SLAB.get(i), ItemBlockSlab.class,
					BlockInit.FULL_SLAB.get(i).getUnlocalizedName().substring(5), BlockInit.HALF_SLAB.get(i),
					BlockInit.FULL_SLAB.get(i));
		}
		LOGGER.info("Slabs Registered!");
		for (int i = 0; i < ItemInit.ITEMS.size(); i++) {
			GameRegistry.registerItem(ItemInit.ITEMS.get(i), ItemInit.ITEMS.get(i).getUnlocalizedName().substring(5));
		}
		LOGGER.info("Items Registered!");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@EventHandler
	public void postInit(FMLInitializationEvent event) {
	}
}
