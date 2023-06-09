package com.example.blablaplane.notifications;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Notification implements Parcelable {

    private final String title;
    private final String message;
    private final String channelId;
    private final int priority;

    public Notification(String title, String message, String channelId, int priority) {
        this.title = title;
        this.message = message;
        this.channelId = channelId;
        this.priority = priority;
    }

    protected Notification(Parcel in) {
        title = in.readString();
        message = in.readString();
        channelId = in.readString();
        priority = in.readInt();
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(message);
        parcel.writeString(channelId);
        parcel.writeInt(priority);
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getChannelId() {
        return channelId;
    }

    public int getPriority() {
        return priority;
    }
}