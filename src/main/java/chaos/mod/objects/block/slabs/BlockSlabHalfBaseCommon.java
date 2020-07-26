package chaos.mod.objects.block.slabs;

import chaos.mod.init.ItemInit;
import chaos.mod.util.interfaces.IModelRegister;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;

public class BlockSlabHalfBaseCommon extends BlockSlabBaseCommon implements IModelRegister {
	public BlockSlabHalfBaseCommon(String name, BlockSlab half, BlockSlab doubleSlab) {
		super(name, half);

		ItemInit.ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
	}

	@Override
	public boolean isDouble() {
		return false;
	}
}
