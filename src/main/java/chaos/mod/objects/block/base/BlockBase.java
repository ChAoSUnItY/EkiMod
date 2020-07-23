package chaos.mod.objects.block.base;

import chaos.mod.Eki;
import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.util.interfaces.IHasModel;
import chaos.mod.util.utils.UtilTranslatable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {
	public BlockBase(String name, CreativeTabs tab) {
		super(Material.ROCK);
		constructor(name, tab);
	}

	private void constructor(String name, CreativeTabs tab) {
		setUnlocalizedName(UtilTranslatable.getEki(name));
		setRegistryName(name);
		setCreativeTab(tab == null ? Eki.BLOCK : tab);
		setHardness(3);
		setResistance(20);

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Eki.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
