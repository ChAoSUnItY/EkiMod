package chaos.mod.objects.block.container;

import chaos.mod.tileentity.TileEntityAnchor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerScreen extends Container {
	private TileEntityAnchor te;
	
	public ContainerScreen(TileEntityAnchor te) {
		this.te = te;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.isUsableByPlayer(playerIn);
	}
}
