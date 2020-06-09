package com.example.babyinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class babysitterinfo extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;
    ImageView logo;
    private Button nextbut;
    private Button btn_1, btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babysitterinfo);

        logo = (ImageView) findViewById(R.id.logo);

        nextbut = (Button) findViewById(R.id.nextbut);
        nextbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPhoto();
            }
        });

        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadID();
            }
        });
    }
    public void openActivity1() {
        Intent intent = new Intent(this, sitter_profile.class);
        startActivity(intent);
    }

    public void uploadPhoto() {
        Toast.makeText(this, "pick an image", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "pick an image"), GALLERY_REQUEST_CODE);
    }

    public void uploadID() {
        Toast.makeText(this, "pick an image", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "pick an image"), GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//         if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//        Uri imageDate = data.getData();
//        logo.setImageURI(imageDate);
//         }
    }
}

