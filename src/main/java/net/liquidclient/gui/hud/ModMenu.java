package net.liquidclient.gui.hud;

import net.minecraft.src.*;
import net.liquidclient.Client;
import net.liquidclient.gui.mod.mods.*;
import net.liquidclient.gui.mod.HudManager;
import net.minecraft.client.Minecraft;
import net.liquidclient.gui.hud.buttons.*;
import net.lax1dude.eaglercraft.TextureLocation;
import net.lax1dude.eaglercraft.adapter.Tessellator;
import net.lax1dude.eaglercraft.EaglerAdapter;


public class ModMenu extends GuiScreen {

    private int PANEL_X, PANEL_Y, PANEL_WIDTH, PANEL_HEIGHT;
	private static final TextureLocation LIQUID_LOGO_LONG = new TextureLocation("/gui/LiquidClient/logo.png");


    public boolean doesGuiPauseGame() {
		return false;
	}
    
    @Override
    public void initGui() {
        buttonList.clear();

        PANEL_WIDTH = 380;
        PANEL_HEIGHT = 220;
        PANEL_X = (width - PANEL_WIDTH) / 2;
        PANEL_Y = (height - PANEL_HEIGHT) / 2;

        int x = PANEL_X + 20;
        int y = PANEL_Y + 40;
        int BUTTON_WIDTH = 120;
        int BUTTON_HEIGHT = 20;
        int gap = 10;

        /* 
        
         LEFT COLUMN

        */

        buttonList.add(new GuiButton(1, x, y, BUTTON_WIDTH, BUTTON_HEIGHT, "Fps: " + Client.INSTANCE.hudManager.fpsMod.isEnabled()));
        buttonList.add(new GuiButton(2, x, y + (BUTTON_HEIGHT + gap), BUTTON_WIDTH, BUTTON_HEIGHT, "Ping: " + Client.INSTANCE.hudManager.pingMod.isEnabled()));
        buttonList.add(new GuiButton(3, x, y + (BUTTON_HEIGHT + gap) * 2, BUTTON_WIDTH, BUTTON_HEIGHT, "Coordinates: " + Client.INSTANCE.hudManager.coordinatesMod.isEnabled()));
        buttonList.add(new GuiButton(4, x, y + (BUTTON_HEIGHT + gap) * 3, BUTTON_WIDTH, BUTTON_HEIGHT, "Clock: " + Client.INSTANCE.hudManager.clockMod.isEnabled()));
        buttonList.add(new GuiButton(5, x, y + (BUTTON_HEIGHT + gap) * 4, BUTTON_WIDTH, BUTTON_HEIGHT, "Client Name: " + Client.INSTANCE.hudManager.clientName.isEnabled()));
        buttonList.add(new GuiButton(6, x, y + (BUTTON_HEIGHT + gap) * 5, BUTTON_WIDTH, BUTTON_HEIGHT, "Entities : " + Client.INSTANCE.hudManager.entityMod.isEnabled()));

        /*
        
         RIGHT COLUMN

        */
        buttonList.add(new GuiButton(7, x + BUTTON_WIDTH + gap, y, BUTTON_WIDTH, BUTTON_HEIGHT, "Vertexes: " + Client.INSTANCE.hudManager.vertexesMod.isEnabled()));
        buttonList.add(new GuiButton(8, x + BUTTON_WIDTH + gap, y + (BUTTON_HEIGHT + gap), BUTTON_WIDTH, BUTTON_HEIGHT, "Triangles: " + Client.INSTANCE.hudManager.triangleMod.isEnabled()));
        buttonList.add(new GuiButton(9, x + BUTTON_WIDTH + gap, y + (BUTTON_HEIGHT + gap) * 2, BUTTON_WIDTH, BUTTON_HEIGHT, "Server IP: " + Client.INSTANCE.hudManager.serverIPMod.isEnabled()));
        buttonList.add(new GuiButton(10, x + BUTTON_WIDTH + gap, y + (BUTTON_HEIGHT + gap) * 3, BUTTON_WIDTH, BUTTON_HEIGHT, "Date: " + Client.INSTANCE.hudManager.dateMod.isEnabled()));
        //buttonList.add(new GuiButton(11, x + BUTTON_WIDTH + gap, y + (BUTTON_HEIGHT + gap) * 4, BUTTON_WIDTH, BUTTON_HEIGHT, "Entity Health:" + Client.INSTANCE.hudManager.entityHealthMod.isEnabled()));

        buttonList.add(new GuiCloseButton(100, PANEL_X + PANEL_WIDTH - 24, PANEL_Y + 10));


        int IMAGE_WIDTH = 130;  
        int IMAGE_HEIGHT = 50;   
        int IMAGE_X_COORD = 0;
        int IMAGE_Y_COORD = 0;

        EaglerAdapter.glPushMatrix();
		EaglerAdapter.glTranslatef(IMAGE_X_COORD, IMAGE_Y_COORD, 0.0f);
		EaglerAdapter.glScalef(1.0f, 1.0f, 1.0f);

		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(0, IMAGE_HEIGHT, 0, 0.0, 1.0); 
		tess.addVertexWithUV(IMAGE_WIDTH, IMAGE_HEIGHT, 0, 1.0, 1.0); 
		tess.addVertexWithUV(IMAGE_WIDTH, 0, 0, 1.0, 0.0); 
		tess.addVertexWithUV(0, 0, 0, 0.0, 0.0);  
		tess.draw();

		EaglerAdapter.glPopMatrix();



    }



    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        // Panel background
        drawRect(PANEL_X, PANEL_Y, PANEL_X + PANEL_WIDTH, PANEL_Y + PANEL_HEIGHT, 0xCC111111);

        // Header bar
        drawRect(PANEL_X, PANEL_Y, PANEL_X + PANEL_WIDTH, PANEL_Y + 30, 0xFF1A1A1A);



        drawCenteredString(fontRenderer, "MOD MENU", width / 2, PANEL_Y + 10, 0xFFFFFF);

        LIQUID_LOGO_LONG.bindTexture();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                Client.INSTANCE.hudManager.fpsMod.toggle();
                button.displayString = "Fps: " + Client.INSTANCE.hudManager.fpsMod.isEnabled();
                break;

            case 2:
                Client.INSTANCE.hudManager.pingMod.toggle();
                button.displayString = "Ping: " + Client.INSTANCE.hudManager.pingMod.isEnabled();
                break;

            case 3:
                Client.INSTANCE.hudManager.coordinatesMod.toggle();
                button.displayString = "Coordinates: " + Client.INSTANCE.hudManager.coordinatesMod.isEnabled();
                break;

            case 4:
                Client.INSTANCE.hudManager.clockMod.toggle();
                button.displayString = "Clock: " + Client.INSTANCE.hudManager.clockMod.isEnabled();
                break;

            case 5:
                Client.INSTANCE.hudManager.clientName.toggle();
                button.displayString = "Client Name: " + Client.INSTANCE.hudManager.clientName.isEnabled();
                break;

            case 6:
                Client.INSTANCE.hudManager.entityMod.toggle();
                button.displayString = "Entities: " + Client.INSTANCE.hudManager.entityMod.isEnabled();
                break;
            
            case 7:
                Client.INSTANCE.hudManager.vertexesMod.toggle();
                button.displayString = "Vertexes: " + Client.INSTANCE.hudManager.vertexesMod.isEnabled();
                break;
            case 8:
                Client.INSTANCE.hudManager.triangleMod.toggle();
                button.displayString = "Triangles: " + Client.INSTANCE.hudManager.triangleMod.isEnabled();
                break;
            case 9:
                Client.INSTANCE.hudManager.serverIPMod.toggle();
                button.displayString = "Server IP: " + Client.INSTANCE.hudManager.serverIPMod.isEnabled();
                break;
            case 10:
                Client.INSTANCE.hudManager.dateMod.toggle();
                button.displayString = "Date: " + Client.INSTANCE.hudManager.dateMod.isEnabled();
                break;
            /*case 11:
                Client.INSTANCE.hudManager.entityHealthMod.toggle();
                button.displayString = "Entity Health Mod: " + Client.INSTANCE.hudManager.entityHealthMod.isEnabled();
                break;*/

            case 100:
                this.mc.displayGuiScreen((GuiScreen) null);
                break;
        }
    }

}
