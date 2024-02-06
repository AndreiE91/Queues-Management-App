package bll;

import bll.validators.OrderClientIdValidator;
import bll.validators.OrderProductIdValidator;
import bll.validators.OrderQuantityValidator;
import bll.validators.Validator;
import dao.BillDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.exception.InsufficientStockException;
import model.exception.InvalidInstanceException;
import model.tbl_Bill;
import model.tbl_Order;
import model.tbl_Product;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Business layer for the order table interactions
 * @author Andrei Eminovici
 */
public class OrderBLL {
    /**
     * Create a reference to the Order, Product and Bill Data Access Objects since the order will need to interact with all of them
     */
    OrderDAO orderDao = new OrderDAO();
    ProductDAO productDao = new ProductDAO();
    BillDAO billDao = new BillDAO();
    /**
     * Create a list to be populated with appropriate validators for an order
     */
    private List<Validator<tbl_Order>> validators;

    public OrderBLL() {
        validators = new ArrayList<Validator<tbl_Order>>();
        validators.add(new OrderClientIdValidator());
        validators.add(new OrderProductIdValidator());
        validators.add(new OrderQuantityValidator());
    }

    public tbl_Order findOrderById(int id) {
        tbl_Order st = orderDao.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The Order with id =" + id + " was not found!");
        }
        return st;
    }

    public boolean existsAtId(int id) {
        tbl_Order st = orderDao.existsAtId(id);
        if (st == null) {
            return false;
        }
        return true;
    }

    public int insertOrder(tbl_Order order) throws SQLException, IllegalAccessException, InsufficientStockException {
        for (Validator<tbl_Order> v : validators) {
            v.validate(order);
        }
        int newQuantity = order.getProd_id_order().getProd_quantity() - order.getOrder_quantity();
        if(newQuantity < 0) {
            throw new InsufficientStockException("Insufficient stock");
        } else { // Decrement product id when creating order
            tbl_Product newProduct = new tbl_Product(order.getProd_id_order().getProd_id(), newQuantity,
                    order.getProd_id_order().getProd_quantity(), order.getProd_id_order().getProd_name());
            productDao.update(newProduct, order.getProd_id_order().getProd_id());
        }
        billDao.insert(generateBill(order));
        return orderDao.insert(order);
    }

    private tbl_Bill generateBill(tbl_Order order) {
        int nrExistingBills = billDao.findAll().size();
        int id = nrExistingBills + 1;
        String prodName = order.getProd_id_order().getProd_name();
        String firstName = order.getClient_id_order().getFirst_name();
        String lastName = order.getClient_id_order().getLast_name();
        String address = order.getClient_id_order().getAddress();
        String email = order.getClient_id_order().getEmail();
        int quantity = order.getOrder_quantity();
        int price = order.getOrder_total_price();
        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = date.format(formatter);

        return new tbl_Bill(id, prodName, firstName, lastName, address, email, quantity, price, formattedDate);
    }

    public void updateOrder(tbl_Order order, int id) throws SQLException, IllegalAccessException, InvalidInstanceException, InsufficientStockException {
        if(orderDao.findById(id) == null) {
            throw new InvalidInstanceException("The Order ID is never in use. Please choose a valid ID");
        }
        for (Validator<tbl_Order> v : validators) {
            v.validate(order);
        }
        tbl_Order currOrder = orderDao.findById(id);
        int newQuantity = order.getProd_id_order().getProd_quantity() - order.getOrder_quantity() + currOrder.getOrder_quantity();
        if(newQuantity < 0) {
            throw new InsufficientStockException("Insufficient stock");
        } else { // Decrement product id when creating order
            tbl_Product newProduct = productDao.findById(order.getProd_id_order().getProd_id());
            newProduct.setProd_quantity(newQuantity);
            productDao.update(newProduct, order.getProd_id_order().getProd_id());
        }
        billDao.insert(generateBill(order));
        orderDao.update(order, id);
    }

    public void removeOrder(int id) throws SQLException, InvalidInstanceException, IllegalAccessException {
        if(orderDao.findById(id) == null) {
            throw new InvalidInstanceException("The Order ID is already in use. Please choose a valid ID");
        }
        tbl_Order currOrder = orderDao.findById(id);
        int prodId = currOrder.getProd_id_order().getProd_id();
        int incrementQuantity = currOrder.getOrder_quantity();
        // Increment back product quantity by the amount present on the order to be deleted
        tbl_Product productRef = productDao.findById(prodId);
        productRef.setProd_quantity(productRef.getProd_quantity() + incrementQuantity);
        productDao.update(productRef, currOrder.getProd_id_order().getProd_id());
        orderDao.remove(id);
    }

    public List<tbl_Order> getAllOrders() {
        return orderDao.findAll();
    }

    public DefaultTableModel getTableModel() throws IllegalAccessException {
        return orderDao.getTableModel();
    }
}
