package chaos.mod.objects.block.base;

import java.util.List;

import chaos.mod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockSlabBase extends BlockSlab {
	private Block block;

	public BlockSlabBase(boolean isFull, Block block, Material material, CreativeTabs tab) {
		super(isFull, material);
		this.block = block;
		useNeighborBrightness = true;
		setBlockName(block.getUnlocalizedName().substring(5) + (isFull ? "_full_slab" : "_half_slab"));
		setCreativeTab(!isFull ? tab : null);
		setHardness(3);
		setResistance(20);

		if (isFull) {
			BlockInit.FULL_SLAB.add(this);
		} else {
			BlockInit.HALF_SLAB.add(this);
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.block.registerBlockIcons(iconRegister);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.block.getIcon(side, meta);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	// Method : getFullSlabName()
	public String func_150002_b(int p_150002_1_) {
		return this.block.getUnlocalizedName().substring(5) + "_slab";
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return this.field_150004_a ? new ItemStack(this.block) : new ItemStack(this);
	}
}
