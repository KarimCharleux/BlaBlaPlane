package com.example.blablaplane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent =new Intent(getApplicationContext(),Publish_SelectCity_Activity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        getApplicationContext().startActivity(intent);
    }
}