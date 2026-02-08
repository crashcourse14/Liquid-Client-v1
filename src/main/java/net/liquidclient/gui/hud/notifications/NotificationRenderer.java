package net.liquidclient.gui.hud.notifications;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ScaledResolution;
import net.lax1dude.eaglercraft.EaglerAdapter;


public class NotificationRenderer {

    private static final int WIDTH = 140;
    private static final int HEIGHT = 30;

    public static void render(ScaledResolution sr) {
        Minecraft mc = Minecraft.getMinecraft();
        if(mc == null || mc.fontRenderer == null) return;

        // Remove expired notifications
        NotificationManager.update();

        if(NotificationManager.getNotifications().isEmpty()) return;

        // Draw on top of everything
        EaglerAdapter.glPushMatrix();
        EaglerAdapter.glDisable(EaglerAdapter.GL_DEPTH_TEST);
        EaglerAdapter.glEnable(EaglerAdapter.GL_BLEND);

        int yOffset = 0;

        for(Notification n : NotificationManager.getNotifications()) {
            int x = sr.getScaledWidth() - WIDTH - 5;
            int y = sr.getScaledHeight() - HEIGHT - 5 - yOffset;

            // Optional: move up if a GUI is open so notifications don't overlap buttons
            if(mc.currentScreen != null) y -= 20;

            drawRect(x, y, x + WIDTH, y + HEIGHT, 0x90000000); // semi-transparent black

            mc.fontRenderer.drawString(n.title, x + 5, y + 5, 0xFFFFFF);
            mc.fontRenderer.drawString(n.message, x + 5, y + 16, 0xCCCCCC);

            yOffset += HEIGHT + 5; // stack notifications
        }

        EaglerAdapter.glDisable(EaglerAdapter.GL_BLEND);
        EaglerAdapter.glEnable(EaglerAdapter.GL_DEPTH_TEST);
        EaglerAdapter.glPopMatrix();
    }

    // Basic rectangle renderer
    private static void drawRect(int x1, int y1, int x2, int y2, int color) {
        net.minecraft.src.Gui.drawRect(x1, y1, x2, y2, color);
    }
}
