package de.CloudEx.master.manager;

import de.CloudEx.master.network.NetworkHandler;
import de.CloudEx.master.network.commands.HelpCommand;
import de.CloudEx.master.network.commands.StopCommand;
import de.CloudEx.master.network.commands.TestCommand;
import de.CloudEx.service.core.CloudNetworkMasterCommandSystem;
import de.CloudEx.service.network.CloudNetworkMasterServer;
import de.CloudEx.service.network.CloudSocketAddress;
import de.CloudEx.service.network.handler.CloudPacketHandler;
import de.CloudEx.service.network.packet.CloudPacketRegistry;
import de.CloudEx.service.services.cloud.CloudCommand;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.ERROR;
import de.CloudEx.service.services.logging.level.INFO;
import de.CloudEx.service.services.network.CloudPacket;
import de.CloudEx.service.services.network.CloudPacketDirection;
import de.CloudEx.service.services.network.CloudPacketSerializer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Master implements Runnable {

    private static Thread masterThread;
    public static final Master INSTANCE = new Master();
    public List<CloudCommand> commands = new ArrayList<>();

    public void launch() {
        masterThread = new Thread(this, "CloudEx-Master");
        masterThread.start();
    }

    public void run() {
        new Logger(INFO.class, "\n" +
                " _______         _____  _     _ ______  _______ _     _\n" +
                " |       |      |     | |     | |     \\ |______  \\___/ \n" +
                " |_____  |_____ |_____| |_____| |_____/ |______ _/   \\_\n" +
                "                                                       \n" +
                "\n");
        new Logger(INFO.class, "Launching Master...");
        CloudPacketRegistry packetRegistry = new CloudPacketRegistry();

        CloudNetworkMasterCommandSystem.INSTANCE.addCloudCommand(new HelpCommand());
        CloudNetworkMasterCommandSystem.INSTANCE.addCloudCommand(new TestCommand());
        CloudNetworkMasterCommandSystem.INSTANCE.addCloudCommand(new StopCommand());

        //new CloudNetworkMasterServer(new CloudSocketAddress("localhost", 2000), packetRegistry, new NetworkHandler()).tryBind();
        new Logger(INFO.class, "Launched Master!");
        CloudNetworkMasterCommandSystem.INSTANCE.launch();
    }

    public void stop() {

    }
}
