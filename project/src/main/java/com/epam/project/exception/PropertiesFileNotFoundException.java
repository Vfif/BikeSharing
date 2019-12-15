package com.epam.project.exception;

public class PropertiesFileNotFoundException extends Exception {
    public PropertiesFileNotFoundException() {
    }

    public PropertiesFileNotFoundException(String s) {
        super(s);
    }

    public PropertiesFileNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PropertiesFileNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
