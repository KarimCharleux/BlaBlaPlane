package com.example.blablaplane.factory;

import android.app.Activity;
import android.content.Intent;

import com.example.blablaplane.activity.HomeActivity;
import com.example.blablaplane.activity.MessageActivity;
import com.example.blablaplane.activity.MyTripActivity;
import com.example.blablaplane.activity.ProfileActivity;

public class FragmentFactory {

    public FragmentFactory() {
    }

    public void changeActivity(int indexMenu, Activity activity) {

        System.out.println("Call the activity :" + activity.getComponentName());

        Intent intentNavigateNewPage;

        switch (indexMenu) {
            case 0:
                intentNavigateNewPage = new Intent(activity, HomeActivity.class);
                System.out.println("To HomeActivity");
                activity.startActivity(intentNavigateNewPage);
                break;
            case 1:
                /*intentNavigateNewPage = new Intent(activity, AddingPage.class);
                activity.startActivity(intentNavigateNewPage);*/
                break;
            case 2:
                intentNavigateNewPage = new Intent(activity, MyTripActivity.class);
                System.out.println("To MyTripActivity");
                activity.startActivity(intentNavigateNewPage);
                break;
            case 3:
                intentNavigateNewPage = new Intent(activity, MessageActivity.class);
                System.out.println("To MessageActivity");
                activity.startActivity(intentNavigateNewPage);
                break;
            case 4:
                intentNavigateNewPage = new Intent(activity, ProfileActivity.class);
                System.out.println("To ProfileActivity");
                activity.startActivity(intentNavigateNewPage);
                break;
            default:
                break;
        }
    }
}
