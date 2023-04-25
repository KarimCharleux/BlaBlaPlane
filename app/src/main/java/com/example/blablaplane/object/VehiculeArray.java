package com.example.blablaplane.object;

import com.example.blablaplane.R;

import java.util.ArrayList;

public class VehiculeArray extends ArrayList<Vehicule> {
    private static VehiculeArray instance;

    private VehiculeArray() {
        super();
        add(new Vehicule(0, "A380", 500, R.drawable.a380));
        add(new Vehicule(1,"RAFALE",1,R.drawable.rafale_fr));
        add(new Vehicule(2,"FALCON 9",6,R.drawable.falcon9));

        add(new Vehicule(3,"TIGRE",4, R.drawable.tigrefr));
        add(new Vehicule(4,"TRANSPORT",15,R.drawable.helicotransport));
    }

    /**
     * Get the instance of the TripArray
     *
     * @return the instance of the TripArray
     */
    public static VehiculeArray getInstance() {
        if (instance == null) {
            instance = new VehiculeArray();
        }
        return instance;
    }

    /**
     * Get a trip by its id
     *
     * @param id the id of the trip
     * @return the trip with the given id
     */
    public Vehicule getTripById(int id) {
        for (Vehicule vehicule : this) {
            if (vehicule.getId() == id) {
                return vehicule;
            }
        }
        return null;
    }

    /**
     * Get all the trips of a pilot
     *
     * @param vehiculeId the id of the pilot
     * @return a TripArray containing all the trips of the pilot
     */
    public VehiculeArray getTripsByPilotId(int vehiculeId) {
        VehiculeArray vehicules = new VehiculeArray();
        for (Vehicule vehicule : this) {
            if (vehicule.getId() == vehiculeId) {
                vehicules.add(vehicule);
            }
        }
        return vehicules;
    }
}
