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

public class RegisterActivity extends AppCompatActivity {
    EditText firstName, lastName, email, phone, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.registerFirstName);
        lastName = findViewById(R.id.registerLastName);
        email = findViewById(R.id.registerEmailAddress);
        phone = findViewById(R.id.registerPhoneNumber);
        password = findViewById(R.id.registerPassword);
        confirmPassword = findViewById(R.id.registerRepeatPassword);

        Button register = findViewById(R.id.RegisterButton);
        register.setOnClickListener(v -> {
            if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty() || phone.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.fillAllField, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                Toast.makeText(this, R.string.errorPassword, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!email.getText().toString().matches(DataBase.EMAIL_REGEX)) {
                Toast.makeText(this, R.string.errorEmail, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!phone.getText().toString().matches(DataBase.PHONE_REGEX)) {
                Toast.makeText(this, R.string.errorPhone, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RegisterActivity.this, R.string.errorEmailDuplicated, Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(RegisterActivity.this, R.string.confirmationCreated, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, SwitcherActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, R.string.errorAccountRetry, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(RegisterActivity.this, R.string.errorAccountRetry, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}