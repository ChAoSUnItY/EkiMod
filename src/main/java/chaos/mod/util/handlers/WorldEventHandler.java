package chaos.mod.util.handlers;

import chaos.mod.util.Reference;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import chaos.mod.util.utils.UtilVersionChecker;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@EventBusSubscriber
public class WorldEventHandler {
	@SubscribeEvent
	public static void onLogging(PlayerLoggedInEvent event) {
		boolean isLatest = UtilVersionChecker.get().isLatestVersion();
		UtilTCString s = isLatest ? new UtilTCString(TranslateType.CHAT, "versioncheckValid", Reference.VERSION).applyFormats(TextFormatting.GREEN, TextFormatting.BOLD)
				: new UtilTCString(TranslateType.CHAT, "versioncheckInvalid", Reference.VERSION, UtilVersionChecker.get().getLatestVersion()).applyFormats(TextFormatting.RED, TextFormatting.BOLD);
		if (!isLatest)
			s.getStyle().setClickEvent(new ClickEvent(Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/eki-mod/files"));
		event.player.sendMessage(s);
	}
}
