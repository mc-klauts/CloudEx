package de.CloudEx.service.services.logging.level;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ERROR implements LEVEL {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("[dd.MM.yyyy] [HH:mm:ss]");

    public void printStringLeveledMessage(Object loggedMessage) {
        System.out.println("[ERROR] " + loggedMessage);
    }
}
