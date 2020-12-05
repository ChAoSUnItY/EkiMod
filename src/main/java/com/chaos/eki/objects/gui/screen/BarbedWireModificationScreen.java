package com.chaos.eki.objects.gui.screen;

import com.chaos.eki.objects.items.BarbedWireMultiBlockItem;
import com.chaos.eki.utils.handler.PacketHandler;
import com.chaos.eki.utils.packet.BarbedWireSetChangedPacket;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class BarbedWireModificationScreen extends Screen {
    private final Hand hand;
    private final PlayerEntity player;
    protected Button[] buttons;
    private int size;
    int x;
    int y;

    public BarbedWireModificationScreen(PlayerEntity player, Hand hand) {
        super(new StringTextComponent("")); //UNUSED
        this.player = player;
        this.hand = hand;
        size = player.getHeldItem(hand).getTag().getInt(BarbedWireMultiBlockItem.PRESERVED_BUILDING_SIZE);
    }

    @Override
    protected void init() {
        x = width / 2;
        y = height / 2;
        buttons = new Button[]{
                addButton(new Button(
                        x - 40,
                        y - 5,
                        20,
                        20,
                        new StringTextComponent("\u276E"),
                        v -> {
                            if (size > 0)
                                size--;
                        },
                        (p1, p2, p3, p4) ->
                                renderTooltip(
                                        p2,
                                        new StringTextComponent("Minimum size: " + 0),
                                        p3,
                                        p4)
                )),
                addButton(new Button(
                        x + 20,
                        y - 5,
                        20,
                        20,
                        new StringTextComponent("\u276F"),
                        v -> {
                            if (size < player.getEntityWorld().getHeight())
                                size++;
                        },
                        (p1, p2, p3, p4) ->
                                renderTooltip(
                                        p2,
                                        new StringTextComponent("Maximum size: " + player.getEntityWorld().getHeight()),
                                        p3,
                                        p4)
                ))
        };
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(
                matrixStack,
                font,
                new StringTextComponent("y : " + size).mergeStyle(TextFormatting.BOLD),
                x,
                y,
                TextFormatting.WHITE.getColor()
        );
    }

    @Override
    public void onClose() {
        PacketHandler.INSTANCE.sendToServer(new BarbedWireSetChangedPacket(player.getUniqueID(), size, hand));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
