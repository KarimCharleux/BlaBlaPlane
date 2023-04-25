package com.example.blablaplane;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.blablaplane.object.TripArray;
import com.example.blablaplane.object.User;
import com.example.blablaplane.object.UserArray;

public class TripAdapter extends BaseAdapter {

    /**
     * Listener to the click on a trip
     */
    private TripAdapterListener listener;

    private TripArray tripArray;

    /**
     * Mechanism to generate the graphic view from the XML file
     */
    private LayoutInflater inflater;

    public TripAdapter(Context context, TripArray tripArray) {
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

        tripView = (FrameLayout) (convertView == null ? inflater.inflate(R.layout.fragment_traject, parent, false) : convertView);

        // Retrieve the views
        TextView departure = tripView.findViewById(R.id.departure_location);
        TextView arrival = tripView.findViewById(R.id.arrival_location);
        TextView duration = tripView.findViewById(R.id.duration_time);
        TextView price = tripView.findViewById(R.id.price_text);
        TextView firstName = tripView.findViewById(R.id.first_name);
        TextView lastName = tripView.findViewById(R.id.last_name);
        TextView rating = tripView.findViewById(R.id.rate);

        // Set the text of the view for the trip
        departure.setText(tripArray.get(i).getDeparture());
        arrival.setText(tripArray.get(i).getArrival());
        duration.setText(tripArray.get(i).getDuration());
        price.setText(tripArray.get(i).getPrice());

        // Set the text of the view for the pilot
        User pilot = UserArray.getInstance().getUserById(tripArray.get(i).getPilotId());
        firstName.setText(pilot.getName());
        lastName.setText(pilot.getSurname());
        rating.setText(pilot.getRating());

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