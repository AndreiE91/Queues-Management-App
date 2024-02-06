package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The main view class of the application's graphical user interface
 * @author Andrei Eminovici
 */
public class ViewMain extends JFrame {
    JLabel labelTitle = new JLabel("Warehouse Management Application");
    JButton buttonClients = new JButton("Clients");
    JButton buttonProducts = new JButton("Products");
    JButton buttonOrders = new JButton("Orders");
    JButton buttonBills = new JButton("Bills");
    JLabel labelBackground = new JLabel();
    ImageIcon bg_icon = new ImageIcon("ViewMain_BG.jpg");



    public ViewMain() {
        this.setTitle("Warehouse Management");
        this.setBounds(100, 100, 900, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.getContentPane().setLayout(null);
        this.getContentPane().add(labelBackground);
        this.getContentPane().add(labelTitle);
        this.getContentPane().add(buttonClients);
        this.getContentPane().add(buttonProducts);
        this.getContentPane().add(buttonOrders);
        this.getContentPane().add(buttonBills);
        this.getContentPane().setBackground(new Color(219, 244, 173));

        labelBackground.setIcon(bg_icon);
        labelBackground.setBounds(65, 110, 720, 200);

        labelTitle.setBounds(35, 10, 747, 133);
        labelTitle.setFont(new Font("Vivaldi", Font.BOLD, 50));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        buttonClients.setBackground(new Color(169, 225, 144));
        buttonClients.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonClients.setBounds(41, 327, 180, 77);

        buttonProducts.setBackground(new Color(205, 199, 118));
        buttonProducts.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonProducts.setBounds(231, 327, 180, 77);

        buttonOrders.setBackground(new Color(165, 170, 82));
        buttonOrders.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonOrders.setBounds(432, 327, 180, 77);

        buttonBills.setBackground(new Color(118, 117, 34));
        buttonBills.setFont(new Font("Tahoma", Font.BOLD, 25));
        buttonBills.setBounds(622, 327, 180, 77);

        this.setVisible(true);
    }

    public JLabel getLabelTitle() {
        return labelTitle;
    }

    public void setLabelTitle(JLabel labelTitle) {
        this.labelTitle = labelTitle;
    }

    public JButton getButtonClients() {
        return buttonClients;
    }

    public void setButtonClients(JButton buttonClients) {
        this.buttonClients = buttonClients;
    }

    public JButton getButtonProducts() {
        return buttonProducts;
    }

    public void setButtonProducts(JButton buttonProducts) {
        this.buttonProducts = buttonProducts;
    }

    public JButton getButtonOrders() {
        return buttonOrders;
    }

    public void setButtonOrders(JButton buttonOrders) {
        this.buttonOrders = buttonOrders;
    }

    public JButton getButtonBills() {
        return buttonBills;
    }

    public void setButtonBills(JButton buttonBills) {
        this.buttonBills = buttonBills;
    }

    public void addClientListener(ActionListener actionListener) {
        buttonClients.addActionListener(actionListener);
    }

    public void addProductListener(ActionListener actionListener) {
        buttonProducts.addActionListener(actionListener);
    }

    public void addOrderListener(ActionListener actionListener) {
        buttonOrders.addActionListener(actionListener);
    }

    public void addBillListener(ActionListener actionListener) {
        buttonBills.addActionListener(actionListener);
    }

    public void refresh() {

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
