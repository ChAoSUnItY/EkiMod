package chaos.mod.init;

import java.util.ArrayList;
import java.util.List;

import chaos.mod.objects.item.ItemWrench;
import net.minecraft.item.Item;

public class ItemInit {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item WRENCH = new ItemWrench("wrench");
}
