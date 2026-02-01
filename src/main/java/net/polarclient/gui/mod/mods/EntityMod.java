package net.polarclient.gui.mod.mods;

import net.minecraft.client.Minecraft;
import net.polarclient.gui.mod.HudMod;

public class EntityMod extends HudMod {

    Minecraft mc = Minecraft.getMinecraft();

    public EntityMod() {
        super("Entity", 5, 60);
    }

    @Override 
    public void draw() {
        fr.drawStringWithShadow("§6[§e" + mc.renderGlobal.getDebugInfoEntitiesShort() + " Entities§6]", getX(), getY(), -1);
        super.draw();
    }


    @Override
    public int getWidth() {
        return fr.getStringWidth("§6[§e" + mc.renderGlobal.getDebugInfoEntitiesShort() + " Entities§6]");
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }

}
