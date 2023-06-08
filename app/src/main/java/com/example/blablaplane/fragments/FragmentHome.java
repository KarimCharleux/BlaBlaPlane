package com.example.blablaplane.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.blablaplane.R;
import com.example.blablaplane.activity.ListTripActivity;
import com.example.blablaplane.activity.SelectCityActivity;
import com.example.blablaplane.activity.SelectCityType;
import com.example.blablaplane.notifications.Message;
import com.example.blablaplane.object.trip.SearchTripInfo;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Observable;
import java.util.Observer;

public class FragmentHome extends Fragment implements Observer {
    private final String TAG = "Notification :  " + getClass().getSimpleName();

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Message.getInstance().addObserver(this);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.d(TAG, "Error with Firebase", task.getException());
            }
            Log.w(TAG, "Token notif = " + task.getResult());
        });

        view.findViewById(R.id.searchBox).setOnClickListener(v -> search());
        view.findViewById(R.id.searchBoxText).setOnClickListener(v -> search());

        // Go to the select city activity when the user click on the departure or destination box
        view.findViewById(R.id.startBox).setOnClickListener(v -> {
            Intent intentNavigateNewPage = new Intent(getContext(), SelectCityActivity.class);
            intentNavigateNewPage.putExtra("SelectType", SelectCityType.SEARCH_DEPARTURE);

            requireActivity().startActivity(intentNavigateNewPage);
        });

        view.findViewById(R.id.destinationBox).setOnClickListener(v -> {
            Intent intentNavigateNewPage = new Intent(getContext(), SelectCityActivity.class);
            intentNavigateNewPage.putExtra("SelectType", SelectCityType.SEARCH_DESTINATION);

            requireActivity().startActivity(intentNavigateNewPage);
        });

        // Set the text of the departure and destination from the SearchTripInfo
        TextView DepartureText = view.findViewById(R.id.TV_startText);
        TextView DestinationText = view.findViewById(R.id.TV_destinationText);
        Spinner spinnerNbOfPeople = view.findViewById(R.id.numberOfPeople);

        DepartureText.setText(SearchTripInfo.departure);
        DestinationText.setText(SearchTripInfo.destination);

        // Set the color to black of departure text and set the close button visible if is filled
        ImageButton closeDeparture = view.findViewById(R.id.closeDeparture);
        if (!DepartureText.getText().toString().equals("Départ")) {
            DepartureText.setTextColor(getResources().getColor(R.color.black));
            closeDeparture.setVisibility(View.VISIBLE);
            closeDeparture.setOnClickListener(v -> {
                DepartureText.setText(getResources().getString(R.string.ACCUEIL_start));
                DepartureText.setTextColor(getResources().getColor(com.google.android.material.R.color.secondary_text_default_material_light));
                closeDeparture.setVisibility(View.INVISIBLE);
                SearchTripInfo.resetDeparture();
            });
        } else {
            DepartureText.setTextColor(getResources().getColor(com.google.android.material.R.color.secondary_text_default_material_light));
            view.findViewById(R.id.closeDeparture).setVisibility(View.INVISIBLE);
        }

        // Set the color to black of destination text and set the close button visible if is filled
        ImageButton closeDestination = view.findViewById(R.id.closeDestination);
        if (!DestinationText.getText().toString().equals("Destination")) {
            DestinationText.setTextColor(getResources().getColor(R.color.black));
            closeDestination.setVisibility(View.VISIBLE);
            closeDestination.setOnClickListener(v -> {
                DestinationText.setText(getResources().getString(R.string.ACCUEIL_destination));
                DestinationText.setTextColor(getResources().getColor(com.google.android.material.R.color.secondary_text_default_material_light));
                closeDestination.setVisibility(View.INVISIBLE);
                SearchTripInfo.resetDestination();
            });
        } else {
            DestinationText.setTextColor(getResources().getColor(com.google.android.material.R.color.secondary_text_default_material_light));
            view.findViewById(R.id.closeDestination).setVisibility(View.INVISIBLE);
        }

        String[] numbers = {"1", "2", "3", "4", "5", "6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, numbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Set the adapter to the spinner
        spinnerNbOfPeople.setAdapter(adapter);
        spinnerNbOfPeople.setSelection(SearchTripInfo.nbPassenger - 1);
        spinnerNbOfPeople.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SearchTripInfo.nbPassenger = Integer.parseInt(adapterView.getItemAtPosition(position).toString());
                System.out.println("SearchTripInfo.nbPassenger = " + SearchTripInfo.nbPassenger);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Nothing to do
            }
        });

        return view;
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.d(TAG, "Message from : " + Message.getInstance().from());
        if (Message.getInstance().isNull()) {
            Log.d(TAG, "Title: " + Message.getInstance().getTitle());
            Log.d(TAG, "Title: " + Message.getInstance().body());
        }
    }

    private void search() {
        Intent intent = new Intent(getActivity(), ListTripActivity.class);
        intent.putExtra("departure", SearchTripInfo.departure);
        intent.putExtra("destination", SearchTripInfo.destination);
        intent.putExtra("nbPassenger", SearchTripInfo.nbPassenger);
        intent.putExtra("date", SearchTripInfo.date);

        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        System.out.println("SAVE FRAG");
        outState.putString("string_value", "Hello world");

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        System.out.println(savedInstanceState);
    }
}