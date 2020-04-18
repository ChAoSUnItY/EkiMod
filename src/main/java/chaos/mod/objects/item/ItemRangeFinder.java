package chaos.mod.objects.item;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemRangeFinder extends ItemBase{
	public ItemRangeFinder(String name) {
		super(name);
		setMaxStackSize(1);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			ChatComponentText text;
			if (stack.hasTagCompound() && stack.getTagCompound().hasKey("startPos")) {
				if (player.isSneaking()) {
					NBTTagCompound nbt = new NBTTagCompound();
					stack.setTagCompound(nbt);
					return true;
				}
				int[] startPos = stack.getTagCompound().getIntArray("startPos");
				int[] endPos = new int[] { x, y, z };
				int ThreeDimLength = (int) Math.round(Math.sqrt(Math.pow(endPos[0] - startPos[0], 2)
						+ Math.pow(endPos[1] - startPos[1], 2) + Math.pow(endPos[2] - startPos[2], 2)));
				int TwoDimLength = (int) Math
						.round(Math.sqrt(Math.pow(endPos[0] - startPos[0], 2) + Math.pow(endPos[2] - startPos[2], 2)));
				text = new ChatComponentText(
						I18n.format("chat.type.text.rangefinder.result", ThreeDimLength, TwoDimLength));
				text.getChatStyle().setColor(EnumChatFormatting.WHITE);
				player.addChatComponentMessage(text);
				return true;
			} else {
				int[] startPos = new int[] { x, y, z };
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setIntArray("startPos", startPos);
				stack.setTagCompound(nbt);
				text = new ChatComponentText(
						I18n.format("chat.type.text.rangefinder.getpos", x, y, z));
				text.getChatStyle().setColor(EnumChatFormatting.GREEN);
				player.addChatComponentMessage(text);
				return true;
			}
		}
		return false;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		boolean hasTag = stack.hasTagCompound() && stack.getTagCompound().hasKey("startPos");
		tooltip.add(EnumChatFormatting.GRAY + I18n.format(getUnlocalizedName() + ".tooltip",
				hasTag ? stack.getTagCompound().getIntArray("startPos")[0] : null,
				hasTag ? stack.getTagCompound().getIntArray("startPos")[1] : null,
				hasTag ? stack.getTagCompound().getIntArray("startPos")[2] : null));
	}
}
