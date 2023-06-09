package com.example.blablaplane.notifications;

import androidx.core.app.NotificationCompat;

public class PaymentNotificationFactory extends NotificationFactoryMethod {

    @Override
    public Notification createNotification(String channelId, String paymentMethod) {
        String title = "BlablaPlane - Confirmation de paiement";
        String message = "Votre paiement via " + paymentMethod + " a été validé.";
        int priority = NotificationCompat.PRIORITY_HIGH;
        return new Notification(title, message, channelId, priority);
    }
}
