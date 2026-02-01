package net.polarclient;

import net.polarclient.event.EventManager;
import net.polarclient.event.EventTarget;
import net.polarclient.event.Event;
import net.minecraft.client.Minecraft;
import net.polarclient.event.events.ClientTick;
import net.polarclient.gui.mod.HudManager;
import net.polarclient.gui.mod.HudMod;

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

    }
}