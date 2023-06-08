package com.example.blablaplane.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.blablaplane.Interface.PictureActivityInterface;
import com.example.blablaplane.R;
import com.bumptech.glide.Glide;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Photo_Activity extends AppCompatActivity implements PictureActivityInterface {
    private Bitmap picture;
    private ImageView imageView;
    DatabaseReference userRef;
    Bitmap currentUserPicture;
    ActivityResultLauncher<Intent> launcher;

    CardView cardView_takePicture;
    Button takePictureButton;
    ImageView returnButton;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        this.cardView_takePicture = findViewById(R.id.cardView_takePicture);
        this.takePictureButton = findViewById(R.id.takePictureButton);
        this.returnButton = findViewById(R.id.returnButton);


        SharedPreferences preferences = this.getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String userID = preferences.getString("user_id", null);
        if(userID != null)
        {
            this.userRef = DataBase.USERS_REFERENCE.child(userID);
        }

        // Check if the user exists and get its data
        this.userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User exists in the database and we can get its data
                    User user = dataSnapshot.getValue(User.class);
                    assert user != null;
                    // Put the first letter of the name in uppercase
                    int lengthPassword = user.getPassword().length();
                    String hiddenPassword = "";
                    for (int i = 0; i < lengthPassword; i++) {
                        hiddenPassword += "*";
                    }

                    //TODO create a way to get picture from firebase
                    String name = user.getName().substring(0, 1).toUpperCase() + user.getName().substring(1);
                    currentUserPicture = user.getPicture();
                    setPicture();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    int resultCode = result.getResultCode();
                    if (resultCode == RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        currentUserPicture = (Bitmap) extras.get("data");
                        setPicture();
                        Toast toast = Toast.makeText(getApplicationContext(), "Picture taken", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (resultCode == RESULT_CANCELED) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Picture not taken", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Picture not taken", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

        View.OnClickListener buttonTakePhoto = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions(Photo_Activity.this, new String[]{Manifest.permission.CAMERA}, PictureActivityInterface.REQUEST_CAMERA);
                } else {
                    takePicture();
                }
            }
        };

        View.OnClickListener returnButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRef.child("picture").setValue(currentUserPicture);
                finish();
            }
        };

        this.cardView_takePicture.setOnClickListener(buttonTakePhoto);
        this.takePictureButton.setOnClickListener(buttonTakePhoto);
        this.returnButton.setOnClickListener(returnButton);
    }


    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        launcher.launch(intent);
    }


    private void setPicture() {
        this.imageView = findViewById(R.id.imageView);
        Glide.with(this)
                .load(currentUserPicture)
                .circleCrop()
                .into(imageView);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PictureActivityInterface.REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Camera authorization granted", Toast.LENGTH_SHORT);
                    toast.show();
                    takePicture();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Camera authorization not granted", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }
}
