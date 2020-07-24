package chaos.mod.util.events;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class StationCreatedEvent extends Event {
	private final BlockPos pos;
	private final String name;
	private final World world;
	
	public StationCreatedEvent(BlockPos pos, String name, World world) {
		this.pos = pos;
		this.name = name;
		this.world = world;
	}
	
	public BlockPos getPos() {
		return pos;
	}
	
	public String getName() {
		return name;
	}
	
	public World getWorld() {
		return world;
	}
}
