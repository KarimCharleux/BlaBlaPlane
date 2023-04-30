package com.example.blablaplane.object.vehicule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blablaplane.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VehiculeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehiculeFragment extends Fragment {

    public VehiculeFragment() {
        // Required empty public constructor
    }

    public static VehiculeFragment newInstance() {
        VehiculeFragment fragment = new VehiculeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vehicule_choice, container, false);
    }
}