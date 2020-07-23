package chaos.mod.util.utils;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

/***
 * Lazy Translate.
 */
public class UtilTranslatable extends TextComponentTranslation {
	public UtilTranslatable(TranslateType type, String key, Object... args) {
		super(type.getName() + ".eki." + key, args);
	}

	public UtilTranslatable(TranslateType type, String keys) {
		super(type.getName() + ".eki." + keys);
	}

	public UtilTranslatable(String key) {
		super(key);
	}

	public UtilTranslatable applyFormat(TextFormatting color) {
		Style style = this.getStyle();
		if (color.isColor()) {
			style.setColor(color);
		}

		if (color.isFancyStyling()) {
			switch (color) {
			case OBFUSCATED:
				style.setObfuscated(true);
				break;
			case BOLD:
				style.setBold(true);
				break;
			case STRIKETHROUGH:
				style.setStrikethrough(true);
				break;
			case UNDERLINE:
				style.setUnderlined(true);
				break;
			case ITALIC:
				style.setItalic(true);
			default:
				break;
			}
		}
		return this;
	}

	public static TextComponentString getTextComponentString(TranslateType type, String key, Object... args) {
		return new TextComponentString(I18n.format(type.getName() + ".eki." + key, args));
	}

	public static String getEki(String s) {
		return "eki." + s;
	}

	public static class UtilTCString extends TextComponentString {
		public UtilTCString(TranslateType type, String key, Object... args) {
			super(I18n.format(type.getName() + ".eki." + key, args));
		}
		
		public UtilTCString(String key, Object... args) {
			super(I18n.format(key, args));
		}

		public UtilTCString applyFormat(TextFormatting color) {
			Style style = this.getStyle();
			if (color.isColor()) {
				style.setColor(color);
			}

			if (color.isFancyStyling()) {
				switch (color) {
				case OBFUSCATED:
					style.setObfuscated(true);
					break;
				case BOLD:
					style.setBold(true);
					break;
				case STRIKETHROUGH:
					style.setStrikethrough(true);
					break;
				case UNDERLINE:
					style.setUnderlined(true);
					break;
				case ITALIC:
					style.setItalic(true);
				default:
					break;
				}
			}
			return this;
		}
		
		public UtilTCString applyFormats(TextFormatting... colors) {
		      for(TextFormatting textformatting : colors) {
		          this.applyFormat(textformatting);
		       }

		       return this;
		    }
	}

	public enum TranslateType implements IStringSerializable {
		CONTAINER("container"), CHAT("chat.type.text");

		private String s;

		TranslateType(String s) {
			this.s = s;
		}

		@Override
		public String getName() {
			return s;
		}

	}
}
