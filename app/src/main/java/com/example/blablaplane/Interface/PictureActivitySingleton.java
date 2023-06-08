package com.example.blablaplane.Interface;

import android.graphics.Bitmap;

import com.bumptech.glide.signature.ObjectKey;

public class PictureActivitySingleton {
    public final static int REQUEST_CAMERA = 301013; // C => 3rd letter of the alphabet, A => 1st letter of the alphabet, M => 13th letter of the alphabet
    public static ObjectKey cacheKey = new ObjectKey("profile_image");
    public static Bitmap pictureProfile = null;
}