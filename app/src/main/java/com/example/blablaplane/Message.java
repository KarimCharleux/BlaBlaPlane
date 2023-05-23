package com.example.blablaplane;

import com.google.firebase.messaging.RemoteMessage;

import java.util.Observable;

public class Message extends Observable {

    private static Message instance;
    private static RemoteMessage message;

    private Message(){
        super();
    }

    public static Message getInstance(){
        if (instance==null) instance=new Message();
        return instance;
    }

    public void set(RemoteMessage message){
        Message.message = message;
        setChanged();
        notifyObservers(message);
    }

    public String from(){
        return message.getFrom();
    }

    public String body(){
        return message.getNotification().getBody();
    }

    public String getTitle(){
        return message.getNotification().getTitle();
    }

    public boolean isNull(){
        return message.getNotification() == null;
    }
}