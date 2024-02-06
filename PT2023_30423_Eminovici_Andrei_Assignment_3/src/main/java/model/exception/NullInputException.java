package model.exception;

/**
 * Exception intended to be thrown if a field of a textBox is left empty
 * @author Andrei Eminovici
 */
public class NullInputException extends Exception {
    public NullInputException(String message) {
        super(message);
    }
}