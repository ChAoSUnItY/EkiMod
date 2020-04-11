package chaos.mod.objects.block.item;

import chaos.mod.objects.block.subblocks.BlockEnamelWall;
import chaos.mod.objects.block.subblocks.BlockTessera;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockMeta extends ItemBlockWithMetadata{
	private String[] subBlocksList;
	
	public ItemBlockMeta(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		//improve the readability of block's unlocalized name.
		Block block = this.field_150939_a;
		if (block instanceof BlockEnamelWall) {
			this.subBlocksList = BlockEnamelWall.types;
		} else if (block instanceof BlockTessera) {
			this.subBlocksList = BlockTessera.types;
		}
		return this.getUnlocalizedName() + "_" + this.subBlocksList[itemStack.getItemDamage()];
	}
}
