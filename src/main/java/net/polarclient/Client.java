package net.polarclient;

public class Client {
    public static final String CLIENT_VERSION = "PolarClient 1.8.9 R9";
    public static final Client INSTANCE = new Client();

    public void startup() {
        System.out.println("Starting " + CLIENT_VERSION);
    }

    public void shutdown() {
        System.out.println("Shutting down " + CLIENT_VERSION);
    }
}