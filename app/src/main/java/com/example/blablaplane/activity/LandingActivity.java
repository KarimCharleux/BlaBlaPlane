package com.example.blablaplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.R;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Button buttonConnect = findViewById(R.id.buttonConnect);
        buttonConnect.setOnClickListener(v -> {
            Intent intent = new Intent(this, ConnectionActivity.class);
            startActivity(intent);
        });

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}