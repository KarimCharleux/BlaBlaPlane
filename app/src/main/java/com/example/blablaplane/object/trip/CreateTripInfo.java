package com.example.blablaplane.object.trip;

import android.content.Context;

import java.util.Date;

public class CreateTripInfo {
    public static String departure;
    public static String destination;
    public static Date departureDate;
    public static Date destinationDate;
    public static int nbPassenger = 1;
    public static Float price;
    public static Integer aircraftId;
    public static String pilotId;

    public static void reset() {
        departure = null;
        destination = null;
        departureDate = null;
        destinationDate = null;
        nbPassenger = 1;
        price = null;
        aircraftId = null;
    }

    public static Trip createTheTrip() {
        City departure = City.getCityByName(CreateTripInfo.departure);
        City destination = City.getCityByName(CreateTripInfo.destination);
        // Destination date is one hour after departure date
        destinationDate = new Date(departureDate.getTime() + 3600000);
        return new Trip(departureDate, destinationDate, departure, destination, price, pilotId, aircraftId, nbPassenger);
    }
}
