package com.example.blablaplane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ListTrip extends AppCompatActivity {

    Button buttonInfoTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trip);

        buttonInfoTrip = findViewById(R.id.infoTrip);

        buttonInfoTrip.setOnClickListener(view -> {
            Intent intent = new Intent(ListTrip.this, TripInfo.class);
            startActivity(intent);
        });
    }
}