package net.liquidclient.gui.hud.notifications;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Gui;
import net.minecraft.src.ScaledResolution;

public class NotificationRenderer {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void render(ScaledResolution sr) {
        NotificationManager.update();

        int y = 10;
        for (Notification n : NotificationManager.getNotifications()) {
            drawNotification(n, sr, y);
            y += 28; // vertical stacking gap
        }
    }

    private static void drawNotification(Notification n, ScaledResolution sr, int y) {
        int width = 180;
        int height = 24;

        int x = sr.getScaledWidth() - width - 10;

        // Background
        Gui.drawRect(
                x,
                y,
                x + width,
                y + height,
                0xAA111111
        );

        // Title
        mc.fontRenderer.drawString(
                n.title,
                x + 6,
                y + 6,
                0xFFFFFF
        );
    }
}

