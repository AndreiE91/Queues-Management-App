package bll.validators;

import dao.ProductDAO;
import model.tbl_Order;

/**
 * Validator class for order product ID
 * @author Andrei Eminovici
 */
public class OrderProductIdValidator implements Validator<tbl_Order> {
    public void validate(tbl_Order t) {
        ProductDAO productDAO = new ProductDAO();
        if (productDAO.findById(t.getProd_id_order().getProd_id()) == null) {
            throw new IllegalArgumentException("The product ID is not valid!(doesn't exist)");
        }

    }

}
