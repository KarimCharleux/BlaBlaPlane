package com.example.blablaplane.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.R;
import com.example.blablaplane.fragments.FooterFragment;

public class ConnexionActivity extends AppCompatActivity {
    FooterFragment footerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footerFragment = new FooterFragment();
        setContentView(R.layout.activity_connexion);
    }
}