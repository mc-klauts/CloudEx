package de.CloudEx.master.network.commands;

import de.CloudEx.master.manager.Master;
import de.CloudEx.service.services.cloud.CloudCommand;

public class StopCommand implements CloudCommand {

    @Override
    public void execute(String[] args) {
        Master.INSTANCE.stop();
    }

    @Override
    public String getUsage() {
        return "Stop -> ";
    }
}
