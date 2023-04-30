package com.example.blablaplane.object.vehicule;

public class Plane {
    private int id;
    private String model;
    private int capacity;
    private int pilotId;
    boolean isHelicopter;

    public Plane(int id, String model, int capacity, int pilotId, boolean isHelicopter) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
        this.pilotId = pilotId;
        this.isHelicopter = isHelicopter;
    }

    public int getId() {
        return this.id;
    }

    public String getModel() {
        return this.model;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getPilotId() {
        return this.pilotId;
    }

    public boolean isHelicopter() {
        return this.isHelicopter;
    }
}
