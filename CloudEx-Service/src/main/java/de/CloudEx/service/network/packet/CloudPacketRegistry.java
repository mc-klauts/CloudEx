package de.CloudEx.service.network.packet;

import de.CloudEx.service.CloudService;
import de.CloudEx.service.services.network.CloudPacket;
import de.CloudEx.service.services.network.CloudPacketDirection;

public class CloudPacketRegistry {

    public void addPacket(CloudPacketDirection cloudPacketDirection, CloudPacket cloudPacket) {
        try {
            if (cloudPacketDirection.equals(CloudPacketDirection.IN)) {
                CloudService.INSTANCE.IN_PACKETS.add(cloudPacket.getClass());

            } else if (cloudPacketDirection.equals(CloudPacketDirection.OUT)) {
                CloudService.INSTANCE.OUT_PACKETS.add(cloudPacket.getClass());

            } else if (cloudPacketDirection.equals(CloudPacketDirection.BOTH)) {
                CloudService.INSTANCE.IN_PACKETS.add(cloudPacket.getClass());
                CloudService.INSTANCE.OUT_PACKETS.add(cloudPacket.getClass());

            } else {
                System.out.println("CloudPacketRegistry: CloudPacketDirection error!");
            }
        } catch (Exception e) {
            System.out.println("CloudPacketRegistry: "+e);
        }
    }

    public void removePacket(CloudPacketDirection cloudPacketDirection, CloudPacket cloudPacket) {
        try {
            if (cloudPacketDirection.equals(CloudPacketDirection.IN)) {
                CloudService.INSTANCE.IN_PACKETS.remove(cloudPacket.getClass());

            } else if (cloudPacketDirection.equals(CloudPacketDirection.OUT)) {
                CloudService.INSTANCE.OUT_PACKETS.remove(cloudPacket.getClass());

            } else if (cloudPacketDirection.equals(CloudPacketDirection.BOTH)) {
                CloudService.INSTANCE.IN_PACKETS.remove(cloudPacket.getClass());
                CloudService.INSTANCE.OUT_PACKETS.remove(cloudPacket.getClass());

            } else {
                System.out.println("CloudPacketRegistry: CloudPacketDirection error!");
            }
        } catch (Exception e) {
            System.out.println("CloudPacketRegistry: "+e);
        }
    }
}
