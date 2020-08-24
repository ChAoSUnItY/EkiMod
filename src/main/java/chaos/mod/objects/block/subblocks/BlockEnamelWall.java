package chaos.mod.objects.block.subblocks;

import java.util.List;

import chaos.mod.Eki;
import chaos.mod.objects.block.base.BlockVariantBase;
import chaos.mod.util.interfaces.IMetaName;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockEnamelWall extends BlockVariantBase implements IModelRegister, IMetaName {
	public static final PropertyEnum<EnamelWallEnumWallType> VARIANT = PropertyEnum.<EnamelWallEnumWallType>create("variant", EnamelWallEnumWallType.class);
	private final String name;

	public BlockEnamelWall(String name) {
		super(name);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnamelWallEnumWallType.BLUE));
		this.name = name;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return ((EnamelWallEnumWallType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnamelWallEnumWallType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnamelWallEnumWallType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		for (EnamelWallEnumWallType variant : EnamelWallEnumWallType.values()) {
			list.add(new ItemStack(this, 1, variant.getMeta()));
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
		for (int i = 0; i < EnamelWallEnumWallType.values().length; i++) {
			Eki.proxy.registerVariantsRnderer(Item.getItemFromBlock(this), i, name + "_" + EnamelWallEnumWallType.values()[i].getName(), "inventory");
		}
	}

	public static enum EnamelWallEnumWallType implements IStringSerializable {
		BLUE("blue", 0), GREEN("green", 1), CYAN("cyan", 2), PURPLE("purple", 3), RED("red", 4), YELLOW("yellow", 5), WHITE("white", 6);

		private static final EnamelWallEnumWallType[] META_LOOKUP = values();
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
	}
}
