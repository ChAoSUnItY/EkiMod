package chaos.mod.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {
	public static enum EnamelWallEnumWallAType implements IStringSerializable {
		BLUE("blue", 0), GREEN("green", 1), CYAN("cyan", 2), PURPLE("purple", 3), RED("red", 4), YELLOW("yellow", 5), WHITE("white", 6);

		private static final EnamelWallEnumWallAType[] META_LOOKUP = new EnamelWallEnumWallAType[values().length];
		private String name;
		private int meta;

		private EnamelWallEnumWallAType(String name, int meta) {
			this.name = name;
			this.meta = meta;
		}

		@Override
		public String getName() {
			return name;
		}

		public int getMeta() {
			return meta;
		}

		@Override
		public String toString() {
			return getName();
		}

		public static EnamelWallEnumWallAType byMetadata(int meta) {
			return META_LOOKUP[meta];
		}

		static {
			for (EnamelWallEnumWallAType enamelWallEnumWallAType : values()) {
				META_LOOKUP[enamelWallEnumWallAType.getMeta()] = enamelWallEnumWallAType;
			}
		}
	}

	public static enum TesseraEnumWallEnum implements IStringSerializable {
		BLACK_DULL("black_dull", 0), CERULEAN_SMOOTH("cerulean_smooth", 1), CERULEAN("cerulean", 2), BLUE_SLATE("blue_slate", 3), BLUE_TEAL("blue_teal", 4), BROWN_TORTILLA("brown_tortilla", 5),
		GRAY_DOVE("gray_dove", 6), GRAY_FOSSIL("gray_fossil", 7), GRAY("gray", 8), GREEN_FERN("green_fern", 9), GREEN_LIME("green_lime", 10), ORANGE_FIRE("orange_fire", 11),
		PURPLE_HEATHER("purple_heather", 12), RED_ROSE("red_rose", 13), RED("red", 14), YELLOW("yellow", 15);

		private static final TesseraEnumWallEnum[] META_LOOKUP = new TesseraEnumWallEnum[values().length];
		private String name;
		private int meta;

		private TesseraEnumWallEnum(String name, int meta) {
			this.name = name;
			this.meta = meta;
		}

		@Override
		public String getName() {
			return name;
		}

		public int getMeta() {
			return meta;
		}

		@Override
		public String toString() {
			return getName();
		}

		public static TesseraEnumWallEnum byMetadata(int meta) {
			return META_LOOKUP[meta];
		}

		static {
			for (TesseraEnumWallEnum tesseraEnumWallEnum : values()) {
				META_LOOKUP[tesseraEnumWallEnum.getMeta()] = tesseraEnumWallEnum;
			}
		}
	}
}
