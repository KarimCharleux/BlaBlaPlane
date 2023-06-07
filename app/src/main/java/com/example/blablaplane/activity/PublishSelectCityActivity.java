package com.example.blablaplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.blablaplane.R;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class PublishSelectCityActivity extends AppCompatActivity {
    private Place place = null;
    private boolean typeStart = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_selectcity);
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);

        // Google Maps API part
        String apiKey = getString(R.string.API_googleMaps);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        if (autocompleteFragment != null) {
            autocompleteFragment.setTypeFilter(TypeFilter.CITIES);
            autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(@NonNull Place placeSelected) {
                    // TODO: Get info about the selected place.
                    place = placeSelected;
                }

                @Override
                public void onError(@NonNull Status status) {
                    // TODO: Handle the error.
                    System.err.println("An error occurred: " + status);
                }
            });

            CardView btn_search = findViewById(R.id.btn_confirm_search);
            Button btn_search_text = findViewById(R.id.btn_confirm_search_text);
            btn_search.setOnClickListener(this::select);
            btn_search_text.setOnClickListener(this::select);

            Intent intent = getIntent();

            if(intent!=null){
                String typeRequest = intent.getStringExtra("type");

                TextView titlePage = findViewById(R.id.frag_publish_title);
                TextView description = findViewById(R.id.frag_publish_txt_select);

                if(typeRequest!=null){
                    Log.d("typeRequest",typeRequest);
                    //definition des labels en fonction
                    if(typeRequest.equals(getResources().getString(R.string.ACCUEIL_start))){
                        titlePage.setText(R.string.ACCUEIL_start);
                        description.setText(R.string.RESEARCH_selectionDepart);
                        this.typeStart = true;
                    }
                    else{
                        titlePage.setText(R.string.ACCUEIL_destination);
                        description.setText(R.string.RESEARCH_selectionArrivee);
                        this.typeStart = false;
                    }
                }
            }
        }
    }

    private void select(View view) {
        if (place == null) {
            Toast.makeText(getApplicationContext(), R.string.RESEARCH_ERR_EmptyDeparturePlaces, Toast.LENGTH_SHORT).show();
        } else {
            Intent intentNavigateNewPage = new Intent(getApplicationContext(), SwitcherActivity.class);

            if(this.typeStart){
                intentNavigateNewPage.putExtra(getResources().getString(R.string.RESEARCH_INTENT_startingPlace), place);
            }
            else{
                intentNavigateNewPage.putExtra(getResources().getString(R.string.RESEARCH_INTENT_destinationPlace), place);
            }


            intentNavigateNewPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intentNavigateNewPage);
        }
    }
}