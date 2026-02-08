package net.liquidclient.gui.hud.notifications;

public class Notification {

    public final String title;
    public final String message;
    public final long startTime;
    public final long duration;

    public Notification(String title, String message, long duration) {
        this.title = title;
        this.message = message;
        this.duration = duration;
        this.startTime = System.currentTimeMillis();
    }

    public float getProgress() {
        return (System.currentTimeMillis() - startTime) / (float) duration;
    }

    public boolean isExpired() {
        return getProgress() >= 1.0f;
    }
}

