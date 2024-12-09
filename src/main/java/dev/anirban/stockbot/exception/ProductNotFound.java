package dev.anirban.stockbot.exception;

public class ProductNotFound extends DataNotFound {
    public ProductNotFound(Integer id) {
        super("Product with ID : " + id + " is not found !!");
    }
}