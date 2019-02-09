package de.CloudEx.master.manager;

import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.ERROR;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.UUID;

public class KeyManager {

    private static final KeyManager instance = new KeyManager();

    public void generateNewKey(String wrapperName) {
        try {
            File file = new File("./local/wrapper/"+wrapperName+"/TOKEN.wrapper");
            if(!file.exists()) {
                file.createNewFile();
            } else {
                new Logger(ERROR.class, "Wrapper already exists! Aborting...");
                return;
            }
            FileWriter writer = new FileWriter(file);
            String token = UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString() +UUID.randomUUID().toString();

            writer.write(token);
            writer.flush();
        } catch (Exception e) {
            new Logger(ERROR.class, "Cannot generate Wrapper-Key! \nerror: "+e);
        }
    }

    public String getWrapperKey(String wrapperName) {
        try {
            File file = new File("./local/wrapper/"+wrapperName+"/TOKEN.wrapper");
            if(file.exists()) {
                Scanner s = new Scanner(file);
                return s.nextLine();
            } else {
                return "Cannot find Wrapper-Key!";
            }

        } catch (Exception e) {
            new Logger(ERROR.class, "Cannot get Wrapper-Key! \nerror: "+e);
        }
        return "Cannot find Wrapper-Key!";
    }

    public static KeyManager getInstance() {
        return instance;
    }
}
