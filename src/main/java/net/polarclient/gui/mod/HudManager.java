package net.polarclient.gui.mod;

import java.sql.Date;
import java.util.ArrayList;

import net.polarclient.gui.mod.mods.*;

public class HudManager {

    public ArrayList<HudMod> hudMods = new ArrayList<>();

    public ClientName clientName;
    public CoordinatesMod coordinatesMod;
    public FpsMod fpsMod;
    public Ping pingMod;
    public FullBrightMod fullBrightMod;
    public ClockMod clockMod;
    public DateMod dateMod;
    public ToggleSprintMod toggleSprintMod;
    public EntityMod entityMod;
    public TriangleMod triangleMod;
    public VertexesMod vertexesMod;
    public ServerIPMod serverIPMod;

    public HudManager() {
        hudMods.add(clientName = new ClientName());
        hudMods.add(coordinatesMod = new CoordinatesMod());
        hudMods.add(fpsMod = new FpsMod());
        hudMods.add(pingMod = new Ping());
        hudMods.add(fullBrightMod = new FullBrightMod());
        hudMods.add(clockMod = new ClockMod());
        hudMods.add(dateMod = new DateMod());
        hudMods.add(toggleSprintMod = new ToggleSprintMod());
        hudMods.add(entityMod = new EntityMod());
        hudMods.add(triangleMod = new TriangleMod());
        hudMods.add(vertexesMod = new VertexesMod());
        hudMods.add(serverIPMod = new ServerIPMod());

    }

    public void renderMods() {
        for (HudMod m : hudMods) {
            if (m.isEnabled()) {
                m.draw();
            }
        }
    }
}
