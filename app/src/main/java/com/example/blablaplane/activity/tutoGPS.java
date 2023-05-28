package com.example.blablaplane.activity;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.blablaplane.R;

public class tutoGPS extends FragmentActivity implements GPSActivityInterface {
    private GPSFragment gpsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuto_gps);
        gpsFragment = (GPSFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_gps);

        if (gpsFragment == null) {
            gpsFragment = new GPSFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_gps, gpsFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }

    }




    @Override
    public void moveCamera() {

    }
}
