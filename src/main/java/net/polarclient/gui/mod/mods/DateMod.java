package net.polarclient.gui.mod.mods;

import java.util.Calendar;

import net.polarclient.gui.mod.HudMod;

public class DateMod extends HudMod {

    public DateMod() {
        super("Date", 5, 90);
    }

    @Override
    public void draw() {
        super.draw();

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is 0-based
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String date = String.format("§6[§e%04d-%02d-%02d§6]§f", year, month, day);

        fr.drawStringWithShadow(date, getX(), getY(), -1);
    }

    @Override
    public int getWidth() {
        return fr.getStringWidth("§6[§e0000-00-00§6]§f");
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }
}
