package com.example.blablaplane.object.trip;

public class Airport {
    private String name;
    private double latitude;
    private double longitude;
    private String city;
    private String country;

    public Airport() {
        this("", 0, 0, "", "");
    }

    public Airport(String name, double latitude, double longitude, String city, String country) {
        this.city = city;
        this.country = country;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
