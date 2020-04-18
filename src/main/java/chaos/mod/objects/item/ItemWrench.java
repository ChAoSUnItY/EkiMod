package chaos.mod.objects.item;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemWrench extends ItemBase{
	public ItemWrench(String name) {
		super(name);
		setMaxStackSize(1);
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		tooltip.add(EnumChatFormatting.GRAY + I18n.format(getUnlocalizedName() + ".tooltip"));
	}
}
