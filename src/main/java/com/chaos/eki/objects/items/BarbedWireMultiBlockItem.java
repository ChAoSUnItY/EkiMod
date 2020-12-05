package com.chaos.eki.objects.items;

import com.chaos.eki.Eki;
import com.chaos.eki.objects.blocks.multiblock.BarbedWireMultiBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class BarbedWireMultiBlockItem extends MultiBlockItemBase {
    public static final String PRESERVED_BUILDING_SIZE = "building_size";

    public BarbedWireMultiBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!stack.hasTag()) {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt(PRESERVED_BUILDING_SIZE, 1);
            stack.setTag(nbt);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn.isRemote)
            Eki.proxy.openBarbedWireModificationScreen(playerIn, handIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
        assert context.getItem().getTag() != null;
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        boolean flag = true;
        if (isPlaceable(world, pos))
            world.setBlockState(
                    pos,
                    state.with(BarbedWireMultiBlock.TYPES, BarbedWireMultiBlock.BarbedWireType.BOTTOM)
            );
        for (int i = 1; i < context.getItem().getTag().getInt(PRESERVED_BUILDING_SIZE) + 1; i++) {
            if (isPlaceable(world, pos.up(i)))
                world.setBlockState(
                        pos.up(i),
                        state.with(BarbedWireMultiBlock.TYPES, BarbedWireMultiBlock.BarbedWireType.CENTER)
                );
            else
                flag = false;
            if (!flag)
                break;
        }
        if (flag)
            world.setBlockState(
                    pos.up(context.getItem().getTag().getInt(PRESERVED_BUILDING_SIZE) + 1),
                    state.with(BarbedWireMultiBlock.TYPES, BarbedWireMultiBlock.BarbedWireType.TOP)
            );
        return true;
    }

    private boolean isPlaceable(World world, BlockPos pos) {
        return world.getBlockState(pos).getCollisionShape(world, pos).isEmpty() ||
                world.getBlockState(pos).getBlockHardness(world, pos) == 0.0F;
    }

    @Override
    public String getTranslationKey() {
        return super.getTranslationKey().replace("block", "item") + "_set";
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(buildInfo(stack)));
    }

    private String buildInfo(ItemStack stack) {
        return "Size : " + (stack.hasTag() && Objects.requireNonNull(stack.getTag()).contains(PRESERVED_BUILDING_SIZE) ? stack.getTag().getInt(PRESERVED_BUILDING_SIZE) : null);
    }
}
