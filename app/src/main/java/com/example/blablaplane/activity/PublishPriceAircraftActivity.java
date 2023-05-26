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
        AircraftArray vehiculeArray = AircraftArray.getInstance();

        // Create the adapter
        AircraftAdapter vehiculeAdapter = new AircraftAdapter(getApplicationContext(), vehiculeArray);

        // Retrieve the list of trips
        ListView vehiculeList = findViewById(R.id.LV_aircraft);

        // Set the adapter
        vehiculeList.setAdapter(vehiculeAdapter);

        // Set the listener
        vehiculeAdapter.setListener(this);
    }

    @Override
    public void onAircraftClick(int vehiculeId) {
        System.out.println("GG CA FONCTIONNE");
    }
}