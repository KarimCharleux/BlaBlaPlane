package com.example.blablaplane.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.blablaplane.R;
import com.example.blablaplane.fragments.ModifyProfile_dialogFragment;
import com.example.blablaplane.object.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ModifyProfile extends AppCompatActivity implements ModifyProfile_dialogFragment.InputDialogListener {

    ImageView logo;

    CardView cardView_firstName;
    CardView cardView_lastName ;
    CardView cardView_email ;
    CardView cardView_password ;
    CardView cardView_phoneNumber ;
    CardView cardView_addAircraft ;
    CardView cardView_confirm ;
    CardView cardView_return ;

    Button button_first_name_title ;
    Button button_first_name_answer ;
    Button button_last_name_title ;
    Button button_last_name_answer ;
    Button button_email_title ;
    Button button_email_answer ;
    Button button_password_title ;
    Button button_password_answer ;
    Button button_phone_number_title ;
    Button button_phone_number_answer ;
    Button button_add_aircraft_title ;
    Button button_confirm ;
    Button button_return ;

    String userFirstName = "";
    String userLastName = "";
    String userEmail = "";
    String userPassword = "";
    String userPhoneNumber = "";
    String userRating = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_modify_profile, container, false);

        SharedPreferences preferences = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String userID = preferences.getString("user_id", null);

        DatabaseReference userRef = DataBase.USERS_REFERENCE.child(userID);

        // Check if the user exists and get its data
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User exists in the database and we can get its data
                    User user = dataSnapshot.getValue(User.class);

                    Button button_first_name_answer = findViewById(R.id.firstName_answer);
                    Button button_last_name_answer = findViewById(R.id.lastName_answer);
                    Button button_email_answer = findViewById(R.id.email_answer);
                    Button button_password_answer = findViewById(R.id.password_answer);
                    Button button_phone_number_answer = findViewById(R.id.phoneNumber_answer);

                    assert user != null;
                    // Put the first letter of the name in uppercase
                    int lengthPassword = user.getPassword().length();
                    String hiddenPassword = "";
                    for (int i = 0; i < lengthPassword; i++) {
                        hiddenPassword += "*";
                    }

                    String name = user.getName().substring(0, 1).toUpperCase() + user.getName().substring(1);
                    button_first_name_answer.setText(name);
                    button_last_name_answer.setText(user.getSurname().toUpperCase());
                    button_email_answer.setText(user.getEmail());
                    button_password_answer.setText(hiddenPassword);

                    // Format the phone number ex: 06 12 34 56 78
                    String phone = user.getPhone().replace(" ", "");
                    phone = phone.substring(0, 2) + " " + phone.substring(2, 4) + " " + phone.substring(4, 6) + " " + phone.substring(6, 8) + " " + phone.substring(8, 10);
                    button_phone_number_answer.setText(phone);

                } else {
                    Intent intent = new Intent(getActivity(), LandingActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Here should be an error message
            }
        });

        this.userFirstName = this.button_first_name_answer.getText().toString();
        this.userLastName = this.button_last_name_answer.getText().toString();
        this.userEmail = this.button_email_answer.getText().toString();
        this.userPassword = preferences.getString("user_password", null);
        this.userPhoneNumber = this.button_phone_number_answer.getText().toString();
        this.userRating = preferences.getString("user_rating", null);

        return view;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profile);

        this.logo = findViewById(R.id.header);

        this.cardView_firstName = findViewById(R.id.cardView_firstName);
        this.cardView_lastName = findViewById(R.id.cardView_lastName);
        this.cardView_email = findViewById(R.id.cardView_email);
        this.cardView_password = findViewById(R.id.cardView_password);
        this.cardView_phoneNumber = findViewById(R.id.cardView_phoneNumber);
        this.cardView_addAircraft = findViewById(R.id.cardView_addAircraft);
        this.cardView_confirm = findViewById(R.id.cardView_confirm);
        this.cardView_return = findViewById(R.id.cardView_return);

        this.button_first_name_title = findViewById(R.id.firstName_title);
        this.button_first_name_answer = findViewById(R.id.firstName_answer);
        this.button_last_name_title = findViewById(R.id.lastName_title);
        this.button_last_name_answer = findViewById(R.id.lastName_answer);
        this.button_email_title = findViewById(R.id.email_title);
        this.button_email_answer = findViewById(R.id.email_answer);
        this.button_password_title = findViewById(R.id.password_title);
        this.button_password_answer = findViewById(R.id.password_answer);
        this.button_phone_number_title = findViewById(R.id.phoneNumber_title);
        this.button_phone_number_answer = findViewById(R.id.phoneNumber_answer);
        this.button_add_aircraft_title = findViewById(R.id.addAircraft_title);
        this.button_confirm = findViewById(R.id.ModifyProfile_ConfirmButton);
        this.button_return = findViewById(R.id.ModifyProfile_ReturnButton);


        View.OnClickListener firstName = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Modification prénom";
                String instruction = "Entrez votre prénom";
                ModifyProfile_dialogFragment dialogFragment = ModifyProfile_dialogFragment.newInstance(title, instruction, ModifyProfile_dialogFragment.InputFieldType.FIRST_NAME);
                dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        };

        View.OnClickListener lastName = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Modification nom";
                String instruction = "Entrez votre nom";
                ModifyProfile_dialogFragment dialogFragment = ModifyProfile_dialogFragment.newInstance(title, instruction, ModifyProfile_dialogFragment.InputFieldType.LAST_NAME);
                dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        };

        View.OnClickListener email = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Modification email";
                String instruction = "Entrez votre email";
                ModifyProfile_dialogFragment dialogFragment = ModifyProfile_dialogFragment.newInstance(title, instruction, ModifyProfile_dialogFragment.InputFieldType.EMAIL);
                dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        };

        View.OnClickListener password = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Modification mot de passe";
                String instruction = "Entrez votre mot de passe";
                ModifyProfile_dialogFragment dialogFragment = ModifyProfile_dialogFragment.newInstance(title, instruction, ModifyProfile_dialogFragment.InputFieldType.PASSWORD);
                dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        };

        View.OnClickListener phoneNumber = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Modification numéro de téléphone";
                String instruction = "Entrez votre numéro de téléphone";
                ModifyProfile_dialogFragment dialogFragment = ModifyProfile_dialogFragment.newInstance(title, instruction, ModifyProfile_dialogFragment.InputFieldType.PHONE_NUMBER);
                dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        };

        View.OnClickListener addAircraft = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        };

        //TODO : delete old account in firebase
        View.OnClickListener confirm = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(userFirstName, userLastName, userEmail, userPassword, userPhoneNumber, userRating);

                DatabaseReference userRef = DataBase.USERS_REFERENCE.child(user.getId());

                // Check if the user already exists in the firebase database
                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // User already exists
                            Toast.makeText(RegisterActivity.this, "⚠️ Cet email est déjà utilisé !", Toast.LENGTH_SHORT).show();
                        } else {
                            // User does not exist so create it
                            DataBase.USERS_REFERENCE.child(user.getId()).setValue(user).addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    // Store the user id in the cache of the app
                                    SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("user_id", user.getId());
                                    editor.apply();

                                    // Go to the home page and display a confirmation message
                                    Toast.makeText(RegisterActivity.this, "✅ Votre compte a bien été créé", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, SwitcherActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "⚠️ Erreur lors de la création du compte, veuillez réessayer", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(RegisterActivity.this, "⚠️ Erreur lors de la création du compte, veuillez réessayer", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        };

        View.OnClickListener returnButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };


        this.cardView_firstName.setOnClickListener(firstName);
        this.button_first_name_answer.setOnClickListener(firstName);
        this.button_first_name_title.setOnClickListener(firstName);

        this.cardView_lastName.setOnClickListener(lastName);
        this.button_last_name_answer.setOnClickListener(lastName);
        this.button_last_name_title.setOnClickListener(lastName);

        this.cardView_email.setOnClickListener(email);
        this.button_email_answer.setOnClickListener(email);
        this.button_email_title.setOnClickListener(email);

        this.cardView_password.setOnClickListener(password);
        this.button_password_answer.setOnClickListener(password);
        this.button_password_title.setOnClickListener(password);

        this.cardView_phoneNumber.setOnClickListener(phoneNumber);
        this.button_phone_number_answer.setOnClickListener(phoneNumber);
        this.button_phone_number_title.setOnClickListener(phoneNumber);

        this.cardView_addAircraft.setOnClickListener(addAircraft);
        this.button_add_aircraft_title.setOnClickListener(addAircraft);

        this.cardView_confirm.setOnClickListener(confirm);
        this.button_confirm.setOnClickListener(confirm);

        this.cardView_return.setOnClickListener(returnButton);
        this.button_return.setOnClickListener(returnButton);
    }

    private String formateDisplayPassword(String password) {
        int lengthPassword = password.length();
        String hiddenPassword = "";
        for (int i = 0; i < lengthPassword; i++) {
            hiddenPassword += "*";
        }
        return hiddenPassword;
    }

    @Override
    public void onTextEntered(String inputText, ModifyProfile_dialogFragment.InputFieldType fieldType) {
        switch (fieldType) {
            case FIRST_NAME:
                this.button_first_name_answer.setText(inputText);
                this.userLastName = inputText;
                break;
            case LAST_NAME:
                this.button_last_name_answer.setText(inputText);
                this.userLastName = inputText;
                break;
            case EMAIL:
                this.button_email_answer.setText(inputText);
                this.userEmail = inputText;
                break;
            case PASSWORD:
                this.button_password_answer.setText(this.formateDisplayPassword(inputText));
                this.userPassword = inputText;
                break;
            case PHONE_NUMBER:
                this.button_phone_number_answer.setText(inputText);
                this.userPhoneNumber = inputText;
                break;
        }
    }


}
