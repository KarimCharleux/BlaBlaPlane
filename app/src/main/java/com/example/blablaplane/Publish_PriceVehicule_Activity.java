package com.example.blablaplane;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.blablaplane.object.vehicule.VehiculeAdapter;
import com.example.blablaplane.object.vehicule.VehiculeAdapterListener;
import com.example.blablaplane.object.vehicule.VehiculeArray;

public class Publish_PriceVehicule_Activity extends AppCompatActivity implements VehiculeAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_publish_price_vehicule);

        // Get the list of trips
        VehiculeArray vehiculeArray = VehiculeArray.getInstance();

        // Create the adapter
        VehiculeAdapter vehiculeAdapter = new VehiculeAdapter(getApplicationContext(), vehiculeArray);

        // Retrieve the list of trips
        ListView vehiculeList = findViewById(R.id.LV_vehicule);

        // Set the adapter
        vehiculeList.setAdapter(vehiculeAdapter);

        // Set the listener
        vehiculeAdapter.setListener(this);
    }

    @Override
    public void onVehiculeClick(int vehiculeId) {
        System.out.println("GG CA FONCTIONNE");
    }
}