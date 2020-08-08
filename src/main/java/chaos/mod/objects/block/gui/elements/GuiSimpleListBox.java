package chaos.mod.objects.block.gui.elements;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import chaos.mod.util.data.station.DataForm;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

/***
 * This list box is borrow from noto's station mod.
 * 
 * @author Noto
 */
public class GuiSimpleListBox extends Gui {
	public int x;
	public int y;
	public int w;
	public int h;
	protected boolean isEnable = true;
	private List<String> items = Lists.newArrayList();
	private List<? extends DataForm> raws;
	public int selectedIndex = -1;
	private int scrollLevel;
	private boolean scrollBerClicked;
	private boolean shouldDrawBackground = true;

	public GuiSimpleListBox(int x, int y, int w, int h, List<? extends DataForm> list) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		list.forEach(d -> items.add(d.getData()));

		raws = Lists.newArrayList(list.iterator());
	}

	public GuiSimpleListBox(int x, int y, int h, List<? extends DataForm> list, Minecraft mc) {
		this.x = x;
		this.y = y;
		this.h = h;

		list.forEach(d -> items.add(d.getData()));
		List<Integer> cache = Lists.newArrayList();

		items.forEach(i -> cache.add(mc.fontRenderer.getStringWidth(i)));

		if (!cache.isEmpty())
			w = Collections.max(cache) + 5;

		raws = Lists.newArrayList(list.iterator());
	}

	public void draw(int mouseX, int mouseY, FontRenderer fontRenderer) {
		if (shouldDrawBackground)
			drawRect(x, y, x + w, y + h, -2139062144);
		int itemMax = h / 12;
		int itemCount = 0;
		if (items.size() > 0 && selectedIndex != -1 && scrollLevel <= selectedIndex && itemMax + scrollLevel > selectedIndex) {
			drawRect(x + 2, y + selectedIndex * 12 - scrollLevel * 12 + 2, x + w - 14, y + selectedIndex * 12 - scrollLevel * 12 + 2 + 12, -2134851392);
		}
		for (int i = scrollLevel; i < itemMax + scrollLevel;) {
			if (items.size() > i) {
				fontRenderer.drawStringWithShadow(items.get(i), 4 + x, itemCount * 12 + y + 4, 16777215);
				itemCount++;
			}
			i++;
		}
		if (h / 12 < items.size()) {
			drawRect(x + w - 12, y, x + w, y + 12, -1061109632);
			drawRect(x + w - 12, y + 12, x + w, y + h - 12, -2134851392);
			drawRect(x + w - 12, y + h - 12, x + w, y + h, -1061109632);
			int top = y + 12 + 2;
			int bottom = y + h - 12 - 2;
			int size = Math.max(1, (bottom - top) / (items.size() - itemMax + 1));
			if (scrollLevel == items.size() - itemMax) {
				drawRect(x + w - 10, y + h - 12 - size - 2, x + w - 2, y + h - 14, -1061109632);
			} else {
				drawRect(x + w - 10, top + size * scrollLevel, x + w - 2, top + size + size * scrollLevel, -1061109632);
			}
		}
	}

	public void mouseClicked(int mouseX, int mouseY, int button) {
		if (button == 0 && items.size() > 0 && isEnable) {
			if (h / 12 < items.size())
				if (x + w - 12 <= mouseX && y <= mouseY && x + w >= mouseX && y + 12 >= mouseY) {
					scrollLevel = Math.max(0, scrollLevel - 1);
				} else if (x + w - 12 <= mouseX && y + h - 12 <= mouseY && x + w >= mouseX && y + h >= mouseY) {
					scrollLevel = Math.min(items.size() - h / 12, scrollLevel + 1);
				} else if (x + w - 12 <= mouseX && y + 12 <= mouseY && x + w >= mouseX && y + h - 12 >= mouseY) {
					int top = y + 12;
					int bottom = y + h - 12;
					int size = (bottom - top) / (items.size() - h / 12 + 1);
					int sel = (mouseY - y - 12) / size;
					scrollLevel = sel;
					scrollBerClicked = true;
				}
			if (x <= mouseX && y <= mouseY && x + w - 12 >= mouseX && y + h >= mouseY) {
				int sel = (mouseY - y - 2) / 12 + scrollLevel;
				if (sel > -1 && sel < items.size())
					if (selectedIndex != sel) {
						selectedIndex = sel;
						selectChanged();
					}
			}
		}
	}

	public void mouseClickMove(int mouseX, int mouseY, int button, long time) {
		if (button == 0 && scrollBerClicked && isEnable) {
			int top = y + 12;
			int bottom = y + h - 12;
			int size = (bottom - top) / (items.size() - h / 12 + 1);
			int sel = (mouseY - y - 12) / size;
			scrollLevel = Math.min(items.size() - h / 12, Math.max(0, sel));
		}
	}

	public void mouseMovedOrUp(int mouseX, int mouseY, int mode) {
		scrollBerClicked = false;
	}

	public void selectChanged() {
	}

	public void setSelectedIndex(int par1) {
		selectedIndex = par1;
		selectChanged();
	}

	public String getText() {
		if (selectedIndex != -1 && selectedIndex < items.size()) {
			String str = items.get(selectedIndex);
			return (str == null) ? "" : str;
		}
		return "";
	}

	public void onResize(int width, int height) {
		w = width;
		h = height;
	}

	public void reloadList(List<? extends DataForm> list) {
		items.clear();
		list.forEach(d -> items.add(d.getData()));
		raws = Lists.newArrayList(list.iterator());
	}

	public boolean onTheMouse(int mouseX, int mouseY) {
		return (mouseX >= x && mouseY >= y && x + w >= mouseX && y + h >= mouseY);
	}

	public List<String> getItems() {
		return items;
	}

	public List<? extends DataForm> getRaws() {
		return raws;
	}

	public void setDatas(List<? extends DataForm> datas) {
		for (int i = 0; i < datas.size(); i++) {
			items.add(datas.get(i).getData());
		}
		raws = Lists.newArrayList(datas.iterator());
	}

	public void shouldDrawBackground(boolean bool) {
		shouldDrawBackground = bool;
	}
}
