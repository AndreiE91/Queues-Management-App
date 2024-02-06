package model;

import java.time.LocalDateTime;

/**
 * The 1:1 class representing the tbl_Bill present into the DB. Since this will never be modified, rather the bills are kept as
 * a history of transactions, this class is declared with the record type
 * @author Andrei Eminovici
 */
public record tbl_Bill(int bill_id, String bill_prod_name, String bill_client_first_name, String bill_client_last_name,
                       String bill_client_address, String bill_client_email, int bill_prod_quantity, int bill_order_total_price,
                       String bill_date) {

    public int getBill_id() {
        return bill_id;
    }

    public int getBill_prod_quantity() {
        return bill_prod_quantity;
    }

    public int getBill_order_total_price() {
        return bill_order_total_price;
    }

    public String getBill_date() {
        return bill_date;
    }

    public String getBill_prod_name() {
        return bill_prod_name;
    }

    public String getBill_client_first_name() {
        return bill_client_first_name;
    }

    public String getBill_client_last_name() {
        return bill_client_last_name;
    }

    public String getBill_client_address() {
        return bill_client_address;
    }

    public String getBill_client_email() {
        return bill_client_email;
    }

    @Override
    public String toString() {
        return "Bill [id=" + bill_id + ", date=" + bill_date + ", first_name=" + bill_client_first_name + ", last_name=" + bill_client_last_name + ", address=" + bill_client_address + ", email=" + bill_client_email + ", prod_name=" + bill_prod_name + ", prod_quantity=" + bill_prod_quantity + ", total_price=" + bill_order_total_price
                + "]";
    }
}
