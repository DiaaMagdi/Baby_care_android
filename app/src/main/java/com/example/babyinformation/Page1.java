package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.babyinformation.fragments.PageFragment2;
import com.example.babyinformation.fragments.PageFragment3;

public class Page1 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

    }

//    public void Location(View view) {
//        Intent intent = new Intent(Page1.this, PageFragment2.class);
//        startActivity(intent);
//    }
//
//    public void Speciality(View view) {
//        Intent intent = new Intent(Page1.this, PageFragment3.class);
//        startActivity(intent);
//    }
}

