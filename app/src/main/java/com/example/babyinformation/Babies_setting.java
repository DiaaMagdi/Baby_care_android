package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Babies_setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babies_setting);
    }

    public void editbaby(View view) {
        Intent baby = new Intent(Babies_setting.this, editbaby.class);
        startActivity(baby);
    }

    public void addbaby(View view) {
        Intent baby = new Intent(Babies_setting.this, addbaby.class);
        startActivity(baby);
    }
}
