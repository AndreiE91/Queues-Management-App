package bll.validators;

import bll.ClientBLL;
import dao.ClientDAO;
import model.*;

/**
 * Validator class for order client ID
 * @author Andrei Eminovici
 */
public class OrderClientIdValidator implements Validator<tbl_Order> {
    public void validate(tbl_Order t) {
        ClientDAO clientDao = new ClientDAO();
        if (clientDao.findById(t.getClient_id_order().getClient_id()) == null) {
            throw new IllegalArgumentException("The client ID is not valid!(doesn't exist)");
        }

    }

}
