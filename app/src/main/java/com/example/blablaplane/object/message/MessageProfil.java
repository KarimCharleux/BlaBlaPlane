package com.example.blablaplane.object.message;

import java.text.SimpleDateFormat;

public class MessageProfil {

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final int id;
    private final String firstName;
    private final String lastName;

    private final String pictureFilePath;

    public MessageProfil(int id, String firstName, String lastName, String pictureFilePath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureFilePath = pictureFilePath;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPictureFilePath() {
        return pictureFilePath;
    }
}