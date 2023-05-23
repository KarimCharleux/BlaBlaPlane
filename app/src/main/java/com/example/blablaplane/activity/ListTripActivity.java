package com.example.blablaplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.object.trip.TripAdapter;
import com.example.blablaplane.object.trip.TripAdapterListener;
import com.example.blablaplane.object.trip.TripArray;

public class ListTripActivity extends AppCompatActivity implements TripAdapterListener, CallBackInterface {

    FragmentFactory fragmentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentFactory = new FragmentFactory();
        setContentView(R.layout.activity_list_trip);

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
        Intent intent = new Intent(ListTripActivity.this, TripInfoActivity.class);
        intent.putExtra("id", tripId);
        startActivity(intent);
    }

    @Override
    public void callBackMethod(int indexMenu) {
        fragmentFactory.changeActivity(indexMenu, this);
    }
}