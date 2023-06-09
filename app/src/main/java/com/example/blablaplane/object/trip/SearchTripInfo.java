package com.example.blablaplane.object.trip;

import java.util.Date;

public class SearchTripInfo {

    public static String departure = "Départ";
    public static String destination = "Destination";
    public static Date date = null;
    public static int nbPassenger = 1;

    public static void reset() {
        departure = "Départ";
        destination = "Destination";
        date = null;
        nbPassenger = 1;
    }

    public static void resetDeparture() {
        departure = "Départ";
    }

    public static void resetDestination() {
        destination = "Destination";
    }

}

