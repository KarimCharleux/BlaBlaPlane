package com.example.blablaplane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.blablaplane.object.TripArray;

public class MyTripActivity extends AppCompatActivity implements TripAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trip);

        // Get the list of trips
        TripArray tripArray = TripArray.getInstance();

        // Create the adapter
        TripAdapter tripAdapter = new TripAdapter(getApplicationContext(), tripArray);

        // Retrieve the list of trips
        ListView tripList = findViewById(R.id.listTripView);

        // Set the adapter
        tripList.setAdapter(tripAdapter);

        // Set the listener
        tripAdapter.setListener(this);
    }

    @Override
    public void onTripClick(int tripId) {
        Intent intent = new Intent(MyTripActivity.this, MyTripActivity.class);
        intent.putExtra("id", tripId);
        startActivity(intent);
    }
}
