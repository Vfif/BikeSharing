package com.epam.project.exception;

public class CommandException extends Exception {
    public CommandException() {
        super();
    }

    public CommandException(String s) {
        super(s);
    }

    public CommandException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CommandException(Throwable throwable) {
        super(throwable);
    }
}
