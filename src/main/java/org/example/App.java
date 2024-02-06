package org.example;

import org.example.controllers.MainController;
import org.example.models.SimulationManager;
import org.example.views.ViewSetup;
import org.example.views.ViewSimulation;

public class App {

    public static void main(String[] args) {

        ViewSetup viewSetup = new ViewSetup();
        ViewSimulation viewSimulation = new ViewSimulation();
        Object sharedObject = new Object();

        SimulationManager simulationManager = new SimulationManager(sharedObject, viewSimulation);
        MainController mainController = new MainController(viewSetup, viewSimulation, simulationManager);
    }
}