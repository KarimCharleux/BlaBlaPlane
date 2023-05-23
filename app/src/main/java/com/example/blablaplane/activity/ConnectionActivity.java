package com.example.blablaplane.activity;

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
import com.example.blablaplane.fragments.FooterFragment;
import com.example.blablaplane.object.DataBase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ConnectionActivity extends AppCompatActivity {
    FooterFragment footerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footerFragment = new FooterFragment();
        setContentView(R.layout.activity_connection);

        Button login = findViewById(R.id.loginButton);
        login.setOnClickListener(view -> {

            EditText email = findViewById(R.id.EmailAddress);
            EditText password = findViewById(R.id.Password);

            if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                Toast.makeText(this, "⚠️ Veuillez remplir tous les champs !", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!email.getText().toString().matches(DataBase.EMAIL_REGEX)) {
                Toast.makeText(this, "⚠️ Veuillez entrer une adresse mail valide !", Toast.LENGTH_SHORT).show();
                return;
            }

            String userId = email.getText().toString().replace(".", "").replace("@", "");

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

                            Toast.makeText(ConnectionActivity.this, "✅ Vous êtes connecté !", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ConnectionActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ConnectionActivity.this, "⚠️ Mauvais identifiants !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ConnectionActivity.this, "⚠️ Mauvais identifiants !", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ConnectionActivity.this, "⚠️ Une erreur est survenue !", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}