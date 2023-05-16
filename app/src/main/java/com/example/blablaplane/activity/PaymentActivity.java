package com.example.blablaplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blablaplane.R;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        List<Button> buttons = new ArrayList<>();
        buttons.add(findViewById(R.id.paypal_button));
        buttons.add(findViewById(R.id.cb_button));
        buttons.add(findViewById(R.id.apple_pay_button));
        buttons.add(findViewById(R.id.google_pay_button));

        for (Button button : buttons) {
            button.setOnClickListener(view -> {
                Intent intentNavigateNewPage = new Intent(PaymentActivity.this, ConfirmationActivity.class);
                System.out.println("To ConfirmationPage");
                PaymentActivity.this.startActivity(intentNavigateNewPage);
            });
        }
    }
}