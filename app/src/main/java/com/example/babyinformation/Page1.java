package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Page1 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

    }

    public void Location(View view) {
        Intent intent = new Intent(this, Page2.class);
        startActivity(intent);
    }

    public void Speciality(View view) {
        Intent intent = new Intent(this, Page3.class);
        startActivity(intent);
    }
}

