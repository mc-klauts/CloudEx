package de.CloudEx.master.network.packets;

import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.ERROR;
import de.CloudEx.service.services.network.CloudPacket;
import de.CloudEx.service.services.network.CloudPacketSerializer;
import io.netty.buffer.ByteBuf;

import java.io.File;
import java.util.Scanner;

public class AuthPacket implements CloudPacket {

    private String name;

    public AuthPacket(String name){
        this.name = name;
    }

    public AuthPacket() {

    }

    @Override
    public void read(ByteBuf cloudPacketSerializer) {
        String token = String.valueOf(cloudPacketSerializer.readableBytes());
    }

    @Override
    public void write(ByteBuf cloudPacketSerializer) {
        try {
            File file = new File("./lcoal/"+name+"/TOKEN.wrapper");
            Scanner scanner = new Scanner(file);
            cloudPacketSerializer.writeBytes(scanner.nextLine().getBytes());

        } catch(Exception e) {
            new Logger(ERROR.class, "The AuthPacket throwed an error! \nerror: "+e);
        }
    }
}
