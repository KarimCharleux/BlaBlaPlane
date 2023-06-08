package com.example.blablaplane.fragments;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.blablaplane.Interface.OnAirportSelectedListenerInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.object.trip.Airport;
import com.google.android.gms.maps.model.LatLng;

import static com.example.blablaplane.object.trip.AirportFinder.findNearestAirport;

public class FragmentGPSButton extends Fragment {

    private CardView cardView;
    private Button button;
    private OnAirportSelectedListenerInterface airportSelectedListenerInterface;
    private static final String LOCATION_PERMISSION = android.Manifest.permission.ACCESS_FINE_LOCATION;


    public FragmentGPSButton(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gps, container, false);

        this.button = view.findViewById(R.id.button);
        this.cardView = view.findViewById(R.id.cardView);

        View.OnClickListener button = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestLocationPermission();
            }
        };
        this.button.setOnClickListener(button);
        this.cardView.setOnClickListener(button);
        return view;
    }

    private void getPosition() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());

                final Airport nearestAirport = findNearestAirport(latlng);
                airportSelectedListenerInterface.onAirportSelected(nearestAirport);
                locationManager.removeUpdates(this);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }


            @Override
            public void onProviderDisabled(String provider) {
            }
        };


        if (ActivityCompat.checkSelfPermission(getContext(), LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, null);
        } else {
            Toast.makeText(getContext(), "ERROR : GPS Position have been removed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            airportSelectedListenerInterface = (OnAirportSelectedListenerInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnAirportSelectedListener");
        }
    }


    private void requestLocationPermission() {
        ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        Toast.makeText(getContext(), "Loading", Toast.LENGTH_SHORT).show();
                        getPosition();
                    } else {
                        Toast.makeText(getContext(), "L'autorisation d'accès à la localisation a été refusée.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        if (ContextCompat.checkSelfPermission(requireContext(), LOCATION_PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "Loading", Toast.LENGTH_SHORT).show();
            getPosition();
        } else {
            requestPermissionLauncher.launch(LOCATION_PERMISSION);
        }
    }
}
