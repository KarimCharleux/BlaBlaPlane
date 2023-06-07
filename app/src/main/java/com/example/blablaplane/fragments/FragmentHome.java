package com.example.blablaplane.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.blablaplane.notifications.Message;
import com.example.blablaplane.R;
import com.example.blablaplane.activity.ListTripActivity;
import com.example.blablaplane.activity.PublishSelectCityActivity;
import com.google.android.libraries.places.api.model.Place;
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Message.getInstance().addObserver(this);

        ConstraintLayout buttonSearch = view.findViewById(R.id.searchBox);
        TextView buttonSearchText = view.findViewById(R.id.searchBoxText);

        buttonSearch.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ListTripActivity.class);
            startActivity(intent);
        });

        buttonSearchText.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ListTripActivity.class);
            startActivity(intent);
        });

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.d(TAG, "Error with Firebase", task.getException());
            }
            Log.w(TAG, "Token notif = " + task.getResult());
        });

        ConstraintLayout depart = view.findViewById(R.id.startBox);
        depart.setOnClickListener(v -> {
            Intent intentNavigateNewPage = new Intent(getContext(), PublishSelectCityActivity.class);
            intentNavigateNewPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            requireActivity().startActivity(intentNavigateNewPage);
        });

        //Retrieve the intent
        Intent intent = requireActivity().getIntent();
        if (intent != null) {
            Place startingPlace = intent.getParcelableExtra(getResources().getString(R.string.RESEARCH_INTENT_stratingPlace));
            TextView TV_depart = view.findViewById(R.id.startText);

            if (startingPlace != null) {
                TV_depart.setText(startingPlace.getName());
            } else {
                TV_depart.setText(R.string.ACCUEIL_start);
            }
        }
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
}