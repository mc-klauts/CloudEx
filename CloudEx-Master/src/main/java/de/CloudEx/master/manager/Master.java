package de.CloudEx.master.manager;

import de.CloudEx.master.network.NetworkHandler;
import de.CloudEx.master.network.commands.*;
import de.CloudEx.master.network.handler.PacketDecoder;
import de.CloudEx.master.network.handler.PacketEncoder;
import de.CloudEx.master.network.packets.AuthPacket;
import de.CloudEx.service.core.CloudNetworkMasterCommandSystem;
import de.CloudEx.service.network.CloudNetworkMasterServer;
import de.CloudEx.service.network.CloudSocketAddress;
import de.CloudEx.service.network.packet.CloudPacketRegistry;
import de.CloudEx.service.services.cloud.CloudCommand;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.INFO;
import de.CloudEx.service.services.network.CloudPacketDirection;

import java.util.ArrayList;
import java.util.List;

public class Master implements Runnable {

    private static Thread masterThread;
    private static final Master instance = new Master();
    public List<CloudCommand> commands = new ArrayList<CloudCommand>();
    private CloudPacketRegistry packetRegistry;
    private FileManager fileManager;
    private CloudNetworkMasterCommandSystem commandSystem = CloudNetworkMasterCommandSystem.getInstance();

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
        new Logger(INFO.class, "Developer: F4LS3, Designer: LucaFX");
        System.out.println("\n");
        new Logger(INFO.class, "Launching Master...");

        packetRegistry = new CloudPacketRegistry(new PacketDecoder(), new PacketEncoder());
        fileManager = new FileManager();

        this.packetRegistry.addPacket(CloudPacketDirection.BOTH, new AuthPacket());
        this.fileManager.runFileSystem();

        commandSystem.addCloudCommand(new HelpCommand());
        commandSystem.addCloudCommand(new TestCommand());
        commandSystem.addCloudCommand(new StopCommand());
        commandSystem.addCloudCommand(new CreateCommand());
        commandSystem.addCloudCommand(new DeleteCommand());

        new CloudNetworkMasterServer(new CloudSocketAddress("localhost", 2000), packetRegistry, new NetworkHandler()).tryBind();
        new Logger(INFO.class, "Launched Master!");
    }

    public void stop() {
        new Logger(INFO.class, "Shutting down Master...");
        System.exit(0);
        new Logger(INFO.class, "Shuted down Master!");
    }

    public static Master getInstance() {
        return instance;
    }

    public CloudPacketRegistry getPacketRegistry() {
        return packetRegistry;
    }
}
