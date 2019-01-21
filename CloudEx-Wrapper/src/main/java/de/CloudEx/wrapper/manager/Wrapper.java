package de.CloudEx.wrapper.manager;

import de.CloudEx.service.network.packet.CloudPacketRegistry;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.INFO;

public class Wrapper implements Runnable {

    private static Thread wrapperThread;
    private static final Wrapper instance = new Wrapper();

    public void launch() {
        wrapperThread = new Thread(this, "CloudEx-Wrapper");
        wrapperThread.start();
    }

    public void run() {
        new Logger(INFO.class, "\n" +
                " _______         _____  _     _ ______  _______ _     _\n" +
                " |       |      |     | |     | |     \\ |______  \\___/ \n" +
                " |_____  |_____ |_____| |_____| |_____/ |______ _/   \\_\n");
        new Logger(INFO.class, "Developer: F4LS3");
        System.out.println("\n");
        CloudPacketRegistry packetRegistry = new CloudPacketRegistry();

        
    }

    public static final Wrapper getInstance() {
        return instance;
    }
}
