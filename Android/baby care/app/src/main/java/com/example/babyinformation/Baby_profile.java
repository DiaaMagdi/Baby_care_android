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

    public void tips (View view) {

    }

    public void settingbaby(View view) {
        Intent settingbabies = new Intent(Baby_profile.this, setting.class);
        startActivity(settingbabies);
    }

    public void FunnyPhotos (View view) {
        Intent intent = new Intent(Baby_profile.this, ChooseGallery.class);
        startActivity(intent);
    }

    public void vaccination (View view) {
        Intent intent = new Intent(Baby_profile.this, vaccination.class);
        startActivity(intent);
    }
}
