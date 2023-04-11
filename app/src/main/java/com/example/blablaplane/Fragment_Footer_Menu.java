package com.example.blablaplane;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class Fragment_Footer_Menu extends Fragment implements View.OnClickListener {

    private ImageButton buttonSearching, buttonAdding, buttonDefault, buttonChatting, buttonProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_footer, container, false);

        buttonSearching = view.findViewById(R.id.buttonSearching);
        buttonAdding = view.findViewById(R.id.buttonAdding);
        buttonDefault = view.findViewById(R.id.buttonDefault);
        buttonChatting = view.findViewById(R.id.buttonChatting);
        buttonProfile = view.findViewById(R.id.buttonProfile);

        buttonSearching.setOnClickListener(this);
        buttonAdding.setOnClickListener(this);
        buttonDefault.setOnClickListener(this);
        buttonChatting.setOnClickListener(this);
        buttonProfile.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intentNavigateNewPage;
        switch (v.getId()) {
            case R.id.buttonSearching:
                /*intentNavigateNewPage = new Intent(getActivity(), SearchingPage.class);
                startActivity(intentNavigateNewPage);*/
                break;
            case R.id.buttonAdding:
                /*intentNavigateNewPage = new Intent(getActivity(), AddingPage.class);
                startActivity(intentNavigateNewPage);*/
                break;
            case R.id.buttonDefault:
                /*intentNavigateNewPage = new Intent(getActivity(), defautlPage.class);
                startActivity(intentNavigateNewPage);*/
                break;
            case R.id.buttonChatting:
                /*intentNavigateNewPage = new Intent(getActivity(), chattingPage.class);
                startActivity(intentNavigateNewPage);*/
                break;
            case R.id.buttonProfile:
                /*intentNavigateNewPage = new Intent(getActivity(), profilePage.class);
                startActivity(intentNavigateNewPage);*/
                break;
            default:
                break;
        }
    }
}
