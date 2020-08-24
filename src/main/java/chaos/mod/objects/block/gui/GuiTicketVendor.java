package chaos.mod.objects.block.gui;

import java.io.IOException;

import chaos.mod.objects.block.container.ContainerTicketVendor;
import chaos.mod.tileentity.TileEntityTicketVendor;
import chaos.mod.util.Reference;
import chaos.mod.util.handlers.PacketHandler;
import chaos.mod.util.network.PacketVendorSpawnItemWorker;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiTicketVendor extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/container/ticket_vendor.png");
	private TileEntityTicketVendor tileEntity;
	private EntityPlayer player;
	protected int baseX = 21;
	protected int baseY = 32;
	protected int baseWidthX = 105;
	protected int baseHeightY = 16;
	protected String state;
	protected String warning;
	public GuiTextField text;
	public GuiButton buttonProvide;
	public GuiButton buttonClear;
	public Container container;

	public GuiTicketVendor(InventoryPlayer invPlayer, TileEntityTicketVendor tileEntity, EntityPlayer player) {
		super(new ContainerTicketVendor(invPlayer, tileEntity, player));

		this.tileEntity = tileEntity;
		this.player = player;
		state = TextFormatting.GRAY + I18n.format("container.ticket_vendor.state.waiting");
		container = new ContainerTicketVendor(invPlayer, tileEntity, player);
	}

	@Override
	public void initGui() {
		super.initGui();
		text = new GuiTextField(0, fontRendererObj, guiLeft + baseX + 1, guiTop + baseY, baseWidthX, baseHeightY);
		buttonProvide = new GuiButton(0, guiLeft + baseX, guiTop + baseY + 20, (baseWidthX / 2) - 10, (baseHeightY / 2) + 10,
				TextFormatting.WHITE + I18n.format("container.ticket_vendor.button.provide"));
		buttonClear = new GuiButton(1, guiLeft + (baseX * 3) + 23, guiTop + baseY + 20, (baseWidthX / 2) - 10, (baseHeightY / 2) + 10,
				TextFormatting.WHITE + I18n.format("container.ticket_vendor.button.clear"));
		buttonList.add(buttonProvide);
		buttonList.add(buttonClear);
		text.setFocused(true);
		text.setCanLoseFocus(true);
		text.setText(I18n.format("container.ticket_vendor.text.default"));
		text.setMaxStringLength(100);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		buttonProvide.drawButton(mc, mouseX, mouseY);
		buttonClear.drawButton(mc, mouseX, mouseY);
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		text.drawTextBox();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString(I18n.format("tile.ticket_vendor.name"), (xSize / 2 - fontRendererObj.getStringWidth(I18n.format("tile.ticket_vendor.name")) / 2) + 3, 8, 4210752);
		fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 93, 4210752);
		fontRendererObj.drawString(state, (xSize / 2) - (fontRendererObj.getStringWidth(state) / 2), 20, 16711680);
		if (warning != null) {
			fontRendererObj.drawString(warning, 8, ySize - 113, 16711680);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(TEXTURES);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		text.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		int price;
		switch (button.id) {
		case 0:
			try {
				price = Integer.parseInt(text.getText().toString());
			} catch (NumberFormatException e) {
				System.out.println(I18n.format("container.ticket_vendor.state.illegal", player.getDisplayNameString()));
				state = TextFormatting.RED + I18n.format("container.ticket_vendor.state.failed", text.getText());
				updateScreen();
				return;
			}
			state = TextFormatting.GREEN + I18n.format("container.ticket_vendor.state.success", price);
			if (price <= 0) {
				state = TextFormatting.RED + I18n.format("container.ticket_vendor.state.shortage", price);
				return;
			}
			PacketHandler.INSTANCE.sendToServer(new PacketVendorSpawnItemWorker(tileEntity.getPos(), price));
			updateScreen();
			break;
		case 1:
			text.setText("");
			text.setFocused(true);
			updateScreen();
			break;
		default:
			break;
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		text.textboxKeyTyped(typedChar, keyCode);
	}

	@Override
	public void updateScreen() {
		text.updateCursorCounter();
		super.updateScreen();
	}
}