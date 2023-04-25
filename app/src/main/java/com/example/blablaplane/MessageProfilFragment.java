package com.example.blablaplane;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessageProfilFragment extends Fragment {

    public MessageProfilFragment() {
        // Required empty public constructor
    }

    public static MessageProfilFragment newInstance(String param1, String param2) {
        MessageProfilFragment fragment = new MessageProfilFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message_profil, container, false);
    }
}