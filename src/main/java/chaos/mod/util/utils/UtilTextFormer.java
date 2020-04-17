package chaos.mod.util.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class UtilTextFormer {
	/***
	 * Use Form for more control.
	 */
	@Deprecated
	public static TextComponentString getFormed(@Nonnull TextComponentString text, @Nullable TextFormatting form) {
		text.getStyle().setBold(true).setItalic(true).setColor(form == null ? TextFormatting.WHITE : form);
		return text;
	}

	public static TextComponentString form(@Nonnull TextComponentString text, @Nonnull TextFormatting form, @Nonnull boolean bold, @Nonnull boolean italic) {
		text.getStyle().setBold(bold).setItalic(italic).setColor(form);
		return text;
	}
}
