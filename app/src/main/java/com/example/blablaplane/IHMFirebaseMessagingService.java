package com.example.blablaplane;

import static com.example.blablaplane.NotifyApp.CHANNEL_IDP;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.blablaplane.activity.SwitcherActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class IHMFirebaseMessagingService extends FirebaseMessagingService {
    private final String TAG = "melanie " + getClass().getSimpleName();

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        Log.d(TAG,"message received");
        Message.getInstance().set(message);

        if(!Message.getInstance().isNull()){
           Intent intent =  new Intent(getApplicationContext(), SwitcherActivity.class);
           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

           NotificationCompat.Builder notifcation = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_IDP)
                   .setSmallIcon(R.drawable.ic_launcher_background)
                   .setContentTitle(Message.getInstance().getTitle())
                   .setContentText(Message.getInstance().body())
                   .setPriority(NotificationManager.IMPORTANCE_DEFAULT);

           NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
           notificationManager.notify(0,notifcation.build());
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG,"new token");
    }
}
