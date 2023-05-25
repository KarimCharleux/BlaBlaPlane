package com.example.blablaplane.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.blablaplane.R;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class FragmentAdding extends Fragment {

    public FragmentAdding() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View addingView = inflater.inflate(R.layout.fragment_adding, container, false);

        // Google Maps API part
        String apiKey = getString(R.string.API_googleMaps);
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), apiKey);
        }

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                requireActivity().getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        if(autocompleteFragment != null) {
            autocompleteFragment.setTypeFilter(TypeFilter.CITIES);
            autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(@NonNull Place place) {
                    // TODO: Get info about the selected place.
                    System.out.printf("Place: %s, %s", place.getName(), place.getId());
                }

                @Override
                public void onError(@NonNull Status status) {
                    // TODO: Handle the error.
                    System.err.println("An error occurred: " + status);
                }
            });
        }


        return addingView;
    }
}