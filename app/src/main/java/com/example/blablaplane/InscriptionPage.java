package com.example.blablaplane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class InscriptionPage extends AppCompatActivity {



    FooterFragment myFragment;
    CheckBox isPiloteCheckBox;
    CheckBox AirplaneCheckBox;
    CheckBox HelicopterCheckBox;
    ConstraintLayout layoutWithSubCheckBox;

    TextView warningTextInscription;

    private Button inscriptionButton;

    private HashMap<String,String> newUserData = new HashMap<>();

    EditText firstName ;
    EditText lastName ;
    EditText emailAdress ;
    EditText password ;
    EditText repeatPassword ;

    private String userFirstName = "";
    private String userLastName = "";
    private String userEmailAdress = "";
    private String userPassword = "";
    private String userRepeatPassword = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        isPiloteCheckBox = findViewById(R.id.checkBoxInscription);
        AirplaneCheckBox = findViewById(R.id.subCheckBoxInscriptionAirplane);
        HelicopterCheckBox = findViewById(R.id.subCheckBoxInscriptionHelicopter);
        layoutWithSubCheckBox = findViewById(R.id.subCheckBoxInscriptionSubLayout);

        this.layoutWithSubCheckBox.setVisibility(View.INVISIBLE);
        this.AirplaneCheckBox.setVisibility(View.INVISIBLE);
        this.HelicopterCheckBox.setVisibility(View.INVISIBLE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        myFragment = new FooterFragment();
        fragmentTransaction.add(R.id.fragmentFooter, myFragment);
        fragmentTransaction.commit();

        firstName = findViewById(R.id.editTextInscriptionSurname);
        lastName = findViewById(R.id.editTextInscriptionName);
        emailAdress = findViewById(R.id.editTextInscriptionEmail);
        password = findViewById(R.id.editTextInscriptionPassword);
        repeatPassword = findViewById(R.id.editTextInscriptionRepeatPassword);

        inscriptionButton = findViewById(R.id.inscriptionButton);

        warningTextInscription = findViewById(R.id.warningTextInscription);

        this.isPiloteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layoutWithSubCheckBox.setVisibility(View.VISIBLE);
                    AirplaneCheckBox.setVisibility(View.VISIBLE);
                    HelicopterCheckBox.setVisibility(View.VISIBLE);
                } else {
                    layoutWithSubCheckBox.setVisibility(View.INVISIBLE);
                    AirplaneCheckBox.setVisibility(View.INVISIBLE);
                    HelicopterCheckBox.setVisibility(View.INVISIBLE);
                }
            }
        });


        this.HelicopterCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = HelicopterCheckBox.isChecked();
                HelicopterCheckBox.setChecked(!isChecked);
            }
        });

        AirplaneCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = AirplaneCheckBox.isChecked();
                AirplaneCheckBox.setChecked(!isChecked);
            }
        });

        isPiloteCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = isPiloteCheckBox.isChecked();
                isPiloteCheckBox.setChecked(!isChecked);
            }
        });


        firstName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userFirstName = firstName.getText().toString();
            }
        });

        lastName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userLastName = lastName.getText().toString();
            }
        });


        emailAdress.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userEmailAdress = emailAdress.getText().toString();
            }
        });


        password.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userPassword = password.getText().toString();
            }
        });


        repeatPassword.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userRepeatPassword = repeatPassword.getText().toString();
            }
        });


        inscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean emailChecked = (userEmailAdress.contains("@") && userEmailAdress.length()>5);
                boolean passwordChecked = (userPassword.length()>6);
                boolean firstNameChecked = (userFirstName.length()>1);
                boolean lastNameChecked = (userLastName.length()>1);
                boolean samePassword = (userPassword.equals(userRepeatPassword));
                warningTextInscription.setVisibility(View.VISIBLE);
                if (!emailChecked){
                    warningTextInscription.setText("Email invalide");
                } else if (!passwordChecked){
                    warningTextInscription.setText("Mot de passe invalide \n(6 caractères minimum)");
                } else if (!firstNameChecked){
                    warningTextInscription.setText("Prénom invalide\n(2 caractères minimum)");
                } else if (!lastNameChecked){
                    warningTextInscription.setText("Nom invalide\n(2 caractères minimum)");
                } else if (!samePassword){
                    warningTextInscription.setText("Les mots de passe ne correspondent pas");
                } else {
                    warningTextInscription.setVisibility(View.INVISIBLE);
                    newUserData.put("name",userFirstName);
                    newUserData.put("surname",userLastName);
                    newUserData.put("email",userEmailAdress);
                    newUserData.put("password",userPassword);
                    newUserData.put("isPilot",String.valueOf(isPiloteCheckBox.isChecked()));
                    newUserData.put("isPilotAirplane",String.valueOf(AirplaneCheckBox.isChecked()));
                    newUserData.put("isPilotHelicopter",String.valueOf(HelicopterCheckBox.isChecked()));
                    //TODO : add new user to database
                }
            }
        });

    }
}










