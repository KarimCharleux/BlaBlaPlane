package com.example.blablaplane.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.blablaplane.R;
import com.example.blablaplane.fragments.FragmentAdding;
import com.example.blablaplane.fragments.FragmentHome;
import com.example.blablaplane.fragments.FragmentMessage;
import com.example.blablaplane.fragments.FragmentMyTrips;
import com.example.blablaplane.fragments.FragmentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SwitcherActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_switcher);
        BottomNavigationView bottomNavigationView = findViewById(R.id.footer_nav);

        // Fragment manager is used to manage navigation between fragments
        fragmentManager = getSupportFragmentManager();

        // Set default fragment for home page
        int currentSelectedItemId = bottomNavigationView.getMenu().getItem(0).getItemId();
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(currentSelectedItemId);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.access_search:
                fragmentManager.beginTransaction().replace(R.id.frame_fragment_container, new FragmentHome()).commit();
                return true;
            case R.id.access_add:
                fragmentManager.beginTransaction().replace(R.id.frame_fragment_container, new FragmentAdding()).commit();
                return true;
            case R.id.access_my_trip:
                fragmentManager.beginTransaction().replace(R.id.frame_fragment_container, new FragmentMyTrips()).commit();
                return true;
            case R.id.access_messages:
                fragmentManager.beginTransaction().replace(R.id.frame_fragment_container, new FragmentMessage()).commit();
                return true;
            case R.id.access_profile:
                fragmentManager.beginTransaction().replace(R.id.frame_fragment_container, new FragmentProfile()).commit();
                return true;
            default:
                fragmentManager.beginTransaction().replace(R.id.frame_fragment_container, new FragmentHome()).commit();
                return true;
        }
    }
}
