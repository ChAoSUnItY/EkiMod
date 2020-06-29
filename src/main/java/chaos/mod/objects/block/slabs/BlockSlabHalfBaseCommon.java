package chaos.mod.objects.block.slabs;

import chaos.mod.Eki;
import chaos.mod.init.ItemInit;
import chaos.mod.util.interfaces.IHasModel;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;

public class BlockSlabHalfBaseCommon extends BlockSlabBaseCommon implements IHasModel {
	public BlockSlabHalfBaseCommon(String name, BlockSlab half, BlockSlab doubleSlab) {
		super(name, half);

		ItemInit.ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public void registerModels() {
		Eki.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
