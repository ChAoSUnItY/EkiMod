package chaos.mod.objects.item;

import java.util.List;

import chaos.mod.util.utils.UtilBlockPos;
import chaos.mod.util.utils.UtilStationSystem;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		if (!world.isRemote) {
			if (stack.hasTagCompound() && stack.getTagCompound().hasKey("startPos")) {
				if (player.isSneaking()) {
					NBTTagCompound nbt = new NBTTagCompound();
					stack.setTagCompound(nbt);
					return EnumActionResult.SUCCESS;
				}
				BlockPos targetPos = UtilBlockPos.getPos(stack.getTagCompound().getIntArray("startPos"));
				int ThreeDimLength = (int) pos.getDistance(targetPos.getX(), targetPos.getY(), targetPos.getZ());
				int TwoDimLength = (int) UtilStationSystem.calculateLength(pos, targetPos);
				player.addChatMessage(new UtilTCString(TranslateType.CHAT, "rangefinder.result", ThreeDimLength, TwoDimLength).applyFormat(TextFormatting.WHITE));
				return EnumActionResult.SUCCESS;
			} else {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setIntArray("startPos", UtilBlockPos.getIntArray(pos));
				stack.setTagCompound(nbt);
				player.addChatMessage(new UtilTCString(TranslateType.CHAT, "rangefinder.getPos", pos.getX(), pos.getY(), pos.getZ()).applyFormat(TextFormatting.GREEN));
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		boolean hasTag = stack.hasTagCompound() && stack.getTagCompound().hasKey("startPos");
		tooltip.add(new UtilTCString(getUnlocalizedName() + ".tooltip", hasTag ? stack.getTagCompound().getIntArray("startPos")[0] : null,
				hasTag ? stack.getTagCompound().getIntArray("startPos")[1] : null, hasTag ? stack.getTagCompound().getIntArray("startPos")[2] : null).applyFormat(TextFormatting.GRAY)
						.getFormattedText());
	}
}
