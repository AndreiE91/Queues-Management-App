package org.example.models;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addClient(List<Server> servers, Client client) throws InterruptedException {
        Server targetServer = null;
        int minQueueLengthFound = Integer.MAX_VALUE;
        for(Server server : servers) {
            if(server.getClients().size() < minQueueLengthFound) {
                minQueueLengthFound = server.getClients().size();
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
