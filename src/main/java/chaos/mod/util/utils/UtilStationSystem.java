package chaos.mod.util.utils;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import chaos.mod.Eki.EkiConfig;
import chaos.mod.util.data.station.Station;
import net.minecraft.util.math.BlockPos;

public class UtilStationSystem {
	private static DecimalFormat df;

	public static double calculatePrice(BlockPos posA, BlockPos posB) {
		double p = (double) (calculateLength(posA, posB) / 100) * EkiConfig.priceMultiplier;
		df = new DecimalFormat("0.00");
		return Double.parseDouble(df.format(p));
	}

	public static double calculateLength(BlockPos posA, BlockPos posB) {
		double l = (double) (Math.round(Math.sqrt(Math.pow(posB.getX() - posA.getX(), 2) + Math.pow(posB.getZ() - posA.getZ(), 2))));
		df = new DecimalFormat("0.00");
		return Double.parseDouble(df.format(l));
	}

	/***
	 * From near to far.
	 */
	public static List<Station> sortByFarNF(final BlockPos pos, List<Station> stations) {
		Collections.sort(stations, new Comparator<Station>() {
			@Override
			public int compare(Station a, Station b) {
				double pa = calculateLength(pos, a.getPos());
				double pb = calculateLength(pos, b.getPos());
				if (pa > pb)
					return 1;
				if (pa < pb)
					return -1;
				return 0;
			}
		});
		return stations;
	}

	/***
	 * From far to near.
	 */
	public static List<Station> sortByFarFN(final BlockPos pos, List<Station> stations) {
		Collections.sort(stations, new Comparator<Station>() {
			@Override
			public int compare(Station a, Station b) {
				double pa = calculateLength(pos, a.getPos());
				double pb = calculateLength(pos, b.getPos());
				if (pa < pb)
					return 1;
				if (pa > pb)
					return -1;
				return 0;
			}
		});
		return stations;
	}

	public static List<Station> sortByName(List<Station> stations) {
		Collections.sort(stations, new Comparator<Station>() {
			@Override
			public int compare(Station a, Station b) {
				String sa = a.getName();
				String sb = b.getName();
				if (sa.compareToIgnoreCase(sb) < 0)
					return -1;
				if (sa.compareToIgnoreCase(sb) > 0)
					return 1;
				return 0;
			}
		});
		return stations;
	}
}
