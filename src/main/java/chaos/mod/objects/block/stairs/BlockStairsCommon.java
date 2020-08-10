package chaos.mod.objects.block.stairs;

import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.util.interfaces.IModelRegister;
import chaos.mod.util.utils.UtilTranslatable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockStairsCommon extends BlockStairs implements IModelRegister {
	public BlockStairsCommon(String name, CreativeTabs tab, Block heritageBlock) {
		super(heritageBlock.getDefaultState());
		setUnlocalizedName(UtilTranslatable.getEki(name));
		setCreativeTab(tab);
		setRegistryName(name);
		useNeighborBrightness = true;

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
}
