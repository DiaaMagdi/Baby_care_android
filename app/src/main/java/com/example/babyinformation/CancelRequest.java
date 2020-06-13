package com.example.babyinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CancelRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_request);
    }
    public void RequestCanceeled(View view) {
        deleterequestpopup bottomdelete = new deleterequestpopup();
        bottomdelete.show(getSupportFragmentManager(), "Example bottom sheet");
    }
}