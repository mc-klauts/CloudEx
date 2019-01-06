package de.CloudEx.master.network.commands;

import de.CloudEx.master.manager.Master;
import de.CloudEx.service.core.CloudNetworkMasterCommandSystem;
import de.CloudEx.service.services.cloud.CloudCommand;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.INFO;

public class HelpCommand implements CloudCommand {

    public void execute(String[] args) {
        if(args[0].equalsIgnoreCase("help")) {
            CloudNetworkMasterCommandSystem.INSTANCE.commands.forEach(cloudCommand -> {
                if(cloudCommand.getUsage() != null) {
                    new Logger(INFO.class, cloudCommand.getUsage());
                }
            });
        }
    }

    public String getUsage() {
        return null;
    }
}
