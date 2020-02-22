package chaos.mod.creativetabs;

import java.util.Comparator;

import chaos.mod.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Eki_block extends CreativeTabs{
	static Comparator<ItemStack> tabSorter;
	
	public Eki_block(String label) {
		super("eki_block");
	}
	
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Item.getItemFromBlock(BlockInit.RETAINING_WALL);
	}	
}
