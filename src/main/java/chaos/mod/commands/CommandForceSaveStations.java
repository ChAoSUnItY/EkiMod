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
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		StationHandler.INSTANCE.reload(sender.getEntityWorld());
	}

	@Override
	public String getCommandName() {
		return "saveStations";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/saveStations";
	}

	@Override
	public List<String> getCommandAliases() {
		return aliases;
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
}
