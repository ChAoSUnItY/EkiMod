package chaos.mod.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityTicketGate extends TileEntity {
	private boolean hasAnchorPos = false;
	private int PosX;
	private int PosZ;
	private int PosY;

	public void saveAnchorPosIn(int posX, int posZ, int posY) {
		if (!world.isRemote) {
			this.PosX = posX;
			this.PosZ = posZ;
			this.PosY = posY;
			this.hasAnchorPos = true;
			this.markDirty();
		}
	}

	public int[] getAnchorPos() {
		return hasAnchorPos ? new int[] { PosX, PosZ } : new int[] { pos.getX(), pos.getZ(), pos.getY() };
	}

	public BlockPos getAnchorPosForSub() {
		return new BlockPos(PosX, PosY, PosZ);
	}

	public boolean hasAnchorPos() {
		return hasAnchorPos;
	}

	public boolean isAnchorExists() {
		if (!hasAnchorPos)
			return false;
		return world.getTileEntity(new BlockPos(this.PosX, this.PosY, this.PosZ)) instanceof TileEntityAnchor;
	}
	
	public boolean hasAnothorAnchor(ItemStack stack) {
		if (!hasAnchorPos) return false;
		int[] another = stack.getTagCompound().getIntArray("pos");
		return new int[] {this.PosX, this.PosZ, this.PosY} != another;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("hasAnchorPos", this.hasAnchorPos);
		compound.setInteger("anchored_posX", this.PosX);
		compound.setInteger("anchored_posZ", this.PosZ);
		compound.setInteger("anchored_posY", this.PosY);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.hasAnchorPos = compound.getBoolean("hasAnchorPos");
		this.PosX = compound.getInteger("anchored_posX");
		this.PosZ = compound.getInteger("anchored_posZ");
		this.PosY = compound.getInteger("anchored_posY");
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
