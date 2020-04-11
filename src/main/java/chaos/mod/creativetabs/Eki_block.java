package chaos.mod.creativetabs;

import chaos.mod.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Eki_block extends CreativeTabs{
	public Eki_block(String lable) {
		super(lable);
	}

	@Override
	public Item getTabIconItem() {
		return new ItemStack(BlockInit.ARCHITECTURAL_CONCRETE).getItem();
	}
}
