package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class sitter_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitter_profile);
    }

    public void reminder(View view) {
    }

    public void cry(View view) {
        Intent cry = new Intent(sitter_profile.this, Babycry.class);
        startActivity(cry);
    }

    public void predict(View view) {
        Intent intent = new Intent(sitter_profile.this, chat.class);
        startActivity(intent);
    }

    public void Diet(View view) {
        Intent intent = new Intent(sitter_profile.this, Food.class);
        startActivity(intent);
    }

    public void order(View view) {
        Intent intent = new Intent(sitter_profile.this, orderlist.class);
        startActivity(intent);
    }

    public void setting(View view) {
    }
}
