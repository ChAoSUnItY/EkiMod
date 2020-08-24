package chaos.mod.util.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketBarbedWireSizeChangedWorker implements IMessage {
	private int size;
	private UUID uuid;
	private EnumHand hand;

	private boolean messageValid;

	public PacketBarbedWireSizeChangedWorker() {
		messageValid = false;
	}

	public PacketBarbedWireSizeChangedWorker(UUID uuid, EnumHand hand, int size) {
		this.uuid = uuid;
		this.hand = hand;
		this.size = size;

		messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
		hand = EnumHand.values()[buf.readInt()];
		size = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, uuid.toString());
		buf.writeInt(hand.ordinal());
		buf.writeInt(size);
	}

	public static class Handler implements IMessageHandler<PacketBarbedWireSizeChangedWorker, IMessage> {

		@Override
		public IMessage onMessage(final PacketBarbedWireSizeChangedWorker message, final MessageContext ctx) {
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

		void processMessage(PacketBarbedWireSizeChangedWorker message, MessageContext ctx) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("size", message.size);
			ctx.getServerHandler().playerEntity.getEntityWorld().getPlayerEntityByUUID(message.uuid).getHeldItem(message.hand).setTagCompound(tag);
		}
	}
}
