package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.tbl_Client;
import model.tbl_Order;
import model.tbl_Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * The view class for the Order graphical interface
 * @author Andrei Eminovici
 */
public class ViewOrder extends JFrame {
    JLabel labelTitle = new JLabel("Order Operations");
    JButton buttonAddOrder = new JButton("Add");
    JButton buttonRemoveOrder = new JButton("Remove");
    JButton buttonUpdate = new JButton("Update");
    JButton buttonClear = new JButton("Clear Fields");
    JButton buttonCalculateTotal = new JButton("Calculate");

    JTextField textFieldId = new JTextField();
    JTextField textFieldClientId = new JTextField();
    JTextField textFieldProductId = new JTextField();
    JTextField textFieldTotalPrice = new JTextField();
    JTextField textFieldQuantity = new JTextField();

    JTextField textFieldTargetId = new JTextField();
    JLabel labelId = new JLabel("ID:");
    JLabel labelClientId = new JLabel("Client ID:");
    JLabel labelProductId = new JLabel("Product ID:");
    JLabel labelTotalPrice = new JLabel("Total Price:");
    JLabel labelQuantity = new JLabel("Quantity:");
    JLabel labelTargetId = new JLabel("Target ID:");
    JButton buttonCopyFields = new JButton("Copy Fields");

    DefaultTableModel model;

    /** Create the JTable, empty for now but will be externally updated to contain data via the setModel method */
    JTable table = new JTable();

    /** Create a JScrollPane to contain the table */
    JScrollPane scrollPane = new JScrollPane(table);

    JButton buttonGenerateRandom = new JButton("Generate");
    JLabel labelGenerateRandom = new JLabel("Generate Amount:");
    JTextField textFieldGenerateRandom = new JTextField();

    public ViewOrder() {
        this.setTitle("Order Operations");
        this.setBounds(250, 100, 900, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);

        this.getContentPane().setLayout(null);
        this.getContentPane().add(labelTitle);
        this.getContentPane().add(buttonAddOrder);
        this.getContentPane().add(buttonRemoveOrder);
        this.getContentPane().add(buttonUpdate);
        this.getContentPane().add(buttonClear);
        this.getContentPane().setBackground(new Color(219, 244, 173));
        this.getContentPane().add(textFieldId);
        this.getContentPane().add(textFieldClientId);
        this.getContentPane().add(textFieldProductId);
        this.getContentPane().add(textFieldTotalPrice);
        this.getContentPane().add(textFieldQuantity);
        this.getContentPane().add(textFieldTargetId);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(labelId);
        this.getContentPane().add(labelClientId);
        this.getContentPane().add(labelProductId);
        this.getContentPane().add(labelTotalPrice);
        this.getContentPane().add(labelQuantity);
        this.getContentPane().add(labelTargetId);
        this.getContentPane().add(buttonCalculateTotal);
        this.getContentPane().add(buttonCopyFields);
        this.getContentPane().add(buttonGenerateRandom);
        this.getContentPane().add(labelGenerateRandom);
        this.getContentPane().add(textFieldGenerateRandom);

        buttonGenerateRandom.setBackground(new Color(176, 175, 36));
        buttonGenerateRandom.setFont(new Font("Tahoma", Font.BOLD, 8));
        buttonGenerateRandom.setBounds(250, 350, 80, 20);

        labelGenerateRandom.setBounds(240, 295, 100, 25);
        labelGenerateRandom.setHorizontalAlignment(SwingConstants.CENTER);
        labelGenerateRandom.setFont(new Font("Tahoma", Font.BOLD, 10));
        textFieldGenerateRandom.setBounds(240, 320, 100, 25);

        scrollPane.setBounds(375, 10, 500, 365);

        labelTitle.setBounds(0, 0, 350, 133);
        labelTitle.setFont(new Font("Vivaldi", Font.BOLD, 45));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        labelId.setBounds(5, 120, 60, 20);
        labelId.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldId.setBounds(70, 120, 100, 25);

        labelClientId.setBounds(0, 150, 65, 20);
        labelClientId.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldClientId.setBounds(70, 150, 100, 25);

        labelProductId.setBounds(0, 180, 65, 20);
        labelProductId.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldProductId.setBounds(70, 180, 100, 25);

        labelQuantity.setBounds(0, 210, 65, 20);
        labelQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldQuantity.setBounds(70, 210, 100, 25);

        labelTotalPrice.setBounds(0, 240, 65, 20);
        labelTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldTotalPrice.setBounds(70, 240, 100, 25);
        textFieldTotalPrice.setEditable(false);

        labelTargetId.setBounds(175, 120, 60, 20);
        labelTargetId.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldTargetId.setBounds(240, 120, 100, 25);

        buttonAddOrder.setBackground(new Color(169, 225, 144));
        buttonAddOrder.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonAddOrder.setBounds(41, 380, 180, 77);

        buttonRemoveOrder.setBackground(new Color(205, 199, 118));
        buttonRemoveOrder.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonRemoveOrder.setBounds(231, 380, 180, 77);

        buttonUpdate.setBackground(new Color(165, 170, 82));
        buttonUpdate.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonUpdate.setBounds(432, 380, 180, 77);

        buttonClear.setBackground(new Color(118, 117, 34));
        buttonClear.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonClear.setBounds(622, 380, 180, 77);

        buttonCalculateTotal.setBackground(new Color(108, 167, 89));
        buttonCalculateTotal.setFont(new Font("Tahoma", Font.BOLD, 9));
        buttonCalculateTotal.setBounds(175, 242, 85, 20);

        buttonCopyFields.setBackground(new Color(115, 232, 61));
        buttonCopyFields.setFont(new Font("Tahoma", Font.BOLD, 8));
        buttonCopyFields.setBounds(250, 150, 80, 20);

        this.setVisible(false);
    }

    public JButton getButtonGenerateRandom() {
        return buttonGenerateRandom;
    }

    public void setButtonGenerateRandom(JButton buttonGenerateRandom) {
        this.buttonGenerateRandom = buttonGenerateRandom;
    }

    public JLabel getLabelGenerateRandom() {
        return labelGenerateRandom;
    }

    public void setLabelGenerateRandom(JLabel labelGenerateRandom) {
        this.labelGenerateRandom = labelGenerateRandom;
    }

    public JTextField getTextFieldGenerateRandom() {
        return textFieldGenerateRandom;
    }

    public void setTextFieldGenerateRandom(JTextField textFieldGenerateRandom) {
        this.textFieldGenerateRandom = textFieldGenerateRandom;
    }

    public JButton getButtonCopyFields() {
        return buttonCopyFields;
    }

    public void setButtonCopyFields(JButton buttonCopyFields) {
        this.buttonCopyFields = buttonCopyFields;
    }

    public JTextField getTextFieldTargetId() {
        return textFieldTargetId;
    }

    public void setTextFieldTargetId(JTextField textFieldTargetId) {
        this.textFieldTargetId = textFieldTargetId;
    }

    public JLabel getLabelId() {
        return labelId;
    }

    public void setLabelId(JLabel labelId) {
        this.labelId = labelId;
    }

    public JLabel getLabelTargetId() {
        return labelTargetId;
    }

    public void setLabelTargetId(JLabel labelTargetId) {
        this.labelTargetId = labelTargetId;
    }

    public JTextField getTextFieldQuantity() {
        return textFieldQuantity;
    }

    public void setTextFieldQuantity(JTextField textFieldQuantity) {
        this.textFieldQuantity = textFieldQuantity;
    }

    public JLabel getLabelQuantity() {
        return labelQuantity;
    }

    public void setLabelQuantity(JLabel labelQuantity) {
        this.labelQuantity = labelQuantity;
    }

    public JTextField getTextFieldId() {
        return textFieldId;
    }

    public void setTextFieldId(JTextField textFieldId) {
        this.textFieldId = textFieldId;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public JTextField getTextFieldClientId() {
        return textFieldClientId;
    }

    public void setTextFieldClientId(JTextField textFieldClientId) {
        this.textFieldClientId = textFieldClientId;
    }

    public JTextField getTextFieldProductId() {
        return textFieldProductId;
    }

    public void setTextFieldProductId(JTextField textFieldProductId) {
        this.textFieldProductId = textFieldProductId;
    }

    public JTextField getTextFieldTotalPrice() {
        return textFieldTotalPrice;
    }

    public void setTextFieldTotalPrice(JTextField textFieldTotalPrice) {
        this.textFieldTotalPrice = textFieldTotalPrice;
    }

    public JLabel getLabelClientId() {
        return labelClientId;
    }

    public void setLabelClientId(JLabel labelClientId) {
        this.labelClientId = labelClientId;
    }

    public JLabel getLabelProductId() {
        return labelProductId;
    }

    public void setLabelProductId(JLabel labelProductId) {
        this.labelProductId = labelProductId;
    }

    public JLabel getLabelTotalPrice() {
        return labelTotalPrice;
    }

    public void setLabelTotalPrice(JLabel labelTotalPrice) {
        this.labelTotalPrice = labelTotalPrice;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JLabel getLabelTitle() {
        return labelTitle;
    }

    public void setLabelTitle(JLabel labelTitle) {
        this.labelTitle = labelTitle;
    }

    public JButton getButtonAddOrder() {
        return buttonAddOrder;
    }

    public void setButtonAddOrder(JButton buttonAddOrder) {
        this.buttonAddOrder = buttonAddOrder;
    }

    public JButton getButtonRemoveOrder() {
        return buttonRemoveOrder;
    }

    public void setButtonRemoveOrder(JButton buttonRemoveOrder) {
        this.buttonRemoveOrder = buttonRemoveOrder;
    }

    public JButton getButtonUpdate() {
        return buttonUpdate;
    }

    public void setButtonUpdate(JButton buttonUpdate) {
        this.buttonUpdate = buttonUpdate;
    }

    public JButton getButtonClear() {
        return buttonClear;
    }

    public void setButtonClear(JButton buttonClear) {
        this.buttonClear = buttonClear;
    }

    public JButton getButtonCalculateTotal() {
        return buttonCalculateTotal;
    }

    public void setButtonCalculateTotal(JButton buttonCalculateTotal) {
        this.buttonCalculateTotal = buttonCalculateTotal;
    }

    public void addAddListener(ActionListener actionListener) {
        buttonAddOrder.addActionListener(actionListener);
    }

    public void addRemoveListener(ActionListener actionListener) {
        buttonRemoveOrder.addActionListener(actionListener);
    }

    public void addUpdateListener(ActionListener actionListener) {
        buttonUpdate.addActionListener(actionListener);
    }

    public void addClearListener(ActionListener actionListener) {
        buttonClear.addActionListener(actionListener);
    }
    public void addCalculateTotalListener(ActionListener actionListener) {
        buttonCalculateTotal.addActionListener(actionListener);
    }
    public void addCopyFieldsListener(ActionListener actionListener) {
        buttonCopyFields.addActionListener(actionListener);
    }

    public void addGenerateRandomListener(ActionListener actionListener) {
        buttonGenerateRandom.addActionListener(actionListener);
    }

    /**
     * <p>Fills all textBox inputs with the corresponding object given by target ID
     * </p>
     */
    public void copyFields() {
        OrderBLL orderBll = new OrderBLL();
        tbl_Order targetOrder = orderBll.findOrderById(Integer.parseInt(textFieldTargetId.getText()));
        textFieldId.setText(String.valueOf(targetOrder.getOrder_id()));
        textFieldProductId.setText(String.valueOf(targetOrder.getProd_id_order().getProd_id()));
        textFieldQuantity.setText(String.valueOf(targetOrder.getOrder_quantity()));
        textFieldClientId.setText(String.valueOf(targetOrder.getClient_id_order().getClient_id()));
        textFieldTotalPrice.setText(String.valueOf(targetOrder.getOrder_total_price()));
    }

    /**
     * <p>Sets the price textField with the correct price based on the selected product and given quantity
     * </p>
     */
    public void setCalculatedPrice() {
        if(Objects.equals(textFieldQuantity.getText(), "") || textFieldProductId.getText().equals("")) {
            showErrorMessage("Null fields not allowed");
            return;
        }
        ProductBLL productBll = new ProductBLL();
        int quantity = 0;
        int productId = 0;
        try {
            quantity = Integer.parseInt(getTextFieldQuantity().getText());
            productId = Integer.parseInt(getTextFieldProductId().getText());
        } catch (NumberFormatException nex) {
            showErrorMessage("Only integer numbers allowed as calculaton input");
            return;
        }
        tbl_Product product = new tbl_Product(0,0,0,"null"); // Dummy initializer
        try {
            product = productBll.findProductById(productId);
        } catch (IndexOutOfBoundsException ex) {
            showErrorMessage("Please enter a valid product ID");
        }
        int totalPrice = product.getProd_price() * quantity;
        if(totalPrice <= 0) {
            showErrorMessage("Negative quantity not allowed");
            return;
        }
        getTextFieldTotalPrice().setText(String.valueOf(totalPrice));
    }

    /**
     * <p>Sets all textBox fields of the input to null
     * </p>
     */
    public void clearAllFields() {
        textFieldTargetId.setText(null);
        textFieldId.setText(null);
        textFieldClientId.setText(null);
        textFieldProductId.setText(null);
        textFieldQuantity.setText(null);
        textFieldTotalPrice.setText(null);
    }

    /**
     * <p>Displays an error message on the view frame
     * </p>
     * @param message the message to be displayed
     */
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
    }

    /**
     * <p>Displays a message on the view frame
     * </p>
     * @param message the message to be displayed
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "",JOptionPane.INFORMATION_MESSAGE);
    }
}
