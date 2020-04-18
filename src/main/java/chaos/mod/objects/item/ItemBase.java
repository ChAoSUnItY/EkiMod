package chaos.mod.objects.item;

import chaos.mod.Main;
import chaos.mod.init.ItemInit;
import chaos.mod.util.Reference;
import net.minecraft.item.Item;

public class ItemBase extends Item{
	public ItemBase(String name) {
		setUnlocalizedName(name);
		setTextureName(Reference.MODID+":"+name);
		setCreativeTab(Main.eki_misc_tab);
		
		ItemInit.ITEMS.add(this);
	}
}
