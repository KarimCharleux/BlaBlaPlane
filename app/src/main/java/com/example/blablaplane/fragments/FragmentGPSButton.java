package com.example.blablaplane.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.blablaplane.Interface.OnAirportSelectedListenerInterface;
import com.example.blablaplane.R;
import com.google.android.gms.maps.model.LatLng;

public class FragmentGPSButton extends Fragment {

    private OnAirportSelectedListenerInterface airportSelectedListenerInterface;
    private static final String LOCATION_PERMISSION = android.Manifest.permission.ACCESS_FINE_LOCATION;

    private ActivityResultLauncher<String> requestPermissionLauncher;

    public FragmentGPSButton() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            airportSelectedListenerInterface = (OnAirportSelectedListenerInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement OnAirportSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        Toast.makeText(getContext(), "Chargement de votre position...", Toast.LENGTH_SHORT).show();
                        getPosition();
                    } else {
                        Toast.makeText(getContext(), "L'autorisation d'accès à la localisation a été refusée.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gps, container, false);

        Button button1 = view.findViewById(R.id.button);
        CardView cardView = view.findViewById(R.id.cardView);

        View.OnClickListener button = view1 -> requestLocationPermission();
        button1.setOnClickListener(button);
        cardView.setOnClickListener(button);
        return view;
    }

    private void getPosition() {
        LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "ERROR: GPS Position has been removed", Toast.LENGTH_SHORT).show();
            return;
        }
        locationManager.requestSingleUpdate(bestProvider, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                Log.d("TAG", latLng.toString());
                airportSelectedListenerInterface.onLatLngSelected(latLng);
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        }, Looper.getMainLooper());
    }

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            getPosition();
        } else {
            requestPermissionLauncher.launch(LOCATION_PERMISSION);
        }
    }
}