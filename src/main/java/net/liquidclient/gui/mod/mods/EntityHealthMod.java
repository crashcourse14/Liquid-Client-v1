package net.liquidclient.gui.mod.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.liquidclient.gui.mod.HudMod;
import net.lax1dude.eaglercraft.EaglerAdapter;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Gui;


public class EntityHealthMod extends HudMod {

    private final Minecraft mc = Minecraft.getMinecraft();

    public EntityHealthMod() {
        super("Entity Health", 5, 60);
    }

    @Override
    public void draw() {
        for (Object obj : mc.theWorld.loadedEntityList) {
            if (obj instanceof EntityLiving) {
                EntityLiving entity = (EntityLiving) obj;

                // skip self
                if (entity == mc.thePlayer) continue;

                // World coordinates relative to player
                double x = entity.posX - RenderManager.instance.viewerPosX;
                double y = entity.posY + entity.height + 0.5 - RenderManager.instance.viewerPosY; // above head
                double z = entity.posZ - RenderManager.instance.viewerPosZ;

                // Get rotation angles
                float yaw = RenderManager.instance.playerViewY;
                float pitch = RenderManager.instance.playerViewX;

                // Rotate coordinates based on yaw
                double cosYaw = Math.cos(Math.toRadians(yaw));
                double sinYaw = Math.sin(Math.toRadians(yaw));

                double rotX = x * cosYaw + z * sinYaw;
                double rotZ = z * cosYaw - x * sinYaw;
                double rotY = y; // keep Y as is

                // Simple perspective projection
                if (rotZ > 0) { // behind camera check
                    double scale = 1 / rotZ; // smaller when farther away
                    int screenX = (int) (this.getX() + rotX * scale * 100);
                    int screenY = (int) (this.getY() - rotY * scale * 100);

                    String healthStr = String.valueOf((int) entity.getHealth());
                    fr.drawStringWithShadow("§c" + healthStr, screenX - fr.getStringWidth(healthStr) / 2, screenY, 0xFFFFFF);
                }
            }
        }

        super.draw();
    }

    @Override
    public int getWidth() {
        return 40; // placeholder
    }

    @Override
    public int getHeight() {
        return 20; // placeholder
    }
}