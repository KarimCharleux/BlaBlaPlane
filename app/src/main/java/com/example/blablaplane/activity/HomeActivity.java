package com.example.blablaplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.fragments.FooterFragment;

public class HomeActivity extends AppCompatActivity implements CallBackInterface {

    FooterFragment footerFragment;
    FragmentFactory fragmentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footerFragment = new FooterFragment();
        fragmentFactory = new FragmentFactory();
        setContentView(R.layout.activity_home);

        ConstraintLayout button = findViewById(R.id.searchBox);
        TextView buttonText = findViewById(R.id.searchBoxText);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListTripActivity.class);
            startActivity(intent);
        });

        buttonText.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListTripActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void callBackMethod(int indexMenu) {
        fragmentFactory.changeActivity(indexMenu, this);
    }
}