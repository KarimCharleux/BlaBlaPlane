package com.example.blablaplane.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.blablaplane.R;
import com.example.blablaplane.fragments.ModifyProfile_dialogFragment;

import java.util.ArrayList;

public class SelectAircraftActivity extends AppCompatActivity {

    CardView cardView_createNewAircraft;
    CardView cardView_return;
    Button SelectAircraft_createNewAircraftButton;
    Button SelectAircraft_returnButton;
    ListView AircraftList;

    ArrayList<Aircraft> listSelectedAircraft = new ArrayList<Aircraft>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Get the list of Aircraft
        AircraftArray aircraftArray = AircraftArray.getInstance();

        // Create the adapter
        AircraftAdapter aircraftAdapter = new AircraftAdapter(getContext(), aircraftArray);

        // Retrieve the list of aircraft
        ListView aircraftList = view.findViewById(R.id.aircraft_list);

        // Set the adapter
        aircraftList.setAdapter(aircraftAdapter);

        // Set the listener
        aircraftAdapter.setListener(this);

        return view;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_aircraft);

        this.cardView_createNewAircraft = findViewById(R.id.cardView_createNewAircraft);
        this.cardView_return = findViewById(R.id.cardView_return);
        this.SelectAircraft_createNewAircraftButton = findViewById(R.id.SelectAircraft_createNewAircraftButton);
        this.SelectAircraft_returnButton = findViewById(R.id.SelectAircraft_returnButton);

        //TODO : open new view
        View.OnClickListener createNewAircraft = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };

        //TODO : update profile with may new aircraft
        View.OnClickListener returnButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };

        this.cardView_createNewAircraft.setOnClickListener(createNewAircraft);
        this.SelectAircraft_createNewAircraftButton.setOnClickListener(createNewAircraft);

        this.cardView_return.setOnClickListener(returnButton);
        this.SelectAircraft_returnButton.setOnClickListener(returnButton);

        this.AircraftList = findViewById(R.id.aircraft_list);
        this.AircraftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer l'élément cliqué à partir de la position
                Aircraft aircraft = AircraftList.getItemAtPosition(position);
                if (listSelectedAircraft.contains(aircraft)) {
                    listSelectedAircraft.remove(aircraft);
                    Toast.makeText(SelectAircraftActivity.this, "Aircraft : " + aircraft.getName() +" removed", Toast.LENGTH_SHORT).show();
                } else {
                    listSelectedAircraft.add(aircraft);
                    Toast.makeText(SelectAircraftActivity.this, "Aircraft : " + aircraft.getName() +" added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
