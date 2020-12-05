package com.chaos.eki.utils.handler;

import com.chaos.eki.Eki;
import com.chaos.eki.utils.packet.BarbedWireSetChangedPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    private static int dist = 0;

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Eki.MOD_ID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void register() {
        INSTANCE.registerMessage(
                dist++,
                BarbedWireSetChangedPacket.class,
                BarbedWireSetChangedPacket::encode,
                BarbedWireSetChangedPacket::new,
                BarbedWireSetChangedPacket::handle
        );
    }

    public static void sendTo(Object message, ServerPlayerEntity player) {
        INSTANCE.sendTo(message,player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }
}
