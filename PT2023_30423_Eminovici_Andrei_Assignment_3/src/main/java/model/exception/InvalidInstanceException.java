package model.exception;

/**
 * Exception intended to be thrown if an instance of an object trying to be created does not correspond with the entries in the DB
 * @author Andrei Eminovici
 */
public class InvalidInstanceException extends Exception {
    public InvalidInstanceException(String message) {
        super(message);
    }
}