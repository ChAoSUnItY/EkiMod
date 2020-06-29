package chaos.mod.util.utils;

import javax.annotation.Nonnull;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class UtilTextFormer {
	public static TextComponentString form(@Nonnull TextComponentString text, @Nonnull TextFormatting form,
			@Nonnull boolean bold, @Nonnull boolean italic) {
		text.getStyle().setBold(bold).setItalic(italic).setColor(form);
		return text;
	}
}
