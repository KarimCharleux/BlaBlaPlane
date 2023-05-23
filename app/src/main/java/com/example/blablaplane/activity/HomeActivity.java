package com.example.blablaplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.Message;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.fragments.FooterFragment;
import com.google.firebase.messaging.FirebaseMessaging;

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
    }



    @Override
    public void callBackMethod(int indexMenu) {
        fragmentFactory.changeActivity(indexMenu, this);
    }

    @Override
    public void update(Observable observable, Object o) {
        Message message = (Message)o;
        Log.d(TAG,"messafe from: "+ Message.getInstance().from());
        if(Message.getInstance().isNull()){
            Log.d(TAG,"title: "+Message.getInstance().getTitle());
            Log.d(TAG,"title: "+Message.getInstance().body());
        }
    }
}