package com.example.blablaplane.object;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataBase {
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final String PHONE_REGEX = "^(\\+33|0033|0)[1-9][0-9]{8}$";
    public static final String DATABASE_LINK = "https://blablaplane-ad945-default-rtdb.europe-west1.firebasedatabase.app/";
    public static final String USERS = "Users";
    public static final String TRIPS = "Trips";
    private static final String AIRCRAFT = "Aircrafts";
    public static final FirebaseDatabase DATABASE = FirebaseDatabase.getInstance(DATABASE_LINK);
    public static final DatabaseReference USERS_REFERENCE = DATABASE.getReference(USERS);
    public static final DatabaseReference TRIPS_REFERENCE = DATABASE.getReference(TRIPS);
    public static final DatabaseReference AIRCRAFT_REFERENCE = DATABASE.getReference(AIRCRAFT);
}
