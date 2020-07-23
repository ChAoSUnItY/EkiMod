package chaos.mod.objects.item;

import java.util.List;

import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTicket extends ItemBase {
	public ItemTicket(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound()) {
			tooltip.add(new UtilTCString(getUnlocalizedName() + ".tooltip", stack.getTagCompound().getInteger("value")).applyFormat(TextFormatting.GRAY).getFormattedText());
		}
	}
}
