package com.chaos.eki.objects.blocks.subblock;

import net.minecraft.util.IStringSerializable;

public enum EnamelWallPositionType implements IStringSerializable {
    TOP("top"), CENTER("center"), BOTTOM("bottom");
    public static final EnamelWallPositionType[] values = values();

    private final String name;

    EnamelWallPositionType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}