package chaos.mod.creativetabs;

import chaos.mod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Eki_misc extends CreativeTabs{
	public Eki_misc(String lable) {
		super(lable);
	}

	@Override
	public Item getTabIconItem() {
		return ItemInit.WRENCH;
	}
}
