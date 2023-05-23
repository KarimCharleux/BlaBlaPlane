package com.example.blablaplane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.blablaplane.CallBackInterface;
import com.example.blablaplane.R;
import com.example.blablaplane.factory.FragmentFactory;
import com.example.blablaplane.fragments.FooterFragment;
import com.example.blablaplane.object.message.MessageProfileAdapter;
import com.example.blablaplane.object.message.MessageProfileAdapterListener;
import com.example.blablaplane.object.message.MessageProfileArray;

public class MessageActivity extends AppCompatActivity implements MessageProfileAdapterListener, CallBackInterface {
    FragmentFactory fragmentFactory;
    FooterFragment footerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footerFragment = new FooterFragment();
        fragmentFactory = new FragmentFactory();
        setContentView(R.layout.activity_message);

        // Get the list of Message profils
        MessageProfileArray messageProfileArray = MessageProfileArray.getInstance();

        // Create the adapter
        MessageProfileAdapter messageProfileAdapter = new MessageProfileAdapter(getApplicationContext(), messageProfileArray);

        // Retrieve the list of Message profils
        ListView messageProfilList = findViewById(R.id.MessageProfilesList);

        // Set the adapter
        messageProfilList.setAdapter(messageProfileAdapter);

        // Set the listener
        messageProfileAdapter.setListener(this);
    }

    @Override
    public void onMessageProfileClick(int messageProfilId) {
       // Intent intent = new Intent(MessageActivity.this, MessageChatBox.class);
       // intent.putExtra("id", messageProfilId);
       // startActivity(intent);
    }

    @Override
    public void callBackMethod(int indexMenu) {
        fragmentFactory.changeActivity(indexMenu,this);
    }
}