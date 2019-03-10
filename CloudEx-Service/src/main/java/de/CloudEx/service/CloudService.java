package de.CloudEx.service;

import java.util.ArrayList;
import java.util.List;

public class CloudService {

    private static final CloudService instance = new CloudService();

    public static CloudService getInstance() {
        return instance;
    }
}
