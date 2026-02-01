package net.polarclient.gui.mod.mods;

import net.polarclient.Client;
import net.polarclient.gui.mod.HudMod;
import net.minecraft.src.FontRenderer;


public class ClientName extends HudMod {

    public FontRenderer fr = mc.fontRenderer;

    public ClientName() {
        super("§6[§ePolar Client (1.5.2)§6]", 5, 10);
    }

    @Override 
    public void draw() {
        fr.drawStringWithShadow(name, getX(), getY(), -1);
        super.draw();
    }

    @Override
    
    public int getWidth() {
        return fr.getStringWidth(name);
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }

}
