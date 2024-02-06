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

public class ViewSetup extends JFrame {
    private JLabel labelTitle = new JLabel("Setup");
    private JLabel labelNumberClients = new JLabel("Number of clients:");
    private JTextField textAreaNumberClients = new JTextField();
    private JLabel labelNumberQueues = new JLabel("Number of queues");
    private JTextField textAreaNumberQueues = new JTextField();
    private JTextField textAreaMaxArrival = new JTextField();
    private JLabel labelSimulationInterval = new JLabel("Simulation interval:");
    private JLabel labelMinArrival = new JLabel("Min arrival time:");
    private JTextField textAreaSimulationInterval = new JTextField();
    private JTextField textAreaMinArrival = new JTextField();
    private JLabel labelMaxArrival = new JLabel("Max arrival time:");
    private JLabel labelMinService = new JLabel("Min service time:");
    private JTextField textAreaMinService = new JTextField();
    private JLabel labelMaxService = new JLabel("Max service time:");

    private JLabel labelMaxTasksPerServer = new JLabel("Max tasks per server:");
    private JTextField textAreaMaxTasksPerServer = new JTextField();
    private JTextField textAreaMaxService = new JTextField();
    private JButton buttonSimulate = new JButton("SIMULATE");

    private String[] policies = {"Min Queue", "Min Time"};
    private JComboBox<String> selectionPolicyBox = new JComboBox<String>(policies);


    public ViewSetup() {
        this.setTitle("Queue Setup");
        this.setBounds(100, 100, 900, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.getContentPane().setLayout(null);
        this.getContentPane().add(labelTitle);
        this.getContentPane().add(selectionPolicyBox);
        this.getContentPane().add(labelNumberClients);
        this.getContentPane().add(textAreaNumberClients);
        this.getContentPane().add(labelNumberQueues);
        this.getContentPane().add(textAreaNumberQueues);
        this.getContentPane().add(textAreaMaxArrival);
        this.getContentPane().add(labelSimulationInterval);
        this.getContentPane().add(labelMinArrival);
        this.getContentPane().add(textAreaSimulationInterval);
        this.getContentPane().add(textAreaMinArrival);
        this.getContentPane().add(labelMaxArrival);
        this.getContentPane().add(labelMinService);
        this.getContentPane().add(textAreaMinService);
        this.getContentPane().add(labelMaxService);
        this.getContentPane().add(textAreaMaxService);
        this.getContentPane().add(buttonSimulate);
        this.getContentPane().add(textAreaMaxTasksPerServer);
        this.getContentPane().add(labelMaxTasksPerServer);

        labelTitle.setBounds(0, 36, 222, 51);
        labelTitle.setFont(new Font("Vivaldi", Font.BOLD, 50));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        selectionPolicyBox.setBounds(20, 282, 195, 56);

        labelNumberClients.setBounds(218, 24, 195, 51);
        labelNumberClients.setHorizontalAlignment(SwingConstants.CENTER);
        labelNumberClients.setFont(new Font("Tahoma", Font.BOLD, 20));

        labelMaxTasksPerServer.setBounds(536, 81, 195, 51);
        labelMaxTasksPerServer.setHorizontalAlignment(SwingConstants.CENTER);
        labelMaxTasksPerServer.setFont(new Font("Tahoma", Font.BOLD, 14));

        textAreaMaxTasksPerServer.setBounds(741, 81, 103, 51);

        textAreaNumberClients.setBounds(423, 24, 103, 51);

        labelNumberQueues.setBounds(218, 80, 195, 56);
        labelNumberQueues.setHorizontalAlignment(SwingConstants.CENTER);
        labelNumberQueues.setFont(new Font("Tahoma", Font.BOLD, 20));

        textAreaNumberQueues.setBounds(423, 80, 103, 56);

        textAreaMaxArrival.setBounds(225, 212, 103, 56);

        labelSimulationInterval.setHorizontalAlignment(SwingConstants.CENTER);
        labelSimulationInterval.setFont(new Font("Tahoma", Font.BOLD, 17));
        labelSimulationInterval.setBounds(536, 24, 195, 51);

        labelMinArrival.setHorizontalAlignment(SwingConstants.CENTER);
        labelMinArrival.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelMinArrival.setBounds(20, 146, 195, 56);

        textAreaSimulationInterval.setBounds(741, 24, 103, 51);

        textAreaMinArrival.setBounds(225, 146, 103, 56);

        labelMaxArrival.setHorizontalAlignment(SwingConstants.CENTER);
        labelMaxArrival.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelMaxArrival.setBounds(20, 212, 195, 56);

        labelMinService.setHorizontalAlignment(SwingConstants.CENTER);
        labelMinService.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelMinService.setBounds(364, 146, 195, 56);

        textAreaMinService.setBounds(569, 146, 103, 56);

        labelMaxService.setHorizontalAlignment(SwingConstants.CENTER);
        labelMaxService.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelMaxService.setBounds(364, 212, 195, 56);

        textAreaMaxService.setBounds(569, 212, 103, 56);

        buttonSimulate.setFont(new Font("Tahoma", Font.BOLD, 26));
        buttonSimulate.setBounds(280, 334, 269, 70);

        this.setVisible(true);
    }

    public JLabel getLabelMaxTasksPerServer() {
        return labelMaxTasksPerServer;
    }

    public String[] getPolicies() {
        return policies;
    }

    public void setPolicies(String[] policies) {
        this.policies = policies;
    }

    public JComboBox<String> getSelectionPolicyBox() {
        return selectionPolicyBox;
    }

    public void setSelectionPolicyBox(JComboBox<String> selectionPolicyBox) {
        this.selectionPolicyBox = selectionPolicyBox;
    }

    public void setLabelMaxTasksPerServer(JLabel labelMaxTasksPerServer) {
        this.labelMaxTasksPerServer = labelMaxTasksPerServer;
    }

    public JTextField getTextAreaMaxTasksPerServer() {
        return textAreaMaxTasksPerServer;
    }

    public void setTextAreaMaxTasksPerServer(JTextField textAreaMaxTasksPerServer) {
        this.textAreaMaxTasksPerServer = textAreaMaxTasksPerServer;
    }

    public JLabel getLabelTitle() {
        return labelTitle;
    }

    public void setLabelTitle(JLabel labelTitle) {
        this.labelTitle = labelTitle;
    }

    public JLabel getLabelNumberClients() {
        return labelNumberClients;
    }

    public void setLabelNumberClients(JLabel labelNumberClients) {
        this.labelNumberClients = labelNumberClients;
    }

    public JTextField getTextAreaNumberClients() {
        return textAreaNumberClients;
    }

    public void setTextAreaNumberClients(JTextField textAreaNumberClients) {
        this.textAreaNumberClients = textAreaNumberClients;
    }

    public JLabel getLabelNumberQueues() {
        return labelNumberQueues;
    }

    public void setLabelNumberQueues(JLabel labelNumberQueues) {
        this.labelNumberQueues = labelNumberQueues;
    }

    public JTextField getTextAreaNumberQueues() {
        return textAreaNumberQueues;
    }

    public void setTextAreaNumberQueues(JTextField textAreaNumberQueues) {
        this.textAreaNumberQueues = textAreaNumberQueues;
    }

    public JTextField getTextAreaMaxArrival() {
        return textAreaMaxArrival;
    }

    public void setTextAreaMaxArrival(JTextField textAreaMaxArrival) {
        this.textAreaMaxArrival = textAreaMaxArrival;
    }

    public JLabel getLabelSimulationInterval() {
        return labelSimulationInterval;
    }

    public void setLabelSimulationInterval(JLabel labelSimulationInterval) {
        this.labelSimulationInterval = labelSimulationInterval;
    }

    public JLabel getLabelMinArrival() {
        return labelMinArrival;
    }

    public void setLabelMinArrival(JLabel labelMinArrival) {
        this.labelMinArrival = labelMinArrival;
    }

    public JTextField getTextAreaSimulationInterval() {
        return textAreaSimulationInterval;
    }

    public void setTextAreaSimulationInterval(JTextField textAreaSimulationInterval) {
        this.textAreaSimulationInterval = textAreaSimulationInterval;
    }

    public JTextField getTextAreaMinArrival() {
        return textAreaMinArrival;
    }

    public void setTextAreaMinArrival(JTextField textAreaMinArrival) {
        this.textAreaMinArrival = textAreaMinArrival;
    }

    public JLabel getLabelMaxArrival() {
        return labelMaxArrival;
    }

    public void setLabelMaxArrival(JLabel labelMaxArrival) {
        this.labelMaxArrival = labelMaxArrival;
    }

    public JLabel getLabelMinService() {
        return labelMinService;
    }

    public void setLabelMinService(JLabel labelMinService) {
        this.labelMinService = labelMinService;
    }

    public JTextField getTextAreaMinService() {
        return textAreaMinService;
    }

    public void setTextAreaMinService(JTextField textAreaMinService) {
        this.textAreaMinService = textAreaMinService;
    }

    public JLabel getLabelMaxService() {
        return labelMaxService;
    }

    public void setLabelMaxService(JLabel labelMaxService) {
        this.labelMaxService = labelMaxService;
    }

    public JTextField getTextAreaMaxService() {
        return textAreaMaxService;
    }

    public void setTextAreaMaxService(JTextField textAreaMaxService) {
        this.textAreaMaxService = textAreaMaxService;
    }

    public JButton getButtonSimulate() {
        return buttonSimulate;
    }

    public void setButtonSimulate(JButton buttonSimulate) {
        this.buttonSimulate = buttonSimulate;
    }

    public void addSimulateListener(ActionListener actionListener) {
        buttonSimulate.addActionListener(actionListener);
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
