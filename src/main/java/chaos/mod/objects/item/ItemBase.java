package chaos.mod.objects.item;

import chaos.mod.Eki;
import chaos.mod.init.ItemInit;
import chaos.mod.util.interfaces.IModelRegister;
import chaos.mod.util.utils.UtilTranslatable;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IModelRegister {
	public ItemBase(String name) {
		setUnlocalizedName(UtilTranslatable.getEki(name));
		setRegistryName(name);
		setCreativeTab(Eki.MISC);

		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Eki.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
