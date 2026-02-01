package net.polarclient.gui.mod.mods;

import net.minecraft.client.Minecraft;
import net.polarclient.gui.mod.HudMod;
import net.lax1dude.eaglercraft.EaglerAdapter;

public class TriangleMod extends HudMod {


    Minecraft mc = Minecraft.getMinecraft();

    public TriangleMod() {
        super("Triangles", 5, 120);
    }

    @Override 
    public void draw() {
        fr.drawStringWithShadow("§6[§eTriangles: " + (EaglerAdapter.getTrianglesPerSecond() / 1000) + "k§6]", getX(), getY(), -1);
        super.draw();
    }


    @Override
    public int getWidth() {
        return fr.getStringWidth("§6[§eTriangles: " + (EaglerAdapter.getTrianglesPerSecond() / 1000) + "k§6]");
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }

}
