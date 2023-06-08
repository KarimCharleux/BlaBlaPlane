package com.example.blablaplane.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;

import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.example.blablaplane.Interface.PictureActivitySingleton;
import com.example.blablaplane.R;
import com.bumptech.glide.Glide;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.ExecutionException;

public class Photo_Activity extends AppCompatActivity {
    ActivityResultLauncher<Intent> launcher;
    private Bitmap picture;
    private ImageView imageView;
    Bitmap currentUserPicture;

    CardView cardView_takePicture;
    Button takePictureButton;
    ImageView returnButton;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        this.returnButton = findViewById(R.id.returnButton);
        this.cardView_takePicture = findViewById(R.id.cardView_takePicture);
        this.takePictureButton = findViewById(R.id.takePictureButton);
        this.imageView = findViewById(R.id.imageView);

        askPermission();

        Glide.with(getApplicationContext())
                .load(PictureActivitySingleton.cacheKey)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        imageView.setImageResource(R.drawable.pp_default);
                    }
                });

        View.OnClickListener buttonTakePhoto = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions(Photo_Activity.this, new String[]{Manifest.permission.CAMERA}, PictureActivitySingleton.REQUEST_CAMERA);
                } else {
                    takePicture();
                }
            }
        };

        View.OnClickListener returnButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfileImageToCache(currentUserPicture);
                finish();
            }
        };
        this.returnButton.setOnClickListener(returnButton);
        this.cardView_takePicture.setOnClickListener(buttonTakePhoto);
        this.takePictureButton.setOnClickListener(buttonTakePhoto);

        takePicture();
    }

    private void askPermission() {
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


    private void saveProfileImageToCache(Bitmap imageBitmap) {
        Glide.with(getApplicationContext())
                .load(imageBitmap)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(PictureActivitySingleton.cacheKey)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        // L'image a été chargée avec succès et mise en cache avec la clé spécifiée
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // L'image a été supprimée du cache
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        // Une erreur s'est produite lors du chargement de l'image
                    }
                });
        PictureActivitySingleton.pictureProfile = currentUserPicture;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PictureActivitySingleton.REQUEST_CAMERA:
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