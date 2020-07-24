package chaos.mod.util.handlers;

import chaos.mod.util.Reference;
import chaos.mod.util.data.station.Station;
import chaos.mod.util.events.StationCreatedEvent;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import chaos.mod.util.utils.UtilVersionChecker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Reference.MODID)
public class WorldEventHandler {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onLogging(EntityJoinWorldEvent event) {
		boolean isLatest = UtilVersionChecker.get().isLatestVersion();
		UtilTCString s = isLatest ? new UtilTCString(TranslateType.CHAT, "versioncheckValid", Reference.VERSION).applyFormats(TextFormatting.GREEN, TextFormatting.BOLD)
				: new UtilTCString(TranslateType.CHAT, "versioncheckInvalid", Reference.VERSION, UtilVersionChecker.get().getLatestVersion()).applyFormats(TextFormatting.RED, TextFormatting.BOLD);
		if (!isLatest)
			s.getStyle().setClickEvent(new ClickEvent(Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/eki-mod/files"));
		if (event.getEntity() instanceof EntityPlayer && event.getWorld().isRemote) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			player.sendMessage(s);
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void saveStation(StationCreatedEvent event) {
		Station sta = new Station(event.getPos(), event.getName());
		try {
			StationHandler.INSTANCE.addNewStation(sta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
