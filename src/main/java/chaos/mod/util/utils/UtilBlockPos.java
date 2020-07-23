package chaos.mod.util.utils;

import net.minecraft.util.math.BlockPos;

public class UtilBlockPos {
	public static int[] getIntArray(BlockPos p) {
		return new int[] {p.getX(), p.getY(), p.getZ()};
	}
	
	public static BlockPos getPos(int[] p) {
		return new BlockPos(p[0], p[1], p[2]);
	}
}
