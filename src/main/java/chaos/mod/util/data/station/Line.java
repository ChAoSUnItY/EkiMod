package chaos.mod.util.data.station;

import java.util.List;

public class Line extends DataForm {
	private String name;
	private List<Station> stas;
	private int color;

	public Line(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Station> getStas() {
		return stas;
	}

	public void addStationPassThrough(Station sta) {
		stas.add(sta);
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public String getData() {
		return name;
	}

	@Override
	public String[] toStringArray() {
		return null;
	}

}
