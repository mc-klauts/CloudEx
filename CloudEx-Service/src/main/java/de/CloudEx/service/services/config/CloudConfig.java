package de.CloudEx.service.services.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CloudConfig {

    private File file;
    private List<Object> content = new ArrayList<Object>();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private JSONObject object = new JSONObject();

    public CloudConfig(File file) {
        this.file = file;
    }

    public void addString(String path, Object value) {
        object.put(path, value);
    }

    public void save() {
        try {
            if(!this.file.exists()) {
                this.file.createNewFile();
            }
            FileWriter writer = new FileWriter(this.file);
            writer.write(this.gson.toJson(this.object));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
