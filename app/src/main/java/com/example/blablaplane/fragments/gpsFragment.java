package com.example.blablaplane.fragments;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.blablaplane.R;
import com.example.blablaplane.activity.GPSActivityInterface;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class gpsFragment extends Fragment {

    private GPSActivityInterface activity;
    private TextView placeNameTextView;
    private Location currentLocation;


    public gpsFragment() {
    }

    public gpsFragment(GPSActivityInterface gpsActivityInterface) {
        activity = gpsActivityInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gps, container, false);
        placeNameTextView = view.findViewById(R.id.place_name);
        final ImageView imageGPSgranted = view.findViewById(R.id.image_gps_granted);
        final ImageView imageGPSActivated = view.findViewById(R.id.image_gps_activated);
        boolean permissionGranted = ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (permissionGranted) {
            imageGPSActivated.setImageResource(R.drawable.gpsOn);
            LocationListener listener = new LocationListener() {
                @Override
                public void onLocationChanged(android.location.Location location) {
                    currentLocation = location;
                    activity.moveCamera();
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    imageGPSgranted.setImageResource(R.drawable.gpsOn);
                }

                @Override
                public void onProviderDisabled(String provider) {
                    imageGPSActivated.setImageResource(R.drawable.locked);
                }

                @Override
                public void onProviderEnabled(String provider) {
                    imageGPSActivated.setImageResource(R.drawable.unlocked);
                }


            };
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(getContext().LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, listener);
            imageGPSActivated.setImageResource(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ? R.drawable.unlocked : R.drawable.locked);
        } else {
            imageGPSActivated.setImageResource(R.drawable.locked);
            imageGPSgranted.setImageResource(R.drawable.gpsOff);
        }


        return view;
    }



    LatLng getPosition() {
        return new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
    }

    String getPlaceName() {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
            return addresses.get(0).getLocality();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void setPlaceName(String placeName) {
        placeNameTextView.setText(placeName);
    }




}
