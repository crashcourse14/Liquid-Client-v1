package net.polarclient.gui.mod.mods;

import net.polarclient.gui.mod.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.GuiPlayerInfo;

public class Ping extends HudMod {

    public Ping() {
        super("Ping", 5, 45);
    }

    @Override 
    public void draw() {
        int ping = -1;
        Minecraft mc = Minecraft.getMinecraft();
        NetClientHandler nh = mc.getNetHandler();
        if(nh != null && mc.thePlayer != null) {
            for(Object o : nh.playerInfoList) {
                if(o instanceof GuiPlayerInfo) {
                    GuiPlayerInfo info = (GuiPlayerInfo)o;
                    if(info.name.equals(mc.thePlayer.username)) {
                        ping = info.responseTime;
                        break;
                    }
                }
            }
        }
        
        fr.drawStringWithShadow("§6[§e" + (ping >= 0 ? ping + "ms" : "N/A") + "§6]", getX(), getY(), -1);
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
