package bll.validators;

import model.tbl_Order;

/**
 * Validator class for order quantity
 * @author Andrei Eminovici
 */
public class OrderQuantityValidator implements Validator<tbl_Order> {
    private static final int MIN_QUANTITY = 1;
    public void validate(tbl_Order t) {

        if (t.getOrder_total_price() < MIN_QUANTITY) {
            throw new IllegalArgumentException("The order quantity limit is not respected!");
        }

    }

}
