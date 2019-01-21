package de.CloudEx.service.core;

import de.CloudEx.service.services.cloud.CloudCommand;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.ERROR;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloudNetworkMasterCommandSystem {

    private static final CloudNetworkMasterCommandSystem instance = new CloudNetworkMasterCommandSystem();

    public List<CloudCommand> commands = new ArrayList<CloudCommand>();

    public void addCloudCommand(CloudCommand cloudCommand) {
        this.commands.add(cloudCommand);
    }

    public void launch() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;

            while ((line = reader.readLine()) != null) {
                for (CloudCommand command : commands) {
                    command.execute(line.split(" "));
                }
            }
        } catch (Exception e) {
            new Logger(ERROR.class, "CommandSystem: "+e);
        }
    }

    public static final CloudNetworkMasterCommandSystem getInstance() {
        return instance;
    }
}
