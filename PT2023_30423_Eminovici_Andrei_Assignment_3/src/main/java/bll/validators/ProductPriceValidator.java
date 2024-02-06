package bll.validators;

import model.tbl_Product;

/**
 * Validator class for product price
 * @author Andrei Eminovici
 */
public class ProductPriceValidator implements Validator<tbl_Product> {
    private static final int MIN_PRICE = 1;
    public void validate(tbl_Product t) {

        if (t.getProd_price() < MIN_PRICE) {
            throw new IllegalArgumentException("The product price limit is not respected!");
        }

    }

}
