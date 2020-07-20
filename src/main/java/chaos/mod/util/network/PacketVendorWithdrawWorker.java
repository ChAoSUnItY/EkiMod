package chaos.mod.util.network;

import java.util.UUID;

import chaos.mod.tileentity.TileEntityTicketVendor;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketVendorWithdrawWorker implements IMessage {

	private boolean messageValid;
	
	private UUID uuid;
	private BlockPos pos;
	
	public PacketVendorWithdrawWorker() {
		this.messageValid = false;
	}
	
	public PacketVendorWithdrawWorker(UUID uuid, BlockPos pos) {
		this.uuid = uuid;
		this.pos = pos;
		this.messageValid = true;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
			this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		} catch (IndexOutOfBoundsException e) {
			return;
		}
		this.messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid)
			return;
		ByteBufUtils.writeUTF8String(buf, uuid.toString());
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
	}

	public static class Handler implements IMessageHandler<PacketVendorWithdrawWorker, IMessage> {

		@Override
		public IMessage onMessage(PacketVendorWithdrawWorker message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.SERVER)
				return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(PacketVendorWithdrawWorker message, MessageContext ctx) {
			TileEntity te = ctx.getServerHandler().player.getServerWorld().getTileEntity(message.pos);
			if (te instanceof TileEntityTicketVendor) {
				TileEntityTicketVendor teTV = (TileEntityTicketVendor) te;
				teTV.withdrawMoney(ctx.getServerHandler().player.getServerWorld().getPlayerEntityByUUID(message.uuid), teTV.getMoney());
			}
		}
	}
}
