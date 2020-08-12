package chaos.mod.objects.item.gui;

import java.io.IOException;

import chaos.mod.util.handlers.PacketHandler;
import chaos.mod.util.network.PacketBarbedWireSizeChangedWorker;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class GuiBarbedWireSetConfig extends GuiScreen {
	private ItemStack stack;
	private EntityPlayer player;
	private EnumHand hand;
	private int size;
	private GuiButton buttonIncrease;
	private GuiButton buttonDecrease;
	private int x;
	private int y;

	public GuiBarbedWireSetConfig(ItemStack stack, EntityPlayer player, EnumHand hand) {
		this.stack = stack;
		this.player = player;
		this.hand = hand;
	}

	@Override
	public void initGui() {
		super.initGui();
		x = width / 2 - 25;
		y = height / 2 - 10;
		size = stack.getTagCompound().getInteger("size") + 2;
		buttonDecrease = new GuiButton(0, x, y, 10, 10, "«");
		buttonIncrease = new GuiButton(1, x + 40, y, 10, 10, "»");
		buttonList.add(buttonDecrease);
		buttonList.add(buttonIncrease);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		GlStateManager.color(1F, 1F, 1F, 1F);
		drawRect(x - 5, y - 5, x + 55, y + 15, -2139062144);
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCenteredString(fontRenderer, "" + size, x + 25, y, 14737632);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 0:
			if (size == 1)
				return;
			size--;
			break;
		case 1:
			if (size == 256)
				return;
			size++;
			break;
		default:
			break;
		}
		updateScreen();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 1 || mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode))
			mc.player.closeScreen();
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	public void onGuiClosed() {
		PacketHandler.INSTANCE.sendToServer(new PacketBarbedWireSizeChangedWorker(player.getPersistentID(), hand, size - 2));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
