package bll.validators;

import model.tbl_Client;

/**
 * Validator class for client age
 * @author Andrei Eminovici
 */
public class ClientAgeValidator implements Validator<tbl_Client> {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 90;
    public void validate(tbl_Client t) {

        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The client age limit is not respected!");
        }

    }

}
