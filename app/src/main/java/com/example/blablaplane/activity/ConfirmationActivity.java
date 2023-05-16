package com.example.blablaplane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.blablaplane.R;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Button addToCalendarButton  = findViewById(R.id.AddToCalendarButton);
        Button backToHomeButton     = findViewById(R.id.backToHomeButton);

        addToCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TO DO
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
}