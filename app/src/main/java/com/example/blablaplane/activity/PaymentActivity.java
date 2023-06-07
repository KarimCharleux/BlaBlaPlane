package com.example.blablaplane.activity;

import static com.example.blablaplane.notifications.NotifyApp.CHANNEL_IDP;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.R;
import com.example.blablaplane.notifications.Notification;
import com.example.blablaplane.notifications.PaymentNotificationFactory;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        int tripId = getIntent().getIntExtra("id", 0);

        List<Button> buttons = new ArrayList<>();
        buttons.add(findViewById(R.id.paypal_button));
        buttons.add(findViewById(R.id.cb_button));
        buttons.add(findViewById(R.id.apple_pay_button));
        buttons.add(findViewById(R.id.google_pay_button));

        for (Button button : buttons) {
            button.setOnClickListener(view -> {
                Notification notification = null;
                String paymentMethod = (String)button.getText();
                try {
                    notification = new PaymentNotificationFactory().createNotification(CHANNEL_IDP, paymentMethod);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
                Intent intentNavigateNewPage = new Intent(PaymentActivity.this, ConfirmationActivity.class);
                intentNavigateNewPage.putExtra("id", tripId);
                intentNavigateNewPage.putExtra("notif", notification);
                System.out.println("To ConfirmationPage");
                PaymentActivity.this.startActivity(intentNavigateNewPage);
            });
        }
    }
}