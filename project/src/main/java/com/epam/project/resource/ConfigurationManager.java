package com.epam.project.resource;

import java.util.ResourceBundle;

public class ConfigurationManager {//singleton
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}