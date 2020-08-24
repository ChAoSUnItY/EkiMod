package chaos.mod.util.network;

import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.utils.UtilByteBuf;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketNoticeStationChangedWorker implements IMessage {
	private Station station;
	private boolean replace;

	private boolean messageValid;

	public PacketNoticeStationChangedWorker() {
		messageValid = false;
	}

	public PacketNoticeStationChangedWorker(Station station, boolean replace) {
		this.station = station;
		this.replace = replace;

		messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		station = UtilByteBuf.readStation(buf);
		replace = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		UtilByteBuf.writeStation(buf, station);
		buf.writeBoolean(replace);
	}

	public static class Handler implements IMessageHandler<PacketNoticeStationChangedWorker, IMessage> {

		@Override
		public IMessage onMessage(final PacketNoticeStationChangedWorker message, final MessageContext ctx) {
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

		void processMessage(PacketNoticeStationChangedWorker message, MessageContext ctx) {
			if (message.replace) {
				StationHandler.INSTANCE.replaceStation(message.station);
			} else {
				if (Minecraft.getMinecraft().isSingleplayer())
					return;

				StationHandler.INSTANCE.addNewStation(message.station);
			}
		}
	}
}
