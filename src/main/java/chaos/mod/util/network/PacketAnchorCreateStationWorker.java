package chaos.mod.util.network;

import chaos.mod.tileentity.TileEntityAnchor;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketAnchorCreateStationWorker implements IMessage {
	private String name;
	private BlockPos pos;
	
	private boolean messageValid;
	
	public PacketAnchorCreateStationWorker() {
		messageValid = false;
	}
	
	public PacketAnchorCreateStationWorker(String name, BlockPos pos) {
		this.name = name;
		this.pos = pos;
		
		messageValid = true;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		name = ByteBufUtils.readUTF8String(buf);
		pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, name);
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
	}

	public static class Handler implements IMessageHandler<PacketAnchorCreateStationWorker, IMessage> {

		@Override
		public IMessage onMessage(PacketAnchorCreateStationWorker message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.SERVER)
				return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(PacketAnchorCreateStationWorker message, MessageContext ctx) {
			TileEntity te = ctx.getServerHandler().player.getServerWorld().getTileEntity(message.pos);
			
			if (te instanceof TileEntityAnchor) {
				TileEntityAnchor teA = (TileEntityAnchor) te;
				teA.setValid(message.name);
			}
		}
	}
}
