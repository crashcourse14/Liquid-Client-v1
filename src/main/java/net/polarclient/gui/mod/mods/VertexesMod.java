package net.polarclient.gui.mod.mods;

import net.minecraft.client.Minecraft;
import net.polarclient.gui.mod.HudMod;
import net.lax1dude.eaglercraft.EaglerAdapter;

public class VertexesMod extends HudMod {


    Minecraft mc = Minecraft.getMinecraft();

    public VertexesMod() {
        super("Vertexes", 5, 135);
    }

    @Override 
    public void draw() {
        fr.drawStringWithShadow("§6[§eVertexes: " + (EaglerAdapter.getVertexesPerSecond() / 1000) + "k§6]", getX(), getY(), -1);
        super.draw();
    }


    @Override
    public int getWidth() {
        return fr.getStringWidth("§6[§eVertexes: " + (EaglerAdapter.getVertexesPerSecond() / 1000) + "k§6]");
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }

}
