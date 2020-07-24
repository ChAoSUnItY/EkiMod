package chaos.mod;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.proxy.ServerProxy;
import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.util.Reference;
import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.RegistryHandler;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.utils.UtilTranslatable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
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

	@EventHandler
	public void onServerStart(FMLServerStartingEvent event) {
		StationHandler.INSTANCE.init(event.getServer().getEntityWorld());
		
		List<BlockPos> invalidPos = Lists.newArrayList();

		for (Station sta : StationHandler.INSTANCE.getStations()) {
			if (event.getServer().getEntityWorld().getTileEntity(sta.getPos()) instanceof TileEntityAnchor) {
				TileEntityAnchor teA = (TileEntityAnchor) event.getServer().getEntityWorld().getTileEntity(sta.getPos());
				if (teA.isValidStation()) {
					return;
				}
			}
			invalidPos.add(sta.getPos());
		}

		if (invalidPos.isEmpty())
			return;

		for (BlockPos pos : invalidPos) {
			StationHandler.INSTANCE.tryRemoveStation(pos);
		}
	}
	
	@EventHandler
	public void onServerStop(FMLServerStoppingEvent event) {
		StationHandler.INSTANCE.saveAll();
	}

	@Config(modid = Reference.MODID, category = "Ticket System")
	public static class EkiConfig {
		@Config.Comment("Multiplier for ticket calculation, formula: MULTIPLIER * (LENGTH TRAVELED / 100) = PRICE")
		@RangeInt(min = 1, max = 10000)
		public static int priceMultiplier = 100;
	}
}
