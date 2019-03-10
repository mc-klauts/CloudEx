package de.CloudEx.service.services.logging.level;

public class MODULE implements LEVEL {

    @Override
    public void printStringLeveledMessage(Object loggedMessage) {
        System.out.println("[MODULE] "+loggedMessage);
    }
}
