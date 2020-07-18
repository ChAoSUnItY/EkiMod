package chaos.mod.util.utils;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class UtilTextFormer {
	public static TextComponentString form(TextComponentString text, TextFormatting form, boolean bold, boolean italic) {
		text.getStyle().setBold(bold).setItalic(italic).setColor(form);
		return text;
	}
}
