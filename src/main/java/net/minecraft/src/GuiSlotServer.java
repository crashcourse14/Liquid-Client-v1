package net.minecraft.src;

import net.lax1dude.eaglercraft.EaglerAdapter;
import net.lax1dude.eaglercraft.IntegratedServer;
import net.lax1dude.eaglercraft.LANServerList.LanServer;
import net.lax1dude.eaglercraft.TextureLocation;
import net.lax1dude.eaglercraft.adapter.Tessellator;
import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.Map;

class GuiSlotServer extends GuiSlot {
    final GuiMultiplayer parentGui;

    private static final TextureLocation defaultServerIcon = new TextureLocation("/gui/unknown_pack.png");
    private static final TextureLocation icons = new TextureLocation("/gui/icons.png");

    /** Map server IPs to their pre-sized preview images */
    private static final Map<String, TextureLocation> serverPreviews = new HashMap<>();
    static {
        serverPreviews.put("wss://wanderwood.thatonebot.site", new TextureLocation("/gui/server_previews/wanderwood.png"));
        serverPreviews.put("wss://messcraft.ovh", new TextureLocation("/gui/server_previews/messcraft.png"));
        serverPreviews.put("wss://mc.arch.lol", new TextureLocation("/gui/server_previews/archmc.png"));
        // add more servers here
    }

    public GuiSlotServer(GuiMultiplayer par1GuiMultiplayer) {
        super(par1GuiMultiplayer.mc, par1GuiMultiplayer.width, par1GuiMultiplayer.height, 32, par1GuiMultiplayer.height - 64, 36);
        this.parentGui = par1GuiMultiplayer;
        this.elementWidth = 128;
    }

    protected int getSize() {
        return GuiMultiplayer.getInternetServerList(this.parentGui).countServers()
             + GuiMultiplayer.getListOfLanServers(this.parentGui).countServers()
             + 1;
    }

    protected void elementClicked(int index, boolean doubleClick) {
        if (index < GuiMultiplayer.getInternetServerList(this.parentGui).countServers()
            + GuiMultiplayer.getListOfLanServers(this.parentGui).countServers()) {

            int prevSelected = GuiMultiplayer.getSelectedServer(this.parentGui);
            GuiMultiplayer.getAndSetSelectedServer(this.parentGui, index);

            ServerData server = GuiMultiplayer.getInternetServerList(this.parentGui).countServers() > index
                    ? GuiMultiplayer.getInternetServerList(this.parentGui).getServerData(index)
                    : null;

            boolean enableSelect = GuiMultiplayer.getSelectedServer(this.parentGui) >= 0
                    && GuiMultiplayer.getSelectedServer(this.parentGui) < this.getSize()
                    && (server == null || server.field_82821_f == 61);

            boolean isInternetServer = GuiMultiplayer.getSelectedServer(this.parentGui) < GuiMultiplayer.getInternetServerList(this.parentGui).countServers();

            GuiMultiplayer.getButtonSelect(this.parentGui).enabled = enableSelect;
            GuiMultiplayer.getButtonEdit(this.parentGui).enabled = isInternetServer;
            GuiMultiplayer.getButtonDelete(this.parentGui).enabled = isInternetServer;

            if (doubleClick && enableSelect) {
                GuiMultiplayer.func_74008_b(this.parentGui, index);
            } else if (isInternetServer && GuiScreen.isShiftKeyDown() && prevSelected > ServerList.forcedServers.size()
                    && prevSelected < GuiMultiplayer.getInternetServerList(this.parentGui).countServers()) {
                GuiMultiplayer.getInternetServerList(this.parentGui).swapServers(prevSelected, GuiMultiplayer.getSelectedServer(this.parentGui));
            }
        }
    }

    protected boolean isSelected(int index) {
        return index == GuiMultiplayer.getSelectedServer(this.parentGui);
    }

    protected int getContentHeight() {
        return this.getSize() * 36;
    }

    protected void drawBackground() {
        this.parentGui.drawDefaultBackground();
    }

    protected void drawSlot(int index, int x, int y, int height, Tessellator t) {
        if (index < GuiMultiplayer.getInternetServerList(this.parentGui).countServers()) {
            drawInternetServer(index, x, y);
        } else if (index < GuiMultiplayer.getInternetServerList(this.parentGui).countServers()
                + GuiMultiplayer.getListOfLanServers(this.parentGui).countServers()) {
            drawLanServer(index, x, y);
        } else {
            drawNoServers(index, x, y);
        }
    }

    private void drawInternetServer(int index, int x, int y) {
        ServerData server = GuiMultiplayer.getInternetServerList(this.parentGui).getServerData(index);
		Tessellator t = Tessellator.instance;

        // Get the server preview, default if not found
        TextureLocation tex = serverPreviews.get(server.serverIP);
        if (tex != null) {
            tex.bindTexture();
            t.startDrawingQuads();

            int previewX = x;
            int previewY = y;
            int previewWidth = 256;
            int previewHeight = 34;

            // Crop 240x50 image to center 36px vertically
            float texVStart = 4f / 50f; // crop top 7 pixels
            float texVEnd = 43f / 50f;  // crop bottom 7 pixels

            t.addVertexWithUV(previewX, previewY + previewHeight, 0, 0, texVEnd);
            t.addVertexWithUV(previewX + previewWidth, previewY + previewHeight, 0, 1, texVEnd);
            t.addVertexWithUV(previewX + previewWidth, previewY, 0, 1, texVStart);
            t.addVertexWithUV(previewX, previewY, 0, 0, texVStart);
            t.draw();
        }



        // Draw server icon on top
        server.refreshIcon();
        if (server.serverIconEnabled && server.serverIconGL != -1) {
            this.mc.renderEngine.bindTexture(server.serverIconGL);
        } else {
            defaultServerIcon.bindTexture();
        }
        int iconSize = 28;
        t.startDrawingQuads();
        t.addVertexWithUV(x + 2, y + 2 + iconSize, 0, 0, 1);
        t.addVertexWithUV(x + 2 + iconSize, y + 2 + iconSize, 0, 1, 1);
        t.addVertexWithUV(x + 2 + iconSize, y + 2, 0, 1, 0);
        t.addVertexWithUV(x + 2, y + 2, 0, 0, 0);
        t.draw();

        // Draw server text
        this.parentGui.drawString(this.parentGui.fontRenderer, server.serverName, x + 38, y + 1, 16777215);
        this.parentGui.drawString(this.parentGui.fontRenderer, server.serverMOTD, x + 38, y + 12, 8421504);

        if (!this.parentGui.mc.gameSettings.hideServerAddress && !server.isHidingAddress()) {
            this.parentGui.drawString(this.parentGui.fontRenderer, server.serverIP, x + 38, y + 23, 3158064);
        } else {
            this.parentGui.drawString(this.parentGui.fontRenderer, StatCollector.translateToLocal("selectServer.hiddenAddress"), x + 38, y + 23, 3158064);
        }

        this.parentGui.drawString(this.parentGui.fontRenderer, server.populationInfo,
                x + 251 - this.parentGui.fontRenderer.getStringWidth(server.populationInfo), y + 2, 8421504);
    }

    private void drawLanServer(int index, int x, int y) {
        LanServer lan = GuiMultiplayer.getListOfLanServers(this.parentGui)
                .getServer(index - GuiMultiplayer.getInternetServerList(this.parentGui).countServers());

        this.parentGui.drawString(this.parentGui.fontRenderer, StatCollector.translateToLocal("lanServer.title"),
                x + 2, y + 1, 16777215);
        this.parentGui.drawString(this.parentGui.fontRenderer, lan.getLanServerMotd(),
                x + 2, y + 12, 8421504);

        if (this.parentGui.mc.gameSettings.hideServerAddress) {
            this.parentGui.drawString(this.parentGui.fontRenderer, StatCollector.translateToLocal("selectServer.hiddenAddress"),
                    x + 2, y + 23, 3158064);
        } else {
            this.parentGui.drawString(this.parentGui.fontRenderer, lan.getLanServerCode(),
                    x + 2, y + 23, 0x558822);
        }
    }

    private void drawNoServers(int index, int x, int y) {
        if (IntegratedServer.relayManager.count() == 0) {
            this.parentGui.drawCenteredString(this.parentGui.fontRenderer,
                    StatCollector.translateToLocal("noRelay.noRelay1"), this.parentGui.width / 2, y + 6, 16777215);
            this.parentGui.drawCenteredString(this.parentGui.fontRenderer,
                    StatCollector.translateToLocal("noRelay.noRelay2"), this.parentGui.width / 2, y + 18, 0xFFAAAAAA);
        } else {
            this.parentGui.drawCenteredString(this.parentGui.fontRenderer,
                    StatCollector.translateToLocal("lanServer.scanning"), this.parentGui.width / 2, y + 6, 16777215);
            String var6;

            switch (GuiMultiplayer.getTicksOpened(this.parentGui) / 3 % 4) {
            case 0:
            default:
                var6 = "O o o";
                break;

            case 1:
            case 3:
                var6 = "o O o";
                break;

            case 2:
                var6 = "o o O";
            }

            this.parentGui.drawCenteredString(this.parentGui.fontRenderer, var6, this.parentGui.width / 2, y + 18, 8421504);
        }
    }
}
