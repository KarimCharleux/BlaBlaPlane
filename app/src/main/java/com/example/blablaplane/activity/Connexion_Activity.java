package com.example.blablaplane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.blablaplane.R;
import com.example.blablaplane.fragments.FooterFragment;

public class Connexion_Activity extends AppCompatActivity {
    FooterFragment footerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footerFragment = new FooterFragment();
        setContentView(R.layout.activity_connexion);
    }
}