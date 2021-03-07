package chaos.mod.objects.block.backport.item;

import chaos.mod.init.BlockInit;
import chaos.mod.objects.block.backport.ImmersiveRetainingWallBlock;
import chaos.mod.util.interfaces.TriFunction;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ImmersiveRetainingWallBlockItem extends ItemBlock {
    public ImmersiveRetainingWallBlockItem(ImmersiveRetainingWallBlock block) {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
        IBlockState state = newState.withProperty(ImmersiveRetainingWallBlock.FACING, player.getHorizontalFacing().getOpposite());

        switch (player.getHorizontalFacing()) {
            case NORTH:
                return setupMultiBlock(world, pos, state, (p, x, y) -> p.east(x).up(y));
            case SOUTH:
                return setupMultiBlock(world, pos, state, (p, x, y) -> p.west(x).up(y));
            case EAST:
                return setupMultiBlock(world, pos, state, (p, x, y) -> p.south(x).up(y));
            case WEST:
                return setupMultiBlock(world, pos, state, (p, x, y) -> p.north(x).up(y));
            default:
                return false;
        }
    }

    private boolean setupMultiBlock(World world, BlockPos pos, IBlockState state, TriFunction<BlockPos, Integer, Integer, BlockPos> posFunction) {
        boolean flag = false;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                BlockPos targetPos = posFunction.apply(pos, j, i);
                if (world.getBlockState(targetPos).getBlockHardness(world, targetPos) == 0.0F)
                    flag |= world.setBlockState(targetPos,
                            state.withProperty(ImmersiveRetainingWallBlock.TYPE, ImmersiveRetainingWallBlock.ImmersiveRetainingWallType.VALUES[j + i * 2]));
            }
        return flag;
    }
}
