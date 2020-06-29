package chaos.mod.objects.item;

import java.util.List;

import chaos.mod.util.utils.UtilTextFormer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRangeFinder extends ItemBase {
	public ItemRangeFinder(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		if (!world.isRemote) {
			ItemStack stack = player.getHeldItem(hand);
			TextComponentString text;
			if (stack.hasTagCompound() && stack.getTagCompound().hasKey("startPos")) {
				if (player.isSneaking()) {
					NBTTagCompound nbt = new NBTTagCompound();
					stack.setTagCompound(nbt);
					return EnumActionResult.SUCCESS;
				}
				int[] startPos = stack.getTagCompound().getIntArray("startPos");
				int[] endPos = new int[] { pos.getX(), pos.getY(), pos.getZ() };
				int ThreeDimLength = (int) Math.round(Math.sqrt(Math.pow(endPos[0] - startPos[0], 2)
						+ Math.pow(endPos[1] - startPos[1], 2) + Math.pow(endPos[2] - startPos[2], 2)));
				int TwoDimLength = (int) Math
						.round(Math.sqrt(Math.pow(endPos[0] - startPos[0], 2) + Math.pow(endPos[2] - startPos[2], 2)));
				text = new TextComponentString(
						I18n.format("chat.type.text.rangefinder.result", ThreeDimLength, TwoDimLength));
				UtilTextFormer.form(text, TextFormatting.WHITE, false, false);
				player.sendMessage(text);
				return EnumActionResult.SUCCESS;
			} else {
				int[] startPos = new int[] { pos.getX(), pos.getY(), pos.getZ() };
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setIntArray("startPos", startPos);
				stack.setTagCompound(nbt);
				text = new TextComponentString(
						I18n.format("chat.type.text.rangefinder.getpos", pos.getX(), pos.getY(), pos.getZ()));
				UtilTextFormer.form(text, TextFormatting.GREEN, false, false);
				player.sendMessage(text);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		boolean hasTag = stack.hasTagCompound() && stack.getTagCompound().hasKey("startPos");
		tooltip.add(TextFormatting.GRAY + I18n.format(getUnlocalizedName() + ".tooltip",
				hasTag ? stack.getTagCompound().getIntArray("startPos")[0] : null,
				hasTag ? stack.getTagCompound().getIntArray("startPos")[1] : null,
				hasTag ? stack.getTagCompound().getIntArray("startPos")[2] : null));
	}

}
