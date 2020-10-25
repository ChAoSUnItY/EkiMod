package com.chaos.eki.objects.blocks;

import com.chaos.eki_lib.objects.blocks.base.BaseBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BuildingBaseBlock extends BaseBlock {
    public BuildingBaseBlock() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .hardnessAndResistance(5f)
                .harvestTool(ToolType.PICKAXE)
                .setRequiresTool());
    }
}
