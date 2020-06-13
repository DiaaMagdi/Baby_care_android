package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    public void prompt(View view) {
    }

    public void Logout(View view) {
        Intent logout = new Intent(setting.this, Welcome.class);
        startActivity(logout);
        finish();
        Toast.makeText(setting.this, "Succesfully logout",Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        deletepopup bottomdelete = new deletepopup();
        bottomdelete.show(getSupportFragmentManager(), "Example bottom sheet");
    }
}
