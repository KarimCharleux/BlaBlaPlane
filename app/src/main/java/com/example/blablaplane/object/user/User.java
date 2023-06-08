package com.example.blablaplane.object.user;

import android.graphics.Bitmap;

import com.example.blablaplane.object.aircraft.Aircraft;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final List<Aircraft> aircraftList;
    private final List<Integer> myTripList;
    private final String id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final String phone;
    private final float rating;
    private Bitmap picture;

    public User() {
        // Constructor for Firebase
        this("", "", "", "", "", 0);
    }

    public User(String name, String surname, String email, String password, String phone, float rating) {
        this(name, surname, email, password, phone, rating, new ArrayList<>());
    }

    public User(String name, String surname, String email, String password, String phone, float rating, ArrayList<Aircraft> aircraftList) {
        this(name, surname, email, password, phone, rating, aircraftList, new ArrayList<>(), null);
    }

    public User(String name, String surname, String email, String password, String phone, float rating, ArrayList<Aircraft> aircraftList, ArrayList<Integer> myTripList, Bitmap picture) {
        this.id = generateUserId(email);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.rating = rating;
        this.aircraftList = aircraftList;
        this.myTripList = myTripList;
        this.picture = picture;
    }

    public static String generateUserId(String email) {
        return email.replace("@", "").replace(".", "");
    }

    public List<Integer> getMyTripList() {
        return myTripList;
    }

    public Bitmap getPicture() {
        return this.picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public void addTrip(int tripId) {
        this.myTripList.add(tripId);
    }

    public void addAircraft(Aircraft aircraft) {
        this.aircraftList.add(aircraft);
    }

    public void removeAircraft(int aircraftId) {
        for (int index = 0; index < this.aircraftList.size(); index++) {
            if (this.aircraftList.get(index).getId() == aircraftId) {
                this.aircraftList.remove(index);
                break;
            }
        }
    }

    public List<Aircraft> getAircraftList() {
        return this.aircraftList;
    }

    public String getId() {
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

    public String getRatingString() {
        return "★ " + this.rating + "/5";
    }

    public float getRating() {
        return this.rating;
    }
}