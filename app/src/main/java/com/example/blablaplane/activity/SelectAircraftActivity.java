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

public class SelectAircraftActivity extends AppCompatActivity {

    CardView cardView_createNewAircraft;
    CardView cardView_return;
    Button SelectAircraft_createNewAircraftButton;
    Button SelectAircraft_returnButton;
    ListView AircraftList;

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

                // Faire quelque chose avec l'élément cliqué
                // Par exemple, afficher un message avec son contenu
                Toast.makeText(getApplicationContext(), "Élément cliqué : " + clickedItem.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
