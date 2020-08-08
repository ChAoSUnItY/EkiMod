package chaos.mod.objects.block.container;

import chaos.mod.tileentity.TileEntityTicketVendor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerTicketVendor extends Container {
	private TileEntityTicketVendor tileEnitity;

	public ContainerTicketVendor(InventoryPlayer invPlayer, TileEntityTicketVendor tileEntity, EntityPlayer player) {
		this.tileEnitity = tileEntity;

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileEnitity.isUsableByPlayer(playerIn);
	}
}
