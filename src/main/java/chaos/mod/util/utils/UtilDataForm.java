package chaos.mod.util.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import chaos.mod.util.data.station.DataForm;
import chaos.mod.util.data.station.StringDataForm;
import net.minecraft.client.network.NetworkPlayerInfo;

public class UtilDataForm {
	public static List<DataForm> toDataForm(List<?> list) {
		return list.stream().map(l -> new StringDataForm(l.toString())).collect(Collectors.toList());
	}

	public static List<String> getPlayersName(Collection<NetworkPlayerInfo> collection) {
		return collection.stream().map(c -> new String(c.getGameProfile().getName())).collect(Collectors.toList());
	}
}
