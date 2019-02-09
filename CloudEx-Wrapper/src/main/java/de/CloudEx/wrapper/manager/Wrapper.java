package de.CloudEx.wrapper.manager;

import de.CloudEx.service.network.CloudNetworkWrapperServer;
import de.CloudEx.service.network.CloudSocketAddress;
import de.CloudEx.service.network.packet.CloudPacketRegistry;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.INFO;
import de.CloudEx.service.services.network.CloudPacketDirection;
import de.CloudEx.wrapper.network.NetworkHandler;
import de.CloudEx.wrapper.network.handler.PacketDecoder;
import de.CloudEx.wrapper.network.handler.PacketEncoder;
import de.CloudEx.wrapper.network.packets.AuthPacket;

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
        new Logger(INFO.class, "Developer: F4LS3, Designer: LucaFX");
        System.out.println("\n");
        new Logger(INFO.class, "Launching Wrapper...");
        CloudPacketRegistry packetRegistry = new CloudPacketRegistry(new PacketDecoder(), new PacketEncoder());

        packetRegistry.addPacket(CloudPacketDirection.BOTH, new AuthPacket());

        new Logger(INFO.class, "Launched Wrapper!");

        new CloudNetworkWrapperServer(new CloudSocketAddress("localhost", 2000), packetRegistry, new NetworkHandler()).tryBind();
    }

    public static final Wrapper getInstance() {
        return instance;
    }
}
