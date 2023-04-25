package com.example.blablaplane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.blablaplane.object.MessageProfilArray;

public class MessageActivity extends AppCompatActivity implements MessageProfilAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // Get the list of Message profils
        MessageProfilArray messageProfilArray = MessageProfilArray.getInstance();

        // Create the adapter
        MessageProfilAdapter messageProfilAdapter = new MessageProfilAdapter(getApplicationContext(), messageProfilArray);

        // Retrieve the list of Message profils
        ListView messageProfilList = findViewById(R.id.MessageProfilesList);

        // Set the adapter
        messageProfilList.setAdapter(messageProfilAdapter);

        // Set the listener
        messageProfilAdapter.setListener(this);
    }

    @Override
    public void onMessageProfilClick(int messageProfilId) {
       // Intent intent = new Intent(MessageActivity.this, MessageChatBox.class);
       // intent.putExtra("id", messageProfilId);
       // startActivity(intent);
    }
}