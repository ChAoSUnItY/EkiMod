package chaos.mod.commands;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.tileentity.TileEntityTicketVendor.SortType;
import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.utils.UtilStationSystem;
import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

public class CommandStations extends CommandBase {
	List<String> aliases = Lists.newArrayList("stations");

	@Override
	public String getName() {
		return "stations";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/stations [<number of stations to display>)] [<NF|FN|Name>]";
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 2 ? Lists.newArrayList("NF", "FN", "Name") : Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		List<Station> stations = StationHandler.INSTANCE.getStations();
		sender.sendMessage(new UtilTCString("============================="));
		if (args.length < 1) {
			stations.forEach(sta -> sender.sendMessage(new UtilTCString(sta.getData())));
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(new UtilTranslatable(TranslateType.CHAT, "command.stations.help"));
			}
			for (int i = 0; i < parseInt(args[0], 1, stations.size()); i++) {
				sender.sendMessage(new UtilTCString(stations.get(i).getData()));
			}
		} else if (args.length == 2) {
			try {
				switch (SortType.valueOf(args[1])) {
				case NF:
					stations = UtilStationSystem.sortByFarNF(sender.getPosition(), stations);
					break;
				case FN:
					stations = UtilStationSystem.sortByFarFN(sender.getPosition(), stations);
					break;
				case NAME:
					stations = UtilStationSystem.sortByName(stations);
					break;
				default:
					break;
				}
				for (int i = 0; i < parseInt(args[0]); i++) {
					sender.sendMessage(new UtilTCString(stations.get(i).getData()));
				}
			} catch (Exception e) {
				sender.sendMessage(new UtilTranslatable(TranslateType.CHAT, "command.stations.error.illegalType").applyFormat(TextFormatting.RED));
			}
		}
		sender.sendMessage(new UtilTCString("============================="));
	}
}
