package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.babyinformation.Remainder.ReminderMain;

public class parent_profile extends AppCompatActivity {

    private Button btn_baby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);

        btn_baby = (Button) findViewById(R.id.baby);
        btn_baby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent baby = new Intent(parent_profile.this, Baby_profile.class);
                startActivity(baby);
            }
        });

    }

    public void Doctors(View view) {
        Intent intent = new Intent(parent_profile.this, AddDoctor.class);
        startActivity(intent);
    }

    public void tips(View view) {


            Intent intent = new Intent(parent_profile.this, ReminderMain.class);
            startActivity(intent);

    }

    public void Diet(View view) {
        Intent intent = new Intent(parent_profile.this, Food.class);
        startActivity(intent);
    }






    public void settingbaby(View view) {
        Intent settingbabies = new Intent(parent_profile.this, setting.class);
        startActivity(settingbabies);
    }
}
