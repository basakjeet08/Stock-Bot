package dev.anirban.stockbot.exception;

public class DataNotFound extends RuntimeException {
    public DataNotFound(String message) {
        super(message);
    }
}