package com.example.blablaplane;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ConstraintLayout button = findViewById(R.id.searchBox);
        TextView buttonText = findViewById(R.id.searchBoxText);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ListTripActivity.class);
            startActivity(intent);
        });

        buttonText.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ListTripActivity.class);
            startActivity(intent);
        });
    }
}