package com.example.blablaplane;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blablaplane.object.MessageProfilArray;

public class MessageProfilAdapter extends BaseAdapter{

    private MessageProfilAdapterListener listener;
    private MessageProfilArray messageProfilArray;

    /**
     * Mechanism to generate the graphic view from the XML file
     */
    private LayoutInflater inflater;

    public MessageProfilAdapter(Context context, MessageProfilArray messageProfilArray) {
        this.inflater = LayoutInflater.from(context);
        this.messageProfilArray = messageProfilArray;
    }

    @Override
    public int getCount() {
            return messageProfilArray.size();
        }

    @Override
    public Object getItem(int i) {
            return messageProfilArray.get(i);
        }

    @Override
    public long getItemId(int i) {
            return messageProfilArray.get(i).getId();
        }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        FrameLayout messageProfilView;

        messageProfilView = (FrameLayout) (convertView == null ? inflater.inflate(R.layout.fragment_message_profil, parent, false) : convertView);

        // Retrieve the views
        TextView firstName = messageProfilView.findViewById(R.id.firstName);
        TextView lastName  = messageProfilView.findViewById(R.id.lastName);
        ImageView pictureProfil = messageProfilView.findViewById(R.id.pictureProfile);

        // Set the text of the view for the trip
        firstName.setText(messageProfilArray.get(i).getFirstName());
        lastName.setText(messageProfilArray.get(i).getLastName());
        pictureProfil.setImageResource(R.drawable.profile1); //A CHANGER



        // Set the listener to the click on a trip
        messageProfilView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMessageProfilClick(messageProfilArray.get(i).getId());
            }
        });

        return messageProfilView;
    }

    /**
     * Set the listener to the click on a trip
     *
     * @param listener Listener to the click on a trip
     */
    public void setListener(MessageProfilAdapterListener listener) {
            this.listener = listener;
        }

}
