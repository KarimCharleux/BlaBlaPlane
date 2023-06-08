package com.example.blablaplane.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.blablaplane.Interface.PictureActivityInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.activity.LandingActivity;
import com.example.blablaplane.activity.Photo_Activity;
import com.example.blablaplane.activity.SelectAircraftActivity;
import com.example.blablaplane.activity.SwitcherActivity;
import com.example.blablaplane.activity.user.ModifyProfile;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.aircraft.Aircraft;
import com.example.blablaplane.object.aircraft.AircraftAdapter;
import com.example.blablaplane.object.aircraft.AircraftAdapterListener;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FragmentProfile extends Fragment implements AircraftAdapterListener {

    SharedPreferences sharedPreferences;
    String userID;
    Object cacheKey = PictureActivityInterface.cacheKey;

    public FragmentProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sharedPreferences = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        userID = sharedPreferences.getString("user_id", null);
        ImageView picture_profile = view.findViewById(R.id.picture_profile);

        if (userID.isEmpty()) {
            Intent intent = new Intent(getActivity(), LandingActivity.class);
            startActivity(intent);
            requireActivity().finish();
        } else {
            DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

            // Check if the user exists and get its data
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User exists in the database and we can get its data
                        User user = dataSnapshot.getValue(User.class);
                        assert user != null;

                        TextView userName = view.findViewById(R.id.first_name);
                        TextView userLastName = view.findViewById(R.id.last_name);
                        TextView userEmail = view.findViewById(R.id.email_user);
                        TextView userPhone = view.findViewById(R.id.phone_number);
                        RatingBar ratingBar = view.findViewById(R.id.ratingBar);

                        // Put the first letter of the name in uppercase
                        String name = user.getName().substring(0, 1).toUpperCase() + user.getName().substring(1);
                        userName.setText(name);
                        userLastName.setText(user.getSurname().toUpperCase());
                        userEmail.setText(user.getEmail());
                        ratingBar.setRating(user.getRating());

                        // Format the phone number ex: 06 12 34 56 78
                        String phone = user.getPhone().replace(" ", "");
                        phone = phone.substring(0, 2) + " " + phone.substring(2, 4) + " " + phone.substring(4, 6) + " " + phone.substring(6, 8) + " " + phone.substring(8, 10);
                        userPhone.setText(phone);

                        // Get the list of Aircraft
                        List<Aircraft> aircraftArray = user.getAircraftList();

                        // Create the adapter
                        AircraftAdapter aircraftAdapter = new AircraftAdapter(getContext(), aircraftArray);

                        // Retrieve the list of aircraft
                        ListView aircraftList = view.findViewById(R.id.aircraft_list);

                        // Set the adapter
                        aircraftList.setAdapter(aircraftAdapter);

                        // Set the listener
                        aircraftAdapter.setListener(FragmentProfile.this);

                        // Add the footer view to the list for the add aircraft button
                        View footerView = inflater.inflate(R.layout.fragment_add_aircraft, null, false);
                        aircraftList.addFooterView(footerView);

                        // Add listener to the add aircraft button
                        CardView addAircraftButton = footerView.findViewById(R.id.buttonAddAircraft);
                        addAircraftButton.setOnClickListener(v -> {
                            Intent intent = new Intent(getActivity(), SelectAircraftActivity.class);
                            startActivity(intent);
                        });

                    } else {
                        // User doesn't exist in the database so delete the user in the cache
                        // and redirect to the login page
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("user_id");
                        editor.apply();

                        Intent intent = new Intent(getActivity(), LandingActivity.class);
                        startActivity(intent);
                        requireActivity().finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Here should be an error message
                }
            });
        }


        Glide.with(getContext())
                .load(PictureActivityInterface.cacheKey)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        picture_profile.setImageDrawable(resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        picture_profile.setImageResource(R.drawable.pp_default);
                    }
                });



        // Disconnect button
        Button buttonDisconnect = view.findViewById(R.id.logoutButton);
        buttonDisconnect.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("user_id");
            editor.apply();

            Toast.makeText(getContext(), R.string.confirmationDisconnected, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), LandingActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        // Modify profile button
        Button buttonModifyProfile = view.findViewById(R.id.modifyButton);
        buttonModifyProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ModifyProfile.class);
            startActivity(intent);
        });

        ImageView pictureProfile = view.findViewById(R.id.picture_profile);
        pictureProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Photo_Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    /**
     * Make a popup to answer to the user if he wants to delete the aircraft
     * If yes, delete the aircraft from the database and the cache
     * If no, do nothing
     *
     * @param aircraftId the id of the aircraft to delete
     */
    @Override
    public void onAircraftClick(int aircraftId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirmation de suppression")
                .setMessage("Voulez-vous vraiment supprimer votre aéronef ?")
                .setPositiveButton("Oui", (dialog, which) -> {
                    // Get the user reference in the database
                    DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

                    // Fetch the user from the database
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // Get the user object
                            User user = snapshot.getValue(User.class);
                            assert user != null;

                            // Remove the aircraft from the user
                            user.removeAircraft(aircraftId);

                            // Update the user in the database
                            userRef.setValue(user);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            System.err.println("Error while fetching user from the database");
                        }
                    });

                    Toast.makeText(getContext(), R.string.confirmationAircraftCreated, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), SwitcherActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("Non", (dialog, which) -> {
                    // Nothing to do
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}