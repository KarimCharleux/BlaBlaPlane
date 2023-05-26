package com.example.blablaplane.object.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blablaplane.R;

public class MessageProfileAdapter extends BaseAdapter{

    private MessageProfileAdapterListener listener;
    private final MessageProfileArray messageProfileArray;

    /**
     * Mechanism to generate the graphic view from the XML file
     */
    private final LayoutInflater inflater;

    public MessageProfileAdapter(Context context, MessageProfileArray messageProfileArray) {
        this.inflater = LayoutInflater.from(context);
        this.messageProfileArray = messageProfileArray;
    }

    @Override
    public int getCount() {
            return messageProfileArray.size();
        }

    @Override
    public Object getItem(int i) {
            return messageProfileArray.get(i);
        }

    @Override
    public long getItemId(int i) {
            return messageProfileArray.get(i).getId();
        }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        FrameLayout messageProfilView;

        messageProfilView = (FrameLayout) (convertView == null ? inflater.inflate(R.layout.fragment_message_profile, parent, false) : convertView);

        // Retrieve the views
        TextView firstName = messageProfilView.findViewById(R.id.cardView_firstName);
        TextView lastName  = messageProfilView.findViewById(R.id.cardView_lastName);
        ImageView pictureProfil = messageProfilView.findViewById(R.id.pictureProfile);

        // Set the text of the view for the trip
        firstName.setText(messageProfileArray.get(i).getFirstName());
        lastName.setText(messageProfileArray.get(i).getLastName());
        pictureProfil.setImageResource(R.drawable.profile1); //A CHANGER



        // Set the listener to the click on a trip
        messageProfilView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMessageProfileClick(messageProfileArray.get(i).getId());
            }
        });

        return messageProfilView;
    }

    /**
     * Set the listener to the click on a trip
     *
     * @param listener Listener to the click on a trip
     */
    public void setListener(MessageProfileAdapterListener listener) {
            this.listener = listener;
        }

}
