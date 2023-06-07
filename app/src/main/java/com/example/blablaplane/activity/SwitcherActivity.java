package com.example.blablaplane.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.NonUiContext;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
    private Fragment fragment;

    @Override
    public void onCreate(Bundle bundle) {
        System.out.println("Bundle");
        System.out.println(bundle);
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
                fragment = new FragmentHome();
                fragmentManager.beginTransaction().replace(R.id.frame_fragment_container, fragment).commit();
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SCORE_KEY",2);
        getSupportFragmentManager().putFragment(outState, "FragmentHome", fragment);
        System.out.println("Frag saved in Act");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("RESTORING");
        System.out.println("My String : "+savedInstanceState.getString("string_value"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("On Start ...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ON_DESTROY");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("ON_STOP");
    }
}
