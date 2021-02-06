package chaos.mod.objects.item

import chaos.mod.init.BlockInit
import chaos.mod.objects.block.base.BlockHorizontalBase
import chaos.mod.objects.block.subblocks.BlockImmersiveRetainingWall
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ItemImmersiveRetainingWall(block: Block) : ItemBlock(block) {
    override fun placeBlockAt(
        stack: ItemStack,
        player: EntityPlayer,
        world: World,
        pos: BlockPos,
        side: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float,
        state: IBlockState
    ): Boolean {
        val newState = BlockInit.IMMERSIVE_RETAINING_WALL
            .defaultState
            .withProperty(BlockHorizontalBase.FACING, side.opposite)

        if (!world.setBlockState(pos, newState, 11)) return false

        return when (side) {
            EnumFacing.NORTH -> setupMultiBlock(world, pos, newState) { p, x, y -> p.east(x).up(y) }
            EnumFacing.SOUTH -> setupMultiBlock(world, pos, newState) { p, x, y -> p.west(x).up(y) }
            EnumFacing.EAST -> setupMultiBlock(world, pos, newState) { p, x, y -> p.south(x).up(y) }
            EnumFacing.WEST -> setupMultiBlock(world, pos, newState) { p, x, y -> p.north(x).up(y) }
            else -> false
        }
    }

    private inline fun setupMultiBlock(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        crossinline posTransformer: (p: BlockPos, x: Int, y: Int) -> BlockPos
    ): Boolean {
        val flag = false

        for (i in 0 until 2)
            for (j in 0 until 2) {
                val targetPos = posTransformer.invoke(pos, j, i)

                if (world.getBlockState(targetPos).getBlockHardness(world, targetPos) == 0.0F)
                    flag.or(
                        world.setBlockState(
                            targetPos,
                            state.withProperty(
                                BlockImmersiveRetainingWall.TYPES,
                                BlockImmersiveRetainingWall.ImmersiveRetainingWallType.VALUES[j + i * 2]
                            )
                        )
                    )
            }

        return flag
    }
}