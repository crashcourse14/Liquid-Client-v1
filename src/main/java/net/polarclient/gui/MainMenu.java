package net.polarclient.gui;

import net.minecraft.src.GuiScreen;
import net.lax1dude.eaglercraft.GuiScreenEditProfile;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiMultiplayer;
import net.minecraft.src.GuiSelectWorld;
import net.lax1dude.eaglercraft.TextureLocation;
import net.minecraft.src.GuiOptions;
import net.lax1dude.eaglercraft.EaglerAdapter;
import net.lax1dude.eaglercraft.adapter.Tessellator;

public class MainMenu extends GuiScreen {

    private static final TextureLocation background = new TextureLocation("/skins/background-polarclient.png");

    
    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(0, 10, 20, "Singleplayer"));
        this.buttonList.add(new GuiButton(1, 40, 20, "Multiplayer"));
        this.buttonList.add(new GuiButton(2, 70, 20, "Options"));
        this.buttonList.add(new GuiButton(3, 100, 20, "Profile"));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        EaglerAdapter.glDisable(EaglerAdapter.GL_LIGHTING);
        EaglerAdapter.glDisable(EaglerAdapter.GL_FOG);
        EaglerAdapter.glEnable(EaglerAdapter.GL_TEXTURE_2D);

        background.bindTexture();

        Tessellator t = Tessellator.instance;
        EaglerAdapter.glColor4f(1f, 1f, 1f, 1f);

        t.startDrawingQuads();
        t.setColorOpaque_I(0xFFFFFF); // white = no tint
        t.addVertexWithUV(0, this.height, 0, 0, 1);
        t.addVertexWithUV(this.width, this.height, 0, 1, 1);
        t.addVertexWithUV(this.width, 0, 0, 1, 0);
        t.addVertexWithUV(0, 0, 0, 0, 0);
        t.draw();


        super.drawScreen(mouseX, mouseY, partialTicks);
    }


    @Override
    protected void actionPerformed(GuiButton button){

        if (button.id == 0){
            mc.displayGuiScreen(new GuiSelectWorld(this));
        }

        if (button.id == 1){
            mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        if (button.id == 2){
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        if (button.id == 3){
            mc.displayGuiScreen(new GuiScreenEditProfile(this));
        }

        super.actionPerformed(button);
    }
}