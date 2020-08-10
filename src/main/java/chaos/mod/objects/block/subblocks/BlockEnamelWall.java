package chaos.mod.objects.block.subblocks;

import com.google.common.collect.Lists;

import chaos.mod.Eki;
import chaos.mod.objects.block.base.BlockVariantBase;
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

public class BlockEnamelWall extends BlockVariantBase {
	public static final PropertyEnum<EnamelWallEnumWallType> VARIANT = PropertyEnum.<EnamelWallEnumWallType>create("variant", EnamelWallEnumWallType.class);

	public BlockEnamelWall(String name) {
		super(name);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnamelWallEnumWallType.BLUE));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(VARIANT).getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnamelWallEnumWallType.byMetadata(meta));
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
		for (EnamelWallEnumWallType variant : EnamelWallEnumWallType.values()) {
			items.add(new ItemStack(this, 1, variant.getMeta()));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnamelWallEnumWallType.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels() {
		Lists.newArrayList(EnamelWallEnumWallType.META_LOOKUP)
				.forEach(val -> Eki.proxy.registerVariantsRnderer(Item.getItemFromBlock(this), val.getMeta(), name + "_" + EnamelWallEnumWallType.values()[val.getMeta()].getName(), "inventory"));
	}

	public static enum EnamelWallEnumWallType implements IStringSerializable {
		BLUE("blue", 0), GREEN("green", 1), CYAN("cyan", 2), PURPLE("purple", 3), RED("red", 4), YELLOW("yellow", 5), WHITE("white", 6);

		private static final EnamelWallEnumWallType[] META_LOOKUP = new EnamelWallEnumWallType[values().length];
		private String name;
		private int meta;

		private EnamelWallEnumWallType(String name, int meta) {
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

		public static EnamelWallEnumWallType byMetadata(int meta) {
			return META_LOOKUP[meta];
		}

		static {
			for (EnamelWallEnumWallType type : values()) {
				META_LOOKUP[type.getMeta()] = type;
			}
		}
	}
}
