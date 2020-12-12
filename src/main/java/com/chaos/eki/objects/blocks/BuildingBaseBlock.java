package com.chaos.eki.objects.blocks;

import com.chaos.eki_lib.objects.blocks.base.BaseBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BuildingBaseBlock extends BaseBlock {
    public BuildingBaseBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(5f)
                .harvestTool(ToolType.PICKAXE));
    }
}
