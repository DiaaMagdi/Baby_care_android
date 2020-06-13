package com.example.babyinformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class orderlist extends AppCompatActivity {

    RecyclerView recyclerView4;
    String s1[], s2[], s3[] ;
    int images[] = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);

        recyclerView4 = findViewById(R.id.recyclerView4);

        s1 = getResources().getStringArray(R.array.numofRequest);
        s2 = getResources().getStringArray(R.array.DoctorName);
        s3 = getResources().getStringArray(R.array.MoreDescription);

        MyAdapter4 myAdapter4 = new MyAdapter4(this, s1, s2, s3, images);
        recyclerView4.setAdapter(myAdapter4);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this));

    }
}
