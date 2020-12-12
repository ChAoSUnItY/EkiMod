package com.chaos.eki.objects.blocks.subblock;

import net.minecraft.util.IStringSerializable;

public enum EnamelWallType implements IStringSerializable {
    BLUE("blue"), GREEN("green"), CYAN("cyan"), PURPLE("purple"), RED("red"), YELLOW("yellow"), WHITE("white");
    public static final EnamelWallType[] values = values();

    private final String name;

    EnamelWallType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}