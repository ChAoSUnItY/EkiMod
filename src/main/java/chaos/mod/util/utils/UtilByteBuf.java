package chaos.mod.util.utils;

import chaos.mod.util.data.station.Station;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class UtilByteBuf {
	public static void writePos(ByteBuf buf, BlockPos pos) {
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
	}

	public static void writeStation(ByteBuf buf, Station station) {
		ByteBufUtils.writeUTF8String(buf, station.getName());
		writePos(buf, station.getPos());
	}

	public static BlockPos readPos(ByteBuf buf) {
		return new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}

	public static Station readStation(ByteBuf buf) {
		return new Station(ByteBufUtils.readUTF8String(buf), readPos(buf));
	}
}
