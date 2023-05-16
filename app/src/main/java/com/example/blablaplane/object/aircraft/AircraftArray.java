package com.example.blablaplane.object.aircraft;

import com.example.blablaplane.R;

import java.util.ArrayList;

public class AircraftArray extends ArrayList<Aircraft> {
    private static AircraftArray instance;

    private AircraftArray() {
        super();
        add(new Aircraft(0, "A380", 500, R.drawable.a380));
        add(new Aircraft(1,"RAFALE",1,R.drawable.rafale_fr));
        add(new Aircraft(2,"FALCON 9",6,R.drawable.falcon9));

        add(new Aircraft(3,"TIGRE",4, R.drawable.tigrefr));
        add(new Aircraft(4,"TRANSPORT",15,R.drawable.helicotransport));
    }

    /**
     * Get the instance of the TripArray
     *
     * @return the instance of the TripArray
     */
    public static AircraftArray getInstance() {
        if (instance == null) {
            instance = new AircraftArray();
        }
        return instance;
    }

    /**
     * Get a trip by its id
     *
     * @param id the id of the trip
     * @return the trip with the given id
     */
    public Aircraft getTripById(int id) {
        for (Aircraft aircraft : this) {
            if (aircraft.getId() == id) {
                return aircraft;
            }
        }
        return null;
    }

    /**
     * Get all the trips of a pilot
     *
     * @param aircraftId the id of the pilot
     * @return a TripArray containing all the trips of the pilot
     */
    public AircraftArray getTripsByPilotId(int aircraftId) {
        AircraftArray aircrafts = new AircraftArray();
        for (Aircraft aircraft : this) {
            if (aircraft.getId() == aircraftId) {
                aircrafts.add(aircraft);
            }
        }
        return aircrafts;
    }
}
