package model.exception;

/**
 * Exception intended to be thrown if a product has insufficient stock when an order is attempted to be created
 * @author Andrei Eminovici
 */
public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}
