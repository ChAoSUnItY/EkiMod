package chaos.mod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import the_fireplace.grandeconomy.api.GrandEconomyApi;

public class TileEntityTicketVendor extends TileEntity {
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
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		int metadata = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound getTileData() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}
}
