package com.epam.project.exception;

public class PropertiesFileNotFound extends Exception {
    public PropertiesFileNotFound() {
    }

    public PropertiesFileNotFound(String s) {
        super(s);
    }

    public PropertiesFileNotFound(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PropertiesFileNotFound(Throwable throwable) {
        super(throwable);
    }
}
