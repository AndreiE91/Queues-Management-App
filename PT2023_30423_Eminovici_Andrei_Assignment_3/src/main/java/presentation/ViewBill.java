package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The view class for the Bill graphical interface
 * @author Andrei Eminovici
 */
public class ViewBill extends JFrame {
    JLabel labelTitle = new JLabel("Bills");
    DefaultTableModel model;

    /** Create the JTable, empty for now but will be externally updated to contain data via the setModel method */
    JTable table = new JTable();

    /** Create a JScrollPane to contain the table */
    JScrollPane scrollPane = new JScrollPane(table);

    public ViewBill() {
        this.setTitle("Bills");
        this.setBounds(25, 25, 1500, 750);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);

        this.getContentPane().setLayout(null);
        this.getContentPane().add(labelTitle);
        this.getContentPane().add(scrollPane);
        this.getContentPane().setBackground(new Color(219, 244, 173));

        scrollPane.setBounds(10, 110, 1470, 570);

        labelTitle.setBounds(650, 0, 200, 133);
        labelTitle.setFont(new Font("Vivaldi", Font.BOLD, 50));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        this.setVisible(false);
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

    public void refresh() {

    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
        refresh();
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "",JOptionPane.INFORMATION_MESSAGE);
        refresh();
    }
}
