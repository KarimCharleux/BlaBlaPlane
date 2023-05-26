package com.example.blablaplane.object.aircraft;

import com.example.blablaplane.R;

import java.util.ArrayList;

public class AircraftArray extends ArrayList<Aircraft> {
    private static AircraftArray instance;

    private AircraftArray() {
        super();
        add(new Aircraft(0, "Piper Seneca", 4, R.drawable.piper_seneca));
        add(new Aircraft(1,"JET RANGER II",2,R.drawable.jet_ranger));
        add(new Aircraft(2,"Diamond DA42",6,R.drawable.diamond_da42));
        add(new Aircraft(3,"Cirrus Vision Jet",4, R.drawable.cirrus_sf50));
        add(new Aircraft(4,"Cessna 182",5,R.drawable.cessna182));
    }

    /**
     * Get the instance of the AircraftArray
     *
     * @return the instance of the AircraftArray
     */
    public static AircraftArray getInstance() {
        if (instance == null) {
            instance = new AircraftArray();
        }
        return instance;
    }


}
