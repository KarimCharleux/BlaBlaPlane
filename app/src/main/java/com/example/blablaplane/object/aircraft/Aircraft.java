package com.example.blablaplane.object.aircraft;

public class Aircraft {

    private final int id;
    private final String name;
    private int passengerCount;
    private int image;

    public Aircraft(int id, String name, int passengerCount, int image) {
        this.id = id;
        this.name = name;
        this.passengerCount = passengerCount;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return passengerCount;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }
}
