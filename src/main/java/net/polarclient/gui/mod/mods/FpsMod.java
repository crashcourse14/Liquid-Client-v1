package net.polarclient.gui.mod.mods;

import net.minecraft.client.Minecraft;
import net.polarclient.gui.mod.HudMod;

public class FpsMod extends HudMod {


    Minecraft mc = Minecraft.getMinecraft();

    public FpsMod() {
        super("FPS", 5, 35);
    }

    @Override 
    public void draw() {
        fr.drawStringWithShadow("§6[§e" + Minecraft.debugFPS + " FPS§6]", getX(), getY(), -1);
        super.draw();
    }


    @Override
    public int getWidth() {
        return fr.getStringWidth("§6[§e" + Minecraft.debugFPS + " FPS§6]");
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }

}
