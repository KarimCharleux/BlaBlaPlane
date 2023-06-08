package com.example.blablaplane.Interface;

import com.bumptech.glide.signature.ObjectKey;

public interface PictureActivityInterface {
    int REQUEST_CAMERA = 301013; // C => 3rd letter of the alphabet, A => 1st letter of the alphabet, M => 13th letter of the alphabet
    ObjectKey cacheKey = new ObjectKey("profile_image");
}