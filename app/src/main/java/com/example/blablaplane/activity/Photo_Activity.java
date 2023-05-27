package com.example.blablaplane.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.blablaplane.R;
import com.example.blablaplane.fragments.PictureFragment;

public class Photo_Activity extends AppCompatActivity implements PictureActivityInterface {
    private Bitmap picture;
    private PictureFragment pictureFragment;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        pictureFragment = (PictureFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentPicture);

        if (pictureFragment == null) {
            pictureFragment = new PictureFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentPicture, pictureFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PictureActivityInterface.REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Camera authorization granted", Toast.LENGTH_SHORT);
                    toast.show();
                    pictureFragment.takePicture();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Camera authorization not granted", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureActivityInterface.REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                picture = (Bitmap) extras.get("data");
                pictureFragment.setPicture(picture);
                Toast toast = Toast.makeText(getApplicationContext(), "Picture taken", Toast.LENGTH_SHORT);
                toast.show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast toast = Toast.makeText(getApplicationContext(), "Picture not taken", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Picture not taken", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
        }
    }
}
