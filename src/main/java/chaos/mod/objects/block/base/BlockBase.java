package chaos.mod.objects.block.base;

import chaos.mod.init.BlockInit;
import chaos.mod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBase extends Block {
	public BlockBase(String name, Material material, CreativeTabs tab) {
		super(material);
		setBlockName(name);
		setBlockTextureName(Reference.MODID+":"+name);
		setCreativeTab(tab);
		setHardness(3);
		setResistance(20);
		
		BlockInit.BLOCKS.add(this);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(this.getTextureName());
	}
}
