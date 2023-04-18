package com.example.blablaplane;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AeronefFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AeronefFragment extends Fragment {


    public AeronefFragment() {
        // Required empty public constructor
    }


    public static AeronefFragment newInstance(String param1, String param2) {
        AeronefFragment fragment = new AeronefFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aeronef, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = (ListView) view.findViewById(R.id.aeronef_list);
        List<String> list = new ArrayList<>(Arrays.asList("avion a", "avion b"));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

    }
}