package chaos.mod.util.handlers;

import chaos.mod.util.network.PacketAddStationWorker;
import chaos.mod.util.network.PacketBarbedWireSizeChangedWorker;
import chaos.mod.util.network.PacketInitStationHandlerWorker;
import chaos.mod.util.network.PacketNoticeStationChangedWorker;
import chaos.mod.util.network.PacketVendorSpawnItemWorker;
import chaos.mod.util.network.PacketVendorWithdrawWorker;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	public static SimpleNetworkWrapper INSTANCE;

	public static int ID = 0;

	public static int nextID() {
		return ID++;
	}

	public static void registerMessages(String channelName) {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);

		INSTANCE.registerMessage(PacketVendorSpawnItemWorker.Handler.class, PacketVendorSpawnItemWorker.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketVendorWithdrawWorker.Handler.class, PacketVendorWithdrawWorker.class, nextID(), Side.SERVER);

		INSTANCE.registerMessage(PacketAddStationWorker.Handler.class, PacketAddStationWorker.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketInitStationHandlerWorker.Handler.class, PacketInitStationHandlerWorker.class, nextID(), Side.CLIENT);
		INSTANCE.registerMessage(PacketNoticeStationChangedWorker.Handler.class, PacketNoticeStationChangedWorker.class, nextID(), Side.CLIENT);

		INSTANCE.registerMessage(PacketBarbedWireSizeChangedWorker.Handler.class, PacketBarbedWireSizeChangedWorker.class, nextID(), Side.SERVER);
	}
}
