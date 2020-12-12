package com.chaos.eki.objects.items;

import com.chaos.eki.utils.handler.RegistryHandler;
import com.chaos.eki_lib.objects.blocks.base.HorizontalBaseBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class StationPlatformOPFMultiBlockItem extends MultiBlockItemBase {
    public StationPlatformOPFMultiBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Nonnull
    @Override
    protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
        BlockPos pos = context.getPos();
        Direction dir = context.getPlacementHorizontalFacing().getOpposite();
        BlockState newStateBase =
                RegistryHandler.SP_OPF_BASE.get().getDefaultState().with(HorizontalBaseBlock.FACING, dir),
                newStatePlatform = state.with(HorizontalBaseBlock.FACING, dir);
        World world = context.getWorld();
        return world.isAirBlock(pos.up()) ? world.setBlockState(pos, newStateBase, 11) && world.setBlockState(pos.up(), newStatePlatform, 11) : false;
    }
}
