package com.chaos.eki.utils.packet;

import com.chaos.eki.objects.items.BarbedWireMultiBlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class BarbedWireSetChangedPacket {
    private final UUID playerUUID;
    private final int size;
    private final Hand hand;

    public BarbedWireSetChangedPacket(PacketBuffer buf) {
        this(buf.readUniqueId(), buf.readVarInt(), buf.readEnumValue(Hand.class));
    }

    public BarbedWireSetChangedPacket(UUID playerUUID, int size, Hand hand) {
        this.playerUUID = playerUUID;
        this.size = size;
        this.hand = hand;
    }

    public static void encode(BarbedWireSetChangedPacket packet, PacketBuffer buf) {
        buf.writeUniqueId(packet.playerUUID);
        buf.writeVarInt(packet.size);
        buf.writeEnumValue(packet.hand);
    }

    public static void handle(final BarbedWireSetChangedPacket packet, Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        if (ctx.getDirection().getReceptionSide() == LogicalSide.SERVER)
            ctx.enqueueWork(() -> {
                PlayerEntity player = ctx.getSender().getServerWorld().getPlayerByUuid(packet.playerUUID);
                CompoundNBT nbt = player.getHeldItem(packet.hand).getTag();
                nbt.putInt(BarbedWireMultiBlockItem.PRESERVED_BUILDING_SIZE, packet.size);
                player.getHeldItem(packet.hand).setTag(nbt);
            });
        ctx.setPacketHandled(true);
    }
}
