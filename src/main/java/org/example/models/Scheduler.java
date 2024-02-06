package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class Scheduler implements Runnable{
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    private SimulationManager simulationManager;
    private Object sharedObject;
    public Scheduler(int maxNoServers, int maxTasksPerServer, Strategy strategy, SimulationManager simulationManager, Object sharedObject) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.strategy = strategy;
        this.simulationManager = simulationManager;
        this.sharedObject = sharedObject;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public int getMaxNoServers() {
        return maxNoServers;
    }

    public void setMaxNoServers(int maxNoServers) {
        this.maxNoServers = maxNoServers;
    }

    public int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public void setMaxTasksPerServer(int maxTasksPerServer) {
        this.maxTasksPerServer = maxTasksPerServer;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void start() {
        Thread worker = new Thread(this);
        worker.start();
    }

    public void distributeClient(Client client) {
        //Distribute clients to servers
        try {
            strategy.addClient(servers, client);
            simulationManager.getGeneratedClients().remove(client);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        //Create server threads
        servers = new ArrayList<Server>();
        for(int i = 0; i < maxNoServers; ++i) {
            servers.add(new Server(maxTasksPerServer, sharedObject, simulationManager));
        }
        //Launch server threads
        for(Server server : servers) {
            server.start();
        }
    }
}
