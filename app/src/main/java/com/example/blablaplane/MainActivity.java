package com.example.blablaplane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {



    Fragment_Footer_Menu myFragment;
    CheckBox isPiloteCheckBox;
    CheckBox AirplaneCheckBox;
    CheckBox HelicopterCheckBox;
    ConstraintLayout layoutWithSubCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isPiloteCheckBox = findViewById(R.id.checkBoxInscription);
        AirplaneCheckBox = findViewById(R.id.subCheckBoxInscriptionAirplane);
        HelicopterCheckBox = findViewById(R.id.subCheckBoxInscriptionHelicopter);
        layoutWithSubCheckBox = findViewById(R.id.subCheckBoxInscriptionSubLayout);

        this.layoutWithSubCheckBox.setVisibility(View.INVISIBLE);
        this.AirplaneCheckBox.setVisibility(View.INVISIBLE);
        this.HelicopterCheckBox.setVisibility(View.INVISIBLE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        myFragment = new Fragment_Footer_Menu();
        fragmentTransaction.add(R.id.fragmentFooter, myFragment);
        fragmentTransaction.commit();


        this.isPiloteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layoutWithSubCheckBox.setVisibility(View.VISIBLE);
                    AirplaneCheckBox.setVisibility(View.VISIBLE);
                    HelicopterCheckBox.setVisibility(View.VISIBLE);
                } else {
                    layoutWithSubCheckBox.setVisibility(View.INVISIBLE);
                    AirplaneCheckBox.setVisibility(View.INVISIBLE);
                    HelicopterCheckBox.setVisibility(View.INVISIBLE);
                }
            }
        });


        this.HelicopterCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = HelicopterCheckBox.isChecked();
                HelicopterCheckBox.setChecked(!isChecked);
            }
        });

        AirplaneCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = AirplaneCheckBox.isChecked();
                AirplaneCheckBox.setChecked(!isChecked);
            }
        });

        isPiloteCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = isPiloteCheckBox.isChecked();
                isPiloteCheckBox.setChecked(!isChecked);
            }
        });
    }
}










