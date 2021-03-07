package chaos.mod.objects.block.backport.item;

import chaos.mod.objects.block.backport.ImmersiveRetainingWallLadderBlock;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ImmersiveRetainingWallLadderBlockItem extends ItemBlock {
    public ImmersiveRetainingWallLadderBlockItem(ImmersiveRetainingWallLadderBlock block) {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
        boolean flag = false;
        IBlockState state = newState.withProperty(BlockHorizontal.FACING, player.getHorizontalFacing().getOpposite());
        for (int i = 0; i < 2; i++) {
            if (world.getBlockState(pos.up(i)).getBlockHardness(world, pos.up(i)) == 0.0F)
                flag |= world.setBlockState(
                        pos.up(i),
                        state.withProperty(
                                ImmersiveRetainingWallLadderBlock.TYPE,
                                ImmersiveRetainingWallLadderBlock.ImmersiveRetainingWallLadderType.VALUES[i])
                );
        }
        return flag;
    }
}
