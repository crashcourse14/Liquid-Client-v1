package net.polarclient.gui.mod.mods;

import net.polarclient.Client;
import net.polarclient.gui.mod.HudMod;
import net.minecraft.src.FontRenderer;


public class TestMod extends HudMod {

    public FontRenderer fr = mc.fontRenderer;

    public TestMod() {
        super("§5[§dTest Mod§5]§f", 5, 5);
    }

    @Override 
    public void draw() {
        fr.drawStringWithShadow(name, getX(), getY(), -1);
        super.draw();
    }

    public int getWidth() {
        return fr.getStringWidth(name);
    }

    public int getHeight() {
        return fr.FONT_HEIGHT;
    }

}
