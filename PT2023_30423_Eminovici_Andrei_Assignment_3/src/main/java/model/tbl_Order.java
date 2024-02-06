package model;

/**
 * The 1:1 class representing the tbl_Order present into the DB.
 * @author Andrei Eminovici
 */
public class tbl_Order {
    /**
     * The ID of the order into the DB
     */
    private int order_id;
    /**
     * The tbl_Client object representing the foreign key into the DB where each order is associated with a client. The real client ID
     * can be obtained via the getter Methods
     */
    private tbl_Client client_id_order;
    /**
     * The tbl_Product object representing the foreign key into the DB where each order is associated with a product. The real product ID
     * can be obtained via the getter Methods
     */
    private tbl_Product prod_id_order;
    /**
     * The quantity of the product on the order
     */
    private int order_quantity;
    /**
     * The total price of the order
     */
    private int order_total_price;

    public tbl_Order(int order_id, tbl_Client client_id_order, tbl_Product prod_id_order, int order_quantity, int order_total_price) {
        this.order_id = order_id;
        this.prod_id_order = prod_id_order;
        this.client_id_order = client_id_order;
        this.order_total_price = order_total_price;
        this.order_quantity = order_quantity;
    }

    public tbl_Order() {

    }

    public int getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(int order_quantity) {
        this.order_quantity = order_quantity;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public tbl_Product getProd_id_order() {
        return prod_id_order;
    }

    public void setProd_id_order(tbl_Product prod_id_order) {
        this.prod_id_order = prod_id_order;
    }

    public tbl_Client getClient_id_order() {
        return client_id_order;
    }

    public void setClient_id_order(tbl_Client client_id_order) {
        this.client_id_order = client_id_order;
    }

    public int getOrder_total_price() {
        return order_total_price;
    }

    public void setOrder_total_price(int order_total_price) {
        this.order_total_price = order_total_price;
    }

    @Override
    public String toString() {
        return "Order [id=" + order_id + ", total_price=" + order_total_price + client_id_order.toString() + prod_id_order.toString()
                + "]";
    }
}
