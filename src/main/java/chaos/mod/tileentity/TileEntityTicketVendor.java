package chaos.mod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import the_fireplace.grandeconomy.api.GrandEconomyApi;

public class TileEntityTicketVendor extends TileEntityRegistrable {
	private double money;

	public void addMoney(double money) {
		this.money += money;
		markDirty();
	}

	public void removeMoney(double money) {
		this.money -= money;
		markDirty();
	}

	public void setMoney(double money) {
		this.money = money;
		markDirty();
	}

	public double getMoney() {
		return this.money;
	}

	public void withdrawMoney(EntityPlayer player, double money) {
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
		compound.setDouble("money", this.money);
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
