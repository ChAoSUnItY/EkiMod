package chaos.mod.util.handlers;

import chaos.mod.util.Reference;
import chaos.mod.util.utils.UtilTextFormer;
import chaos.mod.util.utils.UtilVersionChecker;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@SuppressWarnings("deprecation")
@EventBusSubscriber
public class WorldEventHandler {
	@SubscribeEvent
	public static void onLogging(PlayerLoggedInEvent event) {
		boolean isLatest = UtilVersionChecker.get().isLatestVersion();
		TextComponentString message = new TextComponentString(isLatest
				? I18n.translateToLocalFormatted("chat.type.text.versioncheckValid", Reference.VERSION)
				: I18n.translateToLocalFormatted("chat.type.text.versioncheckInvalid", Reference.VERSION, UtilVersionChecker.get().getLatestVersion()));
		UtilTextFormer.form(message, isLatest ? TextFormatting.GREEN : TextFormatting.RED, true, false);
		if (!isLatest) message.getStyle().setClickEvent(new ClickEvent(Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/eki-mod/files"));
		event.player.sendMessage(message);
	}
}
