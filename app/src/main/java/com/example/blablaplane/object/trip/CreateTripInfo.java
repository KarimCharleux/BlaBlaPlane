package com.example.blablaplane.object.trip;

import java.util.Date;

public class CreateTripInfo {
    public static String departure;
    public static String destination;
    public static Date date;
    public static int nbPassenger = 1;
    public static Float price;
    public static Integer aircraftId;

    public static void reset() {
        departure = null;
        destination = null;
        date = null;
        nbPassenger = 1;
        price = null;
        aircraftId = null;
    }
}
