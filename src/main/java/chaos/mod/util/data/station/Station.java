package chaos.mod.util.data.station;

import net.minecraft.util.math.BlockPos;

public class Station extends DataForm {
	public static final Station EXAMPLE = new Station(new BlockPos(1, 1, 1), "TEST_STATION");
	private BlockPos pos;
	private String name;

	public Station(BlockPos pos, String name) {
		this.pos = pos;
		this.name = name;
	}
	
	public Station(String name, BlockPos pos) {
		this(pos, name);
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

	@Override
	public String getData() {
		return name + " - " + getPosStringFormat();
	}
	
	public String getPosStringFormat() {
		return "(" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")";
	}
}
