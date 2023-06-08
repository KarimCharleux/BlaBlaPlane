package com.example.blablaplane.activity.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.LruCache;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.example.blablaplane.Interface.PictureActivityInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.activity.LandingActivity;
import com.example.blablaplane.activity.Photo_Activity;
import com.example.blablaplane.activity.SwitcherActivity;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ModifyProfile extends AppCompatActivity {

    DatabaseReference userRef;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profile);

        SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        userID = preferences.getString("user_id", null);
        if (userID.isEmpty()) {
            Intent intent = new Intent(this, LandingActivity.class);
            startActivity(intent);
            this.finish();
        } else {

            userRef = DataBase.USERS_REFERENCE.child(userID);

            // Check if the user exists and get its data
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User exists in the database and we can get its data
                        User user = dataSnapshot.getValue(User.class);

                        TextView userName = findViewById(R.id.modifyFirstName);
                        TextView userLastName = findViewById(R.id.modifyLastName);
                        TextView userPhone = findViewById(R.id.modifyPhoneNumber);

                        assert user != null;
                        userName.setText(user.getName());
                        userLastName.setText(user.getSurname());
                        userPhone.setText(user.getPhone());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Here should be an error message
                }
            });
        }

        ImageView pictureProfil = findViewById(R.id.picture_profile);
        pictureProfil.setOnClickListener(x ->{
            Intent intent = new Intent(ModifyProfile.this, Photo_Activity.class);
            startActivity(intent);
        });

        Button modifyButton = findViewById(R.id.ModifyProfile_ConfirmButton);
        modifyButton.setOnClickListener(v -> {
            TextView userFirstName = findViewById(R.id.modifyFirstName);
            TextView userLastName = findViewById(R.id.modifyLastName);
            TextView userPhone = findViewById(R.id.modifyPhoneNumber);

            if (userFirstName.getText().toString().isEmpty() || userLastName.getText().toString().isEmpty() || userPhone.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.fillAllField, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!userPhone.getText().toString().matches(DataBase.PHONE_REGEX)) {
                Toast.makeText(this, R.string.errorPhone, Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a map with the new values of the user
            Map<String, Object> updates = new HashMap<>();
            updates.put("name", userFirstName.getText().toString());
            updates.put("surname", userLastName.getText().toString());
            updates.put("phone", userPhone.getText().toString());

            // Update the user in the database with the new values
            userRef.updateChildren(updates)
                    .addOnSuccessListener(e -> Toast.makeText(ModifyProfile.this, R.string.confirmationModified, Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(ModifyProfile.this, R.string.errorUpdateProfile, Toast.LENGTH_SHORT).show());

            // Go back to the home page
            Intent intent = new Intent(ModifyProfile.this, SwitcherActivity.class);
            startActivity(intent);
        });

        Button returnButton = findViewById(R.id.modifyProfileReturnButton);
        returnButton.setOnClickListener(v -> super.onBackPressed());
        CardView returnButtonCard = findViewById(R.id.modifyCardViewReturn);
        returnButtonCard.setOnClickListener(v -> super.onBackPressed());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPictureProfil();
    }

    private void setPictureProfil() {
        ImageView imageView = findViewById(R.id.picture_profile);
        Drawable cachedProfileImage = null;
        try {
            cachedProfileImage = Glide.with(this)
                    .load(PictureActivityInterface.cacheKey)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .submit()
                    .get();
            if (cachedProfileImage != null) {
                imageView.setImageDrawable(cachedProfileImage);
            } else {
                imageView.setImageResource(R.drawable.pp_default);
            }
        } catch (Exception e) {
            e.printStackTrace();
            imageView.setImageResource(R.drawable.pp_default);
        }

    }
}
