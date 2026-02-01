package net.polarclient.gui.mod.mods;

import java.util.Calendar;

import net.polarclient.gui.mod.HudMod;

public class ClockMod extends HudMod {

    public ClockMod() {
        super("Clock", 5, 75);
    }

    @Override
    public void draw() {
        super.draw();

        Calendar cal = Calendar.getInstance();

        int hour = cal.get(Calendar.HOUR); // 0–11
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        boolean isPM = cal.get(Calendar.AM_PM) == Calendar.PM;

        if (hour == 0) hour = 12; // midnight / noon fix

        String time = String.format(
            "§6[§e%02d::%02d::%02d %s§6]§f",
            hour, minute, second,
            isPM ? "PM" : "AM"
        );

        fr.drawStringWithShadow(time, getX(), getY(), 0xFFFFFF);
    }

    @Override
    public int getWidth() {
        return fr.getStringWidth("§6[§e00::00::00 AM§6]§f");
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }
}
