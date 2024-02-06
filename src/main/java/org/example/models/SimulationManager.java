package org.example.models;
import org.example.views.ViewSimulation;

import javax.swing.text.View;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class SimulationManager implements Runnable {

    private int numberOfClients;
    private int numberOfQueues;
    private int timeMaxSimulation;
    private int minArrivalTime, maxArrivalTime;
    private int minServiceTime, maxServiceTime;
    private int maxTasksPerServer;
    private SelectionPolicy selectionPolicy;
    private Scheduler scheduler;
    private BlockingQueue<Client> generatedClients;
    private AtomicInteger simulationTime;
    private AtomicInteger totalWaitingTime;
    private AtomicInteger totalServiceTime;
    private AtomicInteger peakHour;
    private String interfaceOutputText;
    private ViewSimulation viewSimulation;


    private AtomicBoolean running;
    private final Object sharedObject;


    public SimulationManager(int numberOfClients, int numberOfQueues, int timeMaxSimulation, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, int maxTasksPerServer, SelectionPolicy selectionPolicy, Object sharedObject, ViewSimulation viewSimulation) {
        this.numberOfClients = numberOfClients;
        this.numberOfQueues = numberOfQueues;
        this.timeMaxSimulation = timeMaxSimulation;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.maxTasksPerServer = maxTasksPerServer;
        this.selectionPolicy = selectionPolicy;
        this.generatedClients = new ArrayBlockingQueue<>(numberOfClients);
        this.sharedObject = sharedObject;
        this.simulationTime = new AtomicInteger(0);
        this.running = new AtomicBoolean(false);
        this.totalWaitingTime = new AtomicInteger(0);
        this.totalServiceTime = new AtomicInteger(0);
        this.peakHour = new AtomicInteger(0);
        this.viewSimulation = viewSimulation;
    }

    public SimulationManager(Object sharedObject, ViewSimulation viewSimulation) {
        this.sharedObject = sharedObject;
        this.simulationTime = new AtomicInteger(0);
        this.running = new AtomicBoolean(false);
        this.totalWaitingTime = new AtomicInteger(0);
        this.totalServiceTime = new AtomicInteger(0);
        this.peakHour = new AtomicInteger(0);
        this.viewSimulation = viewSimulation;
    }

    public String getInterfaceOutputText() {
        return interfaceOutputText;
    }

    public void setInterfaceOutputText(String interfaceOutputText) {
        this.interfaceOutputText = interfaceOutputText;
    }

    public AtomicInteger getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public void setTotalWaitingTime(AtomicInteger totalWaitingTime) {
        this.totalWaitingTime = totalWaitingTime;
    }

    public AtomicInteger getTotalServiceTime() {
        return totalServiceTime;
    }

    public void setTotalServiceTime(AtomicInteger totalServiceTime) {
        this.totalServiceTime = totalServiceTime;
    }

    public AtomicInteger getPeakHour() {
        return peakHour;
    }

    public void setPeakHour(AtomicInteger peakHour) {
        this.peakHour = peakHour;
    }

    public Object getSharedObject() {
        return sharedObject;
    }

    public AtomicInteger getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(AtomicInteger simulationTime) {
        this.simulationTime = simulationTime;
    }

    public AtomicBoolean getRunning() {
        return running;
    }

    public void setRunning(AtomicBoolean running) {
        this.running = running;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public int getNumberOfQueues() {
        return numberOfQueues;
    }

    public void setNumberOfQueues(int numberOfQueues) {
        this.numberOfQueues = numberOfQueues;
    }

    public int getTimeMaxSimulation() {
        return timeMaxSimulation;
    }

    public void setTimeMaxSimulation(int timeMaxSimulation) {
        this.timeMaxSimulation = timeMaxSimulation;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public void setMinServiceTime(int minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    public int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public void setMaxTasksPerServer(int maxTasksPerServer) {
        this.maxTasksPerServer = maxTasksPerServer;
    }

    public SelectionPolicy getSelectionPolicy() {
        return selectionPolicy;
    }

    public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public BlockingQueue<Client> getGeneratedClients() {
        return generatedClients;
    }

    public void setGeneratedClients(BlockingQueue<Client> generatedClients) {
        this.generatedClients = generatedClients;
    }

    public void abort() {
        running.set(false);
        this.simulationTime.set(timeMaxSimulation);
        //Stop threads when done
        for (Server server : scheduler.getServers()) {
            server.stop();
        }
        generatedClients.clear();
        simulationTime.set(0);
        this.totalWaitingTime = new AtomicInteger(0);
        this.totalServiceTime = new AtomicInteger(0);
        this.peakHour = new AtomicInteger(0);
    }

    @Override
    public void run() {
        running.set(true);
        //Allocate memory for clients in synchronized data structure
        this.generatedClients = new ArrayBlockingQueue<>(numberOfClients);
        //Generate the clients
        Random random = new Random();
        for (int i = 0; i < numberOfClients; ++i) {
            generatedClients.add(new Client(i + 1, random.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime, random.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime));
        }

        //Instantiate writer
        String fileName = "output_logs.txt"; // set the name of the output file
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));

            //Instantiate scheduler
            if (selectionPolicy == SelectionPolicy.SHORTEST_TIME) {
                this.scheduler = new Scheduler(numberOfQueues, maxTasksPerServer, new ConcreteStrategyTime(), this, this.sharedObject);
            } else {
                this.scheduler = new Scheduler(numberOfQueues, maxTasksPerServer, new ConcreteStrategyQueue(), this, this.sharedObject);
            }

            //Launch scheduler
            scheduler.start();
            try {
                sleep(200); // Allow some time for servers to be initialized
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            while (running.get() && simulationTime.get() < timeMaxSimulation) {
                for (Client client : generatedClients) {
                    //System.out.println("Client arrival time: " + client.getArrivalTime() + " sim time: " + simulationTime.get());
                    if (client.getArrivalTime() <= simulationTime.get()) {
                        scheduler.distributeClient(client);
                    }
                }
                writer.println("Time: " + simulationTime);
                writer.println("Waiting clients: ");
                interfaceOutputText += ("Time: " + simulationTime + "\n");
                interfaceOutputText += ("Waiting clients: " + "\n");
                System.out.println("Time: " + simulationTime);
                System.out.print("Waiting clients: ");
                for (Client client : generatedClients) {
                    if (!client.getServedFlag().get()) {
                        writer.print(client.toString());
                        interfaceOutputText += client.toString();
                        System.out.print(client.toString());
                    } else {
                        generatedClients.remove(client);
                    }
                }
                writer.print("\n");
                interfaceOutputText += "\n";
                System.out.print("\n");

                //Print queue information
                for (int i = 0; i < scheduler.getServers().size(); ++i) {
                    writer.print("Queue " + (i + 1) + ": " + (scheduler.getServers().get(i).getClients().isEmpty() ? "closed\n" : ""));
                    interfaceOutputText += ("Queue " + (i + 1) + ": " + (scheduler.getServers().get(i).getClients().isEmpty() ? "closed\n" : ""));
                    System.out.print("Queue " + (i + 1) + ": " + (scheduler.getServers().get(i).getClients().isEmpty() ? "closed\n" : ""));
                    if (!scheduler.getServers().get(i).getClients().isEmpty()) {
                        for (Client client : scheduler.getServers().get(i).getClients()) {
                            writer.print(client.toString());
                            interfaceOutputText += client.toString();
                            System.out.print(client.toString());
                        }
                        writer.print("\n");
                        interfaceOutputText += "\n";
                        System.out.print("\n");
                    }
                }

                writer.print("\n");
                interfaceOutputText += "\n";
                System.out.print("\n");
                try {
                    synchronized (sharedObject) {
                        sharedObject.notifyAll();
                    }
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                viewSimulation.getTextArea().setText(interfaceOutputText);
                simulationTime.getAndAdd(1);
            }
            float averageWaitingTime = totalWaitingTime.get() / ((float) numberOfQueues * (float) timeMaxSimulation);
            writer.println("Average waiting time: " + averageWaitingTime);
            interfaceOutputText += "Average waiting time: " + averageWaitingTime + "\n";
            System.out.println("Average waiting time: " + averageWaitingTime);

            float averageServiceTime = totalServiceTime.get() / (float) numberOfClients;
            writer.println("Average service time: " + averageServiceTime);
            interfaceOutputText += "Average service time: " + averageServiceTime + "\n";
            System.out.println("Average service time: " + averageServiceTime);

            writer.println("Peak hour: " + peakHour.get());
            interfaceOutputText += "Peak hour: " + peakHour.get() + "\n";
            System.out.println("Peak hour: " + peakHour.get());
            viewSimulation.getTextArea().setText(interfaceOutputText);
            writer.close();
            abort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
