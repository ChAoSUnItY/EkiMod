package com.chaos.eki.proxy;

import com.chaos.eki.objects.gui.screen.BarbedWireModificationScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

import java.util.UUID;

public class ClientProxy extends CommonProxy {
    @Override
    public void openBarbedWireModificationScreen(PlayerEntity player, Hand hand) {
        Minecraft.getInstance().displayGuiScreen(new BarbedWireModificationScreen(player, hand));
    }
}
