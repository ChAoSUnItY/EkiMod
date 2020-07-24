package chaos.mod.util.utils;

import java.text.DecimalFormat;

import chaos.mod.Eki.EkiConfig;
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
}
