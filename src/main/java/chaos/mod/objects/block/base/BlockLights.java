package chaos.mod.objects.block.base;

import chaos.mod.Eki;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockLights extends BlockBase {
	public BlockLights(String name, Float light, Material material, boolean passable, boolean transluent) {
		super(name, Eki.MISC, material);
		setSoundType(SoundType.GLASS);
		setLightLevel(light);
		this.passable = passable;
		this.transluent = transluent;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return transluent ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return passable ? NULL_AABB : FULL_BLOCK_AABB;
	}

	public static class BlockHorizontalBaseLights extends BlockHorizontalBase {
		public BlockHorizontalBaseLights(String name, Float light, Material material, boolean passable, boolean transluent) {
			super(name, Eki.MISC, material, false);
			setSoundType(SoundType.GLASS);
			setLightLevel(light);
			this.passable = passable;
			this.transluent = transluent;
		}

		@Override
		public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
			return BlockFaceShape.UNDEFINED;
		}

		@Override
		public BlockRenderLayer getBlockLayer() {
			return transluent ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
		}

		@Override
		public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
			return passable ? NULL_AABB : FULL_BLOCK_AABB;
		}
	}

	public static class BlockDirectionalBaseLights extends BlockDirectionalBase {
		public BlockDirectionalBaseLights(String name, Float light, Material material, boolean passable, boolean transluent) {
			super(name, Eki.MISC, material, false);
			setSoundType(SoundType.GLASS);
			setLightLevel(light);
			this.passable = passable;
			this.transluent = transluent;
		}

		@Override
		public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
			return BlockFaceShape.UNDEFINED;
		}

		@Override
		public BlockRenderLayer getBlockLayer() {
			return transluent ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
		}

		@Override
		public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
			return passable ? NULL_AABB : FULL_BLOCK_AABB;
		}
	}
}
