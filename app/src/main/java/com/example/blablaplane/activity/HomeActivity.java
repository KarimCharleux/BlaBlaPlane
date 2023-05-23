package com.example.blablaplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.fragments.FooterFragment;
import com.google.android.libraries.places.api.model.Place;

public class HomeActivity extends AppCompatActivity implements CallBackInterface {

    FooterFragment footerFragment;
    FragmentFactory fragmentFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footerFragment = new FooterFragment();
        fragmentFactory = new FragmentFactory();
        setContentView(R.layout.activity_home);

        //reception d'objets de la vue précédente
        Intent intent = getIntent();

        ConstraintLayout depart = findViewById(R.id.startBox);
        depart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNavigateNewPage = new Intent(getApplicationContext(), PublishSelectCityActivity.class);
                System.out.println("VERS HOME");
                intentNavigateNewPage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intentNavigateNewPage);
            }
        });


        if (intent != null){
            Place startingPlace = intent.getParcelableExtra(getResources().getString(R.string.RESEARCH_INTENT_stratingPlace));
            TextView TV_depart = findViewById(R.id.startText);

            if (startingPlace != null){
                TV_depart.setText(startingPlace.getName());
            }
            else{
                TV_depart.setText(R.string.ACCUEIL_start);
            }
        }
    }

    @Override
    public void callBackMethod(int nb) {
        fragmentFactory.changeActivity(nb,this);
    }
}