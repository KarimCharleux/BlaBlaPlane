package com.example.blablaplane.object.trip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.blablaplane.R;
import com.example.blablaplane.object.user.User;
import com.example.blablaplane.object.user.UserArray;

import java.util.List;

public class TripAdapter extends BaseAdapter {

    /**
     * Listener to the click on a trip
     */
    private TripAdapterListener listener;

    private final List<Trip> tripArray;

    /**
     * Mechanism to generate the graphic view from the XML file
     */
    private final LayoutInflater inflater;

    public TripAdapter(Context context, List<Trip> tripArray) {
        this.inflater = LayoutInflater.from(context);
        this.tripArray = tripArray;
    }

    @Override
    public int getCount() {
        return tripArray.size();
    }

    @Override
    public Object getItem(int i) {
        return tripArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return tripArray.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        FrameLayout tripView;

        tripView = (FrameLayout) (convertView == null ? inflater.inflate(R.layout.fragment_trip_box, parent, false) : convertView);

        // Retrieve the views
        TextView departure = tripView.findViewById(R.id.departure_location);
        TextView arrival = tripView.findViewById(R.id.arrival_location);
        TextView departureTime = tripView.findViewById(R.id.departureTime);
        TextView arrivalTime = tripView.findViewById(R.id.arrivalTime);
        TextView duration = tripView.findViewById(R.id.duration_time);
        TextView price = tripView.findViewById(R.id.price_text);
        TextView firstName = tripView.findViewById(R.id.first_name);
        TextView lastName = tripView.findViewById(R.id.last_name);
        TextView rating = tripView.findViewById(R.id.rate);

        // Set the text of the view for the trip
        departure.setText(tripArray.get(i).getDeparture().getCityName());
        arrival.setText(tripArray.get(i).getArrival().getCityName());
        departureTime.setText(tripArray.get(i).getDepartureTime());
        arrivalTime.setText(tripArray.get(i).getArrivalTime());
        duration.setText(tripArray.get(i).getDuration());
        price.setText(tripArray.get(i).getPrice());

        // Set the text of the view for the pilot
        User pilot = UserArray.getInstance().getUserById(tripArray.get(i).getPilotId());
        if (pilot != null) {
            firstName.setText(pilot.getName());
            lastName.setText(pilot.getSurname());
            rating.setText(pilot.getRatingString());
        }

        // Set the listener to the click on a trip
        tripView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onTripClick(tripArray.get(i).getId());
            }
        });

        return tripView;
    }

    /**
     * Set the listener to the click on a trip
     *
     * @param listener Listener to the click on a trip
     */
    public void setListener(TripAdapterListener listener) {
        this.listener = listener;
    }
}