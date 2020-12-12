package com.chaos.eki.objects.blocks.subblock;

import net.minecraft.util.IStringSerializable;

public enum StationPillarType implements IStringSerializable {
    BASE("base"), CENTER("center"), TOP("top");
    public static final StationPillarType[] values = values();

    private final String name;

    StationPillarType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
