package bll;

import bll.validators.Validator;
import dao.BillDAO;
import model.tbl_Bill;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

/**
 * Business layer for the bill table interactions
 * @author Andrei Eminovici
 */
public class BillBLL {
    /**
     * Create a reference to the Bill Data Access Object
     */
    BillDAO billDao = new BillDAO();

    public int insertBill(tbl_Bill bill) throws SQLException, IllegalAccessException {
        return billDao.insert(bill);
    }

    public DefaultTableModel getTableModel() throws IllegalAccessException {
        return billDao.getTableModel();
    }
}
