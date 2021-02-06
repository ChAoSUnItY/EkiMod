package chaos.mod.objects.block.subblocks

import chaos.mod.Eki
import chaos.mod.init.BlockInit
import chaos.mod.init.ItemInit
import chaos.mod.objects.block.base.BlockHorizontalBase
import chaos.mod.objects.item.ItemImmersiveRetainingWall
import chaos.mod.util.interfaces.IModelRegister
import chaos.mod.util.utils.UtilTranslatable
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.IStringSerializable
import net.minecraft.util.Mirror
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import kotlin.math.floor

class BlockImmersiveRetainingWall : Block(Material.ROCK), IModelRegister {
    companion object {
        val FACING: PropertyDirection = BlockHorizontalBase.FACING

        val TYPES: PropertyEnum<ImmersiveRetainingWallType> =
            PropertyEnum.create("type", ImmersiveRetainingWallType::class.java)
    }

    init {
        unlocalizedName = UtilTranslatable.getEki("immersive_retaining_wall")
        setRegistryName("immersive_retaining_wall")
        setCreativeTab(Eki.BLOCK)
        setHardness(5f)

        BlockInit.BLOCKS.add(this)
        ItemInit.ITEMS.add(ItemImmersiveRetainingWall(this))
    }

    override fun isOpaqueCube(state: IBlockState): Boolean = false

    override fun isFullCube(state: IBlockState): Boolean = false

    override fun getBlockFaceShape(
        worldIn: IBlockAccess,
        state: IBlockState,
        pos: BlockPos,
        face: EnumFacing
    ): BlockFaceShape = BlockFaceShape.UNDEFINED

    override fun withRotation(state: IBlockState, rot: Rotation): IBlockState = state.withProperty(
        FACING,
        rot.rotate(state.getValue(BlockHorizontalBase.FACING) as EnumFacing)
    )

    override fun withMirror(state: IBlockState, mirrorIn: Mirror): IBlockState =
        state.withRotation(mirrorIn.toRotation(state.getValue(BlockHorizontalBase.FACING) as EnumFacing))

    override fun getMetaFromState(state: IBlockState): Int {
        var value = state.getValue(FACING).ordinal * 4
        value += state.getValue(TYPES).ordinal

        return value
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        var facing = EnumFacing.getFront(floor(meta / 4.0).toInt())
        val type = ImmersiveRetainingWallType.VALUES[meta % 4]

        if (facing.getAxis() == EnumFacing.Axis.Y) {
            facing = EnumFacing.NORTH
        }

        return defaultState.withProperty(FACING, facing).withProperty(TYPES, type)
    }

    override fun onBlockHarvested(worldIn: World, pos: BlockPos, state: IBlockState, player: EntityPlayer) {
        val wallType = state.getValue(TYPES)
        val facing = state.getValue(FACING)

        when (wallType ?: ImmersiveRetainingWallType.UL) {
            ImmersiveRetainingWallType.DL ->
                when (facing) {
                    EnumFacing.NORTH -> removeBlock(worldIn, pos) { p, x, y -> p.west(x).up(y) }
                    EnumFacing.SOUTH -> removeBlock(worldIn, pos) { p, x, y -> p.east(x).up(y) }
                    EnumFacing.EAST -> removeBlock(worldIn, pos) { p, x, y -> p.north(x).up(y) }
                    EnumFacing.WEST -> removeBlock(worldIn, pos) { p, x, y -> p.south(x).up(y) }
                }
            ImmersiveRetainingWallType.DR ->
                when (facing) {
                    EnumFacing.NORTH -> removeBlock(worldIn, pos) { p, x, y -> p.east(x).up(y) }
                    EnumFacing.SOUTH -> removeBlock(worldIn, pos) { p, x, y -> p.west(x).up(y) }
                    EnumFacing.EAST -> removeBlock(worldIn, pos) { p, x, y -> p.south(x).up(y) }
                    EnumFacing.WEST -> removeBlock(worldIn, pos) { p, x, y -> p.north(x).up(y) }
                }
            ImmersiveRetainingWallType.UL ->
                when (facing) {
                    EnumFacing.NORTH -> removeBlock(worldIn, pos) { p, x, y -> p.west(x).down(y) }
                    EnumFacing.SOUTH -> removeBlock(worldIn, pos) { p, x, y -> p.east(x).down(y) }
                    EnumFacing.EAST -> removeBlock(worldIn, pos) { p, x, y -> p.north(x).down(y) }
                    EnumFacing.WEST -> removeBlock(worldIn, pos) { p, x, y -> p.south(x).down(y) }
                }
            ImmersiveRetainingWallType.UR ->
                when (facing) {
                    EnumFacing.NORTH -> removeBlock(worldIn, pos) { p, x, y -> p.east(x).down(y) }
                    EnumFacing.SOUTH -> removeBlock(worldIn, pos) { p, x, y -> p.west(x).down(y) }
                    EnumFacing.EAST -> removeBlock(worldIn, pos) { p, x, y -> p.south(x).down(y) }
                    EnumFacing.WEST -> removeBlock(worldIn, pos) { p, x, y -> p.north(x).down(y) }
                }
        }
    }

    private inline fun removeBlock(
        world: World,
        pos: BlockPos,
        crossinline blockTransformer: (p: BlockPos, x: Int, y: Int) -> BlockPos
    ) {
        for (i in 0 until 2)
            for (j in 0 until 2) {
                val targetPos = blockTransformer.invoke(pos, j, i)

                if (isPart(world, targetPos))
                    removeBlock(world, targetPos)
            }
    }

    private fun removeBlock(world: World, pos: BlockPos) = world.setBlockToAir(pos)

    private fun isPart(world: World, pos: BlockPos): Boolean =
        world.getBlockState(pos).block is BlockImmersiveRetainingWall

    override fun createBlockState(): BlockStateContainer =
        BlockStateContainer(this, FACING, TYPES)


    enum class ImmersiveRetainingWallType(val type_name: String) : IStringSerializable {
        DL("down_left"), DR("down_right"), UL("up_left"), UR("up_right");

        companion object {
            val VALUES: Array<ImmersiveRetainingWallType> = values()
        }

        override fun getName(): String {
            return type_name
        }
    }
}