package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void edit(View view) {
        Intent editbaby = new Intent(setting.this, editbaby.class);
        startActivity(editbaby);
    }

    public void add(View view) {
        Intent addbaby = new Intent(setting.this, addbaby.class);
        startActivity(addbaby);
    }
}
