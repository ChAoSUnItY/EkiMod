package chaos.mod.objects.block.base;

import chaos.mod.Eki;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLights extends BlockBase {
	public BlockLights(String name, Float light, Material material, boolean passable, boolean transluent) {
		super(name, Eki.MISC, material);
		setSoundType(SoundType.GLASS);
		setLightLevel(light);
		this.passable = passable;
		this.transluent = transluent;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return transluent ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return passable ? NULL_AABB : FULL_BLOCK_AABB;
	}

	public static class BlockFourFaceLights extends BlockFourFace {
		public BlockFourFaceLights(String name, Float light, Material material, boolean passable, boolean transluent) {
			super(name, Eki.MISC, material, false);
			setSoundType(SoundType.GLASS);
			setLightLevel(light);
			this.passable = passable;
			this.transluent = transluent;
		}

		@Override
		public BlockRenderLayer getBlockLayer() {
			return transluent ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
		}

		@Override
		public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
			return passable ? NULL_AABB : FULL_BLOCK_AABB;
		}
	}

	public static class BlockSixFaceLights extends BlockSixFace {
		public BlockSixFaceLights(String name, Float light, Material material, boolean passable, boolean transluent) {
			super(name, Eki.MISC, material, false);
			setSoundType(SoundType.GLASS);
			setLightLevel(light);
			this.passable = passable;
			this.transluent = transluent;
		}

		@Override
		public BlockRenderLayer getBlockLayer() {
			return transluent ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
		}

		@Override
		public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
			return passable ? NULL_AABB : FULL_BLOCK_AABB;
		}
	}
}
