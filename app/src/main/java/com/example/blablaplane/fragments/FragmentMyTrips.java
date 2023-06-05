package com.example.blablaplane.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.blablaplane.R;
import com.example.blablaplane.activity.TripInfoActivity;
import com.example.blablaplane.object.trip.TripAdapter;
import com.example.blablaplane.object.trip.TripAdapterListener;
import com.example.blablaplane.object.trip.TripArray;

public class FragmentMyTrips extends Fragment implements TripAdapterListener {

    public FragmentMyTrips() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_trips, container, false);

        // Get the list of trips
        TripArray tripArray = TripArray.getInstance();

        // Create the adapter
        TripAdapter tripAdapter = new TripAdapter(getActivity(), tripArray);

        // Retrieve the list of trips
        ListView tripList = view.findViewById(R.id.listTripView);

        // Set the adapter
        tripList.setAdapter(tripAdapter);

        // Set the listener
        tripAdapter.setListener(this);

        return view;
    }

    @Override
    public void onTripClick(int tripId) {
        Intent intent = new Intent(getActivity(), TripInfoActivity.class);
        intent.putExtra("id", tripId);
        intent.putExtra("isMyTrip", true);
        startActivity(intent);
    }
}