package com.example.blablaplane.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.R;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.aircraft.AircraftAdapter;
import com.example.blablaplane.object.aircraft.AircraftAdapterListener;
import com.example.blablaplane.object.trip.CreateTripInfo;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class SelectPriceAircraftActivity extends AppCompatActivity implements AircraftAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_price_aircraft);

        String userID = getSharedPreferences("user_data", Context.MODE_PRIVATE).getString("user_id", null);

        // Get the list of its aircraft
        DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

        // Fetch the user from the database
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Get the user object
                User user = snapshot.getValue(User.class);
                assert user != null;

                // Create the adapter
                AircraftAdapter aircraftAdapter = new AircraftAdapter(getApplicationContext(), user.getAircraftList());

                // Retrieve the list of trips
                ListView vehiculeList = findViewById(R.id.LV_aircraft);

                // Set the adapter
                vehiculeList.setAdapter(aircraftAdapter);

                // Set the listener
                aircraftAdapter.setListener(SelectPriceAircraftActivity.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error while fetching user from the database");
            }
        });
    }

    @Override
    public void onAircraftClick(int vehiculeId) {
        // Get the pilot ID
        CreateTripInfo.pilotId = getSharedPreferences("user_data", Context.MODE_PRIVATE).getString("user_id", null);

        // Get the aircraft ID
        CreateTripInfo.aircraftId = vehiculeId;

        // Get the price
        CreateTripInfo.price = Float.valueOf(((EditText) findViewById(R.id.EDIT_price)).getText().toString().replace(',', '.').replace(" €", ""));

        // Go to set the date of the trip
        Intent intentNavigateNewPage = new Intent(getApplicationContext(), SelectDateActivity.class);
        startActivity(intentNavigateNewPage);
    }
}