package dev.anirban.stockbot.exception;

public class PermissionDenied extends RuntimeException {
    public PermissionDenied() {
        super("Permission is denied to perform this operation !!");
    }
}