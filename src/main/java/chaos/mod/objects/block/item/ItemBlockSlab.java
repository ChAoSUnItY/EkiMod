package chaos.mod.objects.block.item;

import chaos.mod.objects.block.base.BlockSlabBase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;

public class ItemBlockSlab extends ItemSlab{
	public ItemBlockSlab(Block block, BlockSlabBase singleSlab, BlockSlabBase doubleSlab) {
		super(block, singleSlab, doubleSlab, block == doubleSlab);
	}
}
