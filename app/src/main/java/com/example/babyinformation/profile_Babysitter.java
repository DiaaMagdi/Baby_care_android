package com.example.babyinformation;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class profile_Babysitter extends AppCompatActivity {

     private Button button1;
     RatingBar ratingBar;

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__babysitter);

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }

    public void openActivity3() {
        Intent intent = new Intent(this, detailsChildern.class);
        startActivity(intent);
    }

//    public void rating(View view) {
//        Toast.makeText(getApplicationContext() , "Rating is : ",Toast.LENGTH_SHORT).show();
//
//    }
}
