package chaos.mod.objects.item;

import chaos.mod.Eki;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemStationManager extends ItemBase {
	public ItemStationManager(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (worldIn.isRemote)
			Eki.proxy.displayManager();
		return new ActionResult<ItemStack>(EnumActionResult.PASS, new ItemStack(this));
	}
}
