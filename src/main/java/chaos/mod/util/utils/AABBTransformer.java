package chaos.mod.util.utils;

import net.minecraft.util.math.AxisAlignedBB;

public class AABBTransformer extends AxisAlignedBB {
    public AABBTransformer(double x1, double y1, double z1, double x2, double y2, double z2) {
        super(x1 / 16, y1 / 16, z1 / 16, x2 / 16, y2 / 16, z2 / 16);
    }
}
