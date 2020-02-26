package chaos.mod.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {
	public static enum EnamelWallEnumWallAType implements IStringSerializable {
		BLUE("blue", 0),GREEN("green", 1),CYAN("cyan", 2),PURPLE("purple", 3),
		RED("red", 4),YELLOW("yellow", 5),WHITE("white", 6);

		private static final EnamelWallEnumWallAType[] META_LOOKUP = new EnamelWallEnumWallAType[values().length];
		private String name;
		private int meta;
		
		private EnamelWallEnumWallAType(String name, int meta) {
			this.name = name;
			this.meta = meta;
		}
		
		@Override
		public String getName() {
			return this.name;
		}
		
		public int getMeta() {
			return this.meta;
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
}
