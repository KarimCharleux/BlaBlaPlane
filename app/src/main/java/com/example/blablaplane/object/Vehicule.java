package com.example.blablaplane.object;

public class Vehicule {
    private String name;
    private int passengerCount;
    private int id;
    private int img;

    public Vehicule(int id, String name, int passengerCount,int img) {
        this.id=id;
        this.name=name;
        this.passengerCount=passengerCount;
        this.img=img;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }
}
