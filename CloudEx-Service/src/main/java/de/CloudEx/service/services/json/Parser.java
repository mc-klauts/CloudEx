package de.CloudEx.service.services.json;

import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.ERROR;
import org.json.JSONObject;

import java.io.File;
import java.util.Scanner;

public class Parser {

    public String parseJsonFromFileIntoString(File file, String toGet) {
        try {
            StringBuilder builder = new StringBuilder();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {builder.append(scanner.nextLine());}
            String json = builder.toString();
            JSONObject obj = new JSONObject(json);
            String toReturn = (String) obj.get(toGet);
            return toReturn;
        } catch (Exception e) {
            new Logger(ERROR.class, "The file parser ran into an error: "+e);
        }
        return "Cannot parse json!";
    }
}
