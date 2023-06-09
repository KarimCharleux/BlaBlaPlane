package com.example.blablaplane.notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Objects;

public class NotifyApp extends Application {

    public static final String CHANNEL_IDP = "Channel Paiement";
    public static final String CHANNEL_IDC = "Channel Calendrier";

    public static NotificationManager getNotificationManager() {
        return notificationManager;
    }

    private static NotificationManager notificationManager;

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channelp = new NotificationChannel(CHANNEL_IDP, "Channel Paiement", NotificationManager.IMPORTANCE_HIGH);
            channelp.setDescription("Channel de BlaBlaPane réservé aux confirmations de paiement.");
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channelp);
            NotificationChannel channelc = new NotificationChannel(CHANNEL_IDC, "Channel Calendrier ", NotificationManager.IMPORTANCE_DEFAULT);
            channelc.setDescription("Channel de BlaBlaPane réservé aux confirmation d'ajout sur le calendrier.");
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channelc);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }


}
