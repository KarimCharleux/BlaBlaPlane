package com.example.blablaplane;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.Interface.AirportInterface;
import com.example.blablaplane.Interface.OnAirportSelectedListenerInterface;
import com.example.blablaplane.activity.SwitcherActivity;
import com.example.blablaplane.object.trip.Airport;
import com.example.blablaplane.object.trip.AirportFinder;
import com.example.blablaplane.object.trip.City;
import com.example.blablaplane.object.trip.CitySuggestion;
import com.google.android.gms.maps.model.LatLng;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public class Destination extends AppCompatActivity implements OnAirportSelectedListenerInterface {


    EditText destinationInput;
    Button destinationButton1;
    Button destinationButton2;
    Button destinationButton3;
    Button destinationButton4;
    Button destinationButton5;
    Button destinationButton6;

    String inputDestination = "";
    Airport destinationAirport;
    City destinationCityChosen;

    City destinationCity1;
    City destinationCity2;
    City destinationCity3;
    City destinationCity4;
    City destinationCity5;
    City destinationCity6;


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

        View.OnClickListener confirm = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };

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
                destinationCityChosen = destinationCity1;
            }
        });

        destinationButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationInput.setText(destinationButton2.getText().toString());
                destinationCityChosen = destinationCity2;
            }
        });

        destinationButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationInput.setText(destinationButton3.getText().toString());
                destinationCityChosen = destinationCity3;
            }
        });

        destinationButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationInput.setText(destinationButton4.getText().toString());
                destinationCityChosen = destinationCity4;
            }
        });

        destinationButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationInput.setText(destinationButton5.getText().toString());
                destinationCityChosen = destinationCity5;
            }
        });

        destinationButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationInput.setText(destinationButton6.getText().toString());
                destinationCityChosen = destinationCity6;
            }
        });




    }

    private void updateRecommendation() {
        ArrayList<City> recommendations = CitySuggestion.searchingClosestCity(inputDestination, 6);
        this.destinationCity1 = recommendations.get(0);
        this.destinationCity2 = recommendations.get(1);
        this.destinationCity3 = recommendations.get(2);
        this.destinationCity4 = recommendations.get(3);
        this.destinationCity5 = recommendations.get(4);
        this.destinationCity6 = recommendations.get(5);
        destinationButton1.setText(this.destinationCity1.getCityName());
        destinationButton2.setText(this.destinationCity2.getCityName());
        destinationButton3.setText(this.destinationCity3.getCityName());
        destinationButton4.setText(this.destinationCity4.getCityName());
        destinationButton5.setText(this.destinationCity5.getCityName());
        destinationButton6.setText(this.destinationCity6.getCityName());
    }





    @Override
    public void onAirportSelected(Airport airport) {
        this.destinationAirport = airport;
        this.inputDestination = airport.getCity();
    }



    private void select(View view) {
        if (inputDestination == null) {
            Toast.makeText(getApplicationContext(), R.string.RESEARCH_ERR_EmptyDeparturePlaces, Toast.LENGTH_SHORT).show();
        } else {
            GeoPoint geopoint = destinationCityChosen.getGeoPoint();
            LatLng latlng = new LatLng(geopoint.getLatitude(), geopoint.getLongitude());
            if (destinationAirport == null) {
                destinationAirport = AirportFinder.findNearestAirport(latlng);
            } else if (!destinationAirport.getCity().equals(inputDestination)) {
                destinationAirport = AirportFinder.findNearestAirport(latlng);
            }

            Intent intentNavigateNewPage = new Intent(getApplicationContext(), SwitcherActivity.class);

            intentNavigateNewPage.putExtra(AirportInterface.KEY, destinationAirport);

            intentNavigateNewPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intentNavigateNewPage);
        }
    }

}
