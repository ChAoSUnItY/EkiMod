package chaos.mod.objects.item.gui;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.objects.block.gui.elements.GuiSimpleListBox;
import chaos.mod.tileentity.TileEntityTicketVendor.SortType;
import chaos.mod.util.Reference;
import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.utils.UtilStationSystem;
import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

public class GuiMainPage extends GuiScreen {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/manager/main.png");
	private GuiSimpleListBox stations;
	private GuiButton inspectButton;
	private GuiButton sortButton;
	private BlockPos pos;
	private SortType type;
	private int x;
	private int y;

	public GuiMainPage(BlockPos pos, SortType type) {
		this.pos = pos;
		if (type == null)
			this.type = SortType.NF;
		else {
			this.type = type;
		}
	}

	@Override
	public void initGui() {
		super.initGui();
		x = width / 2 - 74;
		y = height / 2 - 128;
		stations = new GuiSimpleListBox(x + 5, y + 50, 137, 100, UtilStationSystem.sortByFarNF(pos, StationHandler.INSTANCE.getStations())).setTextColor(0x267F00);
		getCorrectType();
		sortButton = new GuiButton(0, x + 10, y + 153, 45, 20, TextFormatting.DARK_GREEN + type.getName());
		inspectButton = new GuiButton(1, x + 92, y + 153, 45, 20, new UtilTranslatable(TranslateType.CONTAINER, "manager.button.inspect").applyFormat(TextFormatting.DARK_GREEN).getFormattedText());
		buttonList.add(sortButton);
		buttonList.add(inspectButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		GlStateManager.color(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(TEXTURES);
		drawTexturedModalRect(x, y, 0, 0, 147, 256);
		super.drawScreen(mouseX, mouseY, partialTicks);
		fontRendererObj.drawSplitString(new UtilTranslatable(TranslateType.CONTAINER, "manager.intro").getFormattedText(), x + 10, y + 10, 132, 0x267F00);
		stations.draw(mouseX, mouseY, fontRendererObj);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 1 || mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode))
			mc.thePlayer.closeScreen();
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 0:
			nextType();
			break;
		case 1:
			if (stations.selectedIndex == -1)
				return;
			Minecraft.getMinecraft().displayGuiScreen(new GuiInspect(((Station) stations.getRaws().get(stations.selectedIndex)), type, pos));
			break;
		default:
			break;
		}
		updateScreen();
	}

	private void getCorrectType() {
		List<Station> stas = StationHandler.INSTANCE.getStations();
		List<Station> cache = Lists.newArrayList();
		switch (type) {
		case NF:
			cache = UtilStationSystem.sortByFarNF(pos, stas);
			break;
		case FN:
			cache = UtilStationSystem.sortByFarFN(pos, stas);
			break;
		case NAME:
			cache = UtilStationSystem.sortByName(stas);
			break;
		default:
			break;
		}
		stations.reloadList(cache);
	}

	private void nextType() {
		type = SortType.TYPES[(type.ordinal() + 1) % 3];
		sortButton.displayString = TextFormatting.DARK_GREEN + type.getName();
		List<Station> stas = StationHandler.INSTANCE.getStations();
		List<Station> cache = Lists.newArrayList();
		switch (type) {
		case NF:
			cache = UtilStationSystem.sortByFarNF(pos, stas);
			break;
		case FN:
			cache = UtilStationSystem.sortByFarFN(pos, stas);
			break;
		case NAME:
			cache = UtilStationSystem.sortByName(stas);
			break;
		default:
			break;
		}
		stations.reloadList(cache);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		stations.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		stations.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
