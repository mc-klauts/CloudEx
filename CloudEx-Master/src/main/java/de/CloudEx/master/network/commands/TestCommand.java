package de.CloudEx.master.network.commands;

import de.CloudEx.service.services.cloud.CloudCommand;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.INFO;

public class TestCommand implements CloudCommand {

    @Override
    public void execute(String[] args) {
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("test")) {
                new Logger(INFO.class, "Test Command!");
            }
        }
    }

    @Override
    public String getUsage() {
        return "Test -> Testet das Command-System!";
    }
}
