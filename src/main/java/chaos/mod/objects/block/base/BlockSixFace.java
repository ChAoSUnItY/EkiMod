package chaos.mod.objects.block.base;

import chaos.mod.util.interfaces.IModelRegister;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSixFace extends BlockBase implements IModelRegister {
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	public final boolean isOpaqueCube;

	protected BlockSixFace(String name, CreativeTabs tab, Material material, boolean isOpaqueCube) {
		super(name, tab, material);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.isOpaqueCube = isOpaqueCube;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return isOpaqueCube ? true : false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return isOpaqueCube ? true : false;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack) {
		IBlockState state = world.getBlockState(pos.offset(facing.getOpposite()));

		if (state.getBlock() == Blocks.END_ROD) {
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

			if (enumfacing == facing) {
				return getDefaultState().withProperty(FACING, facing.getOpposite());
			}
		}

		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState();
		state = state.withProperty(FACING, EnumFacing.getFront(meta));
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
}
