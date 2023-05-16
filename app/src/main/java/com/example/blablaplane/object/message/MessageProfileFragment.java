package com.example.blablaplane.object.message;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blablaplane.R;

public class MessageProfileFragment extends Fragment {

    public MessageProfileFragment() {
        // Required empty public constructor
    }

    public static MessageProfileFragment newInstance(String param1, String param2) {
        return new MessageProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message_profile, container, false);
    }
}