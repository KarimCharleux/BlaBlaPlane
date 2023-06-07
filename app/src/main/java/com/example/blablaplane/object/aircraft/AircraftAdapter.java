package com.example.blablaplane.object.aircraft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blablaplane.R;

import java.util.List;

public class AircraftAdapter extends BaseAdapter {

    /**
     * Listener to the click on a trip
     */
    private AircraftAdapterListener listener;

    /**
     * List of aircrafts
     */
    private final List<Aircraft> aircraftArray;

    /**
     * Mechanism to generate the graphic view from the XML file
     */
    private final LayoutInflater inflater;

    public AircraftAdapter(Context context, List<Aircraft> aircraftArray) {
        this.inflater = LayoutInflater.from(context);
        this.aircraftArray = aircraftArray;
    }

    @Override
    public int getCount() {
        return aircraftArray.size();
    }

    @Override
    public Object getItem(int i) {
        return aircraftArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return aircraftArray.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        FrameLayout aircraftSelectionView;

        aircraftSelectionView = (FrameLayout) (convertView == null ? inflater.inflate(R.layout.fragment_aircraft_choice, parent, false) : convertView);

        // Retrieve the views
        TextView planeName = aircraftSelectionView.findViewById(R.id.planeName);
        ImageView image = aircraftSelectionView.findViewById(R.id.IV_PLANE);
        TextView nbPassenger = aircraftSelectionView.findViewById(R.id.NUMBER_PASSENGER);


        // Set the text of the view for the trip
        planeName.setText(aircraftArray.get(i).getName());
        image.setImageResource(aircraftArray.get(i).getImage());
        nbPassenger.setText(String.valueOf(aircraftArray.get(i).getPassengerCount()));

        // Set the listener to the click on a trip
        aircraftSelectionView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAircraftClick(aircraftArray.get(i).getId());
            }
        });

        return aircraftSelectionView;
    }

    /**
     * Set the listener to the click on a trip
     *
     * @param listener Listener to the click on a trip
     */
    public void setListener(AircraftAdapterListener listener) {
        this.listener = listener;
    }
}
