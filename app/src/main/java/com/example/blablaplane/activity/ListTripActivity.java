package com.example.blablaplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.R;
import com.example.blablaplane.object.trip.Trip;
import com.example.blablaplane.object.trip.TripAdapter;
import com.example.blablaplane.object.trip.TripAdapterListener;
import com.example.blablaplane.object.trip.TripArray;
import com.example.blablaplane.object.trip.TripInfo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ListTripActivity extends AppCompatActivity implements TripAdapterListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trip);

        String depart = getIntent().getStringExtra("depart");
        String destination = getIntent().getStringExtra("destination");
        int nb_personnes = getIntent().getIntExtra("nb_personnes", 1);
        Date date = getIntent().getParcelableExtra("depart");

        // Get the list of trips
        TripArray tripArray = TripArray.getInstance();
        TripAdapter tripAdapter;

        System.out.println(depart);
        System.out.println(destination);

        if(depart != null && !depart.equals("Départ") && destination != null && !destination.equals("Destination"))
        {
            tripArray.stream()
                    .forEach((k) -> {
                        System.out.print(k.getDeparture().getCityName());
                    });
            List<Trip> filtredList = tripArray.stream()
                    .filter(x->x.getDeparture().getCityName().equals(depart))
                    .filter(x->x.getArrival().getCityName().equals(destination))
                    .collect(Collectors.toList());
            System.out.println(filtredList.size());
            System.out.println(filtredList);
            tripAdapter = new TripAdapter(getApplicationContext(), filtredList);

            TripInfo.depart = "Départ";
            TripInfo.destination = "Destination";
        }
        else {
            tripAdapter = new TripAdapter(getApplicationContext(), tripArray);
        }



        // Retrieve the list of trips
        ListView tripList = findViewById(R.id.listTripView);

        // Set the adapter
        tripList.setAdapter(tripAdapter);

        // Set the listener
        tripAdapter.setListener(this);
    }

    @Override
    public void onTripClick(int tripId) {
        Intent intent = new Intent(ListTripActivity.this, TripInfoActivity.class);
        intent.putExtra("id", tripId);
        startActivity(intent);
    }
}