package chaos.mod.objects.block.gui;

import java.io.IOException;

import chaos.mod.init.BlockInit;
import chaos.mod.objects.block.gui.elements.GuiSimpleListBox;
import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.util.Reference;
import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.PacketHandler;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.network.PacketAddStationWorker;
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
	private GuiTextField OPtext;
	private GuiSimpleListBox lines;
	private GuiButton buttonClear;
	private GuiButton buttonConfirm;
	private GuiButton buttonSetOp;
	private GuiButton buttonAddLine;
	private int x;
	private int y;

	public GuiAnchor(TileEntityAnchor te) {
		this.te = te;
	}

	@Override
	public void initGui() {
		super.initGui();
		x = width / 2 - 90;
		y = height / 2 - 100;
		text = new GuiTextField(0, fontRenderer, x + 70, y + 50, 100, 15);
		OPtext = new GuiTextField(1, fontRenderer, x + 177, y, 100, 15);
		lines = new GuiSimpleListBox(x + 177, y + 79, 100, 87, StationHandler.INSTANCE.getStation(te.getPos()).getLines());
		buttonClear = new GuiButton(0, x + 70, y + 70, 45, 20, new UtilTranslatable(TranslateType.CONTAINER, "button.clear").getFormattedText());
		buttonConfirm = new GuiButton(1, x + 125, y + 70, 45, 20, new UtilTranslatable(TranslateType.CONTAINER, "anchor.button.confirm").getFormattedText());
		buttonSetOp = new GuiButton(2, x + 177, y + 18, 100, 20, new UtilTranslatable(TranslateType.CONTAINER, "anchor.button.setOP").getFormattedText());
		buttonAddLine = new GuiButton(3, x + 177, y + 41, 100, 20, new UtilTranslatable(TranslateType.CONTAINER, "anchor.button.addLine").getFormattedText());
		if (te.isValidStation()) {
			buttonClear.enabled = false;
			buttonConfirm.enabled = false;
			text.setText(StationHandler.INSTANCE.getStation(te.getPos()).getName());
			text.setFocused(false);
			text.setEnabled(false);
		} else {
			buttonSetOp.enabled = false;
			buttonAddLine.enabled = false;
			text.setFocused(true);
			text.setCanLoseFocus(true);
			text.setText(new UtilTranslatable(TranslateType.CONTAINER, "anchor.text").getFormattedText());
			text.setMaxStringLength(100);
		}
		buttonList.add(buttonClear);
		buttonList.add(buttonConfirm);
		buttonList.add(buttonSetOp);
		buttonList.add(buttonAddLine);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
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
		OPtext.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
		// draw string
		fontRenderer.drawString(BlockInit.ANCHOR.getLocalizedName(), (x + 176 / 2) - (fontRenderer.getStringWidth(BlockInit.ANCHOR.getLocalizedName()) / 2), y + 8, 4210752);
		fontRenderer.drawSplitString(new UtilTCString(TranslateType.CONTAINER, "anchor.station.name").getFormattedText() + " " + (te.isValidStation() ? te.getStation().getName() : ""), x + 70, y
				+ 20, 106, 4210752);
		fontRenderer.drawSplitString(new UtilTranslatable(TranslateType.CONTAINER, "anchor.desc").getFormattedText(), x + 5, y + 90, 166, 4210752);
		lines.draw(mouseX, mouseY, fontRenderer);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 0:
			resetText();
			break;
		case 1:
			if (text.getText().isEmpty() || text.getText().equalsIgnoreCase(new UtilTranslatable(TranslateType.CONTAINER, "anchor.text").getFormattedText())) {
				System.out.println("illegal");
				return;
			}
			PacketHandler.INSTANCE.sendToServer(new PacketAddStationWorker(new Station(text.getText(), te.getPos())));
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
		lines.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		lines.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
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
