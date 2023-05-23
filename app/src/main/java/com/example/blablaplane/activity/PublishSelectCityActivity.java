package com.example.blablaplane.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.Exceptions.NoPlacesAvailableException;
import com.example.blablaplane.R;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PublishSelectCityActivity extends AppCompatActivity {
    //googleMaps


    private Button btn_search;

    private Place startingPlace=null;
    private Place arrivalPlace;

    private boolean startFill = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_selectcity);
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);

        //PARTIE GOOGLEMAPS FRAGMENT

        String apiKey = getString(R.string.API_googleMaps);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setTypeFilter(TypeFilter.CITIES);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));


        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                startingPlace = place;
            }
            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Toast.makeText(getApplicationContext(), status.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        btn_search = findViewById(R.id.btn_confirm_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(startingPlace==null){
                    Toast.makeText(getApplicationContext(),R.string.RESEARCH_ERR_EmptyDeparturePlaces, Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intentNavigateNewPage = new Intent(getApplicationContext(), HomeActivity.class);

                    intentNavigateNewPage.putExtra(getResources().getString(R.string.RESEARCH_INTENT_stratingPlace),startingPlace);

                    intentNavigateNewPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intentNavigateNewPage);
                }
            }
        });
        Log.d("CREATION BTN","creation");
    }
}