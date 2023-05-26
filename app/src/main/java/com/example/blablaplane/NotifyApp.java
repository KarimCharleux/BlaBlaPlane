package com.example.blablaplane;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotifyApp extends Application {

    public static final String CHANNEL_ID= "my channel";

    @Override
    public void onCreate(){
        super.onCreate();
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
