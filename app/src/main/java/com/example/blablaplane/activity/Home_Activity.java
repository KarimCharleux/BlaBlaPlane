package com.example.blablaplane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.fragments.FooterFragment;

public class Home_Activity extends AppCompatActivity implements CallBackInterface{

    FooterFragment footerFragment;
    FragmentFactory fragmentFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footerFragment = new FooterFragment();
        fragmentFactory = new FragmentFactory();
        setContentView(R.layout.activity_home);
    }

    @Override
    public void callBackMethod(int nb) {
        fragmentFactory.changeActivity(nb,this);
    }
}