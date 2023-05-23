package com.example.blablaplane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.fragments.FooterFragment;
import com.example.blablaplane.object.aircraft.AircraftAdapter;
import com.example.blablaplane.object.aircraft.AircraftArray;
import com.example.blablaplane.object.aircraft.AircraftAdapterListener;

public class ProfileActivity extends AppCompatActivity implements AircraftAdapterListener, CallBackInterface {
    FragmentFactory fragmentFactory;
    FooterFragment footerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        footerFragment = new FooterFragment();
        fragmentFactory = new FragmentFactory();
        setContentView(R.layout.activity_profile);

        // Get the list of Message profils
        AircraftArray aircraftArray = AircraftArray.getInstance();

        // Create the adapter
        AircraftAdapter aircraftAdapter = new AircraftAdapter(getApplicationContext(), aircraftArray);

        // Retrieve the list of Message profiles
        ListView aircraftList = findViewById(R.id.aircraft_list);

        // Set the adapter
        aircraftList.setAdapter(aircraftAdapter);

        // Set the listener
        aircraftAdapter.setListener(this);
    }

    @Override
    public void onAircraftClick(int aircraftId) {
        System.out.println("Aircraft clicked : " + aircraftId);
    }

    @Override
    public void callBackMethod(int indexMenu) {
        fragmentFactory.changeActivity(indexMenu,this);
    }
}