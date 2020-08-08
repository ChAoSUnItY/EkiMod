package chaos.mod.util.network;

import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.utils.UtilByteBuf;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
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
		//stations
		staT = buf.readInt();
		stations = Lists.newArrayList();
		for (int i = 0; i < staT; i++) {
			stations.add(new Station(ByteBufUtils.readUTF8String(buf), UtilByteBuf.readPos(buf)));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		//stations
		buf.writeInt(staT);
		for (Station sta : stations) {
			ByteBufUtils.writeUTF8String(buf, sta.getName());
			UtilByteBuf.writePos(buf, sta.getPos());
		}
	}
	
	public static class Handler implements IMessageHandler<PacketInitStationHandlerWorker, IMessage> {

		@Override
		public IMessage onMessage(PacketInitStationHandlerWorker message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT)
				return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(PacketInitStationHandlerWorker message, MessageContext ctx) {
			StationHandler.INSTANCE.init(message.stations);
		}
	}
}
