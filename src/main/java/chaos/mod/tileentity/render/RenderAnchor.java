package chaos.mod.tileentity.render;

import chaos.mod.Eki.EkiConfig;
import chaos.mod.tileentity.TileEntityAnchor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAnchor extends TileEntitySpecialRenderer<TileEntityAnchor> {
	@Override
	public void renderTileEntityAt(TileEntityAnchor te, double x, double y, double z, float partialTicks, int destroyStage) {
		Minecraft mc = Minecraft.getMinecraft();
		if (te.getStation().getName() != null && !te.getStation().getName().isEmpty() && !mc.gameSettings.hideGUI && EkiConfig.nametagDisplay) {
			setLightmapDisabled(true);
			drawNameplate(te, te.getStation().getOperator(), x, y, z, 36);
			drawNameplate(te, te.getStation().getName(), x, y + 0.2, z, 36);
			drawNameplate(te, te.getStation().getLvl().getFormattedText(), x, y + 0.4, z, 36);
			setLightmapDisabled(false);
		}
	}
}
