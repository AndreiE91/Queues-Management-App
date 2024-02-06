package org.example.views;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.File;

public class ViewSimulation extends JFrame {
    private JLabel labelTitle = new JLabel("Simulation");
    private JButton buttonAbort = new JButton("ABORT");
    private JTextArea textArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(textArea);



    public ViewSimulation() {
        this.setTitle("Queue Simulation");
        this.setBounds(150, 150, 900, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);

        labelTitle.setBounds(300, 39, 222, 51);
        labelTitle.setFont(new Font("Vivaldi", Font.BOLD, 50));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.getContentPane().setLayout(null);
        this.getContentPane().add(labelTitle);

        buttonAbort.setFont(new Font("Tahoma", Font.BOLD, 26));
        buttonAbort.setBounds(635, 363, 184, 70);
        this.getContentPane().add(buttonAbort);

        scrollPane.setBounds(52, 100, 573, 289);
        //textArea.setBounds(52, 100, 573, 289);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        scrollPane.add(textArea);
        scrollPane.setViewportView(textArea);
        this.getContentPane().add(scrollPane);

        this.setVisible(false);
    }

    public JLabel getLabelTitle() {
        return labelTitle;
    }

    public void setLabelTitle(JLabel labelTitle) {
        this.labelTitle = labelTitle;
    }

    public JButton getButtonAbort() {
        return buttonAbort;
    }

    public void setButtonAbort(JButton buttonAbort) {
        this.buttonAbort = buttonAbort;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void addAbortListener(ActionListener actionListener) {
        buttonAbort.addActionListener(actionListener);
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
