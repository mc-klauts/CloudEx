package de.CloudEx.service.services.cloud;

public interface CloudCommand {

    void execute(String[] args);
    String getUsage();

}
