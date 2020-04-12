package chaos.mod.objects.block.base;

import chaos.mod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;

public class BlockStairsBase extends BlockStairs {
	public BlockStairsBase(Block model, CreativeTabs tab) {
		super(model, 0);
		setBlockName(model.getUnlocalizedName().substring(5) + "_stairs");
		setCreativeTab(tab);
		
		BlockInit.BLOCKS.add(this);
	}
}
