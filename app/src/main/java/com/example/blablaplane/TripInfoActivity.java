package com.example.blablaplane;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.blablaplane.activity.ConfirmationActivity;
import com.example.blablaplane.activity.Paiement_Activity;
import com.example.blablaplane.object.trip.Trip;
import com.example.blablaplane.object.trip.TripArray;
import com.example.blablaplane.object.user.User;
import com.example.blablaplane.object.user.UserArray;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsDisplay;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;
import java.util.List;

public class TripInfoActivity extends AppCompatActivity {

    private MapView map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_info);

        Button bookButton = findViewById(R.id.bookButton);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNavigateNewPage = new Intent(TripInfoActivity.this, Paiement_Activity.class);
                System.out.println("VERS PAIEMENT");
                TripInfoActivity.this.startActivity(intentNavigateNewPage);
            }
        });

        // Get the trip id from the intent (ListTrip)
        int tripId = getIntent().getIntExtra("id", 0);

        // Trip info
        Trip trip = TripArray.getInstance().getTripById(tripId);

        // Start and end point by default if the trip is not found
        GeoPoint startPoint = new GeoPoint(48.8583, 2.2944); // Paris
        GeoPoint endPoint = new GeoPoint(43.7102, 7.2620); // Nice

        if (trip != null) {
            TextView departureCity = findViewById(R.id.departureText);
            TextView arrivalCity = findViewById(R.id.arrivalText);
            TextView departureTime = findViewById(R.id.departureTime);
            TextView arrivalTime = findViewById(R.id.arrivalTime);
            TextView date = findViewById(R.id.dateText);
            TextView price = findViewById(R.id.price);
            TextView duration = findViewById(R.id.durationTime);

            departureCity.setText(trip.getDeparture().getCityName());
            arrivalCity.setText(trip.getArrival().getCityName());
            departureTime.setText(trip.getDepartureTime());
            arrivalTime.setText(trip.getArrivalTime());
            date.setText(trip.getDate());
            price.setText(trip.getPrice());
            duration.setText(trip.getDuration());

            // Pilot info
            User pilot = UserArray.getInstance().getUserById(trip.getPilotId());

            if (pilot != null) {
                TextView namePilot = findViewById(R.id.namePilot);
                TextView surnamePilot = findViewById(R.id.surnamePilot);
                TextView ratingPilot = findViewById(R.id.ratingPilot);

                namePilot.setText(pilot.getName());
                surnamePilot.setText(pilot.getSurname());
                ratingPilot.setText(pilot.getRating());
            } else {
                System.err.println("ERROR : Pilot not found, id :" + trip.getPilotId());
            }

            // Start and end point
            startPoint = trip.getArrival().getGeoPoint();
            endPoint = trip.getDeparture().getGeoPoint();

        } else {
            System.err.println("ERROR : Trip not found, id :" + tripId);
        }

        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        // Get the map view
        map = findViewById(R.id.mapView);
        IMapController mapController = map.getController();

        map.setTileSource(TileSourceFactory.MAPNIK); // Render
        map.setMultiTouchControls(true); // Multi-touch control

        map.getZoomController().activate(); // Zoom control
        CustomZoomButtonsDisplay custom = map.getZoomController().getDisplay();
        custom.setPositions(false, CustomZoomButtonsDisplay.HorizontalPosition.RIGHT, CustomZoomButtonsDisplay.VerticalPosition.CENTER);

        // Create a list of GeoPoints representing the line
        List<GeoPoint> points = new ArrayList<>();
        points.add(startPoint);
        points.add(endPoint);

        // Create a Polyline with the points
        Polyline line = new Polyline();
        line.setPoints(points);

        // Set the line color and width
        line.setColor(Color.rgb(0, 175, 245));

        // Add the Polyline to the map
        map.getOverlayManager().add(line);

        // Set the center of the map and the zoom
        mapController.setCenter(startPoint);
        mapController.setZoom(6.0);

        // Create two points to draw the start and end point
        GeoPoint finalStartPoint = startPoint;
        GeoPoint finalEndPoint = endPoint;
        map.getOverlays().add(new Overlay() {
            @Override
            public void draw(Canvas canvas, MapView mapView, boolean shadow) {
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.rgb(0, 175, 245));

                // Convert GeoPoint to screen pixels
                Point point = new Point();
                mapView.getProjection().toPixels(finalStartPoint, point);
                canvas.drawCircle(point.x, point.y, 15, paint);
                mapView.getProjection().toPixels(finalEndPoint, point);
                canvas.drawCircle(point.x, point.y, 15, paint);
            }
        });

        // Add markers
        ArrayList<OverlayItem> items = new ArrayList<>();
        OverlayItem start = new OverlayItem("Start", "Start", startPoint);
        start.setMarker(getResources().getDrawable(R.drawable.location_pin));
        items.add(start);
        OverlayItem end = new OverlayItem("End", "End", endPoint);
        end.setMarker(getResources().getDrawable(R.drawable.location_pin));
        items.add(end);

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<>(this, items, null);
        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause(); // Pause the map when the activity is paused.
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume(); // Resume the map when the activity is resumed.
    }
}