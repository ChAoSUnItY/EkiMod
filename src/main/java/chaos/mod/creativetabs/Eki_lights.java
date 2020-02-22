package chaos.mod.creativetabs;

import chaos.mod.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Eki_lights extends CreativeTabs{
	public Eki_lights(String label) {
		super("eki_lights");
	}

	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return new ItemStack(BlockInit.GRILLE_LIGHT_BLACK);
	}
}
