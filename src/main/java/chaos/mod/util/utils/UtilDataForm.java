package chaos.mod.util.utils;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.util.data.station.DataForm;
import chaos.mod.util.data.station.StringDataForm;
import net.minecraft.client.network.NetworkPlayerInfo;

public class UtilDataForm {
	public static List<DataForm> toDataForm(List<?> list) {
		List<DataForm> l = Lists.newArrayList();
		list.forEach(item -> l.add(new StringDataForm(item.toString())));
		return l;
	}

	public static List<String> getPlayersName(Collection<NetworkPlayerInfo> collection) {
		List<String> s = Lists.newArrayList();
		collection.forEach(n -> s.add(n.getGameProfile().getName()));
		return s;
	}
}
