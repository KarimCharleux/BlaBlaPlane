package com.example.blablaplane.object.user;

import com.example.blablaplane.object.aircraft.Aircraft;

import java.util.List;

public class User {

    private final List<Aircraft> aircraftList;
    private final int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private float rating;

    public User(int id, String name, String surname, String email, String password, String phone, float rating, List<Aircraft> aircraftList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.rating = rating;
        this.aircraftList = aircraftList;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getRating() {
        return "★ " + this.rating + "/5";
    }

    public List<Aircraft> getAircraftList() {
        return this.aircraftList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
