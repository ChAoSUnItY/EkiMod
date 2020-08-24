package chaos.mod.util.network;

import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.PacketHandler;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.utils.UtilByteBuf;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketAddStationWorker implements IMessage {
	private Station station;

	private boolean messageValid;

	public PacketAddStationWorker() {
		messageValid = false;
	}

	public PacketAddStationWorker(Station station) {
		this.station = station;

		messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		station = UtilByteBuf.readStation(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		UtilByteBuf.writeStation(buf, station);
	}

	public static class Handler implements IMessageHandler<PacketAddStationWorker, IMessage> {

		@Override
		public IMessage onMessage(final PacketAddStationWorker message, final MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.SERVER)
				return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(new Runnable() {
				@Override
				public void run() {
					processMessage(message, ctx);
				}
			});
			return null;
		}

		void processMessage(PacketAddStationWorker message, MessageContext ctx) {
			if (StationHandler.INSTANCE.replaceStation(message.station)) {
				PacketHandler.INSTANCE.sendToAll(new PacketNoticeStationChangedWorker(message.station, true));
			} else {
				StationHandler.INSTANCE.addNewStation(message.station);
				PacketHandler.INSTANCE.sendToAll(new PacketNoticeStationChangedWorker(message.station, false));
			}
		}
	}
}
