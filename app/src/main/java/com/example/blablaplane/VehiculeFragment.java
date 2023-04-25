package com.example.blablaplane;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link vehicule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class vehicule extends Fragment {

    public vehicule() {
        // Required empty public constructor
    }

    public static TrajectFragment newInstance() {
        TrajectFragment fragment = new TrajectFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_traject, container, false);
    }
}