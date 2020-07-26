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
	public String getName() {
		return "nametagDisplay";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/nametagDisplay <true|false>";
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
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, "true", "false") : Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1)
			return;

		EkiConfig.nametagDisplay = parseBoolean(args[0]);
		ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
	}

}
