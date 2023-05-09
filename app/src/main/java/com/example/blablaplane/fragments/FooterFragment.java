package com.example.blablaplane.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.activity.Home_Activity;
import com.example.blablaplane.activity.MessageActivity;
import com.example.blablaplane.activity.MyTripActivity;
import com.example.blablaplane.activity.ProfileActivity;
import com.example.blablaplane.R;

import java.util.ArrayList;
import java.util.List;

public class FooterFragment extends Fragment {

    private CallBackInterface callBackInterface;
    private ArrayList<ImageButton> imageButtons;

    public void setCallBackInterface(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_footer, container, false);

        ImageButton buttonSearching = view.findViewById(R.id.buttonSearching);
        ImageButton buttonAdding = view.findViewById(R.id.buttonAdding);
        ImageButton buttonDefault = view.findViewById(R.id.buttonDefault);
        ImageButton buttonChatting = view.findViewById(R.id.buttonChatting);
        ImageButton buttonProfile = view.findViewById(R.id.buttonProfile);


        ArrayList<ImageButton> imageButtons = new ArrayList<>();

        imageButtons.add(buttonSearching);imageButtons.add(buttonAdding);imageButtons.add(buttonDefault);
        imageButtons.add(buttonChatting);
        imageButtons.add(buttonProfile);

        for(ImageButton IB : imageButtons){
            IB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBackInterface = (CallBackInterface) getActivity();
                    callBackInterface.callBackMethod(imageButtons.indexOf(IB));
                }
            });
        }

        return view;
    }

}
