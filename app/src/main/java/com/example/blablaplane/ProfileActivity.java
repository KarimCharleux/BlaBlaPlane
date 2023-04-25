package com.example.blablaplane;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.blablaplane.object.MessageProfilArray;
import com.example.blablaplane.object.VehiculeArray;

public class ProfileActivity extends AppCompatActivity implements VehiculeAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}