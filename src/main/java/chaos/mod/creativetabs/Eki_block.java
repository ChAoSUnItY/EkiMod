package chaos.mod.creativetabs;

import chaos.mod.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Eki_block extends CreativeTabs{
	public Eki_block(String label) {
		super(label);
	}
	
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return new ItemStack(BlockInit.RETAINING_WALL);
	}	
}
