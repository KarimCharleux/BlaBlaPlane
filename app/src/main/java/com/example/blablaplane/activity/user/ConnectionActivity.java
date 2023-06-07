package com.example.blablaplane.activity.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.R;
import com.example.blablaplane.activity.SwitcherActivity;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ConnectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        Button login = findViewById(R.id.loginButton);
        login.setOnClickListener(view -> {

            EditText email = findViewById(R.id.registerEmailAddress);
            EditText password = findViewById(R.id.registerPassword);

            if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.fillAllField, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!email.getText().toString().matches(DataBase.EMAIL_REGEX)) {
                Toast.makeText(this, R.string.errorEmail, Toast.LENGTH_SHORT).show();
                return;
            }

            String userId = User.generateUserId(email.getText().toString());

            DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userId);

            // Check if the user exists in the firebase database
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User exists so check if the password is correct
                        if (Objects.equals(dataSnapshot.child("password").getValue(), password.getText().toString())) {
                            // Password is correct so we can connect the user and save his id in the shared preferences
                            SharedPreferences sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("user_id", userId);
                            editor.apply();

                            Toast.makeText(ConnectionActivity.this, R.string.confirmationConnected, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ConnectionActivity.this, SwitcherActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ConnectionActivity.this, R.string.wrongId, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ConnectionActivity.this, R.string.wrongId, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ConnectionActivity.this, R.string.errorUnknown, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}