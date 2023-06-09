package com.example.blablaplane.notifications;

import androidx.core.app.NotificationCompat;

public class CalendarNotificationFactory extends NotificationFactoryMethod{

    @Override
    public Notification createNotification(String channelId, String destination) throws Throwable {
        String title = "BlablaPlane - Calendrier";
        String message = "Votre trajet jusqu'à " + destination + " a été ajouté à votre calendrier.";
        int priority = NotificationCompat.PRIORITY_DEFAULT;
        return new Notification(title, message, channelId, priority);
    }
}
