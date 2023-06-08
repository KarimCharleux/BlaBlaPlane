package com.example.blablaplane.Interface;

import com.example.blablaplane.object.trip.Airport;
import com.google.android.gms.maps.model.LatLng;

public interface OnAirportSelectedListenerInterface {
    void onAirportSelected(Airport airport);

    void onLatLngSelected(LatLng latLng);
}
