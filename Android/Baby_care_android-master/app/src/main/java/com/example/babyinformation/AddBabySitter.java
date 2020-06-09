package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddBabySitter extends AppCompatActivity {

    FloatingActionButton floatingActionButton3;
    RecyclerView recyclerView3;
    String s1[], s2[], s3[];
    int images[] = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baby_sitter);

        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpBabySitter popUp = new PopUpBabySitter();
                popUp.show(getSupportFragmentManager(), "Example bottom sheet");
            }
        });
        recyclerView3 = findViewById(R.id.recyclerView3);

        s1 = getResources().getStringArray(R.array.BabySitter);
        s2 = getResources().getStringArray(R.array.DoctorName);
        s3 = getResources().getStringArray(R.array.Salary);

        MyAdapter3 myAdapter = new MyAdapter3(this, s1, s2, s3, images);
        recyclerView3.setAdapter(myAdapter);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
    }

    public void CancelRequest(View view) {
        Intent intent = new Intent(this, CancelRequest.class);
        startActivity(intent);
    }
}