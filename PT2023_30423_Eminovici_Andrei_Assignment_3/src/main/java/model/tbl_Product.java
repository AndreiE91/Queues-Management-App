package model;

/**
 * The 1:1 class representing the tbl_Product present into the DB.
 * @author Andrei Eminovici
 */
public class tbl_Product {
    /**
     * The ID of the product into the DB
     */
    private int prod_id;
    /**
     * The name of the product
     */
    private String prod_name;
    /**
     * The quantity of the product
     */
    private int prod_quantity;
    /**
     * The price of the product
     */
    private int prod_price;

    public tbl_Product(int prod_id, int prod_quantity, int prod_price, String prod_name) {
        this.prod_id = prod_id;
        this.prod_quantity = prod_quantity;
        this.prod_price = prod_price;
        this.prod_name = prod_name;
    }

    public tbl_Product() {

    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getProd_quantity() {
        return prod_quantity;
    }

    public void setProd_quantity(int prod_quantity) {
        this.prod_quantity = prod_quantity;
    }

    public int getProd_price() {
        return prod_price;
    }

    public void setProd_price(int prod_price) {
        this.prod_price = prod_price;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    @Override
    public String toString() {
        return "Product [id=" + prod_id + ", product_name=" + prod_name + ", price=" + prod_price + ", quantity=" + prod_quantity
                + "]";
    }
}
