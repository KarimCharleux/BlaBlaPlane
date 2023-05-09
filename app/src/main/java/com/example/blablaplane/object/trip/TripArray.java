package com.example.blablaplane.object.trip;

import java.util.ArrayList;

public class TripArray extends ArrayList<Trip> {
    private static TripArray instance;

    private TripArray() {
        super();
        add(new Trip(0, "2021-05-01 10:00:00", "2021-05-01 12:00:00", "Nice", "Paris", 400.5F, 3));
        add(new Trip(1, "2021-05-01 10:00:00", "2021-05-01 12:00:00", "Roma", "Milano", 100F, 1));
        add(new Trip(2, "2021-05-01 10:00:00", "2021-05-01 12:00:00", "Milano", "Roma", 250F, 2));
        add(new Trip(3, "2021-05-01 10:00:00", "2021-05-01 12:00:00", "Paris", "Milano", 200F, 2));
        add(new Trip(4, "2021-05-01 10:00:00", "2021-05-01 12:00:00", "Paris", "Roma", 110F, 1));
        add(new Trip(5, "2021-05-01 10:00:00", "2021-05-01 12:00:00", "Roma", "Paris", 150F, 1));
    }

    /**
     * Get the instance of the TripArray
     *
     * @return the instance of the TripArray
     */
    public static TripArray getInstance() {
        if (instance == null) {
            instance = new TripArray();
        }
        return instance;
    }

    /**
     * Get a trip by its id
     *
     * @param id the id of the trip
     * @return the trip with the given id
     */
    public Trip getTripById(int id) {
        for (Trip trip : this) {
            if (trip.getId() == id) {
                return trip;
            }
        }
        return null;
    }

    /**
     * Get all the trips of a pilot
     *
     * @param pilotId the id of the pilot
     * @return a TripArray containing all the trips of the pilot
     */
    public TripArray getTripsByPilotId(int pilotId) {
        TripArray trips = new TripArray();
        for (Trip trip : this) {
            if (trip.getPilotId() == pilotId) {
                trips.add(trip);
            }
        }
        return trips;
    }

    /**
     * Get all the trips of a pilot
     *
     * @param departure the departure city
     * @param arrival   the arrival city
     * @param date      the departure date
     * @return a TripArray containing all the trips of the pilot
     */
    public TripArray getTripsByDepartureAndArrival(String departure, String arrival, String date) {
        TripArray trips = new TripArray();
        for (Trip trip : this) {
            if (trip.getDeparture().equals(departure) && trip.getArrival().equals(arrival) && trip.getDepartureDate().equals(date)) {
                trips.add(trip);
            }
        }
        return trips;
    }
}
