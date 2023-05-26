package com.example.blablaplane.object.aircraft;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aircraft implements Serializable, Parcelable {

    private final int id;
    private final String name;
    private int passengerCount;
    private int image;

    public Aircraft(int id, String name, int passengerCount, int image) {
        this.id = id;
        this.name = name;
        this.passengerCount = passengerCount;
        this.image = image;
    }

    protected Aircraft(Parcel in) {
        id = in.readInt();
        name = in.readString();
        passengerCount = in.readInt();
        image = in.readInt();
    }

    public static final Creator<Aircraft> CREATOR = new Creator<Aircraft>() {
        @Override
        public Aircraft createFromParcel(Parcel in) {
            return new Aircraft(in);
        }

        @Override
        public Aircraft[] newArray(int size) {
            return new Aircraft[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return passengerCount;
    }

    public String getCapacityText() {
        return passengerCount + "";
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(passengerCount);
        dest.writeInt(image);
    }
}
