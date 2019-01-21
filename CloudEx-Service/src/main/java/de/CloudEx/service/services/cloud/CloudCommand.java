package de.CloudEx.service.services.cloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CloudCommand {

    public void execute(String[] args) { }
    public String getUsage() {
        return "";
    }

}
