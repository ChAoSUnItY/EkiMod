package chaos.mod.objects.item;

import java.util.List;

import chaos.mod.util.utils.UtilBlockPos;
import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWrench extends ItemBase {
	public ItemWrench(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if (stack.hasTagCompound()) {
			BlockPos pos = UtilBlockPos.getPos(stack.getTagCompound().getIntArray("pos"));
			tooltip.add(new UtilTCString(getUnlocalizedName() + ".withtag.tooltip", pos.getX(), pos.getY(), pos.getZ()).applyFormat(TextFormatting.GRAY).getFormattedText());
		} else {
			tooltip.add(new UtilTCString(getUnlocalizedName() + ".withouttag.tooltip").applyFormat(TextFormatting.GRAY).getFormattedText());
		}
	}
}
