package org.example.controllers;

import org.example.models.*;
import org.example.views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private ViewSetup viewSetup;
    private ViewSimulation viewSimulation;
    private SimulationManager simulationManager;
    public MainController(ViewSetup viewSetup, ViewSimulation viewSimulation, SimulationManager simulationManager) {
        this.viewSetup = viewSetup;
        this.viewSimulation = viewSimulation;
        this.simulationManager = simulationManager;


        this.viewSetup.addSimulateListener(new SimulateListener());
        this.viewSimulation.addAbortListener(e -> {
                    simulationManager.abort();
            }
        );
    }

    class SimulateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(!simulationManager.getRunning().get()) {
                simulationManager.setNumberOfClients(Integer.parseInt(viewSetup.getTextAreaNumberClients().getText()));
                simulationManager.setNumberOfQueues(Integer.parseInt(viewSetup.getTextAreaNumberQueues().getText()));
                simulationManager.setTimeMaxSimulation(Integer.parseInt(viewSetup.getTextAreaSimulationInterval().getText()));
                simulationManager.setMinArrivalTime(Integer.parseInt(viewSetup.getTextAreaMinArrival().getText()));
                simulationManager.setMaxArrivalTime(Integer.parseInt(viewSetup.getTextAreaMaxArrival().getText()));
                simulationManager.setMinServiceTime(Integer.parseInt(viewSetup.getTextAreaMinService().getText()));
                simulationManager.setMaxServiceTime(Integer.parseInt(viewSetup.getTextAreaMaxService().getText()));
                simulationManager.setMaxTasksPerServer(Integer.parseInt(viewSetup.getTextAreaMaxTasksPerServer().getText()));
                simulationManager.setSelectionPolicy(viewSetup.getSelectionPolicyBox().getSelectedItem().equals("Min Queue") ? SelectionPolicy.SHORTEST_QUEUE : SelectionPolicy.SHORTEST_TIME);
//                simulationManager.setNumberOfClients(10);
//                simulationManager.setNumberOfQueues(3);
//                simulationManager.setTimeMaxSimulation(15);
//                simulationManager.setMinArrivalTime(1);
//                simulationManager.setMaxArrivalTime(10);
//                simulationManager.setMinServiceTime(2);
//                simulationManager.setMaxServiceTime(4);
//                simulationManager.setMaxTasksPerServer(1000);
//                simulationManager.setSelectionPolicy(SelectionPolicy.SHORTEST_QUEUE);
                Thread managerThread = new Thread(simulationManager);
                managerThread.start();
                viewSimulation.setVisible(true);
                } else {
                    viewSetup.showErrorMessage("Simulation still in process!");
                }
            }
            catch (Exception ex) {
                viewSetup.showErrorMessage("An uncategorized error has occured!");
            }
        }
    }
}
