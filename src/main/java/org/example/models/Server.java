package org.example.models;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Server implements Runnable {
    private BlockingQueue<Client> clients;
    private Thread worker;
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicInteger waitingPeriod;
    private int peakHourLocal;
    private int maxWaitingClientsAtOnce;
    private final SimulationManager simulationManager;
    private final Object sharedObject;

    public Server(int maxTasksPerServer, Object sharedObject, SimulationManager simulationManager) {
        this.clients = new ArrayBlockingQueue<Client>(maxTasksPerServer);
        this.waitingPeriod = new AtomicInteger(0);
        this.sharedObject = sharedObject;
        this.simulationManager = simulationManager;
        this.peakHourLocal = 0;
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public void setClients(BlockingQueue<Client> clients) {
        this.clients = clients;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public void addTask(Client newClient) throws InterruptedException {
        if(clients.remainingCapacity() > 0) {
            clients.add(newClient);
            waitingPeriod.getAndAdd(newClient.getServiceTime());
        } else {
            sleep(1000); // Allow time for others to finish and free space in queue before trying again if queue is full
        }
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        running.set(false);
    }

    @Override
    public void run() {
        running.set(true);
        while(running.get()) {
            if(!clients.isEmpty()) {
                try {
                    // Queue's waiting time is the sum of all service times of clients waiting to be served
                    waitingPeriod.getAndSet(0);
                    for (Client client : clients) {
                        waitingPeriod.getAndAdd(client.getServiceTime());
                    }
                    Client nextClient = clients.peek();
                    assert nextClient != null;
                    nextClient.getServedFlag().set(true);
                    //Simulate work
                    while(nextClient.getServiceTime() > 0) {
                        synchronized (sharedObject) {
                            nextClient.setServiceTime(nextClient.getServiceTime() - 1);
                            if(nextClient.getServiceTime() <= 0) {
                                clients.take();
                            }
                            simulationManager.getTotalServiceTime().getAndAdd(1);
                            int waitingTimeThisTick = 0;
                            for (Client client : clients) { // Update clients waiting time
                                client.setTimeWaited(client.getTimeWaited() + 1);
                                waitingTimeThisTick += client.getTimeWaited();
                            }
                            simulationManager.getTotalWaitingTime().getAndAdd(waitingTimeThisTick);

                            int currentWaitingClients = clients.size();
                            if(currentWaitingClients > maxWaitingClientsAtOnce) {
                                maxWaitingClientsAtOnce = currentWaitingClients;
                                peakHourLocal = simulationManager.getSimulationTime().get();
                                if(peakHourLocal > simulationManager.getPeakHour().get()) {
                                    simulationManager.getPeakHour().set(peakHourLocal);
                                }
                            }
                            sharedObject.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
