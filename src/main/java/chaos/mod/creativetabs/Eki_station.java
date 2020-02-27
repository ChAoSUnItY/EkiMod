package chaos.mod.creativetabs;

import chaos.mod.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Eki_station extends CreativeTabs{
	public Eki_station(String label) {
		super(label);
	}
	
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Item.getItemFromBlock(BlockInit.HANDRAIL);
	}
}
