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
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/gui/container/ticket_vendor.png");
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
		this.state = TextFormatting.GRAY + I18n.format("container.ticket_vendor.state.waiting");
		this.container = new ContainerTicketVendor(invPlayer, tileEntity, player);
	}

	@Override
	public void initGui() {
		super.initGui();
		text = new GuiTextField(0, fontRendererObj, guiLeft + baseX + 1, guiTop + baseY, baseWidthX, baseHeightY);
		buttonProvide = new GuiButton(0, guiLeft + baseX, guiTop + baseY + 20, (baseWidthX / 2) - 10,
				(baseHeightY / 2) + 10, TextFormatting.WHITE + I18n.format("container.ticket_vendor.button.provide"));
		buttonClear = new GuiButton(1, guiLeft + (baseX * 3) + 23, guiTop + baseY + 20, (baseWidthX / 2) - 10,
				(baseHeightY / 2) + 10, TextFormatting.WHITE + I18n.format("container.ticket_vendor.button.clear"));
		this.buttonList.add(this.buttonProvide);
		this.buttonList.add(this.buttonClear);
		this.text.setFocused(true);
		this.text.setCanLoseFocus(true);
		this.text.setText(I18n.format("container.ticket_vendor.text.default"));
		this.text.setMaxStringLength(100);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.buttonProvide.drawButton(mc, mouseX, mouseY);
		this.buttonClear.drawButton(mc, mouseX, mouseY);
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.text.drawTextBox();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(I18n.format("tile.ticket_vendor.name"),
				(this.xSize / 2 - this.fontRendererObj.getStringWidth(I18n.format("tile.ticket_vendor.name")) / 2) + 3, 8,
				4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 93, 4210752);
		this.fontRendererObj.drawString(this.state, (this.xSize / 2) - (this.fontRendererObj.getStringWidth(state) / 2), 20,
				16711680);
		if (this.warning != null) {
			this.fontRendererObj.drawString(this.warning, 8, this.ySize - 113, 16711680);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		this.text.mouseClicked(mouseX, mouseY, mouseButton);
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
				this.state = TextFormatting.RED + I18n.format("container.ticket_vendor.state.failed", text.getText());
				updateScreen();
				return;
			}
			this.state = TextFormatting.GREEN + I18n.format("container.ticket_vendor.state.success", price);
			if (price <= 0) {
				this.state = TextFormatting.RED + I18n.format("container.ticket_vendor.state.shortage", price);
				return;
			}
			PacketHandler.INSTANCE.sendToServer(new PacketVendorSpawnItemWorker(this.tileEntity.getPos(), price));
			updateScreen();
			break;
		case 1:
			this.text.setText("");
			this.text.setFocused(true);
			updateScreen();
			break;
		default:
			break;
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		this.text.textboxKeyTyped(typedChar, keyCode);
	}

	@Override
	public void updateScreen() {
		this.text.updateCursorCounter();
		super.updateScreen();
	}
}