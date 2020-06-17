package com.example.babyinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class order extends AppCompatActivity {

    private Button buttonreject,buttonraccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        buttonreject = (Button) findViewById(R.id.buttonreject);
        buttonraccept = (Button) findViewById(R.id.buttonraccept);

        buttonreject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openActivity1();
                }
            }
        );

        buttonraccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        }
        );
    }
    public void openActivity1() {
        Intent intent = new Intent(this, orderlist.class);
        Toast.makeText(getApplicationContext() , "Your Request is rejected ..",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
    public void openActivity2() {
        Intent intent = new Intent(this, orderlist.class);
        Toast.makeText(getApplicationContext() , "Your Request is accepted ..",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
