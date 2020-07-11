package chaos.mod.objects.item;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWrench extends ItemBase {
	public ItemWrench(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound()) {
			int[] pos = stack.getTagCompound().getIntArray("pos");
			tooltip.add(TextFormatting.GRAY
					+ I18n.format(getUnlocalizedName() + ".withtag.tooltip", pos[0], pos[2], pos[1]));
		} else {
			tooltip.add(TextFormatting.GRAY + I18n.format(getUnlocalizedName() + ".withouttag.tooltip"));
		}
	}
}
