package com.example.blablaplane.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.blablaplane.R;
import com.example.blablaplane.activity.select.SelectCityActivity;
import com.example.blablaplane.activity.select.SelectCityType;

public class FragmentAdding extends Fragment {

    public FragmentAdding() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View addingView = inflater.inflate(R.layout.fragment_adding, container, false);

        addingView.findViewById(R.id.btn_confirm_search_text).setOnClickListener(v -> {
            Intent intentNavigateNewPage = new Intent(getContext(), SelectCityActivity.class);
            intentNavigateNewPage.putExtra("SelectType", SelectCityType.CREATE_DEPARTURE);
            startActivity(intentNavigateNewPage);
        });

        return addingView;
    }
}