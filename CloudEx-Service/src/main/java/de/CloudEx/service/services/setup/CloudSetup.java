package de.CloudEx.service.services.setup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.ERROR;
import de.CloudEx.service.services.logging.level.SETUP;
import de.CloudEx.service.services.setup.types.Type;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class CloudSetup {

    private String host;
    private String name;
    private int port;

    public CloudSetup(Type type, File file) {
        try {
            Scanner scanner = new Scanner(System.in);
            JSONObject obj = new JSONObject();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(file);
            if (type.equals(Type.MASTER)) {
                new Logger(SETUP.class, "Entering Master-Setup...");
                new Logger(SETUP.class, "Auf welcher Ip soll der Master-Server starten? (default: localhost)");
                host = scanner.nextLine();
                obj.put("Master-Host", host);

                new Logger(SETUP.class, "Auf welchem Port soll der Master-Server starten? (default: 2000)");
                port = scanner.nextInt();
                obj.put("Master-Port", port);

                writer.write(gson.toJson(obj));
                writer.close();

                new Logger(SETUP.class, "Finished Master-Setup! stoping...");
                System.exit(0);

            } else if(type.equals(Type.WRAPPER)) {
                new Logger(SETUP.class, "Entering Wrapper-Setup...");
                new Logger(SETUP.class, "Auf welche Ip soll sich der Wrapper mit dem Master verbinden? (default: localhost)");
                host = scanner.nextLine();
                obj.put("Wrapper-Host", host);

                new Logger(SETUP.class, "Auf welchem Port soll sich der Wrapper mit dem Master verbinden? (default: 2000)");
                obj.put("Wrapper-Port", port);

                writer.write(gson.toJson(obj));
                writer.close();

                new Logger(SETUP.class, "Finished Wrapper-Setup! stoping...");
                System.exit(0);

            } else if(type.equals(Type.WRAPPER_MASTER_SETUP)) {
                new Logger(SETUP.class, "Entering Wrapper-Setup...");
                new Logger(SETUP.class, "Von welcher Ip wird sich der Wrapper verbinden? (default: localhost)");
                host = scanner.nextLine();
                obj.put("Wrapper-Host", host);

                new Logger(SETUP.class, "Wie soll der neue Wrapper heißen?");
                name = scanner.nextLine();
                obj.put("Wrapper-Name", name);

                writer.write(gson.toJson(obj));
                writer.close();

                new Logger(SETUP.class, "Finished Wrapper-Setup! The Wrapper can now be used!");
            }
        } catch (Exception e) {
            new Logger(ERROR.class, "CloudSetup ran into an error! error: "+e);
        }
    }

    public String getHost() {
        return host;
    }
}
