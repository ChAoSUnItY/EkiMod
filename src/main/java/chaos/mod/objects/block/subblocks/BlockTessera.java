package chaos.mod.objects.block.subblocks;

import java.util.List;

import chaos.mod.init.BlockInit;
import chaos.mod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockTessera extends Block {
	public static final String types[] = new String[] { "black_dull", "cerulean_smooth", "cerulean", "blue_slate",
			"blue_teal", "brown_tortilla", "gray_dove", "gray_fossil", "gray", "green_fern", "green_lime",
			"orange_fire", "purple_heather", "red_rose", "red", "yellow"};
	private IIcon[] textures;

	public BlockTessera(String name, Material material, CreativeTabs tab) {
		super(material);
		setBlockName(name);
		setBlockTextureName(Reference.MODID + ":" + name);
		setCreativeTab(tab);

		BlockInit.META_BLOCKS.add(this);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		textures = new IIcon[types.length];
		
		for (int i = 0; i < types.length; ++i) {
			textures[i] = iconRegister.registerIcon(this.getTextureName()+"_"+types[i]);
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}
		return textures[meta];
	}
	
	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
		for(int i=0;i<types.length;i++) {
			list.add(new ItemStack(block, 1, i));
		}
	}
}
