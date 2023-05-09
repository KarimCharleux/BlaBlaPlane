package com.example.blablaplane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.fragments.FooterFragment;
import com.example.blablaplane.object.trip.TripAdapter;
import com.example.blablaplane.object.trip.TripAdapterListener;
import com.example.blablaplane.object.trip.TripArray;

public class MyTripActivity extends AppCompatActivity implements TripAdapterListener, CallBackInterface {
    FooterFragment footerFragment;
    FragmentFactory fragmentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        footerFragment = new FooterFragment();
        fragmentFactory = new FragmentFactory();
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

    @Override
    public void callBackMethod(int nb) {
        fragmentFactory.changeActivity(nb,this);
    }
}
