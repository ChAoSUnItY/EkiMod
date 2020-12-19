package com.chaos.eki.objects.items;

import com.chaos.eki.utils.handler.RegistryHandler;
import com.chaos.eki_lib.utils.util.UtilDistanceHelper;
import com.chaos.eki_lib.utils.util.UtilStationConverter;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class RangeFinderItem extends Item {
    public static final String PRESERVED_STARTPOS = "startPos";

    public RangeFinderItem() {
        super(RegistryHandler.DEFAULT_PROPERTIES);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getPos();
        if (!context.getWorld().isRemote) {
            if (stack.hasTag() && stack.getTag().contains(PRESERVED_STARTPOS)) {
                assert player != null;
                if (player.isSneaking()) {
                    stack.setTag(new CompoundNBT());
                    return ActionResultType.SUCCESS;
                }
                BlockPos targetPos = UtilStationConverter.toBlockPos(stack.getTag(), PRESERVED_STARTPOS);
                int ThreeDimLength = (int) Math.sqrt(context.getPos().distanceSq(targetPos.getX(), targetPos.getY(), targetPos.getZ(), true));
                int TwoDimLength = (int) UtilDistanceHelper.calculateLength(pos, targetPos);
                player.sendMessage(new TranslationTextComponent("chat.type.text.eki.rangefinder.result", ThreeDimLength, TwoDimLength).applyTextStyle(TextFormatting.WHITE));
            } else {
                CompoundNBT nbt = new CompoundNBT();
                nbt.putIntArray(PRESERVED_STARTPOS, UtilStationConverter.toIntegerArray(pos));
                stack.setTag(nbt);
                assert player != null;
                player.sendMessage(new TranslationTextComponent("chat.type.text.eki.rangefinder.getPos", pos.getX(), pos.getY(), pos.getZ()).applyTextStyle(TextFormatting.GREEN));
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent(
                getTranslationKey() + ".tooltip",
                buildInfo(stack.getTag(), stack.hasTag() && stack.getTag().contains(PRESERVED_STARTPOS))
        ).applyTextStyle(TextFormatting.GRAY));
    }

    private Object[] buildInfo(CompoundNBT nbt, boolean hasTag) {
        if (hasTag)
            return Arrays.stream(nbt.getIntArray(PRESERVED_STARTPOS)).boxed().toArray();
        else
            return new Object[]{null, null, null};
    }
}
