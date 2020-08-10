package chaos.mod.objects.block.base;

import chaos.mod.Eki;
import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.util.interfaces.IModelRegister;
import chaos.mod.util.utils.UtilTranslatable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IModelRegister {
	protected boolean passable = false;
	protected boolean transluent = false;
	
	public BlockBase(String name, CreativeTabs tab, Material material) {
		super(material);
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

	public Material getMaterial() {
		return blockMaterial;
	}
}