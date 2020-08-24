package chaos.mod.util.utils;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.util.data.station.DataForm;
import chaos.mod.util.data.station.StringDataForm;
import net.minecraft.client.network.NetworkPlayerInfo;

public class UtilDataForm {
	public static List<DataForm> toDataForm(List<?> list) {
		List<DataForm> cache = Lists.newArrayList();
		for (Object l : list)
			cache.add(new StringDataForm(l.toString()));
		return cache;
	}

	public static List<String> getPlayersName(Collection<NetworkPlayerInfo> collection) {
		List<String> cache = Lists.newArrayList();
		for (NetworkPlayerInfo i : collection)
			cache.add(i.getGameProfile().getName());
		return cache;
	}
}
