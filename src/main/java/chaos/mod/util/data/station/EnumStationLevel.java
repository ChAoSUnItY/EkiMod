package chaos.mod.util.data.station;

import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import net.minecraft.util.IStringSerializable;

public enum EnumStationLevel implements IStringSerializable {
	SPECIAL("Special Class", "special_class", 0), FIRST("First Class", "first_class", 1), SECOND("Second Class", "second_class", 2), THRID("Third Class", "third_class", 3),
	SIMPLE("Simple", "simple", 4), STAFFLESS("Staffless", "staffless", 5), SIGNAL("Signal", "signal", 6), NON("Non", "non", 7);

	private static final EnumStationLevel[] META_LOOKUP = values();
	private static final List<UtilTranslatable> TEXT_LIST = Lists.newArrayList();
	private String s;
	private String unformatted;
	private int lvl;

	static {
		for (EnumStationLevel val : META_LOOKUP)
			TEXT_LIST.add(new UtilTranslatable(TranslateType.CONTAINER, val.getUnformatted()));
	}

	EnumStationLevel(String s, String unformatted, int lvl) {
		this.s = s;
		this.unformatted = unformatted;
		this.lvl = lvl;
	}

	@Override
	public String getName() {
		return s;
	}

	public String getUnformatted() {
		return unformatted;
	}

	public String getFormattedText() {
		return new UtilTranslatable(TranslateType.CONTAINER, getUnformatted()).getFormattedText();
	}

	public int getStationLevel() {
		return lvl;
	}

	public static EnumStationLevel[] getVal() {
		return META_LOOKUP.clone();
	}

	public static List<UtilTranslatable> getTranslatedTexts() {
		return Lists.newArrayList(TEXT_LIST);
	}

	public static List<StringDataForm> getDataFormTranslatedTexts() {
		List<StringDataForm> cache = Lists.newArrayList();
		for (UtilTranslatable unformatted : TEXT_LIST) {
			cache.add(new StringDataForm(unformatted.getFormattedText()));
		}
		return cache;
	}

	public static EnumStationLevel byMetadata(int meta) {
		return META_LOOKUP[meta];
	}
}
