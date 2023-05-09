package com.example.blablaplane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.fragments.FooterFragment;
import com.example.blablaplane.object.messageProfil.MessageProfilAdapter;
import com.example.blablaplane.object.messageProfil.MessageProfilAdapterListener;
import com.example.blablaplane.object.messageProfil.MessageProfilArray;

public class MessageActivity extends AppCompatActivity implements MessageProfilAdapterListener, CallBackInterface {
    FragmentFactory fragmentFactory;
    FooterFragment footerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footerFragment = new FooterFragment();
        fragmentFactory = new FragmentFactory();
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

    @Override
    public void callBackMethod(int nb) {
        fragmentFactory.changeActivity(nb,this);
    }
}