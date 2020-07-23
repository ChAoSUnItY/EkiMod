package chaos.mod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import the_fireplace.grandeconomy.api.GrandEconomyApi;

public class TileEntityTicketVendor extends TileEntityBase {
	private long money;
	
	public void addMoney(long money) {
		this.money += money;
		markDirty();
	}

	public void removeMoney(long money) {
		this.money -= money;
		markDirty();
	}
	
	public void setMoney(long money) {
		this.money = money;
		markDirty();
	}

	public long getMoney() {
		return this.money;
	}
	
	public void withdrawMoney(EntityPlayer player, long money) {
		if (money >= this.money) {
			GrandEconomyApi.addToBalance(player.getGameProfile().getId(), (float) this.money, true);
			setMoney(0);
		} else {
			GrandEconomyApi.addToBalance(player.getGameProfile().getId(), money, true);
			removeMoney(money);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("money", this.money);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.money = compound.getLong("money");
	}

	public boolean isUsableByPlayer(EntityPlayer playerIn) {
		return this.world.getTileEntity(this.pos) == this;
	}
}
