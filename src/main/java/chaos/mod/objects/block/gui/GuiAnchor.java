package chaos.mod.objects.block.gui;

import java.io.IOException;

import chaos.mod.init.BlockInit;
import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.util.Reference;
import chaos.mod.util.handlers.PacketHandler;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.network.PacketAnchorCreateStationWorker;
import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiAnchor extends GuiScreen {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/anchor.png");
	private TileEntityAnchor te;
	private GuiTextField text;
	private GuiButton buttonClear;
	private GuiButton buttonConfirm;
	private int x, y;

	public GuiAnchor(TileEntityAnchor te) {
		this.te = te;
	}

	@Override
	public void initGui() {
		super.initGui();
		x = width / 2 - 90;
		y = height / 2 - 100;
		text = new GuiTextField(0, fontRenderer, x + 70, y + 50, 100, 15);
		buttonClear = new GuiButton(0, x + 70, y + 70, 45, 15, new UtilTranslatable(TranslateType.CONTAINER, "button.clear").getFormattedText());
		buttonConfirm = new GuiButton(1, x + 125, y + 70, 45, 15, new UtilTranslatable(TranslateType.CONTAINER, "anchor.button.confirm").getFormattedText());
		if (te.isValidStation()) {
			buttonClear.enabled = false;
			buttonConfirm.enabled = false;
			text.setText(StationHandler.INSTANCE.getStation(te.getPos()).getName());
			text.setFocused(false);
			text.setEnabled(false);
		} else {
			text.setFocused(true);
			text.setCanLoseFocus(true);
			text.setText(new UtilTranslatable(TranslateType.CONTAINER, "anchor.text").getFormattedText());
			text.setMaxStringLength(100);
		}
		buttonList.add(buttonClear);
		buttonList.add(buttonConfirm);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawDefaultBackground();
		GlStateManager.color(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(TEXTURES);
		drawTexturedModalRect(x, y, 0, 0, 176, 166);
		// draw anchor
		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		float size = 3.0f;
		GlStateManager.scale(size, size, size);
		float msize = (float) Math.pow(size, -1);
		itemRender.renderItemAndEffectIntoGUI(new ItemStack(BlockInit.ANCHOR), Math.round(x / size) + 5, Math.round(y / size) + 2);
		GlStateManager.scale(msize, msize, msize);
		RenderHelper.enableStandardItemLighting();
		// draw element
		text.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
		// draw string
		fontRenderer.drawString(BlockInit.ANCHOR.getLocalizedName(), (x + 176 / 2) - (fontRenderer.getStringWidth(BlockInit.ANCHOR.getLocalizedName()) / 2), y + 8, 4210752);
		fontRenderer.drawString(new UtilTCString(TranslateType.CONTAINER, "anchor.station.name").getFormattedText() + " " + (te.isValidStation() ? te.getStation().getName() : ""), x + 70, y
				+ 20, 4210752);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 0:
			resetText();
			break;
		case 1:
			if (text.getText().isEmpty() || text.getText().equalsIgnoreCase(new UtilTranslatable(TranslateType.CONTAINER, "anchor.text").getFormattedText())) {
				return;
			}
			PacketHandler.INSTANCE.sendToServer(new PacketAnchorCreateStationWorker(text.getText(), te.getPos()));
			buttonClear.enabled = false;
			buttonConfirm.enabled = false;
			text.setEnabled(false);
			text.setFocused(false);
			break;
		default:
			break;
		}

	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		text.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (!text.isFocused() && (keyCode == 1 || mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode))) {
			mc.player.closeScreen();
		}
		super.keyTyped(typedChar, keyCode);
		text.textboxKeyTyped(typedChar, keyCode);
	}

	@Override
	public void updateScreen() {
		text.updateCursorCounter();
		super.updateScreen();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	private void resetText() {
		text.setText("");
		text.setFocused(true);
		updateScreen();
	}
}
