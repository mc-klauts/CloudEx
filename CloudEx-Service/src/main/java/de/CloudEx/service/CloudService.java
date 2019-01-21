package de.CloudEx.service;

import de.CloudEx.service.services.network.CloudPacket;

import java.util.ArrayList;
import java.util.List;

public class CloudService {

    private static final CloudService instance = new CloudService();
    public List<Class<? extends CloudPacket>> IN_PACKETS = new ArrayList<Class<? extends CloudPacket>>();
    public List<Class<? extends CloudPacket>> OUT_PACKETS = new ArrayList<Class<? extends CloudPacket>>();

    public static CloudService getInstance() {
        return instance;
    }
}
