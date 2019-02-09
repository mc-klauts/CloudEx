package de.CloudEx.master.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.ERROR;
import de.CloudEx.service.services.logging.level.INFO;
import de.CloudEx.service.services.logging.level.SETUP;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileManager {

    public void runFileSystem() {
        try {
            /** Creating the Main-Directory **/
            new File("./local/").mkdirs();

            /** Creating the Wrapper-Directory **/
            new File("./local/wrapper/").mkdirs();

            new Logger(INFO.class, "FileSystem is now Running!");
        } catch (Exception e) {
            new Logger(ERROR.class,"FileSystem throwed an error while starting! \nerror: "+e);
        }
    }

    public void proofMasterConfig() {
        try {
            /** Proffing the Master-Config **/
            File masterConfig = new File("./config.json");
            if(masterConfig.exists()) {
                new Logger(INFO.class, "Master-Config found!");
                return;
            } else {
                masterConfig.createNewFile();


                JSONObject obj = new JSONObject();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                FileWriter writer = new FileWriter(masterConfig);
                Scanner scanner = new Scanner(System.in);

                String host = "127.0.0.1";
                int port = 2000;

                new Logger(SETUP.class, "Entering Master-CloudSetup...");
                new Logger(SETUP.class, "Auf welche Ip-Addresse soll der Master-Server starten? (default: localhost)");

                writer.write(gson.toJson(obj));
                writer.close();
            }

        } catch (Exception e) {
            new Logger(ERROR.class, "FileSystem throwed an error while proffing MasterConfig! \nerror: "+e);
        }
    }
}
