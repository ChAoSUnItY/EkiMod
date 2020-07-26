package chaos.mod.commands;

import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.util.handlers.StationHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandForceSaveStations extends CommandBase {
	List<String> aliases = Lists.newArrayList("saveSta", "saveStations");

	@Override
	public String getName() {
		return "saveStations";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/saveStations";
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return sender.canUseCommand(2, "");
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		StationHandler.INSTANCE.reload(sender.getEntityWorld());
	}

}
