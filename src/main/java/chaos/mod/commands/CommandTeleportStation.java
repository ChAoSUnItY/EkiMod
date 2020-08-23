package chaos.mod.commands;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import net.minecraft.block.BlockAir;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandTeleportStation extends CommandBase {
	List<String> aliases = Lists.newArrayList("tpSta");

	@Override
	public String getName() {
		return "tpSta";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return new UtilTranslatable(TranslateType.CHAT, "command.teleportStations.usage").getFormattedText();
		// return "/stations [<number of stations to display>)] [<NF|FN|Name>]";
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? StationHandler.INSTANCE.getStationsName() : Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1)
			return;
		Station sta = StationHandler.INSTANCE.getStation(args[0], true);
		if (sta.equals(Station.EXAMPLE))
			return;
		BlockPos pos = sta.getPos();
		for (BlockPos securePos = sta.getPos();; securePos = new BlockPos(pos.getX(), securePos.getY() + 1, pos.getZ())) {
			if (server.getWorld(getCommandSenderAsPlayer(sender).world.provider.getDimension()).getBlockState(securePos).getBlock() instanceof BlockAir) {
				pos = securePos;
				break;
			}
		}
		getCommandSenderAsPlayer(sender).setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
	}
}
