package com.example.blablaplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.R;
import com.example.blablaplane.object.trip.Trip;
import com.example.blablaplane.object.trip.TripAdapter;
import com.example.blablaplane.object.trip.TripAdapterListener;
import com.example.blablaplane.object.trip.TripArray;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ListTripActivity extends AppCompatActivity implements TripAdapterListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trip);

        String depart = getIntent().getStringExtra("departure");
        String destination = getIntent().getStringExtra("destination");
        int nbPassenger = getIntent().getIntExtra("nbPassenger", 1);
        Date date = getIntent().getParcelableExtra("date");

        // Get the list of trips
        TripArray tripArray = TripArray.getInstance();
        TripAdapter tripAdapter;

        List<Trip> filteredList = tripArray;

        // Filter the list of trips according to departure and destination
        if (!depart.equals("Départ") && !destination.equals("Destination")) {
            filteredList = tripArray.stream()
                    .filter(x -> x.getDeparture().getCityName().equals(depart))
                    .filter(x -> x.getArrival().getCityName().equals(destination))
                    .collect(Collectors.toList());
        } else if (!destination.equals("Destination")) {
            filteredList = tripArray.stream()
                    .filter(x -> x.getArrival().getCityName().equals(destination))
                    .collect(Collectors.toList());
        } else if (!depart.equals("Départ")) {
            filteredList = tripArray.stream()
                    .filter(x -> x.getDeparture().getCityName().equals(depart))
                    .collect(Collectors.toList());
        }

        // Filter the list of trips according to the number of passengers
        filteredList = filteredList.stream()
                .filter(x -> x.getSeatsLeft() >= nbPassenger)
                .collect(Collectors.toList());

        // Filter the list of trips according to the date
        // TODO: filter the list of trips according to the date

        // Set the adapter
        tripAdapter = new TripAdapter(getApplicationContext(), filteredList);

        // Retrieve the list of trips
        ListView tripList = findViewById(R.id.listTripView);

        // Set the adapter
        tripList.setAdapter(tripAdapter);

        // Set the listener
        tripAdapter.setListener(this);

        // If the list is empty, display a message
        LinearLayout noTripMessage = findViewById(R.id.noTripMessage);
        if (filteredList.isEmpty()) {
            noTripMessage.setVisibility(View.VISIBLE);
            noTripMessage.setOnClickListener(view -> this.onBackPressed());
        } else {
            noTripMessage.setVisibility(View.INVISIBLE);
        }

        // Set the listener on the return button
        findViewById(R.id.returnHome).setOnClickListener(view -> this.onBackPressed());
    }

    @Override
    public void onTripClick(int tripId) {
        Intent intent = new Intent(ListTripActivity.this, TripInfoActivity.class);
        intent.putExtra("id", tripId);
        startActivity(intent);
    }
}