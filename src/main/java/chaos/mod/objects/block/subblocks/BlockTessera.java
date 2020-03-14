package chaos.mod.objects.block.subblocks;

import chaos.mod.Main;
import chaos.mod.init.BlockInit;
import chaos.mod.init.ItemInit;
import chaos.mod.objects.block.item.ItemBlockVariants;
import chaos.mod.util.handlers.EnumHandler;
import chaos.mod.util.handlers.EnumHandler.TesseraEnumWallEnum;
import chaos.mod.util.interfaces.IHasModel;
import chaos.mod.util.interfaces.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockTessera extends Block implements IHasModel, IMetaName {
	public static final PropertyEnum<EnumHandler.TesseraEnumWallEnum> VARIANT = PropertyEnum.<EnumHandler.TesseraEnumWallEnum>create(
			"variant", EnumHandler.TesseraEnumWallEnum.class);
	private final String name;

	public BlockTessera(String name, Material material, CreativeTabs tab) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.TesseraEnumWallEnum.BLACK_DULL));
		this.name = name;

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumHandler.TesseraEnumWallEnum) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, TesseraEnumWallEnum.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((TesseraEnumWallEnum) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumHandler.TesseraEnumWallEnum variant : TesseraEnumWallEnum.values()) {
			items.add(new ItemStack(this, 1, variant.getMeta()));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumHandler.TesseraEnumWallEnum.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels() {
		for (int i = 0; i < TesseraEnumWallEnum.values().length; i++) {
			Main.proxy.registerVariantsRnderer(Item.getItemFromBlock(this), i,
					name + "_" + TesseraEnumWallEnum.values()[i].getName(), "inventory");
		}
	}
}
