package com.example.blablaplane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.blablaplane.R;
import com.example.blablaplane.object.aircraft.AircraftAdapter;
import com.example.blablaplane.object.aircraft.AircraftAdapterListener;
import com.example.blablaplane.object.aircraft.AircraftArray;

public class PublishPriceAircraftActivity extends AppCompatActivity implements AircraftAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_price_aircraft);

        // Get the list of trips
        AircraftArray aircraftArray = AircraftArray.getInstance();

        // Create the adapter
        AircraftAdapter aircraftAdapter = new AircraftAdapter(getApplicationContext(), aircraftArray);

        // Retrieve the list of trips
        ListView aircraftList = findViewById(R.id.LV_aircraft);

        // Set the adapter
        aircraftList.setAdapter(aircraftAdapter);

        // Set the listener
        aircraftAdapter.setListener(this);
    }

    @Override
    public void onAircraftClick(int aircraftId) {
        System.out.println("Aircraft clicked : " + aircraftId);
    }
}