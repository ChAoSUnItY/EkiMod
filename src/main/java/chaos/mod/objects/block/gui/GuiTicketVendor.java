package chaos.mod.objects.block.gui;

import chaos.mod.objects.block.container.ContainerTicketVendor;
import chaos.mod.tileentity.TileEnitityTicketVendor;
import chaos.mod.util.Reference;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiTicketVendor extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/container/ticket_vendor.png");
	private TileEnitityTicketVendor tileEntity;
	private IInventory playerInv;
	private GuiTextField textField;

	public GuiTicketVendor(IInventory playerInv, TileEnitityTicketVendor tileEntity) {
		super(new ContainerTicketVendor(playerInv, tileEntity));
		setGuiSize(176, 166);
		this.tileEntity = tileEntity;
		this.playerInv = playerInv;
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		/*
		String tileName = this.tileEntity.getDisplayName().getFormattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 8,
				4210752);
		this.fontRenderer.drawString(net.minecraft.client.resources.I18n.format("container.inventory"), 8, this.ySize - 93, 0x404040);
		*/
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}
