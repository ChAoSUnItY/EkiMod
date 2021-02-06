package chaos.mod.proxy;

import chaos.mod.tileentity.TileEntityAnchor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy {
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	public void displayManager() {

	}
	
	public void displayBarbedWireSetConfig(ItemStack stack, EntityPlayer player, EnumHand hand) {
		
	}
	
	public void displayAnchor(TileEntityAnchor te) {
		
	}
	
	public void displayStationMap(EntityPlayer player) {
		
	}

	public void registerItemRenderer(Item item, int meta, String id) {

	}

	public void registerVariantsRnderer(Item item, int meta, String filename, String id) {

	}

	public void registerTESRs() {

	}
}
