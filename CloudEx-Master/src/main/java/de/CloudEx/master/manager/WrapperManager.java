package de.CloudEx.master.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.CloudEx.master.types.Wrapper;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.ERROR;
import de.CloudEx.service.services.logging.level.INFO;
import de.CloudEx.service.services.logging.level.WARN;
import de.CloudEx.service.services.setup.CloudSetup;
import de.CloudEx.service.services.setup.types.Type;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WrapperManager {

    private static final WrapperManager instance = new WrapperManager();

    public List<Wrapper> WrapperQueue = new ArrayList<>();

    public void createNewWrapper(String wrapperName) {
        try {
            File wrapperDir = new File("./local/wrapper/"+wrapperName+"/");
            File wrapperFile = new File("./local/wrapper/"+wrapperName+"/config.json");
            if(!wrapperDir.exists()) {
                wrapperDir.mkdirs();
            }
            if(!wrapperFile.exists()) {
                wrapperFile.createNewFile();
            }

            CloudSetup cloudSetup = new CloudSetup(Type.WRAPPER_MASTER_SETUP, wrapperFile);
            Wrapper wrapper = new Wrapper(wrapperName, cloudSetup.getHost());

        } catch (Exception e) {
            new Logger(ERROR.class, "Failed to create Wrapper! \nName: "+wrapperName);
        }
    }

    public void deleteWrapper(String wrapperName) {
        File wrapperDir = new File("./local/wrapper/"+wrapperName+"/");
        File wrapperFile = new File("./local/wrapper/"+wrapperName+"/config.json");
        File wrapperToken = new File("./local/wrapper/"+wrapperName+"/TOKEN.wrapper");

        if(wrapperDir.exists() && wrapperFile.exists() && wrapperToken.exists()) {
            wrapperToken.delete();
            new Logger(WARN.class, "The Wrapper "+wrapperName+" got deleted!");
        } else if(!wrapperDir.exists() && !wrapperFile.exists()) {
            new Logger(ERROR.class, "The Wrapper "+wrapperName+" dosn't exist!");
        }
    }

    public void addWrapperInQueue(Wrapper wrapper) {
        WrapperQueue.add(wrapper);
    }

    public void checkWrapperQueue() {
        for (int i = 0; i < WrapperQueue.size(); i++) {
            Wrapper wrapper = WrapperQueue.get(i);
        }
    }

    public List<Wrapper> getWrapperQueue() {
        return WrapperQueue;
    }

    public static WrapperManager getInstance() {
        return instance;
    }
}
