package dev.anirban.stockbot.exception;

public class EmployeeAlreadyExists extends RuntimeException {
    public EmployeeAlreadyExists(String username) {
        super("Employee with the Username : " + username + " already exists !!");
    }
}