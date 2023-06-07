package com.example.blablaplane.activity;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.blablaplane.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class AddContactActivity extends AppCompatActivity {


    private static final String TAG = "CONTACT_TAG";
    private static final int WRITE_CONTACT_PERMISSION_CODE = 100;
    private String[] contactPermissions;
    private Uri image_uri;

    private Bitmap photoPilote;

    private int tripId;


    EditText firstName_ET;
    EditText lastName_ET;
    EditText Email_ET;
    EditText PhoneMobile_ET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Intent intent = getIntent();
        this.photoPilote = intent.getParcelableExtra("photo");
        this.tripId = intent.getIntExtra("tripId",0);

        String parcellableLastName = intent.getStringExtra("lastName");
        String parcellableFirstName = intent.getStringExtra("firstName");



        firstName_ET = findViewById(R.id.ET_firstName);
        lastName_ET = findViewById(R.id.ET_lastName);
        Email_ET = findViewById(R.id.ET_email);
        PhoneMobile_ET = findViewById(R.id.ET_phoneMobile);


        if(parcellableFirstName!=null){firstName_ET.setText(parcellableFirstName);}
        if(parcellableLastName!=null){lastName_ET.setText(parcellableLastName);}

        if(this.photoPilote!=null){
            //je prend l'image view dans la page
            ImageView pilotePhoto = findViewById(R.id.thumbnailIv);
            pilotePhoto.setImageBitmap(this.photoPilote);
        }

        contactPermissions = new String[]{Manifest.permission.WRITE_CONTACTS};


        //save Contact

        findViewById(R.id.saveContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWriteContactPermissionEnabled()){
                    //permission already enabled -> save contact
                    saveContact();
                }
                else{
                    //permission not enabled, request
                    requestWriteContactPermission();
                }
            }
        });

        findViewById(R.id.backToInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private byte[] convertImgToBytes() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photoPilote.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        return baos.toByteArray();
    }

    private void saveContact() {

        String firstName = firstName_ET.getText().toString().trim();
        String lastName = lastName_ET.getText().toString().trim();
        String Email = Email_ET.getText().toString().trim();
        String PhoneMobile = PhoneMobile_ET.getText().toString().trim();

        ArrayList<ContentProviderOperation> cpo = new ArrayList<>();

        //contact id
        int rawContactId = cpo.size();

        cpo.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());


        //Ajout nom et prenom
        cpo.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId)
                .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, firstName)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, lastName)
                .build());

        //Ajout Numero de telephone
        cpo.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId)
                .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, PhoneMobile)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());

        //Ajout email
        cpo.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId)
                .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA, Email)
                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                .build());

        //CONVERSION IMAGE EN BYTES POUR SAUVEGARDER DANS L'IMAGE

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photoPilote.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] pilotePhotoInBytes = baos.toByteArray();

        if(pilotePhotoInBytes!=null){
            //contact avec image
            //Ajout email
            cpo.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId)
                    .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Photo.PHOTO, pilotePhotoInBytes)
                    .withValue(ContactsContract.CommonDataKinds.Email.DATA, Email)
                    .build());
        }
        //contact sans images
        else{
            cpo.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId)
                    .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Email.DATA, Email)
                    .build());
        }

        //save contact
        try{
            ContentProviderResult[] contactFinal = getContentResolver().applyBatch(ContactsContract.AUTHORITY, cpo);


            Toast.makeText(this, "Contact enregistré ✅",Toast.LENGTH_SHORT).show();

            //retour sur la page precedente
            Intent intent = new Intent(AddContactActivity.this, TripInfoActivity.class);
            intent.putExtra("id", tripId);
            startActivity(intent);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private boolean isWriteContactPermissionEnabled(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestWriteContactPermission(){
        ActivityCompat.requestPermissions(this,contactPermissions, WRITE_CONTACT_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //handle permission results

        if(grantResults.length > 0){
            if(requestCode == WRITE_CONTACT_PERMISSION_CODE){
                boolean haveWriteContactPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if(haveWriteContactPermission){
                    //permission granted -> save contact
                    saveContact();
                }
                else{
                    //permission denied
                    Toast.makeText(this, "Permission denied to save contact",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private byte[] imageUriToBytes() {
        Bitmap bitmap;
        ByteArrayOutputStream baos = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image_uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            return baos.toByteArray();
        }
        catch (Exception e){
            Log.d(TAG, "imageUriToBytes: "+e.getMessage());
            return null;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);


    }

}