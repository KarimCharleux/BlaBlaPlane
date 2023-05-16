package com.example.blablaplane.activity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.Exceptions.NoPlacesAvailableException;
import com.example.blablaplane.R;

import java.io.IOException;
import java.util.List;

public class PublishSelectCityActivity extends AppCompatActivity {

    // System of location search
    private LocationRequest locationRequest;

    private Location location;

    private ListView listCity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_selectcity);

        EditText input_city = findViewById(R.id.input_selectCity);

        Button buttonSearch = findViewById(R.id.btn_confirm_search);

        Log.d("CREATION BTN", "creation");

        buttonSearch.setOnClickListener(view -> {
            String addressRequest;
            // Get the address request from the input
            addressRequest = input_city.getText().toString();

            // List of addresses
            List<Address> addressList;

            Log.d("INPUT", addressRequest);

            if (!TextUtils.isEmpty(addressRequest)) {
                Geocoder geocoder = new Geocoder(getApplicationContext());

                try {
                    addressList = geocoder.getFromLocationName(addressRequest, 10);
                } catch (IOException err) {
                    throw new NoPlacesAvailableException("No Places available");
                }

                Log.d("GPS RESULTS", "" + addressList.toString());

                // If geocoder found results, we display them in the listview
                if (!addressList.isEmpty()) {
                    // Display the list of addresses
                } else {

                }
            }
        });
    }
}