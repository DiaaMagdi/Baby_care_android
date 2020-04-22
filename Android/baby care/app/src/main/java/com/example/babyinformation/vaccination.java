package com.example.babyinformation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class vaccination extends AppCompatActivity {

    RecyclerView recyclerView2;
    String s1[],s2[],s3[],s4[];
    int images[] = {R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        recyclerView2 = findViewById(R.id.recyclerView2);

        s1 = getResources().getStringArray(R.array.NameOfVaccination);
        s2 = getResources().getStringArray(R.array.TypeOfVaccination);
        s3 = getResources().getStringArray(R.array.DoctorName);
        s4 = getResources().getStringArray(R.array.Date);

        MyAdapter2 myAdapter2 = new MyAdapter2(this, s1, s2, s3,s4, images);
        recyclerView2.setAdapter(myAdapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

    }
}

