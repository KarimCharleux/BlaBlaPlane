package com.example.blablaplane.activity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.blablaplane.Exceptions.NoPlacesAvailableException;
import com.example.blablaplane.R;

import java.io.IOException;
import java.util.List;


public class Publish_SelectCity_Activity extends AppCompatActivity {

    //systeme de recherche de localisation
    private LocationRequest locationRequest;
    private Location location;


    private ListView lv_city;

    private Button btn_search;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_selectcity);

        EditText input_city = findViewById(R.id.input_selectCity);

        btn_search = findViewById(R.id.btn_confirm_search);

        Log.d("CREATION BTN","creation");

       btn_search.setOnClickListener(view -> {
           String addressRequest = "";
           //on recupere la valeur de l'input dans une string
           addressRequest = input_city.getText().toString();

           //on cree une liste d'adresses
           List<Address> addressList;

           Log.d("INPUT",addressRequest);

           if(!TextUtils.isEmpty(addressRequest)){
               Geocoder geocoder = new Geocoder(getApplicationContext());

               try { addressList = geocoder.getFromLocationName(addressRequest,10);}
               catch (IOException err){throw new NoPlacesAvailableException("No Places available");}

               Log.d("GPS RESULTS",""+addressList.toString());

               //si geocoder à trouver des résultats, on affiche des la listeview
               if(!addressList.isEmpty()){
                   //TODO : mettre en forme la listview

               }
               //sinon...
               else{

               }
           }
       });
    }



}