package chaos.mod.util.data.station;

import net.minecraft.util.math.BlockPos;

public class Station {
	public static final Station EXAMPLE = new Station(new BlockPos(1, 1, 1), "TEST_STATION");
	private BlockPos pos;
	private String name;

	public Station(BlockPos pos, String name) {
		this.pos = pos;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public BlockPos getPos() {
		return pos;
	}

	@Override
	public boolean equals(Object obj) {
		Station sta = (Station) obj;
		return sta.getPos().equals(pos) && sta.getName().equals(name);
	}

	public String getPosStringFormat() {
		return pos.getX() + ", " + pos.getY() + ", " + pos.getZ();
	}
}
