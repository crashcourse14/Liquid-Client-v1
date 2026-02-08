package net.liquidclient.gui.hud.notifications;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NotificationManager {

    private static final List<Notification> notifications = new ArrayList<Notification>();

    public static void push(String title, String message, long duration) {
        notifications.add(new Notification(title, message, duration));
    }

    public static void update() {
        Iterator<Notification> it = notifications.iterator();
        while (it.hasNext()) {
            if (it.next().isExpired()) {
                it.remove();
            }
        }
    }

    public static List<Notification> getNotifications() {
        return notifications;
    }
}

