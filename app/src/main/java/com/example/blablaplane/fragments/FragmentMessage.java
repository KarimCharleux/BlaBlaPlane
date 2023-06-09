package com.example.blablaplane.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.blablaplane.R;
import com.example.blablaplane.object.message.MessageProfileAdapter;
import com.example.blablaplane.object.message.MessageProfileAdapterListener;
import com.example.blablaplane.object.message.MessageProfileArray;

public class FragmentMessage extends Fragment implements MessageProfileAdapterListener {

    public FragmentMessage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        // Get the list of Message profiles
        MessageProfileArray messageProfileArray = MessageProfileArray.getInstance();

        // Create the adapter
        MessageProfileAdapter messageProfileAdapter = new MessageProfileAdapter(getContext(), messageProfileArray);

        // Retrieve the list of Message profils
        ListView messageProfileList = view.findViewById(R.id.MessageProfilesList);

        // Set the adapter
        messageProfileList.setAdapter(messageProfileAdapter);

        // Set the listener
        messageProfileAdapter.setListener(this);

        return view;
    }

    @Override
    public void onMessageProfileClick(int messageProfilId) {
        // Intent intent = new Intent(MessageActivity.this, MessageChatBox.class);
        // intent.putExtra("id", messageProfilId);
        // startActivity(intent);
    }
}