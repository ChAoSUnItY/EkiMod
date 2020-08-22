package chaos.mod.util.data.station;

import net.minecraft.util.math.BlockPos;

public class Station extends DataForm {
	public static final Station EXAMPLE = new Station(new BlockPos(1, 1, 1), "TEST_STATION", "ChAoS_UnItY", EnumStationLevel.STAFFLESS);
	private BlockPos pos;
	private String name;
	private EnumStationLevel lvl;
	private String operator;

	public Station(BlockPos pos, String name, String operator, EnumStationLevel lvl) {
		this.pos = pos;
		this.name = name;
		this.operator = operator;
		this.lvl = lvl;
	}

	/**
	 * For bytebuf order.
	 * 
	 * @param name
	 * @param pos
	 */
	public Station(String name, BlockPos pos, String operator, EnumStationLevel lvl) {
		this(pos, name, operator, lvl);
	}

	public String getName() {
		return name;
	}

	public BlockPos getPos() {
		return pos;
	}

	public EnumStationLevel getLvl() {
		return lvl;
	}

	public String getOperator() {
		return operator;
	}

	@Override
	public boolean equals(Object obj) {
		Station sta = (Station) obj;
		return sta.getPos().equals(pos) && sta.getName().equals(name);
	}

	@Override
	public String getData() {
		return String.format("%s - %s", name, getPosStringFormat());
	}

	public String getPosStringFormat() {
		return String.format("(%d,%d,%d)", pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public String[] toStringArray() {
		return null;
	}
}
