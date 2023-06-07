package com.example.blablaplane;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Objects;

public class NotifyApp extends Application {

    public static final String CHANNEL_ID= "my channel";

    public static NotificationManager getNotificationManager() {
        return notificationManager;
    }

    private static NotificationManager notificationManager;

    private void createNotificationChannel(String name, String description, int importance) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,importance);
            channel.setDescription(description);
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

    @Override
    public void onCreate(){
        super.onCreate();
        createNotificationChannel("channel de blablaplane", "channel pour l'application blablaplane",NotificationManager.IMPORTANCE_DEFAULT);
        String channelName = "my channel Name";
        NotificationChannel notificationChannel = null;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationChannel = new NotificationChannel(CHANNEL_ID,channelName, NotificationManager.IMPORTANCE_DEFAULT);
        }
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }



}
