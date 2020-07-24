package chaos.mod.tileentity;

import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.util.data.station.Station;
import chaos.mod.util.events.StationCreatedEvent;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.utils.UtilBlockPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityAnchor extends TileEntityBase {
	public List<BlockPos> gatesPos;
	
	public TileEntityAnchor() {
		gatesPos = Lists.newArrayList();
	}
	
	public void setValid(String name) {
		StationCreatedEvent event = new StationCreatedEvent(pos, name, world);
		MinecraftForge.EVENT_BUS.post(event);
	}
	
	public boolean isValidStation() {
		return StationHandler.INSTANCE.isExist(pos);
	}
	
	public Station getStation() {
		return StationHandler.INSTANCE.getStation(pos);
	}
	
	public boolean removeStation() {
		return StationHandler.INSTANCE.tryRemoveStation(pos);
	}

	public void addGate(BlockPos pos) {
		gatesPos.add(pos);
	}

	public void deleteGate(BlockPos pos) {
		for (int i = 0; i < gatesPos.size(); i++) {
			if (gatesPos.get(i).equals(pos)) {
				gatesPos.remove(i);
				return;
			}
		}
	}

	public void resetAllGate() {
		for (BlockPos p : gatesPos) {
			TileEntityRegistrable teTG = (TileEntityRegistrable) world.getTileEntity(p);
			teTG.reset();
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for (BlockPos p : gatesPos) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setIntArray("gate", UtilBlockPos.getIntArray(p));
			list.appendTag(tag);
		}
		compound.setTag("anchorOBJ", list);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList list = compound.getTagList("anchorOBJ", 10);
		gatesPos = Lists.newArrayList();
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound tag = list.getCompoundTagAt(i);
			gatesPos.add(UtilBlockPos.getPos(tag.getIntArray("gate")));
		}
	}
}
