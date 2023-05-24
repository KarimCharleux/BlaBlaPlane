package com.example.blablaplane.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.fragments.FooterFragment;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.aircraft.AircraftAdapter;
import com.example.blablaplane.object.aircraft.AircraftAdapterListener;
import com.example.blablaplane.object.aircraft.AircraftArray;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements AircraftAdapterListener, CallBackInterface {
    FragmentFactory fragmentFactory;
    FooterFragment footerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        footerFragment = new FooterFragment();
        fragmentFactory = new FragmentFactory();
        setContentView(R.layout.activity_profile);

        SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String userID = preferences.getString("user_id", null);
        if (userID.isEmpty()) {
            Intent intent = new Intent(this, LandingActivity.class);
            startActivity(intent);
            finish();
        } else {
            DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

            // Check if the user exists and get its data
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User exists in the database and we can get its data
                        User user = dataSnapshot.getValue(User.class);

                        TextView userName = findViewById(R.id.first_name);
                        TextView userLastName = findViewById(R.id.last_name);
                        TextView userEmail = findViewById(R.id.email_user);
                        TextView userPhone = findViewById(R.id.phone_number);
                        RatingBar ratingBar = findViewById(R.id.ratingBar);


                        assert user != null;
                        userName.setText(user.getName());
                        userLastName.setText(user.getSurname().toUpperCase());
                        userEmail.setText(user.getEmail());
                        userPhone.setText(user.getPhone());
                        ratingBar.setRating(user.getRating());

                    } else {
                        Intent intent = new Intent(ProfileActivity.this, LandingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Here should be an error message
                }
            });
        }

        // Get the list of Aircraft
        AircraftArray aircraftArray = AircraftArray.getInstance();

        // Create the adapter
        AircraftAdapter aircraftAdapter = new AircraftAdapter(getApplicationContext(), aircraftArray);

        // Retrieve the list of Message profiles
        ListView aircraftList = findViewById(R.id.aircraft_list);

        // Set the adapter
        aircraftList.setAdapter(aircraftAdapter);

        // Set the listener
        aircraftAdapter.setListener(this);

        Button buttonDisconnect = findViewById(R.id.logoutButton);
        buttonDisconnect.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("user_id");
            editor.apply();

            Intent intent = new Intent(this, LandingActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public void onAircraftClick(int aircraftId) {
        System.out.println("Aircraft clicked : " + aircraftId);
    }

    @Override
    public void callBackMethod(int indexMenu) {
        fragmentFactory.changeActivity(indexMenu, this);
    }
}