package chaos.mod.tileentity;

import chaos.mod.util.utils.UtilBlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityTicketGate extends TileEntityBase {
	public BlockPos AnchorPos;
	public boolean isRegistered;

	public TileEntityTicketGate() {
	}

	public void setAnchor(BlockPos pos) {
		AnchorPos = pos;
		isRegistered = true;
	}

	public boolean isBound() {
		return isRegistered;
	}

	public BlockPos getAnchor() {
		return !isRegistered ? pos : AnchorPos;
	}
	
	public boolean isSame(BlockPos pos) {
		if (!isRegistered) return false;
		return AnchorPos.equals(pos);
	}

	public void reset() {
		AnchorPos = this.getPos();
		isRegistered = false;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("registered", isRegistered);
		if (isRegistered)
			compound.setIntArray("anchor", UtilBlockPos.getIntArray(AnchorPos));
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		isRegistered = compound.getBoolean("registered");
		if (isRegistered)
			AnchorPos = UtilBlockPos.getPos(compound.getIntArray("anchor"));
	}
}
