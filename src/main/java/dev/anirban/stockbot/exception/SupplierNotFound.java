package dev.anirban.stockbot.exception;

public class SupplierNotFound extends DataNotFound {
    public SupplierNotFound(Integer id) {
        super("Supplier with ID : " + id + " is not found !!");
    }
}