package net.liquidclient.gui.hud.notifications;


public class Notification {

    public String title;
    public String message;
    public long endTime;

    public Notification(String title, String message, long duration) {
        this.title = title;
        this.message = message;
        this.endTime = System.currentTimeMillis() + duration;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > endTime;
    }
}
