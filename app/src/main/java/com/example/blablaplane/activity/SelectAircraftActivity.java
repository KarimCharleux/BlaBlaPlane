package com.example.blablaplane.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.blablaplane.R;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.aircraft.Aircraft;
import com.example.blablaplane.object.aircraft.AircraftAdapter;
import com.example.blablaplane.object.aircraft.AircraftAdapterListener;
import com.example.blablaplane.object.aircraft.AircraftArray;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

public class SelectAircraftActivity extends AppCompatActivity implements AircraftAdapterListener {

    CardView cardView_createNewAircraft;
    CardView cardView_return;
    Button SelectAircraft_createNewAircraftButton;
    Button SelectAircraft_returnButton;
    ListView AircraftList;

    List<Aircraft> aircraftListSelected = new ArrayList<Aircraft>();
    private DatabaseReference Database;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_aircraft);
        
        Intent intent = getIntent();
        this.aircraftListSelected = intent.getParcelableArrayListExtra("aircraftList");


        this.cardView_createNewAircraft = findViewById(R.id.cardView_createNewAircraft);
        this.cardView_return = findViewById(R.id.cardView_return);
        this.SelectAircraft_createNewAircraftButton = findViewById(R.id.SelectAircraft_createNewAircraftButton);
        this.SelectAircraft_returnButton = findViewById(R.id.SelectAircraft_returnButton);


        // Get the list of Aircraft
        AircraftArray aircraftArray = AircraftArray.getInstance();
        aircraftArray.removeAll(aircraftListSelected);

        // Create the adapter
        AircraftAdapter aircraftAdapter = new AircraftAdapter(this, aircraftArray);
        

        // Set the adapter
        this.AircraftList.setAdapter(aircraftAdapter);

        // Set the listener
        aircraftAdapter.setListener(this);


        SharedPreferences preferences = this.getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String userID = preferences.getString("user_id", null);

        DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

        //TODO : open new view
        View.OnClickListener createNewAircraft = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };

        View.OnClickListener returnButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRef.child("listAricraft").setValue(aircraftListSelected);
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
                Aircraft aircraft = (Aircraft) AircraftList.getItemAtPosition(position);
                if (aircraftListSelected.contains(aircraft)) {
                    aircraftListSelected.remove(aircraft);
                    Toast.makeText(SelectAircraftActivity.this, "Aircraft : " + aircraft.getName() +" removed", Toast.LENGTH_SHORT).show();
                } else {
                    aircraftListSelected.add(aircraft);
                    Toast.makeText(SelectAircraftActivity.this, "Aircraft : " + aircraft.getName() +" added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAircraftClick(int aircraftId) {
        System.out.println("Aircraft clicked : " + aircraftId);
    }
}
