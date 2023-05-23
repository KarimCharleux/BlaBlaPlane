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
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    EditText firstName, lastName, email, phone, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.EmailAddress);
        phone = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.Password);
        confirmPassword = findViewById(R.id.RepeatPassword);

        Button register = findViewById(R.id.RegisterButton);
        register.setOnClickListener(v -> {
            if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty() || phone.getText().toString().isEmpty()) {
                Toast.makeText(this, "⚠️ Veuillez remplir tous les champs !", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                Toast.makeText(this, "⚠️ Les mots de passes doivent être les mêmes !", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!email.getText().toString().matches(DataBase.EMAIL_REGEX)) {
                Toast.makeText(this, "⚠️ Veuillez entrer une adresse mail valide !", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!phone.getText().toString().matches(DataBase.PHONE_REGEX)) {
                Toast.makeText(this, "⚠️ Veuillez entrer un numéro de téléphone valide !", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString(), phone.getText().toString(), 5);

            DatabaseReference userRef = DataBase.USERS_REFERENCE.child(user.getId());

            // Check if the user already exists in the firebase database
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User already exists
                        Toast.makeText(RegisterActivity.this, "⚠️ Cet email est déjà utilisé !", Toast.LENGTH_SHORT).show();
                    } else {
                        // User does not exist so create it
                        DataBase.USERS_REFERENCE.child(user.getId()).setValue(user).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Store the user id in the cache of the app
                                SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("user_id", user.getId());
                                editor.apply();

                                // Go to the home page and display a confirmation message
                                Toast.makeText(RegisterActivity.this, "✅ Votre compte a bien été créé", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "⚠️ Erreur lors de la création du compte, veuillez réessayer", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(RegisterActivity.this, "⚠️ Erreur lors de la création du compte, veuillez réessayer", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}