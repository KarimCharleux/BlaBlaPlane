package com.example.blablaplane.notifications;

public abstract class NotificationFactoryMethod {

    public abstract Notification createNotification(String channelId, String info) throws Throwable;

}
