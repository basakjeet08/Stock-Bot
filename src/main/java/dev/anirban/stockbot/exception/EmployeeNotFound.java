package dev.anirban.stockbot.exception;

public class EmployeeNotFound extends DataNotFound {
    public EmployeeNotFound(String username) {
        super("Employee with the Username : " + username + " is not found !!");
    }
}