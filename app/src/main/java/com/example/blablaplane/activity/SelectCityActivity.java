package com.example.blablaplane.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.blablaplane.Interface.OnAirportSelectedListenerInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.fragments.FragmentGPSButton;
import com.example.blablaplane.object.trip.Airport;
import com.example.blablaplane.object.trip.CreateTripInfo;
import com.example.blablaplane.object.trip.SearchTripInfo;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SelectCityActivity extends AppCompatActivity implements OnAirportSelectedListenerInterface {
    private Place place = null;
    private AutocompleteSupportFragment autocompleteFragment;

    private SelectCityType selectType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_selectcity);
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentGPSButton gps = new FragmentGPSButton();
        fragmentTransaction.add(R.id.fragment_container, gps);
        fragmentTransaction.commit();


        // Google Maps API part
        String apiKey = getString(R.string.API_googleMaps);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        // Initialize the AutocompleteSupportFragment.
        autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        if (autocompleteFragment != null) {
            autocompleteFragment.setTypeFilter(TypeFilter.CITIES);
            autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(@NonNull Place placeSelected) {
                    place = placeSelected;
                }

                @Override
                public void onError(@NonNull Status status) {
                    System.err.println("An error occurred: " + status);
                }
            });

            CardView buttonCardView = findViewById(R.id.btn_confirm_search);
            Button buttonText = findViewById(R.id.btn_confirm_search_text);
            TextView titlePage = findViewById(R.id.frag_publish_title);
            TextView descriptionPage = findViewById(R.id.recapCreateTrip);

            buttonCardView.setOnClickListener(this::select);
            buttonText.setOnClickListener(this::select);

            Intent intent = getIntent();
            if (intent != null) {
                this.selectType = (SelectCityType) intent.getSerializableExtra("SelectType");

                titlePage.setText(this.selectType.getTitle());
                descriptionPage.setText(this.selectType.getDescription());
                buttonText.setText(this.selectType.getButtonText());
            }
        }
    }

    private void select(View view) {
        if (place == null) {
            Toast.makeText(getApplicationContext(), R.string.RESEARCH_ERR_EmptyDeparturePlaces, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent;

            switch (this.selectType) {
                case CREATE_DEPARTURE:
                    CreateTripInfo.departure = place.getName();
                    // Go to the next activity to select the destination
                    intent = new Intent(getApplicationContext(), SelectCityActivity.class);
                    intent.putExtra("SelectType", SelectCityType.CREATE_DESTINATION);
                    startActivity(intent);
                    break;
                case CREATE_DESTINATION:
                    CreateTripInfo.destination = place.getName();
                    // Go to the next activity to set the price
                    intent = new Intent(getApplicationContext(), SelectPriceAircraftActivity.class);
                    startActivity(intent);
                    break;
                case SEARCH_DEPARTURE:
                    SearchTripInfo.departure = place.getName();
                    // Go to the home page to search
                    intent = new Intent(getApplicationContext(), SwitcherActivity.class);
                    startActivity(intent);
                    break;
                case SEARCH_DESTINATION:
                    SearchTripInfo.destination = place.getName();
                    // Go to the home page to search
                    intent = new Intent(getApplicationContext(), SwitcherActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }




    @Override
    public void onLatLngSelected(LatLng latLng) {
        reverseGeocode(latLng);
    }

    @Override
    public void onAirportSelected(Airport airport) {
    }

    private void reverseGeocode(LatLng latLng) {
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                String city = address.getLocality();
                place = Place.builder().setName(city).build();
                autocompleteFragment.setText(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}