package chaos.mod.objects.block.gui.stationManager;

import java.io.IOException;

import chaos.mod.init.BlockInit;
import chaos.mod.tileentity.TileEntityTicketVendor.SortType;
import chaos.mod.util.Reference;
import chaos.mod.util.data.station.Station;
import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

public class GuiInspect extends GuiScreen {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/manager/main.png");
	private GuiButton inspectButton;
	private Station station;
	private SortType type;
	private BlockPos pos;
	private int x;
	private int y;

	public GuiInspect(Station station, SortType type, BlockPos pos) {
		this.station = station;
		this.type = type;
		this.pos = pos;
	}

	@Override
	public void initGui() {
		super.initGui();
		x = width / 2 - 74;
		y = height / 2 - 128;
		inspectButton = new GuiButton(0, x + 10, y + 10, 45, 20, new UtilTranslatable(TranslateType.CONTAINER, "manager.button.back").applyFormat(TextFormatting.DARK_GREEN).getFormattedText());
		buttonList.add(inspectButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		GlStateManager.color(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(TEXTURES);
		drawTexturedModalRect(x, y, 0, 0, 147, 256);
		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		float size = 3.0f;
		GlStateManager.scale(size, size, size);
		float msize = (float) Math.pow(size, -1);
		itemRender.renderItemAndEffectIntoGUI(new ItemStack(BlockInit.ANCHOR), Math.round(x / size) + 17, Math.round(y / size) + 15);
		GlStateManager.scale(msize, msize, msize);
		RenderHelper.enableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		super.drawScreen(mouseX, mouseY, partialTicks);
		fontRenderer.drawSplitString("Sta. Name: " + station.getName(), x + 10, y + 100, 127, 0x267F00);
		fontRenderer.drawSplitString("Position: " + station.getPosStringFormat(), x + 10, y + 120, 127, 0x267F00);
		fontRenderer.drawSplitString("Operator: " + station.getOperator(), x + 10, y + 140, 127, 0x267F00);
		fontRenderer.drawSplitString("Sta. Level: " + station.getLvl().getFormattedText(), x + 10, y + 160, 127, 0x267F00);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 1 || mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode)) {
			mc.player.closeScreen();
		}
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == 0)
			Minecraft.getMinecraft().displayGuiScreen(new GuiMainPage(pos, type));
		super.actionPerformed(button);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
