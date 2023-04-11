package com.example.blablaplane;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.object.Trip;
import com.example.blablaplane.object.TripArray;
import com.example.blablaplane.object.User;
import com.example.blablaplane.object.UserArray;

import java.text.NumberFormat;

public class TripInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_info);

        int tripId = getIntent().getIntExtra("id", 0);

        // Trip info
        Trip trip = TripArray.getInstance().getTripById(tripId);

        if (trip != null) {
            TextView departureCity = findViewById(R.id.departureText);
            TextView arrivalCity = findViewById(R.id.arrivalText);
            TextView departureTime = findViewById(R.id.departureTime);
            TextView arrivalTime = findViewById(R.id.arrivalTime);
            TextView date = findViewById(R.id.dateText);
            TextView price = findViewById(R.id.price);
            TextView duration = findViewById(R.id.durationTime);

            departureCity.setText(trip.getDeparture());
            arrivalCity.setText(trip.getArrival());
            departureTime.setText(trip.getDepartureTime());
            arrivalTime.setText(trip.getArrivalTime());
            date.setText(trip.getDate());
            price.setText(trip.getPrice());
            duration.setText(trip.getDuration());

            // Pilot info
            User pilot = UserArray.getInstance().getUserById(trip.getPilotId());

            if (pilot != null) {
                TextView namePilot = findViewById(R.id.namePilot);
                TextView surnamePilot = findViewById(R.id.surnamePilot);
                TextView ratingPilot = findViewById(R.id.ratingPilot);

                namePilot.setText(pilot.getName());
                surnamePilot.setText(pilot.getSurname());
                ratingPilot.setText(pilot.getRating());
            } else {
                System.err.println("ERROR : Pilot not found, id :" + trip.getPilotId());
            }
        } else {
            System.err.println("ERROR : Trip not found, id :" + tripId);
        }
    }
}