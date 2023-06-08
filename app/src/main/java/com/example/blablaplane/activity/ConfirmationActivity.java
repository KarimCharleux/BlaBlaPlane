package com.example.blablaplane.activity;

import static com.example.blablaplane.notifications.NotifyApp.CHANNEL_IDC;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.blablaplane.R;
import com.example.blablaplane.notifications.CalendarNotificationFactory;
import com.example.blablaplane.notifications.Notification;
import com.example.blablaplane.notifications.NotifyApp;
import com.example.blablaplane.object.DataBase;
import com.example.blablaplane.object.trip.CreateTripInfo;
import com.example.blablaplane.object.trip.SearchTripInfo;
import com.example.blablaplane.object.trip.Trip;
import com.example.blablaplane.object.trip.TripArray;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.TimeZone;

public class ConfirmationActivity extends AppCompatActivity {

    private int notificationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Reset the search context to avoid to keep the previous search
        SearchTripInfo.reset();

        // Reset the create context
        CreateTripInfo.reset();

        int tripId = getIntent().getIntExtra("id", 0);
        Trip trip = TripArray.getInstance().getTripById(tripId);

        Button addToCalendarButton = findViewById(R.id.AddToCalendarButton);
        Button backToHomeButton = findViewById(R.id.backToHomeButton);

        Intent intent = getIntent();
        Notification notification = intent.getExtras().getParcelable("notif");

        addToCalendarButton.setOnClickListener(view -> addToCalendar(trip));

        backToHomeButton.setOnClickListener(view -> {
            Intent intentNavigateNewPage = new Intent(ConfirmationActivity.this, SwitcherActivity.class);
            System.out.println("VERS HOME");
            ConfirmationActivity.this.startActivity(intentNavigateNewPage);
        });
        sendNotificationOnChannel(notification);

        // Get the user ID from the cache
        String userID = this.getSharedPreferences("user_data", Context.MODE_PRIVATE).getString("user_id", null);

        // Add the trip to he user's trip list in database
        DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

        // Fetch the user from the database
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Get the user object
                User user = snapshot.getValue(User.class);
                assert user != null;

                // Remove the aircraft from the user
                user.addTrip(tripId);

                // Update the user in the database
                userRef.setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error while fetching user from the database");
            }
        });
    }

    private void sendNotificationOnChannel(Notification notificationCustom) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), notificationCustom.getChannelId())
                .setSmallIcon(R.drawable.footer_logo)
                .setContentTitle(notificationCustom.toString())
                .setContentText(notificationCustom.getMessage())
                .setPriority(notificationCustom.getPriority());
        NotifyApp.getNotificationManager().notify(++notificationId, notification.build());
    }

    private void addToCalendar(Trip trip) {
        String title = "Travel from " + trip.getDeparture() + " to " + trip.getArrival();
        String event_location = trip.getDeparture().getCityName() + ", " + trip.getDeparture().getCountry();
        String description = "Be ready for your trip at destination of " + trip.getArrival().getCityName() + " don't forget to take contact with the pilot before";
        Long startDate = trip.getDepartureDate().getTime(); //  String.valueOf(trip.getDepartureDate().getTime());
        Long endDate = trip.getArrivalDate().getTime(); //String.valueOf(trip.getArrivalDate().getTime());

        long calID = 3;

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.CALENDAR_ID, calID);
        intent.putExtra(CalendarContract.Events.TITLE, title);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, event_location);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);
        intent.putExtra(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate);
        Notification notificationCalendar;

        try {
            notificationCalendar = new CalendarNotificationFactory().createNotification(CHANNEL_IDC, trip.getArrival().toString());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        sendNotificationOnChannel(notificationCalendar);

        startActivity(intent);
    }

}