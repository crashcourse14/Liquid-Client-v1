package net.polarclient.gui.mod.mods;

import net.polarclient.gui.mod.HudMod;

public class CoordinatesMod extends HudMod {



    public CoordinatesMod() {
        super("Coordinates", 5, 25);
    }

    @Override 
    public void draw() {
        fr.drawStringWithShadow("§6[§eCoordinates§6] §fX: " + Math.round(mc.thePlayer.posX * 1000.0) / 1000.0 + " Y: " + Math.round(mc.thePlayer.posY * 1000.0) / 1000.0 + " Z: " + Math.round(mc.thePlayer.posZ * 1000.0) / 1000.0, getX(), getY(), -1);
        super.draw();
    }

    @Override
    public int getWidth() {
        return fr.getStringWidth("§6[§eCoordinates§6] §fX: " + Math.round(mc.thePlayer.posX * 1000.0) / 1000.0 + " Y: " + Math.round(mc.thePlayer.posY * 1000.0) / 1000.0 + " Z: " + Math.round(mc.thePlayer.posZ * 1000.0) / 1000.0);
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }

}
