package chaos.mod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IStringSerializable;

public class TileEntityTicketVendor extends TileEntityRegistrable {
	private double money;

	public TileEntityTicketVendor() {
		money = 0;
	}

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
		return money;
	}

	public void withdrawMoney(EntityPlayer player, double money) {
		/*
		 * if (money >= this.money) {
		 * GrandEconomyApi.addToBalance(player.getGameProfile().getId(), (float)
		 * this.money, true); setMoney(0); } else {
		 * GrandEconomyApi.addToBalance(player.getGameProfile().getId(), money, true);
		 * removeMoney(money); }
		 */
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setDouble("money", money);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		money = compound.getDouble("money");

	}

	public boolean isUsableByPlayer(EntityPlayer playerIn) {
		return worldObj.getTileEntity(pos) == this;
	}

	public enum SortType implements IStringSerializable {
		NF("NF"), FN("FN"), NAME("Name");

		private String name;

		public static final SortType[] TYPES = values();

		SortType(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	}
}
