package bll.validators;

/**
 * Interface to be implemented by all validator classes
 * @author Andrei Eminovici
 */
public interface Validator<T> {

    public void validate(T t);
}
