package chaos.mod.util.utils

import net.minecraft.util.math.AxisAlignedBB

case class AABBTransformer(x1: Double,
                           y1: Double,
                           z1: Double,
                           x2: Double,
                           y2: Double,
                           z2: Double)
  extends AxisAlignedBB(x1 / 16,
    y1 / 16,
    z1 / 16,
    x2 / 16,
    y2 / 16,
    z2 / 16)
