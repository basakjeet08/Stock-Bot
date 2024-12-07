package dev.anirban.stockbot.exception;

public class PermissionDenied extends RuntimeException {
    public PermissionDenied() {
        super("Permission to perform this Operation is denied !!");
    }
}