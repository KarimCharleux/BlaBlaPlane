package com.example.blablaplane.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.Message;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.fragments.FooterFragment;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.android.libraries.places.api.model.Place;

import java.util.Observable;
import java.util.Observer;

public class HomeActivity extends AppCompatActivity implements CallBackInterface, Observer {

    FooterFragment footerFragment;
    FragmentFactory fragmentFactory;

    private final String TAG = "melanie " + getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footerFragment = new FooterFragment();
        fragmentFactory = new FragmentFactory();
        setContentView(R.layout.activity_home);

        Message.getInstance().addObserver(this);

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

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener( task -> {
            if(!task.isSuccessful()){
                Log.d(TAG,"erreur avec Firebase", task.getException());
            }
            Log.w(TAG,"token notif="+task.getResult());
        });

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

    @Override
    public void update(Observable observable, Object o) {
        Log.d(TAG,"message from: "+ Message.getInstance().from());
        if(Message.getInstance().isNull()){
            Log.d(TAG,"title: "+Message.getInstance().getTitle());
            Log.d(TAG,"title: "+Message.getInstance().body());
        }
    }
}