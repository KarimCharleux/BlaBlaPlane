package com.example.blablaplane.object.user;

import com.example.blablaplane.object.vehicule.Plane;

import java.util.List;

public class User {
    private final int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private float rating;
    private List<Plane> planes;

    public User(int id, String name, String surname, String email, String password, String phone, float rating, List<Plane> planes) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.rating = rating;
        this.planes = planes;
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

    public List<Plane> getPlanes() {
        return this.planes;
    }
}
