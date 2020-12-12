package com.chaos.eki.objects.blocks.subblock;

import net.minecraft.util.IStringSerializable;

public enum TesseraWallType implements IStringSerializable {
    BLACK_DULL("black_dull"), CERULEAN_SMOOTH("cerulean_smooth"),CERULEAN("cerulean"),
    BLUE_SLATE("blue_slate"), BLUE_TEAL("blue_teal"), BROWN_TORTILLA("brown_tortilla"),
    GRAY_DOVE("gray_dove"), GRAY_FOSSIL("gray_fossil"), GRAY("gray"),
    GREEN_FERN("green_fern"), GREEN_LIME("green_lime"), ORANGE_FIRE("orange_fire"),
    PURPLE_HEATHER("purple_heather"), RED_ROSE("red_rose"), RED("red"),
    YELLOW("yellow");
    public static final TesseraWallType[] values = values();

    public final String name;

    TesseraWallType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
