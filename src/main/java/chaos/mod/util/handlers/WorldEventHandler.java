package chaos.mod.util.handlers;

import chaos.mod.util.Reference;
import chaos.mod.util.network.PacketInitStationHandlerWorker;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import chaos.mod.util.utils.UtilVersionChecker;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@EventBusSubscriber(modid = Reference.MODID)
public class WorldEventHandler {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onLogging(PlayerLoggedInEvent event) {
		boolean isLatest = UtilVersionChecker.INSTANCE.isLatestVersion();
		UtilTCString s = isLatest ? new UtilTCString(TranslateType.CHAT, "versioncheckValid", Reference.VERSION).applyFormats(TextFormatting.GREEN, TextFormatting.BOLD)
				: new UtilTCString(TranslateType.CHAT, "versioncheckInvalid", Reference.VERSION, UtilVersionChecker.INSTANCE.getLatestVersion()).applyFormats(TextFormatting.RED, TextFormatting.BOLD);
		if (!isLatest)
			s.getStyle().setClickEvent(new ClickEvent(Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/eki-mod/files"));
		event.player.sendMessage(s);

		// init station handler
		if (event.player.isServerWorld()) {
			PacketHandler.INSTANCE.sendTo(new PacketInitStationHandlerWorker(StationHandler.INSTANCE.getStations()), (EntityPlayerMP) event.player);
		}
	}
}
