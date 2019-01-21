package de.CloudEx.master.network.commands;

import de.CloudEx.master.manager.Master;
import de.CloudEx.service.services.cloud.CloudCommand;

public class StopCommand extends CloudCommand {

    @Override
    public void execute(String[] args) {
        if(args[0].equalsIgnoreCase("stop")) {
            Master.getInstance().stop();
        }
    }

    @Override
    public String getUsage() {
        return "Stop -> Stoppt das CloudSystem und seine Systeme!";
    }
}
