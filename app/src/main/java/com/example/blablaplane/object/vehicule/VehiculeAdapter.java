package com.example.blablaplane.object.vehicule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blablaplane.R;

public class VehiculeAdapter extends BaseAdapter {

    /**
     * Listener to the click on a trip
     */
    private VehiculeAdapterListener listener;

    private VehiculeArray vehiculeArray;

    /**
     * Mechanism to generate the graphic view from the XML file
     */
    private LayoutInflater inflater;

    public VehiculeAdapter(Context context, VehiculeArray vehiculeArray) {
        this.inflater = LayoutInflater.from(context);
        this.vehiculeArray = vehiculeArray;
    }

    @Override
    public int getCount() {
        return vehiculeArray.size();
    }

    @Override
    public Object getItem(int i) {
        return vehiculeArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return vehiculeArray.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        FrameLayout vehiculeSelectionView;

        vehiculeSelectionView = (FrameLayout) (convertView == null ? inflater.inflate(R.layout.fragment_vehicule_choice, parent, false) : convertView);

        // Retrieve the views
        TextView planeName = vehiculeSelectionView.findViewById(R.id.planeName);
        ImageView image = vehiculeSelectionView.findViewById(R.id.IV_PLANE);
        TextView nbPassenger = vehiculeSelectionView.findViewById(R.id.NUMBER_PASSENGER);


        // Set the text of the view for the trip
        planeName.setText(vehiculeArray.get(i).getName());
        image.setImageResource(vehiculeArray.get(i).getImg());
        nbPassenger.setText(Integer.toString(vehiculeArray.get(i).getCapacity()));

        // Set the listener to the click on a trip
        vehiculeSelectionView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onVehiculeClick(vehiculeArray.get(i).getId());
            }
        });

        return vehiculeSelectionView;
    }

    /**
     * Set the listener to the click on a trip
     *
     * @param listener Listener to the click on a trip
     */
    public void setListener(VehiculeAdapterListener listener) {
        this.listener = listener;
    }
}
