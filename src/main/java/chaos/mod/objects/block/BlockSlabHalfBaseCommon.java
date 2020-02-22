package chaos.mod.objects.block;

import chaos.mod.Main;
import chaos.mod.init.ItemInit;
import chaos.mod.util.IHasModel;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;

public class BlockSlabHalfBaseCommon extends BlockSlabBaseCommon implements IHasModel {
	public BlockSlabHalfBaseCommon(String name, Material material, CreativeTabs tab, BlockSlab half,
			BlockSlab doubleSlab) {
		super(name, material, tab, half);

		ItemInit.ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
