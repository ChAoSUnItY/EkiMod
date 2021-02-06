package chaos.mod.util.handlers;

import chaos.mod.Eki;
import chaos.mod.proxy.ClientProxy;
import chaos.mod.util.Reference;
import chaos.mod.util.network.PacketInitStationHandlerWorker;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import chaos.mod.util.utils.UtilVersionChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = Reference.MODID)
public class EventHandler {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onLogging(PlayerLoggedInEvent event) {
		boolean isLatest = UtilVersionChecker.INSTANCE.isLatestVersion();
		UtilTCString s = isLatest ? new UtilTCString(TranslateType.CHAT, "versioncheckValid", Reference.VERSION).applyFormats(TextFormatting.GREEN, TextFormatting.BOLD)
				: new UtilTCString(TranslateType.CHAT, "versioncheckInvalid", Reference.VERSION, UtilVersionChecker.INSTANCE.getLatestVersion()).applyFormats(TextFormatting.RED, TextFormatting.BOLD);
		if (!isLatest)
			s.getStyle().setClickEvent(new ClickEvent(Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/eki-mod/files"));
		event.player.sendMessage(s);

		// init station handler
		if (event.player.isServerWorld())
			PacketHandler.INSTANCE.sendTo(new PacketInitStationHandlerWorker(StationHandler.INSTANCE.getStations()), (EntityPlayerMP) event.player);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onKeyPressed(KeyInputEvent event) {
		KeyBinding[] keyBindings = ClientProxy.binds;
		if (keyBindings[0].isPressed())
			Eki.proxy.displayStationMap(Minecraft.getMinecraft().player);
	}
}
