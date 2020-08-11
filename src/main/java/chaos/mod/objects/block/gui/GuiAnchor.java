package chaos.mod.objects.block.gui;

import java.io.IOException;

import com.google.common.collect.Lists;

import chaos.mod.init.BlockInit;
import chaos.mod.objects.block.gui.elements.GuiSimpleListBox;
import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.util.Reference;
import chaos.mod.util.data.station.EnumStationLevel;
import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.PacketHandler;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.network.PacketAddStationWorker;
import chaos.mod.util.utils.UtilDataForm;
import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
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
	private GuiTextField nameText;
	private GuiTextField OPText;
	private GuiButton buttonClearA;
	private GuiButton buttonClearB;
	private GuiButton buttonInsert;
	private GuiButton buttonConfirm;
	private GuiSimpleListBox staLvlList;
	private GuiSimpleListBox playerList;
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
		nameText = new GuiTextField(0, fontRenderer, x + 70, y + 50, 100, 15);
		OPText = new GuiTextField(1, fontRenderer, x + 177, y, 100, 15);
		buttonClearA = new GuiButton(0, x + 70, y + 70, 45, 20, new UtilTranslatable(TranslateType.CONTAINER, "button.clear").getFormattedText());
		buttonClearB = new GuiButton(2, x + 177, y + 18, 45, 20, new UtilTranslatable(TranslateType.CONTAINER, "button.clear").getFormattedText());
		buttonInsert = new GuiButton(3, x + 225, y + 18, 45, 20, new UtilTranslatable(TranslateType.CONTAINER, "anchor.button.insert").getFormattedText());
		buttonConfirm = new GuiButton(1, x + 125, y + 70, 45, 20, new UtilTranslatable(TranslateType.CONTAINER, "anchor.button.confirm").getFormattedText());
		staLvlList = new GuiSimpleListBox(x - 105, y + 1, 104, 99, EnumStationLevel.getDataFormTranslatedTexts());
		playerList = new GuiSimpleListBox(x + 177, y + 41, 100, 125,
				mc.isSingleplayer() ? Lists.newArrayList() : UtilDataForm.toDataForm(UtilDataForm.getPlayersName(mc.getConnection().getPlayerInfoMap())));
		if (te.isValidStation()) {
			buttonClearA.enabled = false;
			nameText.setText(StationHandler.INSTANCE.getStation(te.getPos()).getName());
			nameText.setFocused(false);
			nameText.setEnabled(false);
			OPText.setText(StationHandler.INSTANCE.getStation(te.getPos()).getOperator());
			OPText.setFocused(true);
			staLvlList.selectedIndex = StationHandler.INSTANCE.getStation(te.getPos()).getLvl().getStationLevel();
		} else {
			nameText.setFocused(true);
			nameText.setCanLoseFocus(true);
			nameText.setText(new UtilTranslatable(TranslateType.CONTAINER, "anchor.text").getFormattedText());
			nameText.setMaxStringLength(100);
			OPText.setCanLoseFocus(true);
			OPText.setMaxStringLength(100);
		}
		buttonList.add(buttonClearA);
		buttonList.add(buttonClearB);
		buttonList.add(buttonInsert);
		buttonList.add(buttonConfirm);
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
		RenderHelper.enableGUIStandardItemLighting();
		super.drawScreen(mouseX, mouseY, partialTicks);
		// draw element
		nameText.drawTextBox();
		OPText.drawTextBox();
		// draw string
		fontRenderer.drawString(BlockInit.ANCHOR.getLocalizedName(), (x + 176 / 2) - (fontRenderer.getStringWidth(BlockInit.ANCHOR.getLocalizedName()) / 2), y + 8, 4210752);
		fontRenderer.drawSplitString(new UtilTranslatable(TranslateType.CONTAINER, "anchor.station.name").getFormattedText() + (te.isValidStation() ? te.getStation().getName() : ""), x + 70, y
				+ 20, 106, 4210752);
		fontRenderer.drawSplitString(new UtilTranslatable(TranslateType.CONTAINER, "anchor.station.class").getFormattedText()
				+ (te.isValidStation() ? StationHandler.INSTANCE.getStation(te.getPos()).getLvl().getFormattedText() : ""), x + 5, y + 55, 66, 4210752);
		fontRenderer.drawSplitString(new UtilTranslatable(TranslateType.CONTAINER, "anchor.desc").getFormattedText(), x + 5, y + 93, 166, 4210752);
		// draw list
		playerList.draw(mouseX, mouseY, fontRenderer);
		staLvlList.draw(mouseX, mouseY, fontRenderer);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 0:
			resetText(nameText);
			break;
		case 1:
			if (nameText.getText().isEmpty() || nameText.getText().equalsIgnoreCase(new UtilTranslatable(TranslateType.CONTAINER, "anchor.text").getFormattedText()))
				return;
			PacketHandler.INSTANCE.sendToServer(new PacketAddStationWorker(
					new Station(nameText.getText(), te.getPos(), OPText.getText(), staLvlList.selectedIndex == -1 ? EnumStationLevel.NON : EnumStationLevel.byMetadata(staLvlList.selectedIndex))));
			buttonClearA.enabled = false;
			nameText.setEnabled(false);
			nameText.setFocused(false);
			OPText.setFocused(false);
			break;
		case 2:
			resetText(OPText);
			break;
		case 3:
			if (playerList.selectedIndex != -1) {
				if (OPText.getText().isEmpty()) {
					OPText.setText(playerList.getText());
				} else {
					OPText.setText(OPText.getText() + ", " + playerList.getText());
				}
			}
			break;
		default:
			break;
		}

	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		nameText.mouseClicked(mouseX, mouseY, mouseButton);
		OPText.mouseClicked(mouseX, mouseY, mouseButton);
		playerList.mouseClicked(mouseX, mouseY, mouseButton);
		staLvlList.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		playerList.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		staLvlList.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (!nameText.isFocused() && !OPText.isFocused() && (keyCode == 1 || mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode)))
			mc.player.closeScreen();
		super.keyTyped(typedChar, keyCode);
		nameText.textboxKeyTyped(typedChar, keyCode);
		OPText.textboxKeyTyped(typedChar, keyCode);
	}

	@Override
	public void updateScreen() {
		nameText.updateCursorCounter();
		OPText.updateCursorCounter();
		super.updateScreen();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	private void resetText(GuiTextField text) {
		text.setText("");
		text.setFocused(true);
		updateScreen();
	}
}
