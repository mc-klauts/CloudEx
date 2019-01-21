package de.CloudEx.master.manager;

import de.CloudEx.master.network.NetworkHandler;
import de.CloudEx.master.network.commands.HelpCommand;
import de.CloudEx.master.network.commands.StopCommand;
import de.CloudEx.master.network.commands.TestCommand;
import de.CloudEx.service.core.CloudNetworkMasterCommandSystem;
import de.CloudEx.service.network.CloudNetworkMasterServer;
import de.CloudEx.service.network.CloudSocketAddress;
import de.CloudEx.service.network.packet.CloudPacketRegistry;
import de.CloudEx.service.services.cloud.CloudCommand;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.INFO;

import java.util.ArrayList;
import java.util.List;

public class Master implements Runnable {

    private static Thread masterThread;
    private static final Master instance = new Master();

    public void launch() {
        masterThread = new Thread(this, "CloudEx-Master");
        masterThread.start();
    }

    public void run() {
        new Logger(INFO.class, "\n" +
                " _______         _____  _     _ ______  _______ _     _\n" +
                " |       |      |     | |     | |     \\ |______  \\___/ \n" +
                " |_____  |_____ |_____| |_____| |_____/ |______ _/   \\_\n");
        new Logger(INFO.class, "Developer: F4LS3");
        System.out.println("\n");
        CloudPacketRegistry packetRegistry = new CloudPacketRegistry();

        CloudNetworkMasterCommandSystem.getInstance().addCloudCommand(new HelpCommand());
        CloudNetworkMasterCommandSystem.getInstance().addCloudCommand(new TestCommand());
        CloudNetworkMasterCommandSystem.getInstance().addCloudCommand(new StopCommand());

        new CloudNetworkMasterServer(new CloudSocketAddress("localhost", 2000), packetRegistry, new NetworkHandler()).tryBind();
    }

    public void stop() {
        new Logger(INFO.class, "Shutting down the System...");
        System.exit(0);
    }

    public static Master getInstance() {
        return instance;
    }
}
