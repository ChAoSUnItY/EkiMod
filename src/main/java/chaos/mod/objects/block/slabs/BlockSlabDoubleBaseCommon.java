package chaos.mod.objects.block.slabs;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockSlabDoubleBaseCommon extends BlockSlabBaseCommon {
	public BlockSlabDoubleBaseCommon(String name, Material material, CreativeTabs tab, BlockSlab half) {
		super(name, material, tab, half);
	}

	@Override
	public boolean isDouble() {
		return true;
	}
}
