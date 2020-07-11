package chaos.mod.objects.block.slabs;

import net.minecraft.block.BlockSlab;

public class BlockSlabDoubleBaseCommon extends BlockSlabBaseCommon {
	public BlockSlabDoubleBaseCommon(String name, BlockSlab half) {
		super(name, half);
		setCreativeTab(null);
	}

	@Override
	public boolean isDouble() {
		return true;
	}
}
