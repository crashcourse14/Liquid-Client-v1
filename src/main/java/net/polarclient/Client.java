package net.polarclient;

import net.polarclient.event.*;
import net.minecraft.client.Minecraft;
import net.polarclient.event.events.ClientTick;
import net.polarclient.gui.mod.*;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;

public class Client {
    public static final String CLIENT_VERSION = "PolarClient 1.5.2 R9";
    public static final Client INSTANCE = new Client();
    Minecraft mc = Minecraft.getMinecraft();

    public EventManager eventManager;

    public HudManager hudManager;

    public void startup() {

        eventManager = new EventManager();
        hudManager = new HudManager();


        EventManager.register(this);

        System.out.println("Starting " + CLIENT_VERSION);
    }

    public void shutdown() {
        EventManager.unregister(this);
        System.out.println("Shutting down " + CLIENT_VERSION);

    }

    @EventTarget
    public void onTick(ClientTick event) {

        if (Client.INSTANCE.hudManager.fullBrightMod.isEnabled() && mc.theWorld != null) {
            mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 5201, 0)); // Night Vision
            mc.gameSettings.gammaSetting = 420;
        } else if (!Client.INSTANCE.hudManager.fullBrightMod.isEnabled() && mc.theWorld != null) {
            mc.thePlayer.removePotionEffect(Potion.nightVision.id);
            mc.gameSettings.gammaSetting = 1;
        }
    }
}