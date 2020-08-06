package chaos.mod.util.network;

import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.utils.UtilByteBuf;
import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketNoticeStationChangedWorker implements IMessage {
	private Station station;
	private boolean add;

	private boolean messageValid;

	public PacketNoticeStationChangedWorker() {
		messageValid = false;
	}

	public PacketNoticeStationChangedWorker(Station station, boolean add) {
		this.station = station;
		this.add = add;

		messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		station = UtilByteBuf.readStation(buf);
		add = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		UtilByteBuf.writeStation(buf, station);
		buf.writeBoolean(add);
	}

	public static class Handler implements IMessageHandler<PacketNoticeStationChangedWorker, IMessage> {

		@Override
		public IMessage onMessage(PacketNoticeStationChangedWorker message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT)
				return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(PacketNoticeStationChangedWorker message, MessageContext ctx) {
			if (StationHandler.INSTANCE.isExist(message.station.getPos()))
				return;
			
			if (message.add) {
				StationHandler.INSTANCE.addNewStation(message.station);
			} else {
				if (!StationHandler.INSTANCE.tryRemoveStation(message.station.getPos())) {
					Minecraft.getMinecraft().player.sendMessage(new UtilTranslatable(TranslateType.CHAT, "desync"));
				}
			}
		}
	}
}
