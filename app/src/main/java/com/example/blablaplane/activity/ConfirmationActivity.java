package com.example.blablaplane.activity;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;

import com.example.blablaplane.R;
import com.example.blablaplane.object.trip.Trip;
import com.example.blablaplane.object.trip.TripArray;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.Manifest;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        int tripId = getIntent().getIntExtra("id", 0);
        Trip trip = TripArray.getInstance().getTripById(tripId);

        Button addToCalendarButton  = findViewById(R.id.AddToCalendarButton);
        Button backToHomeButton     = findViewById(R.id.backToHomeButton);

        addToCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCalendar(trip);
            }
        });

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNavigateNewPage = new Intent(ConfirmationActivity.this, HomeActivity.class);
                System.out.println("VERS HOME");
                ConfirmationActivity.this.startActivity(intentNavigateNewPage);
            }
        });

    }

    private void addToCalendar(Trip trip)
    {
        String title        = "Travel from "+trip.getDeparture()+" to "+trip.getArrival();
        String event_location     = trip.getDeparture().getCityName()+", "+trip.getDeparture().getCountry();
        String description  = "Be ready for your trip at destination of "+trip.getArrival().getCityName() + " don't forget to take contact with the pilot before";
        Long startDate = trip.getDepartureDate().getTime(); //  String.valueOf(trip.getDepartureDate().getTime());
        Long endDate =   trip.getArrivalDate().getTime(); //String.valueOf(trip.getArrivalDate().getTime());

        long calID;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            calID = 3;
        }else{
            calID = 1;
        }

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