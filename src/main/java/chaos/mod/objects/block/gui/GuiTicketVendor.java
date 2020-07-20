package chaos.mod.objects.block.gui;

import java.io.IOException;

import chaos.mod.Eki;
import chaos.mod.objects.block.container.ContainerTicketVendor;
import chaos.mod.tileentity.TileEntityTicketVendor;
import chaos.mod.util.Reference;
import chaos.mod.util.handlers.PacketHandler;
import chaos.mod.util.network.PacketVendorSpawnItemWorker;
import chaos.mod.util.network.PacketVendorWithdrawWorker;
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
import the_fireplace.grandeconomy.api.GrandEconomyApi;

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
	public GuiButton buttonWithdraw;
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
		text = new GuiTextField(0, fontRenderer, guiLeft + baseX + 1, guiTop + baseY, baseWidthX, baseHeightY);
		if (Eki.isApiModLoaded) {
			buttonProvide = new GuiButton(0, guiLeft + 21, guiTop + baseY + 20, (baseWidthX / 3), (baseHeightY / 2) + 10, TextFormatting.WHITE + I18n.format("container.ticket_vendor.button.provide"));
			buttonWithdraw = new GuiButton(1, guiLeft + 58, guiTop + baseY + 20, (baseWidthX / 3), (baseHeightY / 2) + 10, I18n.format("container.ticket_vendor.button.withdraw"));
			buttonClear = new GuiButton(2, guiLeft + 95, guiTop + baseY + 20, (baseWidthX / 3), (baseHeightY / 2) + 10, TextFormatting.WHITE + I18n.format("container.ticket_vendor.button.clear"));
			this.buttonList.add(this.buttonWithdraw);
		} else {
			buttonProvide = new GuiButton(0, guiLeft + baseX, guiTop + baseY + 20, (baseWidthX / 2) - 10, (baseHeightY / 2) + 10,
					TextFormatting.WHITE + I18n.format("container.ticket_vendor.button.provide"));
			buttonClear = new GuiButton(1, guiLeft + (baseX * 3) + 23, guiTop + baseY + 20, (baseWidthX / 2) - 10, (baseHeightY / 2) + 10,
					TextFormatting.WHITE + I18n.format("container.ticket_vendor.button.clear"));
		}
		this.buttonList.add(this.buttonProvide);
		this.buttonList.add(this.buttonClear);
		this.text.setFocused(true);
		this.text.setCanLoseFocus(true);
		this.text.setText(I18n.format("container.ticket_vendor.text.default"));
		this.text.setMaxStringLength(100);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (Eki.isApiModLoaded) {
			this.buttonWithdraw.drawButton(mc, mouseX, mouseY, partialTicks);
		}
		this.buttonProvide.drawButton(mc, mouseX, mouseY, partialTicks);
		this.buttonClear.drawButton(mc, mouseX, mouseY, partialTicks);
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.text.drawTextBox();
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(I18n.format("tile.ticket_vendor.name"), (this.xSize / 2 - this.fontRenderer.getStringWidth(I18n.format("tile.ticket_vendor.name")) / 2) + 3, 8, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 93, 4210752);
		this.fontRenderer.drawString(this.state, (this.xSize / 2) - (this.fontRenderer.getStringWidth(state) / 2), 20, 16711680);
		if (this.warning != null) {
			this.fontRenderer.drawString(this.warning, 8, this.ySize - 113, 16711680);
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
		if (Eki.isApiModLoaded) {
			switch (button.id) {
			case 0:
				tryVendingTicket();
				break;
			case 1:
				if (this.player.canUseCommand(2, "")) {
					this.state = TextFormatting.GREEN + I18n.format("container.ticket_vendor.state.withdraw");
					PacketHandler.INSTANCE.sendToServer(new PacketVendorWithdrawWorker(this.player.getGameProfile().getId(), this.tileEntity.getPos()));
				} else {
					this.state = TextFormatting.RED + I18n.format("container.ticket_vendor.state.permission");
				}
				updateScreen();
				break;
			case 2:
				resetText();
				break;
			default:
				break;
			}
			return;
		} else {
			switch (button.id) {
			case 0:
				tryVendingTicket();
				break;
			case 1:
				resetText();
				break;
			default:
				break;
			}
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

	private void tryVendingTicket() {
		int price;
		try {
			price = Integer.parseInt(text.getText());
		} catch (NumberFormatException e) {
			this.state = TextFormatting.RED + I18n.format("container.ticket_vendor.state.failed", text.getText());
			updateScreen();
			return;
		}
		this.state = TextFormatting.GREEN + I18n.format("container.ticket_vendor.state.success", price);
		if (price <= 0) {
			this.state = TextFormatting.RED + I18n.format("container.ticket_vendor.state.shortage", price);
			return;
		} else if (Eki.isApiModLoaded) {
			if ((int) GrandEconomyApi.getBalance(player.getUniqueID(), true) >= price) {
				GrandEconomyApi.takeFromBalance(player.getUniqueID(), price, true);
			} else {
				this.state = TextFormatting.RED + I18n.format("container.ticket_vendor.state.shortage", price);
				updateScreen();
				return;
			}
		}
		PacketHandler.INSTANCE.sendToServer(new PacketVendorSpawnItemWorker(this.tileEntity.getPos(), price, player.getGameProfile().getId()));
		updateScreen();
	}

	private void resetText() {
		this.text.setText("");
		this.text.setFocused(true);
		updateScreen();
	}
}
