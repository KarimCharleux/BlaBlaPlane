package com.example.blablaplane.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.blablaplane.R;
import com.example.blablaplane.activity.SwitcherActivity;
import com.example.blablaplane.activity.TripInfoActivity;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.trip.Trip;
import com.example.blablaplane.object.trip.TripAdapter;
import com.example.blablaplane.object.trip.TripAdapterListener;
import com.example.blablaplane.object.trip.TripArray;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentMyTrips extends Fragment implements TripAdapterListener {

    public FragmentMyTrips() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_trips, container, false);

        String userID = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE).getString("user_id", null);
        DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

        // Check if the user exists and get its data
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User exists in the database and we can get its data
                    User user = dataSnapshot.getValue(User.class);
                    assert user != null;

                    // Get the list of trips id
                    List<Integer> tripIdArray = user.getMyTripList();

                    List<Trip> tripArray = new ArrayList<>();
                    // Get the list of trips
                    for (int tripId : tripIdArray) {
                        Trip trip = TripArray.getInstance().getTripById(tripId);
                        if (trip != null) {
                            tripArray.add(trip);
                        }
                    }

                    // Create the adapter
                    TripAdapter tripAdapter = new TripAdapter(getActivity(), tripArray);

                    // Retrieve the list of trips
                    ListView tripList = view.findViewById(R.id.listTripView);

                    // Set the adapter
                    tripList.setAdapter(tripAdapter);

                    // Set the listener
                    tripAdapter.setListener(FragmentMyTrips.this);

                    // If there is no trip, display a message
                    if (tripIdArray.isEmpty()) {
                        view.findViewById(R.id.noMyTripMessage).setVisibility(View.VISIBLE);
                    } else {
                        view.findViewById(R.id.noMyTripMessage).setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Here should be an error message
            }
        });

        Button returnButton = view.findViewById(R.id.returnHome);
        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SwitcherActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

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