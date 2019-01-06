package de.CloudEx.service.services.logging;

import de.CloudEx.service.services.logging.level.LEVEL;

public class Logger {

    public Logger(Class<? extends LEVEL> levelClass, String loggedMessage) {
        try {
            levelClass.newInstance().printStringLeveledMessage(loggedMessage);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
