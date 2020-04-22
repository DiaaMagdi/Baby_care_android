package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddDoctor extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    String s1[], s2[], s3[];
    int images[] = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDoctor.this,PopUp.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.Doctor);
        s2 = getResources().getStringArray(R.array.DoctorName);
        s3 = getResources().getStringArray(R.array.MoreDescription);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, s3, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
