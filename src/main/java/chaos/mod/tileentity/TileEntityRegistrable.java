package chaos.mod.tileentity;

import chaos.mod.util.utils.UtilBlockPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public abstract class TileEntityRegistrable extends TileEntityBase {
	public BlockPos anchorPos;
	public boolean isRegistered;
	
	public void setAnchor(BlockPos pos) {
		anchorPos = pos;
		isRegistered = true;
	}

	public boolean isBound() {
		return isRegistered;
	}

	public BlockPos getAnchor() {
		return !isRegistered ? pos : anchorPos;
	}
	
	public boolean isSame(BlockPos pos) {
		if (!isRegistered) return false;
		return anchorPos.equals(pos);
	}

	public void reset() {
		anchorPos = this.getPos();
		isRegistered = false;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("registered", isRegistered);
		if (isRegistered)
			compound.setIntArray("anchor", UtilBlockPos.getIntArray(anchorPos));
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		isRegistered = compound.getBoolean("registered");
		if (isRegistered)
			anchorPos = UtilBlockPos.getPos(compound.getIntArray("anchor"));
	}
}
