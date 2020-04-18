package chaos.mod.objects.block.item;

import chaos.mod.objects.block.base.BlockSlabBase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemBlockSlab extends ItemSlab{
	public ItemBlockSlab(Block block, BlockSlabBase singleSlab, BlockSlabBase doubleSlab) {
		super(block, singleSlab, doubleSlab, block == doubleSlab);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return this.getUnlocalizedName();
	}
}
