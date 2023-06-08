package com.example.blablaplane.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.R;
import com.example.blablaplane.object.trip.CreateTripInfo;
import com.example.blablaplane.object.trip.Trip;
import com.example.blablaplane.object.trip.TripArray;

import java.util.Calendar;

public class SelectDateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog with the current date
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);

        // Limit the date to the current date and after
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        // Display the dialog
        datePickerDialog.show();

        findViewById(R.id.ButtonCreateTrip).setOnClickListener(v -> {
            Trip trip = CreateTripInfo.createTheTrip();
            CreateTripInfo.reset();
            TripArray.getInstance().add(trip);
            Toast.makeText(this, R.string.ConfirmationTripCreated, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, SwitcherActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Create a calendar object with the selected date
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(Calendar.YEAR, year);
        selectedDate.set(Calendar.MONTH, month);
        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        // Get the current time
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a TimePickerDialog with the current time
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view1, hourOfDay, minuteOfDay) -> {
            selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
            selectedDate.set(Calendar.MINUTE, minuteOfDay);

            // Set the date in the CreateTripInfo object
            CreateTripInfo.departureDate = selectedDate.getTime();

        }, hour, minute, true);

        // Afficher le TimePickerDialog
        timePickerDialog.show();
    }
}