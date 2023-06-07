package com.example.blablaplane.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.blablaplane.notifications.Message;
import com.example.blablaplane.R;
import com.example.blablaplane.activity.ListTripActivity;
import com.example.blablaplane.activity.PublishSelectCityActivity;
import com.google.android.libraries.places.api.model.Place;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class FragmentHome extends Fragment implements Observer {

    private final String TAG = "Notification :  " + getClass().getSimpleName();

    public FragmentHome() {
        // Required empty public constructor
    }

    private String depart;
    private String arrivee;
    private Date date;
    private int nbTravelers;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Message.getInstance().addObserver(this);

        ConstraintLayout buttonSearch = view.findViewById(R.id.searchBox);
        TextView buttonSearchText = view.findViewById(R.id.searchBoxText);


        if(savedInstanceState == null){
            //it is the first time the fragment is being called
            depart = getResources().getString(R.string.ACCUEIL_start);
            arrivee = getResources().getString(R.string.ACCUEIL_destination);
            nbTravelers = Integer.parseInt(getResources().getString(R.string.ACCUEIL_number_of_people));
        }else{
            //not the first time so we will check SavedInstanceState bundle
            depart = savedInstanceState.getString("tvDepart",getResources().getString(R.string.ACCUEIL_start));
            arrivee = savedInstanceState.getString("tvArrivee",getResources().getString(R.string.ACCUEIL_destination));
            nbTravelers = savedInstanceState.getInt("nbTraveler",Integer.getInteger(getResources().getString(R.string.ACCUEIL_number_of_people)));
        }


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

        ConstraintLayout Constraintdepart = view.findViewById(R.id.startBox);
        ConstraintLayout Constraintarrivee = view.findViewById(R.id.destinationBox);

        TextView TV_depart = view.findViewById(R.id.TV_startText);
        TextView TV_arrivee = view.findViewById(R.id.TV_destinationText);

        Constraintdepart.setOnClickListener(v -> {
            Intent intentNavigateNewPage = new Intent(getContext(), PublishSelectCityActivity.class);
            intentNavigateNewPage.putExtra("type",getResources().getString(R.string.ACCUEIL_start));

            if(savedInstanceState != null){
                savedInstanceState.putString("tvDepart",TV_depart.getText().toString());
                savedInstanceState.putString("tvArrivee",TV_arrivee.getText().toString());
            }

            intentNavigateNewPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            requireActivity().startActivity(intentNavigateNewPage);
        });

        Constraintarrivee.setOnClickListener(v -> {
            Intent intentNavigateNewPage = new Intent(getContext(), PublishSelectCityActivity.class);
            intentNavigateNewPage.putExtra("type",getResources().getString(R.string.ACCUEIL_destination));

            if(savedInstanceState != null){
                savedInstanceState.putString("tvDepart",TV_depart.getText().toString());
                savedInstanceState.putString("tvArrivee",TV_arrivee.getText().toString());
            }

            intentNavigateNewPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            requireActivity().startActivity(intentNavigateNewPage);
        });

        //Retrieve the intent
        Intent intent = requireActivity().getIntent();
        if (intent != null) {
            Place startingPlace = intent.getParcelableExtra(getResources().getString(R.string.RESEARCH_INTENT_startingPlace));
            Place destinationPlace = intent.getParcelableExtra(getResources().getString(R.string.RESEARCH_INTENT_destinationPlace));

            if (startingPlace != null) {TV_depart.setText(startingPlace.getName());}
            if(destinationPlace!=null){TV_arrivee.setText(destinationPlace.getName());}
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