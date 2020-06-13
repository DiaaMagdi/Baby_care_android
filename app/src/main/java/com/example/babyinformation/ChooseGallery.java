package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChooseGallery extends AppCompatActivity
    implements MyAdapter1.ListItemClickListener {

    RecyclerView recyclerView1;
    String s1[];
    int images[] = {R.drawable.logo,R.drawable.logo,R.drawable.logo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gallery);

        recyclerView1 = findViewById(R.id.recyclerView1);
        s1 = getResources().getStringArray(R.array.BabyName);

        MyAdapter1 myAdapter1 = new MyAdapter1( s1, images ,this);
        recyclerView1.setAdapter(myAdapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(this, funny.class);
        intent.putExtra("data1", s1[position]);
        intent.putExtra("myImage", images[position]);
        startActivity(intent);
    }
}

