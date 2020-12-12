package com.chaos.eki.objects.blocks.subblock;

import net.minecraft.util.IStringSerializable;

public enum BaseColorType implements IStringSerializable {
    BLACK("black"), WHITE("white");
    public static final BaseColorType[] values = values();

    private final String name;

    BaseColorType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
