package com.example.blablaplane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.fragments.FooterFragment;
import com.example.blablaplane.object.vehicule.VehiculeAdapter;
import com.example.blablaplane.object.vehicule.VehiculeAdapterListener;
import com.example.blablaplane.object.vehicule.VehiculeArray;

public class ProfileActivity extends AppCompatActivity implements VehiculeAdapterListener, CallBackInterface {
    FragmentFactory fragmentFactory;
    FooterFragment footerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        footerFragment = new FooterFragment();
        fragmentFactory = new FragmentFactory();
        setContentView(R.layout.activity_profile);

        // Get the list of Message profils
        VehiculeArray vehiculeArray = VehiculeArray.getInstance();

        // Create the adapter
        VehiculeAdapter vehiculeAdapter = new VehiculeAdapter(getApplicationContext(), vehiculeArray);

        // Retrieve the list of Message profils
        ListView vehiculeList = findViewById(R.id.aeronef_list);

        // Set the adapter
        vehiculeList.setAdapter(vehiculeAdapter);

        // Set the listener
        vehiculeAdapter.setListener(this);
    }

    @Override
    public void onVehiculeClick(int vehiculeId) {
        return;
    }

    @Override
    public void callBackMethod(int nb) {
        fragmentFactory.changeActivity(nb,this);
    }
}