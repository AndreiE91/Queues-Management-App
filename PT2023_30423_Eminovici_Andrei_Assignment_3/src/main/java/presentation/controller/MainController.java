package presentation.controller;

import bll.BillBLL;
import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import com.github.javafaker.Faker;
import model.exception.InsufficientStockException;
import model.exception.InvalidInstanceException;
import model.exception.NullInputException;
import presentation.*;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * The controller which adds functionality to all the features from the view classes
 * @author Andrei Eminovici
 */
public class MainController {
    private ViewMain viewMain;
    private ViewClient viewClient;
    private ViewProduct viewProduct;
    private ViewOrder viewOrder;
    private ViewBill viewBill;

    public MainController(ViewMain viewMain, ViewClient viewClient, ViewProduct viewProduct, ViewOrder viewOrder,
                          ViewBill viewBill) {
        this.viewMain = viewMain;
        this.viewClient = viewClient;
        this.viewProduct = viewProduct;
        this.viewOrder = viewOrder;
        this.viewBill = viewBill;


        this.viewClient.addAddListener(new ClientAddListener());
        this.viewClient.addRemoveListener(new ClientRemoveListener());
        this.viewClient.addUpdateListener(new ClientUpdateListener());
        this.viewClient.addClearListener(e -> {
                    viewClient.clearAllFields();
                }
        );
        this.viewClient.addCopyFieldsListener(e -> {
                    viewClient.copyFields();
                }
        );
        this.viewClient.addGenerateRandomListener(new ClientGenerateRandomListener());

        this.viewProduct.addAddListener(new ProductAddListener());
        this.viewProduct.addRemoveListener(new ProductRemoveListener());
        this.viewProduct.addUpdateListener(new ProductUpdateListener());
        this.viewProduct.addClearListener(e -> {
            viewProduct.clearAllFields();
                }
        );
        this.viewProduct.addCopyFieldsListener(e -> {
            viewProduct.copyFields();
                }
        );
        this.viewProduct.addGenerateRandomListener(new ProductGenerateRandomListener());

        this.viewOrder.addAddListener(new OrderAddListener());
        this.viewOrder.addRemoveListener(new OrderRemoveListener());
        this.viewOrder.addUpdateListener(new OrderUpdateListener());
        this.viewOrder.addClearListener(e -> {
            viewOrder.clearAllFields();
                }
        );
        this.viewOrder.addCalculateTotalListener(e -> {
                    viewOrder.setCalculatedPrice();
                }
        );
        this.viewOrder.addCopyFieldsListener(e -> {
            viewOrder.copyFields();
                }
        );
        this.viewOrder.addGenerateRandomListener(new OrderGenerateRandomListener());
        this.viewMain.addClientListener(e -> {
                    ClientBLL clientBll = new ClientBLL();
            try {
                viewClient.getTable().setModel(clientBll.getTableModel());
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            viewClient.setVisible(true);
                }
        );
        this.viewMain.addProductListener(e -> {
                    ProductBLL productBll = new ProductBLL();
                    try {
                        viewProduct.getTable().setModel(productBll.getTableModel());
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
            viewProduct.setVisible(true);
                }
        );
        this.viewMain.addOrderListener(e -> {
                    OrderBLL orderBll = new OrderBLL();
                    try {
                        viewOrder.getTable().setModel(orderBll.getTableModel());
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
            viewOrder.setVisible(true);
                }
        );
        this.viewMain.addBillListener(e -> {
            BillBLL billBll = new BillBLL();
            try {
                viewBill.getTable().setModel(billBll.getTableModel());
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            viewBill.setVisible(true);
                }
        );
    }

    class ClientGenerateRandomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBLL clientBll = new ClientBLL();
            int n = -1;
            try {
                n = Integer.parseInt(viewClient.getTextFieldGenerateRandom().getText());
            } catch(NumberFormatException ex) {
                viewClient.showErrorMessage("Please enter a valid amount");
            }
            if(n <= 0) {
                viewClient.showErrorMessage("Invalid amount");
            } else {
                try {
                    Faker faker = new Faker();

                    Random random = new Random();

                    for (int i = 0; i < n; ++i) {
                        int id = random.nextInt(Integer.MAX_VALUE);
                        // Skip over ids which already exist in the DB
                        if (clientBll.existsAtId(id)) {
                            --i;
                            continue;
                        }
                        int age = random.nextInt(90 - 18 + 1) + 18;
                        String firstName = faker.name().firstName();
                        String lastName = faker.name().lastName();
                        String address = faker.address().fullAddress();
                        String email = faker.internet().emailAddress();
                        if(firstName.contains("'")) {
                            firstName = firstName.replace("'", " ");
                        }
                        if(lastName.contains("'")) {
                            lastName = lastName.replace("'", " ");
                        }
                        if(address.contains("'")) {
                            address = address.replace("'", " ");
                        }
                        if(email.contains("'")) {
                            email = email.replace("'", " ");
                        }

                        tbl_Client newClient = new tbl_Client(id, age, firstName, lastName, address, email);
                        //System.out.println(newClient.toString());

                        clientBll.insertClient(newClient);
                    }

                    viewClient.showMessage("Successfully generated and inserted " + n + " random clients!");

                    viewClient.getTable().setModel(clientBll.getTableModel());
                } catch (IllegalArgumentException ex) {
                    viewClient.showErrorMessage(ex.getMessage());
                } catch (Exception ex) {
                    viewClient.showErrorMessage("Error generating clients!");
                }
            }
        }
    }

    class ProductGenerateRandomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProductBLL productBll = new ProductBLL();
            int n = -1;
            try {
                n = Integer.parseInt(viewProduct.getTextFieldGenerateRandom().getText());
            } catch(NumberFormatException ex) {
                viewProduct.showErrorMessage("Please enter a valid amount");
            }
            if(n <= 0) {
                viewProduct.showErrorMessage("Invalid amount");
            } else {
                try {
                    Faker faker = new Faker();

                    Random random = new Random();

                    for (int i = 0; i < n; ++i) {
                        int id = random.nextInt(Integer.MAX_VALUE);
                        // Skip over ids which already exist in the DB
                        if (productBll.existsAtId(id)) {
                            --i;
                            continue;
                        }
                        int quantity = random.nextInt(25000);
                        int price = random.nextInt(1000);
                        String name = faker.commerce().productName();
                        if(name.contains("'")) {
                            name = name.replace("'", " ");
                        }

                        tbl_Product newProduct = new tbl_Product(id, quantity, price, name);

                        productBll.insertProduct(newProduct);
                    }

                    viewProduct.showMessage("Successfully generated and inserted " + n + " random products!");

                    viewProduct.getTable().setModel(productBll.getTableModel());
                } catch (IllegalArgumentException ex) {
                    viewProduct.showErrorMessage(ex.getMessage());
                } catch (Exception ex) {
                    viewProduct.showErrorMessage("Error generating products!");
                }
            }
        }
    }

    class OrderGenerateRandomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            OrderBLL orderBll = new OrderBLL();
            ClientBLL clientBll = new ClientBLL();
            ProductBLL productBll = new ProductBLL();
            BillBLL billBll = new BillBLL();
            int n = -1;
            try {
                n = Integer.parseInt(viewOrder.getTextFieldGenerateRandom().getText());
            } catch(NumberFormatException ex) {
                viewOrder.showErrorMessage("Please enter a valid amount");
            }
            if(n <= 0) {
                viewOrder.showErrorMessage("Invalid amount");
            } else {
                try {
                    Faker faker = new Faker();

                    Random random = new Random();

                    for (int i = 0; i < n; ++i) {
                        int id = random.nextInt(Integer.MAX_VALUE);
                        // Skip over ids which already exist in the DB
                        if (orderBll.existsAtId(id)) {
                            --i;
                            continue;
                        }
                        int quantity = random.nextInt(2500);
                        tbl_Product randomProduct = productBll.getRandomExistingProduct();
                        if(randomProduct.getProd_quantity() - quantity < 0) {
                            --i;
                            continue;
                        }
                        tbl_Client randomClient = clientBll.getRandomExistingClient();
                        int totalPrice = quantity * randomProduct.getProd_price();

                        tbl_Order newOrder = new tbl_Order(id, randomClient, randomProduct,
                                quantity, totalPrice
                        );
                        // System.out.println(newOrder.toString());
                        orderBll.insertOrder(newOrder);
                    }

                    viewOrder.showMessage("Successfully generated and inserted " + n + " random orders!");

                    viewOrder.getTable().setModel(orderBll.getTableModel());
                    viewBill.getTable().setModel(billBll.getTableModel());
                    viewProduct.getTable().setModel(orderBll.getTableModel());
                } catch (IllegalArgumentException ex) {
                    viewOrder.showErrorMessage(ex.getMessage());
                } catch (Exception ex) {
                    viewOrder.showErrorMessage("Error generating orders!");
                }
            }
        }
    }

    class ClientAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBLL clientBll = new ClientBLL();
            try {
                int id = Integer.parseInt(viewClient.getTextFieldId().getText());
                int age = Integer.parseInt(viewClient.getTextFieldAge().getText());
                String firstName = viewClient.getTextFieldFirstName().getText();
                String lastName = viewClient.getTextFieldLastName().getText();
                String address = viewClient.getTextFieldAddress().getText();
                String email = viewClient.getTextFieldEmail().getText();

                tbl_Client newClient = new tbl_Client(id, age, firstName, lastName, address, email);

                clientBll.insertClient(newClient);

                viewClient.showMessage("Successfully added new client!");

                viewClient.getTable().setModel(clientBll.getTableModel());
            } catch (IllegalArgumentException ex) {
                viewClient.showErrorMessage(ex.getMessage());
            } catch (Exception ex) {
                viewClient.showErrorMessage("Error adding new client!");
            }
        }
    }

    class ClientUpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBLL clientBll = new ClientBLL();
            try {
                int targetId = Integer.parseInt(viewClient.getTextFieldTargetId().getText());

                int id = Integer.parseInt(viewClient.getTextFieldId().getText());
                int age = Integer.parseInt(viewClient.getTextFieldAge().getText());
                String firstName = viewClient.getTextFieldFirstName().getText();
                String lastName = viewClient.getTextFieldLastName().getText();
                String address = viewClient.getTextFieldAddress().getText();
                String email = viewClient.getTextFieldEmail().getText();

                tbl_Client newClient = new tbl_Client(id, age, firstName, lastName, address, email);

                clientBll.updateClient(newClient, targetId);

                viewClient.showMessage("Successfully updated client with id=" + targetId);

                viewClient.getTable().setModel(clientBll.getTableModel());
            } catch (IllegalArgumentException | InvalidInstanceException ex) {
                viewClient.showErrorMessage(ex.getMessage());
            } catch (Exception ex) {
                viewClient.showErrorMessage("Error updating client!");
            }
        }
    }

    class ClientRemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBLL clientBll = new ClientBLL();
            try {
                int targetId = Integer.parseInt(viewClient.getTextFieldTargetId().getText());

                clientBll.removeClient(targetId);

                viewClient.showMessage("Successfully deleted client with id=" + targetId);

                viewClient.getTable().setModel(clientBll.getTableModel());
            } catch (InvalidInstanceException ex) {
                viewClient.showErrorMessage(ex.getMessage());
            } catch (Exception ex) {
                viewClient.showErrorMessage("Error deleting client!");
            }
        }
    }

    class ProductAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductBLL productBll = new ProductBLL();
            try {
                int id = Integer.parseInt(viewProduct.getTextFieldId().getText());
                int quantity = Integer.parseInt(viewProduct.getTextFieldQuantity().getText());
                int price = Integer.parseInt(viewProduct.getTextFieldPrice().getText());
                String name = viewProduct.getTextFieldName().getText();

                tbl_Product newProduct = new tbl_Product(id, quantity, price, name);

                productBll.insertProduct(newProduct);

                viewProduct.showMessage("Successfully added new product!");

                viewProduct.getTable().setModel(productBll.getTableModel());
            } catch (IllegalArgumentException ex) {
                viewProduct.showErrorMessage(ex.getMessage());
            } catch (Exception ex) {
                viewProduct.showErrorMessage("Error adding new product!");
            }
        }
    }

    class ProductUpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductBLL productBll = new ProductBLL();
            try {
                int targetId = Integer.parseInt(viewProduct.getTextFieldTargetId().getText());

                int id = Integer.parseInt(viewProduct.getTextFieldId().getText());
                int quantity = Integer.parseInt(viewProduct.getTextFieldQuantity().getText());
                int price = Integer.parseInt(viewProduct.getTextFieldPrice().getText());
                String name = viewProduct.getTextFieldName().getText();

                tbl_Product newProduct = new tbl_Product(id, quantity, price, name);

                productBll.updateProduct(newProduct, targetId);

                viewProduct.showMessage("Successfully updated product with id=" + targetId);

                viewProduct.getTable().setModel(productBll.getTableModel());
            } catch (IllegalArgumentException | InvalidInstanceException ex) {
                viewProduct.showErrorMessage(ex.getMessage());
            } catch (Exception ex) {
                viewProduct.showErrorMessage("Error updating product!");
            }
        }
    }

    class ProductRemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductBLL productBll = new ProductBLL();
            try {
                int targetId = Integer.parseInt(viewProduct.getTextFieldTargetId().getText());

                productBll.removeProduct(targetId);

                viewProduct.showMessage("Successfully deleted product with id=" + targetId);

                viewProduct.getTable().setModel(productBll.getTableModel());
            } catch (InvalidInstanceException ex) {
                viewProduct.showErrorMessage(ex.getMessage());
            } catch (Exception ex) {
                viewProduct.showErrorMessage("Error deleting product!");
            }
        }
    }

    class OrderAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderBLL orderBll = new OrderBLL();
            ClientBLL clientBll = new ClientBLL();
            ProductBLL productBll = new ProductBLL();
            BillBLL billBll = new BillBLL();
            try {
                viewOrder.setCalculatedPrice();
                int id = Integer.parseInt(viewOrder.getTextFieldId().getText());
                int clientId = Integer.parseInt(viewOrder.getTextFieldClientId().getText());
                int productId = Integer.parseInt(viewOrder.getTextFieldProductId().getText());
                int quantity = Integer.parseInt(viewOrder.getTextFieldQuantity().getText());
                int price = Integer.parseInt(viewOrder.getTextFieldTotalPrice().getText());

                tbl_Order newOrder = new tbl_Order(id, clientBll.findClientById(clientId), productBll.findProductById(productId), quantity, price);

                orderBll.insertOrder(newOrder);

                viewOrder.showMessage("Successfully added new order!");

                viewBill.getTable().setModel(billBll.getTableModel());
                viewOrder.getTable().setModel(orderBll.getTableModel());
                viewProduct.getTable().setModel(productBll.getTableModel());
            } catch (IllegalArgumentException ex) {
                viewOrder.showErrorMessage(ex.getMessage());
            } catch (Exception ex) {
                viewOrder.showErrorMessage("Error adding new order!");
            }
        }
    }

    class OrderUpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderBLL orderBll = new OrderBLL();
            ClientBLL clientBll = new ClientBLL();
            ProductBLL productBll = new ProductBLL();
            BillBLL billBll = new BillBLL();
            try {
                viewOrder.setCalculatedPrice();
                int targetId = Integer.parseInt(viewOrder.getTextFieldTargetId().getText());
                int id = Integer.parseInt(viewOrder.getTextFieldId().getText());
                int clientId = Integer.parseInt(viewOrder.getTextFieldClientId().getText());
                int productId = Integer.parseInt(viewOrder.getTextFieldProductId().getText());
                int quantity = Integer.parseInt(viewOrder.getTextFieldQuantity().getText());
                int price = Integer.parseInt(viewOrder.getTextFieldTotalPrice().getText());

                tbl_Order newOrder = new tbl_Order(id, clientBll.findClientById(clientId), productBll.findProductById(productId), quantity, price);

                orderBll.updateOrder(newOrder, targetId);

                viewOrder.showMessage("Successfully updated order with id=" + targetId);

                viewBill.getTable().setModel(billBll.getTableModel());
                viewOrder.getTable().setModel(orderBll.getTableModel());
                viewProduct.getTable().setModel(productBll.getTableModel());
            } catch (IllegalArgumentException ex) {
                viewOrder.showErrorMessage(ex.getMessage());
            } catch (Exception ex) {
                viewOrder.showErrorMessage("Error updating order!");
            }
        }
    }

    class OrderRemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderBLL orderBll = new OrderBLL();
            ProductBLL productBll = new ProductBLL();
            try {
                int targetId = Integer.parseInt(viewOrder.getTextFieldTargetId().getText());

                orderBll.removeOrder(targetId);

                viewOrder.showMessage("Successfully deleted order with id=" + targetId);

                viewOrder.getTable().setModel(orderBll.getTableModel());
                viewProduct.getTable().setModel(productBll.getTableModel());
            } catch (InvalidInstanceException ex) {
                viewOrder.showErrorMessage(ex.getMessage());
            } catch (Exception ex) {
                viewOrder.showErrorMessage("Error deleting order!");
            }
        }
    }
}
