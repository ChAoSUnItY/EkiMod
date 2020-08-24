package chaos.mod.commands;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.Eki.EkiConfig;
import chaos.mod.util.Reference;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;

public class CommandNametagDisplay extends CommandBase {
	List<String> aliases = Lists.newArrayList("display", "nametagDisplay");

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1)
			return;

		EkiConfig.nametagDisplay = parseBoolean(args[0]);
		ConfigManager.load(Reference.MODID, Config.Type.INSTANCE);
	}

	@Override
	public String getCommandName() {
		return "nametagDisplay";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/nametagDisplay <true|false>";
	}

	@Override
	public List<String> getCommandAliases() {
		return aliases;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		return (List<String>) (args.length == 1 ? getListOfStringsMatchingLastWord(args, "true", "false") : Collections.emptyList());
	}
}
