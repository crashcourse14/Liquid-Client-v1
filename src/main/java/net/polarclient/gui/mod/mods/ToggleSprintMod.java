package net.polarclient.gui.mod.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.src.KeyBinding;
import net.polarclient.gui.mod.HudMod;

public class ToggleSprintMod extends HudMod {

    private static final Minecraft mc = Minecraft.getMinecraft();

    // Custom keybind (default: R)
    public static final KeyBinding toggleSprintKey =
            new KeyBinding("Toggle Sprint", 19); // 19 = R in 1.5.2

    private boolean toggled = false;

    public ToggleSprintMod() {
        super("ToggleSprint", 5, 35);
    }

    @Override
    public void draw() {
        super.draw();

        // Toggle when keybind is pressed
        if (toggleSprintKey.isPressed()) {
            toggled = !toggled;
        }

        // Legit sprint behavior
        if (toggled && mc.thePlayer != null) {
            if (!mc.thePlayer.isSneaking()) {
                mc.thePlayer.setSprinting(true);
            }
        }

        // HUD text
        String text = toggled ? "§6[§eSprinting (Toggled)§6]" : "§6[§eNot Sprinting§6]";
        fr.drawStringWithShadow(
                text,
                getX(),
                getY(),
                toggled ? 0x00FF00 : 0xFF5555
        );
    }

    @Override
    public int getWidth() {
        return fr.getStringWidth("§6[§eSprinting (Toggled)§6]");
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }
}
