package bll.validators;

import model.tbl_Product;

/**
 * Validator class for product quantity
 * @author Andrei Eminovici
 */
public class ProductQuantityValidator implements Validator<tbl_Product> {
    private static final int MIN_QUANTITY = 1;
    public void validate(tbl_Product t) {

        if (t.getProd_quantity() < MIN_QUANTITY) {
            throw new IllegalArgumentException("The product quantity limit is not respected!");
        }

    }

}
