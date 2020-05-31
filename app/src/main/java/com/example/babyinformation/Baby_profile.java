package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Baby_profile extends AppCompatActivity {

    private Button btn_parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_profile);

        btn_parent = (Button) findViewById(R.id.parent);
        btn_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parent = new Intent(Baby_profile.this, parent_profile.class);
                startActivity(parent);
            }
        });
    }

    public void vaccination(View view) {
        Intent vaccination_intent = new Intent(Baby_profile.this, Activity_Vaccination.class);
        //TODO: send the mother's id/token in the next intent for viewing the mother's own children's vaccination
        vaccination_intent.putExtra("mother_id","1");
        startActivity(vaccination_intent);
    }

    public void funny(View view) {
        Intent funny = new Intent(Baby_profile.this, ChooseGallery.class);
        startActivity(funny);
    }


    public void cry(View view) {
        Intent cry = new Intent(Baby_profile.this, Babycry.class);
        startActivity(cry);
    }

    public void predict(View view) {
        Intent intent = new Intent(Baby_profile.this, chat.class);
        startActivity(intent);
    }

    public void settingbaby(View view) {
        Intent intent = new Intent(Baby_profile.this, Babies_setting.class);
        startActivity(intent);
    }
}
