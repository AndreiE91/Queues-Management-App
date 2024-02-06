package presentation;

import bll.ClientBLL;
import model.tbl_Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The view class for the Client graphical interface
 * @author Andrei Eminovici
 */
public class ViewClient extends JFrame {
    JLabel labelTitle = new JLabel("Client Operations");
    JButton buttonAddClient = new JButton("Add");
    JButton buttonRemoveClient = new JButton("Remove");
    JButton buttonUpdate = new JButton("Update");
    JButton buttonClear = new JButton("Clear Fields");
    JButton buttonCopyFields = new JButton("Copy Fields");
    JTextField textFieldId = new JTextField();
    JTextField textFieldFirstName = new JTextField();
    JTextField textFieldLastName = new JTextField();
    JTextField textFieldAddress = new JTextField();
    JTextField textFieldEmail = new JTextField();
    JTextField textFieldAge = new JTextField();
    JTextField textFieldTargetId = new JTextField();
    JLabel labelId = new JLabel("ID:");
    JLabel labelFirstName = new JLabel("First Name:");
    JLabel labelLastName = new JLabel("Last Name:");
    JLabel labelAddress = new JLabel("Address:");
    JLabel labelEmail = new JLabel("Email:");
    JLabel labelAge = new JLabel("Age:");
    JLabel labelTargetId = new JLabel("Target ID:");
    DefaultTableModel model;

    /** Create the JTable, empty for now but will be externally updated to contain data via the setModel method */
    JTable table = new JTable();

    /** Create a JScrollPane to contain the table */
    JScrollPane scrollPane = new JScrollPane(table);
    JButton buttonGenerateRandom = new JButton("Generate");
    JLabel labelGenerateRandom = new JLabel("Generate Amount:");
    JTextField textFieldGenerateRandom = new JTextField();

    public ViewClient() {
        this.setTitle("Client Operations");
        this.setBounds(250, 100, 900, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);

        this.getContentPane().setLayout(null);
        this.getContentPane().add(labelTitle);
        this.getContentPane().add(buttonAddClient);
        this.getContentPane().add(buttonRemoveClient);
        this.getContentPane().add(buttonUpdate);
        this.getContentPane().add(buttonClear);
        this.getContentPane().setBackground(new Color(219, 244, 173));
        this.getContentPane().add(textFieldId);
        this.getContentPane().add(textFieldFirstName);
        this.getContentPane().add(textFieldLastName);
        this.getContentPane().add(textFieldAddress);
        this.getContentPane().add(textFieldEmail);
        this.getContentPane().add(textFieldAge);
        this.getContentPane().add(textFieldTargetId);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(labelId);
        this.getContentPane().add(labelFirstName);
        this.getContentPane().add(labelLastName);
        this.getContentPane().add(labelAddress);
        this.getContentPane().add(labelEmail);
        this.getContentPane().add(labelAge);
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
        labelTitle.setFont(new Font("Vivaldi", Font.BOLD, 50));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        labelId.setBounds(5, 120, 60, 20);
        labelId.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldId.setBounds(70, 120, 100, 25);

        labelFirstName.setBounds(0, 150, 65, 20);
        labelFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldFirstName.setBounds(70, 150, 100, 25);

        labelLastName.setBounds(0, 180, 65, 20);
        labelLastName.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldLastName.setBounds(70, 180, 100, 25);

        labelAddress.setBounds(5, 210, 60, 20);
        labelAddress.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldAddress.setBounds(70, 210, 100, 25);

        labelEmail.setBounds(5, 240, 60, 20);
        labelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldEmail.setBounds(70, 240, 100, 25);

        labelAge.setBounds(5, 270, 60, 20);
        labelAge.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldAge.setBounds(70, 270, 100, 25);

        labelTargetId.setBounds(175, 120, 60, 20);
        labelTargetId.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldTargetId.setBounds(240, 120, 100, 25);

        buttonAddClient.setBackground(new Color(169, 225, 144));
        buttonAddClient.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonAddClient.setBounds(41, 380, 180, 77);

        buttonRemoveClient.setBackground(new Color(205, 199, 118));
        buttonRemoveClient.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonRemoveClient.setBounds(231, 380, 180, 77);

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

    public JLabel getLabelFirstName() {
        return labelFirstName;
    }

    public void setLabelFirstName(JLabel labelFirstName) {
        this.labelFirstName = labelFirstName;
    }

    public JLabel getLabelLastName() {
        return labelLastName;
    }

    public void setLabelLastName(JLabel labelLastName) {
        this.labelLastName = labelLastName;
    }

    public JLabel getLabelAddress() {
        return labelAddress;
    }

    public void setLabelAddress(JLabel labelAddress) {
        this.labelAddress = labelAddress;
    }

    public JLabel getLabelEmail() {
        return labelEmail;
    }

    public void setLabelEmail(JLabel labelEmail) {
        this.labelEmail = labelEmail;
    }

    public JLabel getLabelAge() {
        return labelAge;
    }

    public void setLabelAge(JLabel labelAge) {
        this.labelAge = labelAge;
    }

    public JLabel getLabelTargetId() {
        return labelTargetId;
    }

    public void setLabelTargetId(JLabel labelTargetId) {
        this.labelTargetId = labelTargetId;
    }

    public JTextField getTextFieldId() {
        return textFieldId;
    }

    public void setTextFieldId(JTextField textFieldId) {
        this.textFieldId = textFieldId;
    }

    public JTextField getTextFieldFirstName() {
        return textFieldFirstName;
    }

    public void setTextFieldFirstName(JTextField textFieldFirstName) {
        this.textFieldFirstName = textFieldFirstName;
    }

    public JTextField getTextFieldLastName() {
        return textFieldLastName;
    }

    public void setTextFieldLastName(JTextField textFieldLastName) {
        this.textFieldLastName = textFieldLastName;
    }

    public JTextField getTextFieldAddress() {
        return textFieldAddress;
    }

    public void setTextFieldAddress(JTextField textFieldAddress) {
        this.textFieldAddress = textFieldAddress;
    }

    public JTextField getTextFieldEmail() {
        return textFieldEmail;
    }

    public void setTextFieldEmail(JTextField textFieldEmail) {
        this.textFieldEmail = textFieldEmail;
    }

    public JTextField getTextFieldAge() {
        return textFieldAge;
    }

    public void setTextFieldAge(JTextField textFieldAge) {
        this.textFieldAge = textFieldAge;
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

    public JButton getButtonAddClient() {
        return buttonAddClient;
    }

    public void setButtonAddClient(JButton buttonAddClient) {
        this.buttonAddClient = buttonAddClient;
    }

    public JButton getButtonRemoveClient() {
        return buttonRemoveClient;
    }

    public void setButtonRemoveClient(JButton buttonRemoveClient) {
        this.buttonRemoveClient = buttonRemoveClient;
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
        buttonAddClient.addActionListener(actionListener);
    }

    public void addRemoveListener(ActionListener actionListener) {
        buttonRemoveClient.addActionListener(actionListener);
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
     * <p>Sets all textBox fields of the input to null
     * </p>
     */
    public void clearAllFields() {
        textFieldTargetId.setText(null);
        textFieldEmail.setText(null);
        textFieldAddress.setText(null);
        textFieldLastName.setText(null);
        textFieldFirstName.setText(null);
        textFieldId.setText(null);
        textFieldAge.setText(null);
    }

    /**
     * <p>Fills all textBox inputs with the corresponding object given by target ID
     * </p>
     */
    public void copyFields() {
        ClientBLL clientBll = new ClientBLL();
        tbl_Client targetClient = clientBll.findClientById(Integer.parseInt(textFieldTargetId.getText()));
        textFieldId.setText(String.valueOf(targetClient.getClient_id()));
        textFieldFirstName.setText(targetClient.getFirst_name());
        textFieldLastName.setText(targetClient.getLast_name());
        textFieldAddress.setText(targetClient.getAddress());
        textFieldEmail.setText(targetClient.getEmail());
        textFieldAge.setText(String.valueOf(targetClient.getAge()));
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
