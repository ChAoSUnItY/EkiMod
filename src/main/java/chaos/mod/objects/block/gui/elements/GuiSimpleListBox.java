package chaos.mod.objects.block.gui.elements;

import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.util.data.station.DataForm;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

/***
 * This list box is borrow from noto's station mod.
 * 
 * @author Noto
 */
public class GuiSimpleListBox<T extends DataForm> extends Gui {
	public int locationX;
	public int locationY;
	public int width;
	public int height;
	protected boolean isEnable = true;
	private List<String> items = Lists.newArrayList();
	private List<? extends DataForm> raws;
	public int selectedIndex = -1;
	private int scrollLevel;
	private boolean scrollBerClicked;

	public GuiSimpleListBox(int x, int y, int w, int h) {
		this.locationX = x;
		this.locationY = y;
		this.width = w;
		this.height = h;
	}

	public GuiSimpleListBox(int x, int y, int w, int h, List<? extends DataForm> list) {
		this.locationX = x;
		this.locationY = y;
		this.width = w;
		this.height = h;
		for (int i = 0; i < list.size(); i++) {
			this.items.add(list.get(i).getData());
		}
		raws = Lists.newArrayList(list.iterator());
	}

	public void draw(int mouseX, int mouseY, FontRenderer fontRenderer) {
		drawRect(this.locationX, this.locationY, this.locationX + this.width, this.locationY + this.height, -2139062144);
		int itemMax = this.height / 12;
		int itemCount = 0;
		if (this.items.size() > 0 && this.selectedIndex != -1 && this.scrollLevel <= this.selectedIndex && itemMax + this.scrollLevel > this.selectedIndex) {
			drawRect(this.locationX + 2, this.locationY + this.selectedIndex * 12 - this.scrollLevel * 12 + 2, this.locationX + this.width - 14, this.locationY + this.selectedIndex * 12
					- this.scrollLevel * 12 + 2 + 12, -2134851392);
		}
		for (int i = this.scrollLevel; i < itemMax + this.scrollLevel;) {
			if (this.items.size() > i) {
				fontRenderer.drawStringWithShadow(this.items.get(i), 4 + this.locationX, itemCount * 12 + this.locationY + 4, 16777215);
				itemCount++;
			}
			i++;
		}
		if (this.height / 12 < this.items.size()) {
			drawRect(this.locationX + this.width - 12, this.locationY, this.locationX + this.width, this.locationY + 12, -1061109632);
			drawRect(this.locationX + this.width - 12, this.locationY + 12, this.locationX + this.width, this.locationY + this.height - 12, -2134851392);
			drawRect(this.locationX + this.width - 12, this.locationY + this.height - 12, this.locationX + this.width, this.locationY + this.height, -1061109632);
			int top = this.locationY + 12 + 2;
			int bottom = this.locationY + this.height - 12 - 2;
			int size = Math.max(1, (bottom - top) / (this.items.size() - itemMax + 1));
			if (this.scrollLevel == this.items.size() - itemMax) {
				drawRect(this.locationX + this.width - 10, this.locationY + this.height - 12 - size - 2, this.locationX + this.width - 2, this.locationY + this.height - 14, -1061109632);
			} else {
				drawRect(this.locationX + this.width - 10, top + size * this.scrollLevel, this.locationX + this.width - 2, top + size + size * this.scrollLevel, -1061109632);
			}
		}
	}

	public void mouseClicked(int mouseX, int mouseY, int button) {
		if (button == 0 && this.items.size() > 0 && this.isEnable) {
			if (this.height / 12 < this.items.size())
				if (this.locationX + this.width - 12 <= mouseX && this.locationY <= mouseY && this.locationX + this.width >= mouseX && this.locationY + 12 >= mouseY) {
					this.scrollLevel = Math.max(0, this.scrollLevel - 1);
				} else if (this.locationX + this.width - 12 <= mouseX && this.locationY + this.height - 12 <= mouseY && this.locationX + this.width >= mouseX
						&& this.locationY + this.height >= mouseY) {
					this.scrollLevel = Math.min(this.items.size() - this.height / 12, this.scrollLevel + 1);
				} else if (this.locationX + this.width - 12 <= mouseX && this.locationY + 12 <= mouseY && this.locationX + this.width >= mouseX && this.locationY + this.height - 12 >= mouseY) {
					int top = this.locationY + 12;
					int bottom = this.locationY + this.height - 12;
					int size = (bottom - top) / (this.items.size() - this.height / 12 + 1);
					int sel = (mouseY - this.locationY - 12) / size;
					this.scrollLevel = sel;
					this.scrollBerClicked = true;
				}
			if (this.locationX <= mouseX && this.locationY <= mouseY && this.locationX + this.width - 12 >= mouseX && this.locationY + this.height >= mouseY) {
				int sel = (mouseY - this.locationY - 2) / 12 + this.scrollLevel;
				if (sel > -1 && sel < this.items.size())
					if (this.selectedIndex != sel) {
						this.selectedIndex = sel;
						selectChanged();
					}
			}
		}
	}

	public void mouseClickMove(int mouseX, int mouseY, int button, long time) {
		if (button == 0 && this.scrollBerClicked && this.isEnable) {
			int top = this.locationY + 12;
			int bottom = this.locationY + this.height - 12;
			int size = (bottom - top) / (this.items.size() - this.height / 12 + 1);
			int sel = (mouseY - this.locationY - 12) / size;
			this.scrollLevel = Math.min(this.items.size() - this.height / 12, Math.max(0, sel));
		}
	}

	public void mouseMovedOrUp(int mouseX, int mouseY, int mode) {
		this.scrollBerClicked = false;
	}

	public void selectChanged() {
	}

	public void setSelectedIndex(int par1) {
		this.selectedIndex = par1;
		selectChanged();
	}

	public String getText() {
		if (this.selectedIndex != -1 && this.selectedIndex < this.items.size()) {
			String str = this.items.get(this.selectedIndex);
			return (str == null) ? "" : str;
		}
		return "";
	}

	public void onResize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void reloadList(List<T> list) {
		this.items.clear();
		for (int i = 0; i < list.size(); i++) {
			this.items.add(list.get(i).toString());
		}
	}

	public boolean onTheMouse(int mouseX, int mouseY) {
		return (mouseX >= this.locationX && mouseY >= this.locationY && this.locationX + this.width >= mouseX && this.locationY + this.height >= mouseY);
	}

	public List<String> getItems() {
		return items;
	}

	public List<? extends DataForm> getRaws() {
		return raws;
	}
}
