package chaos.mod.objects.block.gui;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.Eki;
import chaos.mod.init.BlockInit;
import chaos.mod.objects.block.container.ContainerTicketVendor;
import chaos.mod.objects.block.gui.elements.GuiSimpleListBox;
import chaos.mod.tileentity.TileEntityTicketVendor;
import chaos.mod.tileentity.TileEntityTicketVendor.SortType;
import chaos.mod.util.Reference;
import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.PacketHandler;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.network.PacketVendorSpawnItemWorker;
import chaos.mod.util.network.PacketVendorWithdrawWorker;
import chaos.mod.util.utils.UtilStationSystem;
import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import the_fireplace.grandeconomy.api.GrandEconomyApi;

public class GuiTicketVendor extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/container/ticket_vendor.png");
	private TileEntityTicketVendor te;
	private EntityPlayer player;
	private SortType type;
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
	public GuiButton buttonSort;
	public GuiSimpleListBox listStations;
	public Container container;

	public GuiTicketVendor(InventoryPlayer invPlayer, TileEntityTicketVendor te, EntityPlayer player) {
		super(new ContainerTicketVendor(invPlayer, te, player));

		this.te = te;
		this.player = player;
		this.type = SortType.NF;
		state = new UtilTCString(TranslateType.CONTAINER, "ticket_vendor.state.waiting").applyFormat(TextFormatting.GRAY).getFormattedText();
		container = new ContainerTicketVendor(invPlayer, te, player);
	}

	@Override
	public void initGui() {
		super.initGui();
		text = new GuiTextField(0, fontRenderer, guiLeft + baseX + 1, guiTop + baseY, baseWidthX, baseHeightY);
		if (Eki.isApiModLoaded) {
			buttonProvide = new GuiButton(0, guiLeft + 23, guiTop + 52, 35, 20, new UtilTCString(TranslateType.CONTAINER, "ticket_vendor.button.provide").getFormattedText());
			buttonWithdraw = new GuiButton(1, guiLeft + 58, guiTop + 52, 45, 20, new UtilTCString(TranslateType.CONTAINER, "ticket_vendor.button.withdraw").getFormattedText());
			buttonClear = new GuiButton(2, guiLeft + 103, guiTop + 52, 35, 20, new UtilTCString(TranslateType.CONTAINER, "button.clear").getFormattedText());
			buttonList.add(buttonWithdraw);
		} else {
			buttonProvide = new GuiButton(0, guiLeft + baseX, guiTop + baseY + 20, (baseWidthX / 2) - 10, 20,
					new UtilTCString(TranslateType.CONTAINER, "ticket_vendor.button.provide").getFormattedText());
			buttonClear = new GuiButton(1, guiLeft + (baseX * 3) + 23, guiTop + baseY + 20, (baseWidthX / 2) - 10, 20, new UtilTCString(TranslateType.CONTAINER, "button.clear").getFormattedText());
		}
		buttonSort = new GuiButton(3, guiLeft + xSize, guiTop - 20, 40, 20, type.getName());
		listStations = new GuiSimpleListBox(guiLeft + xSize, guiTop + 10, ySize - 20, UtilStationSystem.sortByFarNF(te.getAnchor(), StationHandler.INSTANCE.getStations()), mc);
		buttonList.add(buttonProvide);
		buttonList.add(buttonClear);
		if (!StationHandler.INSTANCE.getStations().isEmpty())
			buttonList.add(buttonSort);
		text.setFocused(true);
		text.setCanLoseFocus(true);
		text.setText(new UtilTCString(TranslateType.CONTAINER, "ticket_vendor.text.default").getFormattedText());
		text.setMaxStringLength(100);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (Eki.isApiModLoaded)
			buttonWithdraw.drawButton(mc, mouseX, mouseY, partialTicks);
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		text.drawTextBox();
		listStations.draw(mouseX, mouseY, fontRenderer);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(BlockInit.TICKET_VENDOR.getLocalizedName(), (xSize / 2 - fontRenderer.getStringWidth(BlockInit.TICKET_VENDOR.getLocalizedName()) / 2), 8, 4210752);
		fontRenderer.drawString(new UtilTranslatable("container.inventory").getFormattedText(), 8, ySize - 93, 4210752);
		fontRenderer.drawString(state, xSize / 2 - fontRenderer.getStringWidth(state) / 2, 20, 16711680);
		if (warning != null)
			fontRenderer.drawString(warning, 8, ySize - 113, 16711680);
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
		listStations.mouseClicked(mouseX, mouseY, mouseButton);
		// predict price and enter to the textfield
		if (listStations.selectedIndex != -1)
			text.setText(String.format("%.2f", UtilStationSystem.calculatePrice(te.getAnchor(), ((Station) listStations.getRaws().get(listStations.selectedIndex)).getPos())));
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
				if (player.canUseCommand(2, "")) {
					state = new UtilTCString(TranslateType.CONTAINER, "ticket_vendor.state.withdraw").applyFormat(TextFormatting.GREEN).getFormattedText();
					PacketHandler.INSTANCE.sendToServer(new PacketVendorWithdrawWorker(player.getGameProfile().getId(), te.getPos()));
				} else {
					state = new UtilTCString(TranslateType.CONTAINER, "ticket_vendor.state.permission").applyFormat(TextFormatting.RED).getFormattedText();
				}
				break;
			case 2:
				resetText();
				break;
			case 3:
				nextType();
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
			case 3:
				nextType();
				break;
			default:
				break;
			}
		}
		updateScreen();
	}

	private void nextType() {
		type = SortType.TYPES[(type.ordinal() + 1) % 3];
		buttonSort.displayString = type.getName();
		List<Station> stations = StationHandler.INSTANCE.getStations();
		List<Station> cache = Lists.newArrayList();
		switch (type) {
		case NF:
			cache = UtilStationSystem.sortByFarNF(te.getPos(), stations);
			break;
		case FN:
			cache = UtilStationSystem.sortByFarFN(te.getPos(), stations);
			break;
		case NAME:
			cache = UtilStationSystem.sortByName(stations);
			break;
		default:
			break;
		}
		listStations.reloadList(cache);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		text.textboxKeyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		listStations.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}

	@Override
	public void updateScreen() {
		text.updateCursorCounter();
		super.updateScreen();
	}

	private void tryVendingTicket() {
		int price;
		try {
			price = (int) Double.parseDouble(text.getText());
		} catch (NumberFormatException e) {
			state = new UtilTCString(TranslateType.CONTAINER, "ticket_vendor.state.failed", text.getText()).applyFormat(TextFormatting.RED).getFormattedText();
			updateScreen();
			return;
		}
		state = new UtilTCString(TranslateType.CONTAINER, "ticket_vendor.state.success", price).applyFormat(TextFormatting.GREEN).getFormattedText();
		if (price <= 0) {
			state = new UtilTCString(TranslateType.CONTAINER, "ticket_vendor.state.shortage", price).applyFormat(TextFormatting.RED).getFormattedText();
			return;
		} else if (Eki.isApiModLoaded) {
			if ((int) GrandEconomyApi.getBalance(player.getUniqueID(), true) >= price)
				GrandEconomyApi.takeFromBalance(player.getUniqueID(), price, true);
			else {
				state = new UtilTCString(TranslateType.CONTAINER, "ticket_vendor.state.shortage", price).applyFormat(TextFormatting.RED).getFormattedText();
				updateScreen();
				return;
			}
		}
		PacketHandler.INSTANCE.sendToServer(new PacketVendorSpawnItemWorker(this.te.getPos(), price));
		updateScreen();
	}

	private void resetText() {
		text.setText("");
		text.setFocused(true);
		updateScreen();
	}
}
