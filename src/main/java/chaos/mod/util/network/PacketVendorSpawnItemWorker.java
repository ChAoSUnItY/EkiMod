package chaos.mod.util.network;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.UUID;

import chaos.mod.init.ItemInit;
import chaos.mod.tileentity.TileEntityTicketVendor;
import chaos.mod.util.utils.UtilLogger;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketVendorSpawnItemWorker implements IMessage {

	private boolean messageValid;

	private BlockPos pos;
	private int price;
	private UUID uuid;

	public PacketVendorSpawnItemWorker() {
		this.messageValid = false;
	}

	public PacketVendorSpawnItemWorker(BlockPos pos, int price, UUID uuid) {
		this.pos = pos;
		this.price = price;
		this.uuid = uuid;
		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
			this.price = buf.readInt();
			this.uuid = UUID.fromString(buf.readCharSequence(36, Charset.forName("utf-8")).toString());
		} catch (IndexOutOfBoundsException e) {
			return;
		}
		this.messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid)
			return;
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		buf.writeInt(price);
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
	    bb.putLong(uuid.getLeastSignificantBits());
		buf.writeBytes(bb.array());
	}

	public static class Handler implements IMessageHandler<PacketVendorSpawnItemWorker, IMessage> {

		@Override
		public IMessage onMessage(PacketVendorSpawnItemWorker message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.SERVER)
				return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(PacketVendorSpawnItemWorker message, MessageContext ctx) {
			TileEntity tileEntity = ctx.getServerHandler().player.getServerWorld().getTileEntity(message.pos);
			World world = ctx.getServerHandler().player.getEntityWorld();
			BlockPos pos = tileEntity.getPos();
			ItemStack ticket = new ItemStack(ItemInit.TICKET, 1);
			NBTTagCompound nbt = new NBTTagCompound();
			
			nbt.setInteger("value", message.price);
			ticket.setTagCompound(nbt);
			tileEntity.getWorld().spawnEntity(new EntityItem(tileEntity.getWorld(), pos.getX()+0.5, pos.getY()+1,
					pos.getZ()+0.5, ticket));
			((TileEntityTicketVendor) tileEntity).addMoney(message.price);
			UtilLogger.info(((TileEntityTicketVendor) tileEntity).getMoney());
			world.playSound(null, pos, SoundEvents.BLOCK_DISPENSER_LAUNCH, SoundCategory.BLOCKS, 1, 1);
		}
	}
}
