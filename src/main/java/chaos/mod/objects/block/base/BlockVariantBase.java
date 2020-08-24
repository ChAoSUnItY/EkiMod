package chaos.mod.objects.block.base;

import chaos.mod.Eki;
import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.objects.block.item.ItemBlockVariants;
import chaos.mod.util.interfaces.IMetaName;
import chaos.mod.util.interfaces.IModelRegister;
import chaos.mod.util.utils.UtilTranslatable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class BlockVariantBase extends Block implements IMetaName, IModelRegister {
	protected final String name;
	protected boolean passable = false;
	protected boolean transluent = false;

	public BlockVariantBase(String name) {
		super(Material.ROCK);
		setUnlocalizedName(UtilTranslatable.getEki(name));
		setRegistryName(name);
		setCreativeTab(Eki.BLOCK);
		this.name = name;

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return null;
	}

	@Override
	public void registerModels() {

	}
}
