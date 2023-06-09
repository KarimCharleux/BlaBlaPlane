package com.example.blablaplane.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.blablaplane.R;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.aircraft.Aircraft;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CreateNewAircraftActivity extends AppCompatActivity {

    EditText name, nbPassenger, picture;
    Button confirmButton, returnButton;
    CardView confirmCard, returnCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_aircraft);

        name = findViewById(R.id.firstName);
        nbPassenger = findViewById(R.id.numberPassenger);
        picture = findViewById(R.id.picture);

        confirmButton = findViewById(R.id.RegisterButton);
        returnButton = findViewById(R.id.ReturnButton);
        confirmCard = findViewById(R.id.cardView4);
        returnCard = findViewById(R.id.cardView5);

        // Get the user ID from the cache
        String userID = this.getSharedPreferences("user_data", Context.MODE_PRIVATE).getString("user_id", null);

        View.OnClickListener confirmCreate = view -> {
            if (name.getText().toString().equals("") || nbPassenger.getText().toString().equals("") || picture.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), R.string.fillAllField, Toast.LENGTH_SHORT).show();
            } else {
                Aircraft theNewAircraft = new Aircraft(name.getText().toString(), Integer.parseInt(nbPassenger.getText().toString()), R.drawable.smaal_plane_round_logo);

                // Add the new aircraft to the Aircraft database for future use
                DataBase.AIRCRAFT_REFERENCE.child(String.valueOf(theNewAircraft.getId())).setValue(theNewAircraft);

                // Next step : Add the new aircraft to the user's aircraft list
                DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

                // Fetch the user from the database
                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Get the user object
                        User user = snapshot.getValue(User.class);
                        assert user != null;

                        // Add the new aircraft to the user's list of aircraft
                        user.addAircraft(theNewAircraft);

                        // Update the user in the database
                        userRef.setValue(user);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.err.println("Error while fetching user from the database");
                    }
                });

                Toast.makeText(getApplicationContext(), "✅ " + theNewAircraft.getName() + " ajouté !", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CreateNewAircraftActivity.this, SwitcherActivity.class);
                startActivity(intent);
            }
        };

        View.OnClickListener returnView = view -> onBackPressed();

        this.returnButton.setOnClickListener(returnView);
        this.returnCard.setOnClickListener(returnView);

        this.confirmButton.setOnClickListener(confirmCreate);
        this.confirmCard.setOnClickListener(confirmCreate);
    }
}