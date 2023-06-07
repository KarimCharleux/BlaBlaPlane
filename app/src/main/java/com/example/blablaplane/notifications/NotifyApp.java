package com.example.blablaplane.notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Objects;

public class NotifyApp extends Application {

    public static final String CHANNEL_IDP= "Channel Paiement";

    public static NotificationManager getNotificationManager() {
        return notificationManager;
    }

    private static NotificationManager notificationManager;

    private void createNotificationChannel(String name, String description, int importance) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_IDP,name,importance);
            channel.setDescription(description);
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

    @Override
    public void onCreate(){
        super.onCreate();
        createNotificationChannel("channel de paiement", "channel de paiement pour l'application blablaplane",NotificationManager.IMPORTANCE_HIGH);
    }



}
