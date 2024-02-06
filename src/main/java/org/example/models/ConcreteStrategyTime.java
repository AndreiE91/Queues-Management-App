package org.example.models;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addClient(List<Server> servers, Client client) throws InterruptedException {
        Server targetServer = null;
        int minWaitingPeriodFound = Integer.MAX_VALUE;
        for(Server server : servers) {
            if(server.getWaitingPeriod().get() < minWaitingPeriodFound) {
                minWaitingPeriodFound = server.getWaitingPeriod().get();
                targetServer = server;
            }
        }
        if(targetServer == null) {
            System.out.println("Error: No servers found!");
            return;
        }
        targetServer.addTask(client);
    }
}
