package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.tbl_Client;
import model.tbl_Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The view class for the Product graphical interface
 * @author Andrei Eminovici
 */
public class ViewProduct extends JFrame {
    JLabel labelTitle = new JLabel("Product Operations");
    JButton buttonAddProduct = new JButton("Add");
    JButton buttonRemoveProduct = new JButton("Remove");
    JButton buttonUpdate = new JButton("Update");
    JButton buttonClear = new JButton("Clear Fields");
    JTextField textFieldId = new JTextField();
    JTextField textFieldName = new JTextField();
    JTextField textFieldQuantity = new JTextField();
    JTextField textFieldPrice = new JTextField();
    JTextField textFieldTargetId = new JTextField();
    JLabel labelId = new JLabel("ID:");
    JLabel labelName = new JLabel("Name:");
    JLabel labelQuantity = new JLabel("Quantity:");
    JLabel labelPrice = new JLabel("Price:");
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

    public ViewProduct() {
        this.setTitle("Product Operations");
        this.setBounds(250, 100, 900, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);

        this.getContentPane().setLayout(null);
        this.getContentPane().add(labelTitle);
        this.getContentPane().add(buttonAddProduct);
        this.getContentPane().add(buttonRemoveProduct);
        this.getContentPane().add(buttonUpdate);
        this.getContentPane().add(buttonClear);
        this.getContentPane().setBackground(new Color(219, 244, 173));
        this.getContentPane().add(textFieldId);
        this.getContentPane().add(textFieldName);
        this.getContentPane().add(textFieldQuantity);
        this.getContentPane().add(textFieldPrice);
        this.getContentPane().add(textFieldTargetId);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(labelId);
        this.getContentPane().add(labelName);
        this.getContentPane().add(labelQuantity);
        this.getContentPane().add(labelPrice);
        this.getContentPane().add(labelTargetId);
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

        labelName.setBounds(0, 150, 65, 20);
        labelName.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldName.setBounds(70, 150, 100, 25);

        labelQuantity.setBounds(0, 180, 65, 20);
        labelQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldQuantity.setBounds(70, 180, 100, 25);

        labelPrice.setBounds(5, 210, 60, 20);
        labelPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldPrice.setBounds(70, 210, 100, 25);

        labelTargetId.setBounds(175, 120, 60, 20);
        labelTargetId.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldTargetId.setBounds(240, 120, 100, 25);

        buttonAddProduct.setBackground(new Color(169, 225, 144));
        buttonAddProduct.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonAddProduct.setBounds(41, 380, 180, 77);

        buttonRemoveProduct.setBackground(new Color(205, 199, 118));
        buttonRemoveProduct.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonRemoveProduct.setBounds(231, 380, 180, 77);

        buttonUpdate.setBackground(new Color(165, 170, 82));
        buttonUpdate.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonUpdate.setBounds(432, 380, 180, 77);

        buttonClear.setBackground(new Color(118, 117, 34));
        buttonClear.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonClear.setBounds(622, 380, 180, 77);

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

    public JTextField getTextFieldName() {
        return textFieldName;
    }

    public void setTextFieldName(JTextField textFieldName) {
        this.textFieldName = textFieldName;
    }

    public JTextField getTextFieldQuantity() {
        return textFieldQuantity;
    }

    public void setTextFieldQuantity(JTextField textFieldQuantity) {
        this.textFieldQuantity = textFieldQuantity;
    }

    public JTextField getTextFieldPrice() {
        return textFieldPrice;
    }

    public void setTextFieldPrice(JTextField textFieldPrice) {
        this.textFieldPrice = textFieldPrice;
    }

    public JLabel getLabelName() {
        return labelName;
    }

    public void setLabelName(JLabel labelName) {
        this.labelName = labelName;
    }

    public JLabel getLabelQuantity() {
        return labelQuantity;
    }

    public void setLabelQuantity(JLabel labelQuantity) {
        this.labelQuantity = labelQuantity;
    }

    public JLabel getLabelPrice() {
        return labelPrice;
    }

    public void setLabelPrice(JLabel labelPrice) {
        this.labelPrice = labelPrice;
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

    public JButton getButtonAddProduct() {
        return buttonAddProduct;
    }

    public void setButtonAddProduct(JButton buttonAddProduct) {
        this.buttonAddProduct = buttonAddProduct;
    }

    public JButton getButtonRemoveProduct() {
        return buttonRemoveProduct;
    }

    public void setButtonRemoveProduct(JButton buttonRemoveProduct) {
        this.buttonRemoveProduct = buttonRemoveProduct;
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

    public void addAddListener(ActionListener actionListener) {
        buttonAddProduct.addActionListener(actionListener);
    }

    public void addRemoveListener(ActionListener actionListener) {
        buttonRemoveProduct.addActionListener(actionListener);
    }

    public void addUpdateListener(ActionListener actionListener) {
        buttonUpdate.addActionListener(actionListener);
    }

    public void addClearListener(ActionListener actionListener) {
        buttonClear.addActionListener(actionListener);
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
        ProductBLL productBll = new ProductBLL();
        tbl_Product targetProduct = productBll.findProductById(Integer.parseInt(textFieldTargetId.getText()));
        textFieldId.setText(String.valueOf(targetProduct.getProd_id()));
        textFieldName.setText(targetProduct.getProd_name());
        textFieldQuantity.setText(String.valueOf(targetProduct.getProd_quantity()));
        textFieldPrice.setText(String.valueOf(targetProduct.getProd_price()));
    }

    /**
     * <p>Sets all textBox fields of the input to null
     * </p>
     */
    public void clearAllFields() {
        textFieldTargetId.setText(null);
        textFieldName.setText(null);
        textFieldQuantity.setText(null);
        textFieldPrice.setText(null);
        textFieldId.setText(null);
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
