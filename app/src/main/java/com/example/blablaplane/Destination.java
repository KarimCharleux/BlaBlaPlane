package com.example.blablaplane;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.object.trip.CitySuggestion;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Destination extends AppCompatActivity {


    EditText destinationInput;
    Button destinationButton1;
    Button destinationButton2;
    Button destinationButton3;
    Button destinationButton4;
    Button destinationButton5;
    Button destinationButton6;

    String inputDestination = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        destinationInput = findViewById(R.id.selectDestination);
        destinationButton1 = findViewById(R.id.destination1);
        destinationButton2 = findViewById(R.id.destination2);
        destinationButton3 = findViewById(R.id.destination3);
        destinationButton4 = findViewById(R.id.destination4);
        destinationButton5 = findViewById(R.id.destination5);
        destinationButton6 = findViewById(R.id.destination6);

        inputDestination = "p";
        updateRecommendation();
        inputDestination = "";

        destinationInput.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputDestination = destinationInput.getText().toString();
                updateRecommendation();
            }

            @Override
            public void afterTextChanged(Editable s) {
                inputDestination = destinationInput.getText().toString();
                updateRecommendation();
            }
        });

        destinationButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationInput.setText(destinationButton1.getText().toString());
            }
        });

        destinationButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationInput.setText(destinationButton2.getText().toString());
            }
        });

        destinationButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationInput.setText(destinationButton3.getText().toString());
            }
        });

        destinationButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationInput.setText(destinationButton4.getText().toString());
            }
        });

        destinationButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationInput.setText(destinationButton5.getText().toString());
            }
        });

        destinationButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationInput.setText(destinationButton6.getText().toString());
            }
        });
    }

    private void updateRecommendation() {
        ArrayList<String> recommendations = CitySuggestion.searchingClosestCity(inputDestination, 6);
        destinationButton1.setText(recommendations.get(0));
        destinationButton2.setText(recommendations.get(1));
        destinationButton3.setText(recommendations.get(2));
        destinationButton4.setText(recommendations.get(3));
        destinationButton5.setText(recommendations.get(4));
        destinationButton6.setText(recommendations.get(5));
    }

}
