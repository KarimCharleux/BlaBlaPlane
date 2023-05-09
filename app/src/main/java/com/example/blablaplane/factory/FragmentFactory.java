package com.example.blablaplane.factory;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.blablaplane.activity.Home_Activity;
import com.example.blablaplane.activity.MessageActivity;
import com.example.blablaplane.activity.MyTripActivity;
import com.example.blablaplane.activity.ProfileActivity;
import com.example.blablaplane.fragments.FooterFragment;
import com.example.blablaplane.fragments.HeaderFragment;
import com.example.blablaplane.object.messageProfil.MessageProfilFragment;
import com.example.blablaplane.object.trip.TrajectFragment;
import com.example.blablaplane.object.vehicule.AeronefFragment;
import com.example.blablaplane.object.vehicule.VehiculeFragment;

import java.util.List;

public class FragmentFactory {

    public FragmentFactory(){
    }

    public void changeActivity(int nb, Activity activity){
        System.out.println("APPEL de l'activite :"+activity.getComponentName());
        Intent intentNavigateNewPage = activity.getIntent();

        switch (nb) {
            case 0:
                intentNavigateNewPage = new Intent(activity, Home_Activity.class);
                System.out.println("VERS HOME");
                activity.startActivity(intentNavigateNewPage);
                break;
            case 1:
                /*intentNavigateNewPage = new Intent(activity, AddingPage.class);
                activity.startActivity(intentNavigateNewPage);*/
                break;
            case 2:
                intentNavigateNewPage = new Intent(activity, MyTripActivity.class);
                System.out.println("VERS MYTRIP");
                activity.startActivity(intentNavigateNewPage);
                break;
            case 3:
                intentNavigateNewPage = new Intent(activity, MessageActivity.class);
                System.out.println("VERS MESSAGE");
                activity.startActivity(intentNavigateNewPage);
                break;
            case 4:
                intentNavigateNewPage = new Intent(activity, ProfileActivity.class);
                System.out.println("VERS PROFILE");
                activity.startActivity(intentNavigateNewPage);
                break;
            default:
                break;
        }
    }
}
