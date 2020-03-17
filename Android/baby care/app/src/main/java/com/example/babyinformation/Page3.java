package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Page3 extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();

            }
        });

    }

    public void openActivity1() {
        Intent intent = new Intent(this, Page1.class);
        startActivity(intent);
    }

    public void openActivity2() {
        Intent intent = new Intent(this, Page2.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, Page3.class);
        startActivity(intent);
    }
}