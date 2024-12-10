package dev.anirban.stockbot.exception;

public class RestockNotFound extends DataNotFound {
    public RestockNotFound(Integer id) {
        super("Restock with ID : " + id + " is not found !!");
    }
}