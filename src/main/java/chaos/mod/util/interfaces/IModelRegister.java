package chaos.mod.util.interfaces;

import chaos.mod.Eki;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface IModelRegister {
	default void registerModels() {
		Eki.proxy.registerItemRenderer(Item.getItemFromBlock((Block) this), 0, "inventory");
	}
}
