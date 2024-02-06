package org.example.models;

import java.util.concurrent.atomic.AtomicBoolean;

public class Client {
    private int id, arrivalTime, serviceTime;
    private int timeWaited;
    private AtomicBoolean servedFlag = new AtomicBoolean(false);

    public Client(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.timeWaited = 0;
    }

    public int getTimeWaited() {
        return timeWaited;
    }

    public void setTimeWaited(int timeWaited) {
        this.timeWaited = timeWaited;
    }

    public AtomicBoolean getServedFlag() {
        return servedFlag;
    }

    public void setServedFlag(AtomicBoolean servedFlag) {
        this.servedFlag = servedFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "(" + id + "," + arrivalTime + "," + serviceTime + ");";
    }
}
