package chaos.mod.init;

import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.objects.item.ItemRangeFinder;
import chaos.mod.objects.item.ItemTicket;
import chaos.mod.objects.item.ItemWrench;
import net.minecraft.item.Item;

public class ItemInit {
	public static final List<Item> ITEMS = Lists.newArrayList();

	public static final Item WRENCH = new ItemWrench("wrench");

	public static final Item TICKET = new ItemTicket("ticket");

	public static final Item RANGEFINDER = new ItemRangeFinder("rangefinder");
}
