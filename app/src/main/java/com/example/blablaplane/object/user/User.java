package com.example.blablaplane.object.user;

import com.example.blablaplane.object.aircraft.Aircraft;
import com.example.blablaplane.object.trip.Trip;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Aircraft> aircraftList;
    private List<Trip> myTripList;
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private float rating;

    public User() {
        // Constructor for Firebase
    }

    public User(String name, String surname, String email, String password, String phone, float rating) {
        this(name, surname, email, password, phone, rating, new ArrayList<>());
    }

    public User(String name, String surname, String email, String password, String phone, float rating, ArrayList<Aircraft> aircraftList) {
        this(name, surname, email, password, phone, rating, aircraftList, new ArrayList<>());
    }

    public User(String name, String surname, String email, String password, String phone, float rating, ArrayList<Aircraft> aircraftList, ArrayList<Trip> myTripList) {
        this.id = generateUserId(email);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.rating = rating;
        this.aircraftList = aircraftList;
        this.myTripList = myTripList;
    }

    public static String generateUserId(String email) {
        return email.replace("@", "").replace(".", "");
    }

    public List<Trip> getMyTripList() {
        return myTripList;
    }

    public boolean addTrip(Trip trip) {
        return this.myTripList.add(trip);
    }

    public boolean removeTrip(Trip trip) {
        return this.myTripList.remove(trip);
    }

    public void addAircraft(Aircraft aircraft) {
        this.aircraftList.add(aircraft);
    }

    public void removeAircraft(Aircraft aircraft) {
        this.aircraftList.remove(aircraft);
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
