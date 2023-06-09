package com.example.blablaplane.activity.select;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.R;
import com.example.blablaplane.activity.CreateNewAircraftActivity;
import com.example.blablaplane.activity.SwitcherActivity;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.aircraft.Aircraft;
import com.example.blablaplane.object.aircraft.AircraftAdapter;
import com.example.blablaplane.object.aircraft.AircraftAdapterListener;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectAircraftActivity extends AppCompatActivity implements AircraftAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_aircraft);

        ArrayList<Aircraft> aircraftList = new ArrayList<>();

        // Fetch all aircraft from the database
        DataBase.AIRCRAFT_REFERENCE.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot aircraftSnapshot : snapshot.getChildren()) {
                    Aircraft aircraft = aircraftSnapshot.getValue(Aircraft.class);
                    aircraftList.add(aircraft); // Add each aircraft to the list
                }

                // Create the adapter
                AircraftAdapter aircraftAdapter = new AircraftAdapter(getApplicationContext(), aircraftList);

                // Set the adapter
                ListView AircraftList = findViewById(R.id.SelectAircraft_aircraftList);
                AircraftList.setAdapter(aircraftAdapter);

                // Set the listener
                aircraftAdapter.setListener(SelectAircraftActivity.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error while fetching aircrafts from the database");
            }
        });

        Button buttonCreateAircraft = findViewById(R.id.SelectAircraft_createNewAircraftButton);
        buttonCreateAircraft.setOnClickListener(view -> {
            Intent intent = new Intent(SelectAircraftActivity.this, CreateNewAircraftActivity.class);
            startActivity(intent);
        });

        Button buttonReturn = findViewById(R.id.SelectAircraft_returnButton);
        buttonReturn.setOnClickListener(view -> this.onBackPressed());
    }

    /**
     * This method is called when the user clicks on an aircraft
     * It adds the aircraft to the user's list of aircraft
     *
     * @param aircraftId The id of the aircraft
     */
    @Override
    public void onAircraftClick(int aircraftId) {
        DatabaseReference aircraftDR = DataBase.AIRCRAFT_REFERENCE.child(String.valueOf(aircraftId));
        aircraftDR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Get the aircraft from the database
                Aircraft aircraft = snapshot.getValue(Aircraft.class);
                assert aircraft != null;

                // Fetch the user id from the shared preferences
                String userID = getSharedPreferences("user_data", Context.MODE_PRIVATE).getString("user_id", null);
                DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

                // Fetch the user from the database
                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Get the user object
                        User user = snapshot.getValue(User.class);
                        assert user != null;

                        // Add the new aircraft to the user's list of aircraft
                        user.addAircraft(aircraft);

                        // Update the user in the database
                        userRef.setValue(user);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.err.println("Error while fetching user from the database");
                    }
                });

                Toast.makeText(getApplicationContext(), "✅ " + aircraft.getName() + " ajouté !", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SelectAircraftActivity.this, SwitcherActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error while fetching aircraft from the database");
            }
        });
    }
}