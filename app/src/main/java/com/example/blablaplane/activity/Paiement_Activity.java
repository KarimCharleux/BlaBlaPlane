package com.example.blablaplane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.blablaplane.R;

import java.util.ArrayList;
import java.util.List;

public class Paiement_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement);

        List<Button> buttons = new ArrayList<>();
        buttons.add(findViewById(R.id.paypal_button));
        buttons.add(findViewById(R.id.cb_button));
        buttons.add(findViewById(R.id.apple_pay_button));
        buttons.add(findViewById(R.id.google_pay_button));

        for(Button button : buttons)
        {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentNavigateNewPage = new Intent(Paiement_Activity.this, Home_Activity.class);
                    System.out.println("VERS CONFIRAMATION");
                    Paiement_Activity.this.startActivity(intentNavigateNewPage);
                }
            });
        }
    }
}