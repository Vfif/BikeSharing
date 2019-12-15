package com.epam.project.resource;

import com.epam.project.exception.PropertiesFileNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseManager {
    private static final int DEFAULT_POOL_SIZE = 10;
    private static DatabaseManager instance;
    private static Properties properties;
    private static String url;
    private static int poolSize;

    private DatabaseManager() throws PropertiesFileNotFoundException {
        try {
            FileInputStream stream =
                    new FileInputStream("C:\\Users\\Home\\IdeaProjects\\JavaWeb\\project\\src\\main\\resources\\database\\database.properties");
            properties = new Properties();
            properties.load(stream);
            url = (String) properties.remove("url");
            try {
                poolSize = Integer.parseInt((String) properties.remove("poolSize"));
            } catch (NumberFormatException e) {
                poolSize = DEFAULT_POOL_SIZE;
            }
        } catch (IOException e) {
            throw new PropertiesFileNotFoundException();
        }
    }

    public static DatabaseManager getInstance() throws PropertiesFileNotFoundException {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getUrl() {
        return url;
    }
}
