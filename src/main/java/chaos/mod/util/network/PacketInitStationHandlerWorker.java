package chaos.mod.util.network;

import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.utils.UtilByteBuf;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketInitStationHandlerWorker implements IMessage {
	private List<Station> stations;
	private int staT;

	private boolean messageValid;

	public PacketInitStationHandlerWorker() {
		messageValid = false;
	}

	public PacketInitStationHandlerWorker(List<Station> stations) {
		this.stations = stations;
		staT = stations.size();

		messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// stations
		staT = buf.readInt();
		stations = Lists.newArrayList();
		for (int i = 0; i < staT; i++) {
			stations.add(UtilByteBuf.readStation(buf));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// stations
		buf.writeInt(staT);
		for (Station s : stations)
			UtilByteBuf.writeStation(buf, s);
	}

	public static class Handler implements IMessageHandler<PacketInitStationHandlerWorker, IMessage> {

		@Override
		public IMessage onMessage(final PacketInitStationHandlerWorker message, final MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT)
				return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(new Runnable() {
				@Override
				public void run() {
					processMessage(message, ctx);
				}
			});
			return null;
		}

		void processMessage(PacketInitStationHandlerWorker message, MessageContext ctx) {
			StationHandler.INSTANCE.init(message.stations);
		}
	}
}
