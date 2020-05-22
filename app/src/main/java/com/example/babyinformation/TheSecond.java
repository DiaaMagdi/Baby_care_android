package com.example.babyinformation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TheSecond extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    ImageView img, arrow_back, phone, fav_button,mail;
    TextView number, doc_mail;

    int counter = 0;
    int[] favImages = {R.drawable.fav2, R.drawable.fav};

    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_second);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney;
        sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        doc_mail = (TextView) findViewById(R.id.doc_mail);
        mail = (ImageView) findViewById(R.id.mail);
        fav_button = findViewById(R.id.fav);
        arrow_back = (ImageView) findViewById(R.id.arrow_back);
        img = (ImageView) findViewById(R.id.message_mopile);
        phone = (ImageView) findViewById(R.id.phone);
        number = (TextView) findViewById(R.id.number);

        //Start SMS code
        if (ContextCompat.checkSelfPermission(TheSecond.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(TheSecond.this,
                    Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(TheSecond.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);

            } else {
                ActivityCompat.requestPermissions(TheSecond.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = number.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(num, null, "Hello", null, null);
                    Toast.makeText(TheSecond.this, "Sent", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(TheSecond.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //End SMS Code-------------------------
        //start phone call----------------------------
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        //End phone call----------------------------

        //start back Intent
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBackActivity();
            }
        });
        //End----------------------------------------------------------

        //start fav code-------------------------------
        fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeFavIcone();
                Toast.makeText(TheSecond.this, "Add to favorite", Toast.LENGTH_SHORT).show();
            }
        });
        //End fav code-------------------------------

        //start send email---------------
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
        //End send email-----------------
    }

    public void sendMail() {
        String mailReceiver = doc_mail.getText().toString();
        String[] recipient = mailReceiver.split(",");
        String subject = "";
        String messege = "";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, messege);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "choose an email client"));
    }

    public void makeFavIcone() {
        if (counter < 2) {
            fav_button.setBackgroundResource(favImages[counter]);
            counter++;
        }else {
            counter=0;
            fav_button.setBackgroundResource(favImages[counter]);
            counter++;
        }
    }

    public void makePhoneCall() {
        String num = number.getText().toString();
        if (num.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(TheSecond.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(TheSecond.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + num;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(TheSecond.this, "there is no number", Toast.LENGTH_SHORT).show();
        }
    }

    public void openBackActivity() {
        Intent intent = new Intent(this, AddDoctor.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(TheSecond.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "permession granded", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "permession cancled", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
