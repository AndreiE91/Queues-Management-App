package bll;

import bll.validators.ProductPriceValidator;
import bll.validators.ProductQuantityValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.exception.InvalidInstanceException;
import model.tbl_Client;
import model.tbl_Product;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Business layer for the product table interactions
 * @author Andrei Eminovici
 */
public class ProductBLL {
    /**
     * Create a reference to the Product Data Access Object
     */
    ProductDAO productDao = new ProductDAO();
    /**
     * Create a list to be populated with appropriate validators for a product
     */
    private List<Validator<tbl_Product>> validators;

    public ProductBLL() {
        validators = new ArrayList<Validator<tbl_Product>>();
        validators.add(new ProductPriceValidator());
        validators.add(new ProductQuantityValidator());
    }

    public tbl_Product findProductById(int id) {
        tbl_Product st = productDao.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The Product with id =" + id + " was not found!");
        }
        return st;
    }

    public boolean existsAtId(int id) {
        tbl_Product st = productDao.existsAtId(id);
        if (st == null) {
            return false;
        }
        return true;
    }

    public tbl_Product getRandomExistingProduct() {
        Random random = new Random();
        ArrayList<tbl_Product> products = (ArrayList<tbl_Product>) productDao.findAll();
        int randomIndex = random.nextInt(products.size());
        return products.get(randomIndex);
    }

    public int insertProduct(tbl_Product product) throws SQLException, IllegalAccessException {
        for (Validator<tbl_Product> v : validators) {
            v.validate(product);
        }
        return productDao.insert(product);
    }

    public void updateProduct(tbl_Product product, int id) throws SQLException, IllegalAccessException, InvalidInstanceException {
        if(productDao.findById(id) == null) {
            throw new InvalidInstanceException("The Product ID is never in use. Please choose a valid ID");
        }
        for (Validator<tbl_Product> v : validators) {
            v.validate(product);
        }
        productDao.update(product, id);
    }

    public void removeProduct(int id) throws SQLException, InvalidInstanceException {
        if(productDao.findById(id) == null) {
            throw new InvalidInstanceException("The Product ID is already in use. Please choose a valid ID");
        }
        productDao.remove(id);
    }

    public List<tbl_Product> getAllProducts() {
        return productDao.findAll();
    }

    public DefaultTableModel getTableModel() throws IllegalAccessException {
        return productDao.getTableModel();
    }
}
