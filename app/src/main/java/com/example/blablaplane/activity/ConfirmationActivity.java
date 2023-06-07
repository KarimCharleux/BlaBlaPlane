package com.example.blablaplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.R;
import com.example.blablaplane.object.trip.Trip;
import com.example.blablaplane.object.trip.TripArray;

import java.util.TimeZone;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        int tripId = getIntent().getIntExtra("id", 0);
        Trip trip = TripArray.getInstance().getTripById(tripId);

        Button addToCalendarButton = findViewById(R.id.AddToCalendarButton);
        Button backToHomeButton = findViewById(R.id.backToHomeButton);

        addToCalendarButton.setOnClickListener(view -> addToCalendar(trip));

        backToHomeButton.setOnClickListener(view -> {
            Intent intentNavigateNewPage = new Intent(ConfirmationActivity.this, SwitcherActivity.class);
            System.out.println("VERS HOME");
            ConfirmationActivity.this.startActivity(intentNavigateNewPage);
        });

    }

    private void addToCalendar(Trip trip) {
        String title = "Travel from " + trip.getDeparture() + " to " + trip.getArrival();
        String event_location = trip.getDeparture().getCityName() + ", " + trip.getDeparture().getCountry();
        String description = "Be ready for your trip at destination of " + trip.getArrival().getCityName() + " don't forget to take contact with the pilot before";
        Long startDate = trip.getDepartureDate().getTime(); //  String.valueOf(trip.getDepartureDate().getTime());
        Long endDate = trip.getArrivalDate().getTime(); //String.valueOf(trip.getArrivalDate().getTime());

        long calID = 3;

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.CALENDAR_ID, calID);
        intent.putExtra(CalendarContract.Events.TITLE, title);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, event_location);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);
        intent.putExtra(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate);

        startActivity(intent);
    }

}