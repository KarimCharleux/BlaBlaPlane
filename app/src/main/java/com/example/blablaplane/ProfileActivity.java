package com.example.blablaplane;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.blablaplane.object.vehicule.VehiculeAdapter;
import com.example.blablaplane.object.vehicule.VehiculeAdapterListener;
import com.example.blablaplane.object.vehicule.VehiculeArray;

public class ProfileActivity extends AppCompatActivity implements VehiculeAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Get the list of Message profils
        VehiculeArray vehiculeArray = VehiculeArray.getInstance();

        // Create the adapter
        VehiculeAdapter vehiculeAdapter = new VehiculeAdapter(getApplicationContext(), vehiculeArray);

        // Retrieve the list of Message profils
        ListView vehiculeList = findViewById(R.id.aeronef_list);

        // Set the adapter
        vehiculeList.setAdapter(vehiculeAdapter);

        // Set the listener
        vehiculeAdapter.setListener(this);
    }

    @Override
    public void onVehiculeClick(int vehiculeId) {
        return;
    }
}