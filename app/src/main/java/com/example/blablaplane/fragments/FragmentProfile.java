package com.example.blablaplane.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.blablaplane.R;
import com.example.blablaplane.activity.LandingActivity;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.aircraft.AircraftAdapter;
import com.example.blablaplane.object.aircraft.AircraftAdapterListener;
import com.example.blablaplane.object.aircraft.AircraftArray;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FragmentProfile extends Fragment implements AircraftAdapterListener {

    public FragmentProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences preferences = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String userID = preferences.getString("user_id", null);
        if (userID.isEmpty()) {
            Intent intent = new Intent(getActivity(), LandingActivity.class);
            startActivity(intent);
            requireActivity().finish();
        } else {
            DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

            // Check if the user exists and get its data
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User exists in the database and we can get its data
                        User user = dataSnapshot.getValue(User.class);

                        TextView userName = view.findViewById(R.id.first_name);
                        TextView userLastName = view.findViewById(R.id.last_name);
                        TextView userEmail = view.findViewById(R.id.email_user);
                        TextView userPhone = view.findViewById(R.id.phone_number);
                        RatingBar ratingBar = view.findViewById(R.id.ratingBar);

                        assert user != null;
                        // Put the first letter of the name in uppercase
                        String name = user.getName().substring(0, 1).toUpperCase() + user.getName().substring(1);
                        userName.setText(name);
                        userLastName.setText(user.getSurname().toUpperCase());
                        userEmail.setText(user.getEmail());
                        // Format the phone number ex: 06 12 34 56 78
                        String phone = user.getPhone().replace(" ", "");
                        phone = phone.substring(0, 2) + " " + phone.substring(2, 4) + " " + phone.substring(4, 6) + " " + phone.substring(6, 8) + " " + phone.substring(8, 10);
                        userPhone.setText(phone);
                        ratingBar.setRating(user.getRating());

                    } else {
                        Intent intent = new Intent(getActivity(), LandingActivity.class);
                        startActivity(intent);
                        requireActivity().finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Here should be an error message
                }
            });
        }

        // Get the list of Aircraft
        AircraftArray aircraftArray = AircraftArray.getInstance();

        // Create the adapter
        AircraftAdapter aircraftAdapter = new AircraftAdapter(getContext(), aircraftArray);

        // Retrieve the list of Message profiles
        ListView aircraftList = view.findViewById(R.id.aircraft_list);

        // Set the adapter
        aircraftList.setAdapter(aircraftAdapter);

        // Set the listener
        aircraftAdapter.setListener(this);

        Button buttonDisconnect = view.findViewById(R.id.logoutButton);
        buttonDisconnect.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("user_id");
            editor.apply();

            Intent intent = new Intent(getActivity(), LandingActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        return view;
    }

    @Override
    public void onAircraftClick(int aircraftId) {
        System.out.println("Aircraft clicked : " + aircraftId);
    }
}