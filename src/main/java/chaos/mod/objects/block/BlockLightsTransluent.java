package chaos.mod.objects.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockLightsTransluent extends BlockLights {
	private final boolean Passable;

	public BlockLightsTransluent(String name, Material material, CreativeTabs tab, Float light, boolean Passable) {
		super(name, material, tab, light);
		this.Passable = Passable;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		if (!Passable) {
			return NULL_AABB;
		}
		return NULL_AABB;
	}
}
