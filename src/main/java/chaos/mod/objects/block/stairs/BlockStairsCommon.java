package chaos.mod.objects.block.stairs;

import chaos.mod.Eki;
import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.util.interfaces.IHasModel;
import chaos.mod.util.utils.UtilTranslatable;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockStairsCommon extends BlockStairs implements IHasModel{
	public BlockStairsCommon(String name, CreativeTabs tab, IBlockState state) {
		super(state);
		this.setUnlocalizedName(UtilTranslatable.getEki(name));
		this.setCreativeTab(tab);
		this.setRegistryName(name);
		this.useNeighborBrightness = true;
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		Eki.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
