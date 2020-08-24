package chaos.mod.util.network;

import chaos.mod.init.ItemInit;
import chaos.mod.tileentity.TileEntityTicketVendor;
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

	public PacketVendorSpawnItemWorker() {
		this.messageValid = false;
	}

	public PacketVendorSpawnItemWorker(BlockPos pos, int price) {
		this.pos = pos;
		this.price = price;
		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
			this.price = buf.readInt();
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
	}

	public static class Handler implements IMessageHandler<PacketVendorSpawnItemWorker, IMessage> {
		@Override
		public IMessage onMessage(final PacketVendorSpawnItemWorker message, final MessageContext ctx) {
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

		void processMessage(PacketVendorSpawnItemWorker message, MessageContext ctx) {
			TileEntity te = ctx.getServerHandler().playerEntity.getServerWorld().getTileEntity(message.pos);
			World world = ctx.getServerHandler().playerEntity.getEntityWorld();
			BlockPos pos = te.getPos();
			ItemStack ticket = new ItemStack(ItemInit.TICKET, 1);
			NBTTagCompound nbt = new NBTTagCompound();

			nbt.setInteger("value", message.price);
			ticket.setTagCompound(nbt);
			if (!ctx.getServerHandler().playerEntity.inventory.addItemStackToInventory(ticket)) {
				te.getWorld().spawnEntityInWorld(new EntityItem(te.getWorld(), pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, ticket));
			}
			((TileEntityTicketVendor) te).addMoney(message.price);
			world.playSound(null, pos, SoundEvents.BLOCK_DISPENSER_LAUNCH, SoundCategory.BLOCKS, 1, 1);
		}
	}
}
