package com.example.blablaplane.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.List;

public class CreateNewAircraftActivity extends AppCompatActivity {

    EditText name, nbPassenger, picture;
    Button confirmButton, returnButton;
    CardView confirmCard, returnCard;
    DatabaseReference aircraftRef;

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

        SharedPreferences preferences = this.getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String userID = preferences.getString("user_id", null);

        DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

        Aircraft aircraft = new Aircraft(name.getText().toString(), Integer.parseInt(nbPassenger.getText().toString()), Integer.parseInt(picture.getText().toString()));
        this.aircraftRef = DataBase.AIRCRAFT_REFERENCE.child(String.valueOf(aircraft.getId()));

        View.OnClickListener confirm = view -> {
            if (name.getText().toString().equals("") || nbPassenger.getText().toString().equals("") || picture.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Merci de remplir toutes les sections", Toast.LENGTH_SHORT).show();
            } else {

                aircraftRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(CreateNewAircraftActivity.this, "⚠️ Ce nom est déjà utilisé !", Toast.LENGTH_SHORT).show();
                        } else {
                            DataBase.AIRCRAFT_REFERENCE.child(String.valueOf(aircraft.getId())).setValue(aircraft).addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {

                                    // Go to the home page and display a confirmation message
                                    Toast.makeText(CreateNewAircraftActivity.this, "✅ L'appareil a bien été créé", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(CreateNewAircraftActivity.this, SwitcherActivity.class);
                                    startActivity(intent);  // Go to the home page

                                    if (dataSnapshot.exists()) {
                                        // User exists in the database and we can get its data
                                        User user = dataSnapshot.getValue(User.class);
                                        assert user != null;
                                        List<Aircraft> userAircrafts = user.getAircraftList();
                                        userAircrafts.add(aircraft);
                                        userRef.child("listAricraft").setValue(userAircrafts);
                                        finish();
                                    }
                                } else {
                                    Toast.makeText(CreateNewAircraftActivity.this, "⚠️ Erreur lors de la création de l'appareil, veuillez réessayer", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(CreateNewAircraftActivity.this, "⚠️ Erreur lors de la création de l'appareil, veuillez réessayer", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };

        View.OnClickListener returnView = view -> finish();

        this.returnButton.setOnClickListener(returnView);
        this.returnCard.setOnClickListener(returnView);

        this.confirmButton.setOnClickListener(confirm);
        this.confirmCard.setOnClickListener(confirm);

    }
}