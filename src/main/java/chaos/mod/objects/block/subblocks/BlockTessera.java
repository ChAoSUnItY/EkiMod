package chaos.mod.objects.block.subblocks;

import com.google.common.collect.Lists;

import chaos.mod.Eki;
import chaos.mod.objects.block.base.BlockVariantBase;
import chaos.mod.util.interfaces.IModelRegister;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockTessera extends BlockVariantBase implements IModelRegister {
	public static final PropertyEnum<TesseraEnumWallEnum> VARIANT = PropertyEnum.<TesseraEnumWallEnum>create("variant", TesseraEnumWallEnum.class);

	public BlockTessera(String name) {
		super(name);
		setDefaultState(blockState.getBaseState().withProperty(VARIANT, TesseraEnumWallEnum.BLACK_DULL));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(VARIANT).getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, TesseraEnumWallEnum.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).getMeta();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (TesseraEnumWallEnum variant : TesseraEnumWallEnum.META_LOOKUP)
			items.add(new ItemStack(this, 1, variant.getMeta()));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return TesseraEnumWallEnum.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels() {
		Lists.newArrayList(TesseraEnumWallEnum.META_LOOKUP)
				.forEach(val -> Eki.proxy.registerVariantsRnderer(Item.getItemFromBlock(this), val.getMeta(), name + "_" + TesseraEnumWallEnum.values()[val.getMeta()].getName(), "inventory"));
	}

	public static enum TesseraEnumWallEnum implements IStringSerializable {
		BLACK_DULL("black_dull", 0), CERULEAN_SMOOTH("cerulean_smooth", 1), CERULEAN("cerulean", 2), BLUE_SLATE("blue_slate", 3), BLUE_TEAL("blue_teal", 4), BROWN_TORTILLA("brown_tortilla", 5),
		GRAY_DOVE("gray_dove", 6), GRAY_FOSSIL("gray_fossil", 7), GRAY("gray", 8), GREEN_FERN("green_fern", 9), GREEN_LIME("green_lime", 10), ORANGE_FIRE("orange_fire", 11),
		PURPLE_HEATHER("purple_heather", 12), RED_ROSE("red_rose", 13), RED("red", 14), YELLOW("yellow", 15);

		private static final TesseraEnumWallEnum[] META_LOOKUP = values();
		private String name;
		private int meta;

		private TesseraEnumWallEnum(String name, int meta) {
			this.name = name;
			this.meta = meta;
		}

		@Override
		public String getName() {
			return name;
		}

		public int getMeta() {
			return meta;
		}

		@Override
		public String toString() {
			return getName();
		}

		public static TesseraEnumWallEnum byMetadata(int meta) {
			return META_LOOKUP[meta];
		}
	}
}
