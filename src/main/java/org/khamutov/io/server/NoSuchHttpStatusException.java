package org.khamutov.io.server;

public class NoSuchHttpStatusException extends RuntimeException{
    public NoSuchHttpStatusException(String message) {
        super(message);
    }
}
